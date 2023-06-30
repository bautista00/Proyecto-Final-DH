import { Link, useParams, useNavigate } from "react-router-dom";
import { useContextGlobal } from "../Components/utils/GlobalContext";
import { useEffect, useState } from "react";
import Gallery from "../Components/Gallery";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import CommentInput from "../Components/CommentInput";
import { Rate } from "antd";
import PoliticCards from "../Components/PoliticCards";
import { axiosInstance } from "../config";
import ShareProductButton from "../Components/ShareProductButton";

import { scheduler } from "dhtmlx-scheduler";
import "dhtmlx-scheduler";
import "dhtmlx-scheduler/codebase/dhtmlxscheduler.css";
import "dhtmlx-scheduler/codebase/dhtmlxscheduler";

function format(date, format) {
  const year = date.getFullYear();
  const month = padZero(date.getMonth() + 1);
  const day = padZero(date.getDate());
  const hours = padZero(date.getHours());
  const minutes = padZero(date.getMinutes());

  let formattedDate = format.replace(/yyyy/g, year);
  formattedDate = formattedDate.replace(/MM/g, month);
  formattedDate = formattedDate.replace(/dd/g, day);
  formattedDate = formattedDate.replace(/HH/g, hours);
  formattedDate = formattedDate.replace(/mm/g, minutes);

  return formattedDate;
}

// Helper function to pad zeros for single-digit numbers
function padZero(number) {
  return (number < 10 ? "0" : "") + number;
}

