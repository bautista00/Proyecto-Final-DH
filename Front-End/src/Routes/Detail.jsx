import { useParams } from "react-router-dom";
import { useContextGlobal } from "../Components/utils/GlobalContext";
import { useEffect, useState } from "react";
import Gallery from "../Components/Gallery";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import StarRating from "../Components/StarRating";
import CommentCard from "../Components/CommentCard";
import CommentInput from "../Components/CommentInput";
import { Rate } from "antd";
import PoliticCards from "../Components/PoliticCards";

const Detail = () => {
  const images = [
    "/images/cancha8.jpg",
    "/images/cancha5.jpg",
    "/images/cancha6.jpg",
    "/images/cancha7.jpg",
  ];

  const { id } = useParams();
  const { data } = useContextGlobal();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <div className="detailGeneralDiv">
      <a href="../">
        <i className="fa-solid fa-arrow-left fa-2xl"></i>
      </a>
      <div className="detailMidDiv">
        <h2>{data[id - 1]?.name}</h2>
        <h4> Valoraciones (3)</h4>
        <div className="locationDiv">
          <img src="/images/location.png" alt="" />
          <p>{data[id - 1]?.location}</p>
        </div>
        <Rate disabled defaultValue={4} style={{ color: "#fadb14", fontSize: 20, padding: 0 }}/>
      </div>
      <div className="blockImage">
        <div className="leftBlock">
          <img src={data[id - 1]?.image} alt="" />
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
          <h2 id="valorationSection" >Valoraciones</h2>
          <div className="commentSection">
            <CommentCard/>
            <CommentCard/>
            <CommentCard/>
            <CommentInput/>
          </div>
        </div>
        <div className="divInfo">
          <h2>Qué tienes que saber</h2>
          <div className="detailPolicies">
            <PoliticCards houseRules={'Recomendamos llevar equipo e indumentaria necesaria para realizar la actividad. Te pedimos cumplir con el horario de llegada y de salida de tu reserva, y si se necesita utilizar por tiempo extra, consultar en la recepción del lugar.'} safety={'Nuestro espacio está abastecido con profesionales médicos y accesibilidad a un servicio de emergencias y traslado ante cualquier cirscunstancia que lo requiera.'} cancelation={'Si desea cancelar la reserva efectuada en el sitio, se recomienda cancelar con anticipación, siendo el tiempo límite 24hs antes de la reserva, de lo contrario se perderá lo abonado y deberá reprogramar la reserva.'} />
          </div>
        </div>
        <div className="divInfo">
          <h2>Qué servicios ofrecemos</h2>
          <div className="allServices">
            {data[id - 1]?.services.map((service) => (
              <div key={service.name} className="service-item">
                <FontAwesomeIcon icon={service.icon} />
                {service.name}
              </div>
            ))}
          </div>
        </div>
        <div className="divInfo">
          <h2>Descripción</h2>
          <div className="detailDescription">
            <p>{data[id - 1]?.description}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Detail;
