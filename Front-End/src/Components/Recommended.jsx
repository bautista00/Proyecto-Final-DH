import React from "react";
import Card from "../Components/Card";
import { useContextGlobal } from "../Components/utils/GlobalContext";

const Recommended = () => {
    const {data} = useContextGlobal()
  
    const getRandomCards = (count) => {
      const shuffledData = [...data].sort(() => Math.random() - 0.5);
      return shuffledData.slice(0, count);
    };
  
    const renderCards = () => {
      const randomCards = getRandomCards(4);
      return randomCards.map((card, index) => (
        <Card key={index} id={card.id} name={card.name} location={card.location} image={card.image}
        />
      ));
    };
  return (
    <div className="card-title">
        <h2>Recomendados</h2>
        <div className="card-container">
          {renderCards()}
        </div>
    </div>
  )
}

export default Recommended