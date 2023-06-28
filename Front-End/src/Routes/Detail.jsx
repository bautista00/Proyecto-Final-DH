import { Link, useParams } from "react-router-dom";
import { useContextGlobal } from "../Components/utils/GlobalContext";
import { useEffect, useState } from "react";
import Gallery from "../Components/Gallery";
import { redirect} from "react-router-dom";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import StarRating from "../Components/StarRating";
import CommentCard from "../Components/CommentCard";
import CommentInput from "../Components/CommentInput";
import { Rate, message } from "antd";
import PoliticCards from "../Components/PoliticCards";
import { DateRangePicker } from "react-date-range";
import { addDays } from "date-fns";
import { axiosInstance } from "../config";
import ShareAppButton from "../Components/ShareAppButton";
import ShareProductButton from "../Components/ShareProductButton";

const Detail = () => {
  const images = [
    "/images/cancha8.jpg",
    "/images/cancha5.jpg",
    "/images/cancha6.jpg",
    "/images/cancha7.jpg",
  ];

  const [detail, setDetail] = useState({});
  const { id } = useParams();
  const { data } = useContextGlobal();
  
  const [state, setState] = useState([
    {
      startDate: new Date(),
      endDate: addDays(new Date(), 7),
      key: "selection",
    },
  ]);

  const [show, setShow] = useState({message: "Debe iniciar sesión como usuario para poder reservar", state: false})

  const fetchData = async (id) => {
    try {
      const result = await axiosInstance.get(`/detailcancha/${id}`);
      console.log(result.data);
      setDetail(result.data);
    } catch (error) {
      console.error("Error al obtener los detalles:", error);
    }
  }

  useEffect(() => {
    console.log(id);
    fetchData(id);
  }, [id]);

  useEffect(() => {
    window.scrollTo(0, 0);
    setShow({message:show.message, state:false})
  }, []);

  if (!detail) return null;
  console.log(localStorage.getItem("auth"))

  const validarUser = () =>{
    setShow({message:show.message,state:true})
  }

  return (
    <div className="detailGeneralDiv">
      <div className="containerArrowBack">
        <a href="../">
          <i className="fa-solid fa-arrow-left fa-2xl"></i>
        </a>
      </div>
      <div className="detailMidDiv">
        <h2>{detail?.nombre}</h2>
        <h4> Valoraciones (3)</h4>
        <div className="locationDiv">
          <img src="/images/location.png" alt="" />
          <p>{detail?.domicilio?.provincia}</p>
        </div>
        <Rate
          disabled
          defaultValue={4}
          style={{ color: "#fadb14", fontSize: 20, padding: 0 }}
        />
      </div>
      <div className="blockImage">
        <div className="leftBlock">
          <img src={detail?.imagesDTOSList?.[0].url} alt="" />
        </div>
        <div className="rightBlock">
          <div className="semiBlock">
            <img src="/images/cancha8.jpg" alt="" />
            <img src="/images/cancha5.jpg" alt="" />
          </div>
          <div className="semiBlock">
            <img src="/images/cancha6.jpg" alt="" />
            <img src="/images/cancha7.jpg" alt="" />
            <Gallery images={images} />
          </div>
        </div>
      </div>
      <ShareProductButton />
      <div className="detailBottomDiv">
        <div className="divLocation">
          <h2>Dónde puedes encontrarnos</h2>
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3284.052866542891!2d-58.37652822334277!3d-3
         4.60282465745262!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95bccacd02e46f1f%3A0x70f27b4c4725a03b!2sSan%20Mart%C3%AD
         n%20444%2C%20C1004%20AAJ%2C%20Buenos%20Aires!5e0!3m2!1ses!2sar!4v1684863236125!5m2!1ses!2sar"
          ></iframe>
        </div>
        <div className="divInfo">
          <h2 id="valorationSection">Valoraciones</h2>
          <div className="commentSection">
            <div id="commentsBox">
              <CommentInput />
            </div>
          </div>
        </div>
        <div className="divInfo">
          <h2>Qué tienes que saber</h2>
          <div className="detailPolicies">
            <PoliticCards
              houseRules={
                "Recomendamos llevar equipo e indumentaria necesaria para realizar la actividad. Te pedimos cumplir con el horario de llegada y de salida de tu reserva, y si se necesita utilizar por tiempo extra, consultar en la recepción del lugar."
              }
              safety={
                "Nuestro espacio está abastecido con profesionales médicos y accesibilidad a un servicio de emergencias y traslado ante cualquier cirscunstancia que lo requiera."
              }
              cancelation={
                "Si desea cancelar la reserva efectuada en el sitio, se recomienda cancelar con anticipación, siendo el tiempo límite 24hs antes de la reserva, de lo contrario se perderá lo abonado y deberá reprogramar la reserva."
              }
            />
          </div>
        </div>
        <div className="divInfo">
          <h2>Qué servicios ofrecemos</h2>
          <div className="allServices">
            {data[0]?.services.map((service) => (
              <div key={service.name} className="service-item">
                <FontAwesomeIcon icon={service.icon} />
                {service.name}
              </div>
            ))}
          </div>
        </div>

        <div className="dateReservation">
          <h2>Fechas disponibles</h2>
          <div className="dateReservationContent">
            <DateRangePicker
              staticRanges={[]}
              onChange={(item) => setState([item.selection])}
              showSelectionPreview={false}
              moveRangeOnFirstSelection={false}
              months={2}
              ranges={state}
              direction="horizontal"
            />

            <div className="buttonReservation">
              <h3>Reserva tu cancha a un click</h3>
              
              {/* Revisar autorización God - agregar or para que también permita hacer reservas */}
              {(localStorage.getItem("auth") == "true" && localStorage.getItem("role")== "USER") ?
                  <Link to={`/Booking`} state={{detail: detail}}> <button>Reservar</button></Link>:
                  (localStorage.getItem("auth") == "true" && localStorage.getItem("role")== "ADMIN")?
                  <button onClick={validarUser}>Reservar</button> :
                  <Link to="/Login?message=Debes%20iniciar%20sesión%20o%20registrarte"> <button>Reservar</button></Link>                 
              }
              {show.state && <p>{show.message}</p>}
            </div>
            
          </div>
        </div>

        <div className="divInfo">
          <h2>Descripción</h2>
          <div className="detailDescription">
            <p>{data[0]?.description}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Detail;
