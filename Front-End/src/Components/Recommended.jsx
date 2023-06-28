import React, { useEffect, useState } from "react";
import RecommendedCard from "../Components/RecommendedCard";
import { useContextGlobal } from "../Components/utils/GlobalContext";
import { axiosInstance } from "../config";

const Recommended = () => {
  // const { data } = useContextGlobal();
  const [canchas, setCanchas] = useState([]);

  useEffect(() => {
    axiosInstance.get("/listallcanchas").then((response) => {
      setCanchas(response.data);
    });
  }, []);

  const getRandomCards = (count) => {
    console.log(canchas);
    const shuffledData = [...canchas].sort(() => Math.random() - 0.5);
    return shuffledData.slice(0, count);
  };

  const renderCards = () => {
    const randomCards = getRandomCards(1);
    return randomCards.map((card) => (
      <RecommendedCard
        key={card.id}
        id={card.id}
        name={card.nombre}
        location={card.domicilio.barrio.nombre}
        image={card.images.url[0]}
      />
    ));
  };
  return (
    <div className="card-title-recommended">
      <h2>Recomendados</h2>
      <div className="card-container-recommended">{renderCards()}</div>
    </div>
  );
};

export default Recommended;