const Detail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [detail, setDetail] = useState({});
  const [rateValue, setRateValue]= useState(0)
  const [servicios, setServicios] = useState([]);
  const [serviciosLocal, setServiciosLocal] = useState([
    { name: "Vestuarios", icon: "fa-solid fa-person-booth" },
    { name: "Duchas", icon: "fa-solid fa-shower" },
    { name: "Confiteria", icon: "fa-solid fa-burger" },
    { name: "Bar", icon: "fa-solid fa-martini-glass" },
    { name: "Wifi", icon: "fa-solid fa-wifi" },
    { name: "Estacionamiento", icon: "fa-solid fa-square-parking" },
  ]);
  const [serviciosFinal, setServiciosFinal] = useState([]);
  const [programmedEvents, setProgrammedEvents] = useState([]);

  const [mapLoaded, setMapLoaded] = useState(false);

  // const [state, setState] = useState([
  //   {
  //     startDate: new Date(),
  //     endDate: addDays(new Date(), 7),
  //     key: "selection",
  //   },
  // ]);
  
  const [selectedDate, setSelectedDate] = useState({ date: null, hours: 0 });

  const [show, setShow] = useState({
    message: "Debe iniciar sesión como usuario para poder reservar",
    state: false,
  });

  const fetchData = async (id) => {
    const result = await axiosInstance.get(`/detailcancha/${id}`);

    setDetail(result.data);
    setServicios(result.data?.canchaDTO?.serviciosList);

    const programmedEventsInDetail =
      result.data?.turnoList?.map(({ id, fecha, horas }) => ({
        id,
        text: "Reservado",
        start_date: fecha,
        end_date: format(
          new Date(
            new Date(fecha).setHours(new Date(fecha).getHours() + horas)
          ),
          "yyyy-MM-dd HH:mm"
        ),
      })) || [];

    setProgrammedEvents(programmedEventsInDetail);
    setMapLoaded(true)
  };

  useEffect(() => {
    if (detail?.canchaDTO?.promedio !== undefined) {
      setRateValue(detail?.canchaDTO?.promedio);
      console.log(rateValue);
    }
  }, [detail?.canchaDTO?.promedio]);

  useEffect(() => {
    fetchData(id);
    window.scrollTo(0, 0);
    setShow({ message: show.message, state: false });
  }, []);

  useEffect(() => {
    scheduler.config.header = [
      "day",
      "week",
      "month",
      "date",
      "prev",
      "today",
      "next",
    ];

    scheduler.parse([...programmedEvents], "json");
    scheduler.init("scheduleContainer", new Date(), "day");
    scheduler.config.first_hour = 8;
    scheduler.config.last_hour = 18;
    scheduler.config.time_step = 60;
    //scheduler.config.drag_move = true;

    scheduler.templates.hour_scale = function (date) {
      return scheduler.date.date_to_str("%h:%i %A")(date);
    };

    scheduler.templates.event_bar_text = function (start, end, event) {
      return (
        scheduler.date.date_to_str("%h:%i %A")(start) +
        " - " +
        scheduler.date.date_to_str("%h:%i %A")(end)
      );
    };

    scheduler.attachEvent("onEventAdded", (id, data) => {
      const event = scheduler.getEvent(id);
      const date = format(event.start_date, "yyyy-MM-dd HH:mm");
      const hours = event.end_date.getHours() - event.start_date.getHours();

      setSelectedDate({ date, hours });
    });
  }, [programmedEvents, selectedDate]);

  if (!detail) return null;

  const validarUser = () => {
    setShow({ message: show.message, state: true });
  };

  return (
    <div className="detailGeneralDiv">
      <div className="containerArrowBack">
        <a href="../">
          <i className="fa-solid fa-arrow-left fa-2xl"></i>
        </a>
      </div>
      <div className="detailMidDiv">
        <h2>{detail?.canchaDTO?.nombre}</h2>
        <h4> Valoraciones ({detail?.canchaDTO?.valoracionList.length})</h4>
        <div className="locationDivIcono">
          <div className="locationDiv">
          <img src="/images/location.png" alt="" />
          <p>
            {detail?.canchaDTO?.domicilio?.provincia},{" "}
            {detail?.canchaDTO?.domicilio?.calle}{" "}
            {detail?.canchaDTO?.domicilio?.numero}
          </p>

          </div>
        
          <ShareProductButton descripcion={detail?.canchaDTO?.descripcion} imagen={detail?.canchaDTO?.images?.url[0]} id={detail?.canchaDTO?.id} />
        </div>
        <Rate
          disabled
          value={rateValue}
          style={{ color: "#fadb14", fontSize: 20, padding: 0 }}
        />
      </div>
      <div className="blockImage">
        <div className="leftBlock">
          <img src={detail?.canchaDTO?.images?.url[0]} alt="" />
        </div>
        <div className="rightBlock">
          <div className="semiBlock">
            <img src={detail?.canchaDTO?.images?.url[1]} alt="" />
            <img src={detail?.canchaDTO?.images?.url[2]} alt="" />
          </div>
          <div className="semiBlock">
            <img src={detail?.canchaDTO?.images?.url[3]} alt="" />
            <img src={detail?.canchaDTO?.images?.url[0]} alt="" />
            <Gallery images={detail?.canchaDTO?.images?.url} />
          </div>
        </div>
      </div>
      
      <div className="detailBottomDiv">
        <div className="divLocation">
          <h2>Dónde puedes encontrarnos</h2>
          <iframe
            src={`https://www.google.com/maps/embed/v1/place?key=AIzaSyDIXxKR3FboVvl_VMi1LhVlUjWZlpmrqP4&q=${encodeURIComponent(detail?.canchaDTO?.domicilio?.provincia + ", " + detail?.canchaDTO?.domicilio?.calle + " " + detail?.canchaDTO?.domicilio?.numero)}`}
          ></iframe>
        </div>
        <div className="divInfo">
          <h2 id="valorationSection">Valoraciones</h2>
          <div className="commentSection">
            <div id="commentsBox">
              <CommentInput valoraciones={detail?.canchaDTO?.valoracionList} canchaId = {detail?.canchaDTO?.id}/>
            </div>
          </div>
        </div>
        <div className="divInfo">
          <h2>Qué tienes que saber</h2>
          <div className="detailPolicies">
            <PoliticCards
              houseRules={detail?.canchaDTO?.criteriosList[0]?.descripcion}
              safety={detail?.canchaDTO?.criteriosList[1]?.descripcion}
              cancelation={detail?.canchaDTO?.criteriosList[2]?.descripcion}
            />
          </div>
        </div>
        <div className="divInfo">
          <h2>Qué servicios ofrecemos</h2>
          <div className="allServices">
            {serviciosLocal.map((service) => (
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
            <div
              id="scheduleContainer"
              style={{ width: "80%", height: "350px", marginLeft: 100 }}
            ></div>

            <div className="buttonReservation">
              <h3>Reserva tu cancha a un click</h3>

              {/* Revisar autorización God - agregar or para que también permita hacer reservas */}
              {localStorage.getItem("auth") == "true" &&
              localStorage.getItem("role") == "USER" ? (
                <button
                  onClick={() => {
                    navigate("/Booking", {
                      state: {
                        selectedDate,
                        detail: { ...detail, id: parseInt(id) },
                      },
                    });
                  }}
                >
                  Reservar
                </button>
              ) : localStorage.getItem("auth") == "true" &&
                localStorage.getItem("role") == "ADMIN" ? (
                <button onClick={validarUser}>Reservar</button>
              ) : (
                <Link to="/Login?message=Debes%20iniciar%20sesión%20o%20registrarte">
                  {" "}
                  <button>Reservar</button>
                </Link>
              )}
              {show.state && <p>{show.message}</p>}
            </div>
          </div>
        </div>

        {/* <div className="dateReservation">
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

              {/* Revisar autorización God - agregar or para que también permita hacer reservas 
              {localStorage.getItem("auth") == "true" &&
              localStorage.getItem("role") == "USER" ? (
                <Link to={`/Booking`} state={{ detail: detail }}>
                  {" "}
                  <button>Reservar</button>
                </Link>
              ) : localStorage.getItem("auth") == "true" &&
                localStorage.getItem("role") == "ADMIN" ? (
                <button onClick={validarUser}>Reservar</button>
              ) : (
                <Link to="/Login?message=Debes%20iniciar%20sesión%20o%20registrarte">
                  {" "}
                  <button>Reservar</button>
                </Link>
              )}
              {show.state && <p>{show.message}</p>}
            </div>
          </div>
        </div> */}

        <div className="divInfo">
          <h2>Descripción</h2>
          <div className="detailDescription">
            <p>{detail?.canchaDTO?.descripcion}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Detail;
