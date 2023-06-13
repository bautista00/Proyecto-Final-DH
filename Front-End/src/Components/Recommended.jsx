import React from "react";
import RecommendedCard from "../Components/RecommendedCard";
import { useContextGlobal } from "../Components/utils/GlobalContext";

const Recommended = () => {
    const {data} = useContextGlobal()
  
    const getRandomCards = (count) => {
      const shuffledData = [...data].sort(() => Math.random() - 0.5);
      return shuffledData.slice(0, count);
    };
  
    const renderCards = () => {
      const randomCards = getRandomCards(8);
      return randomCards.map((card, index) => (
        <RecommendedCard key={index} id={card.id} name={card.name} location={card.location} image={card.image}/>
      ));
    };
  return (
    <div className="card-title-recommended">

        <h2>Recomendados</h2>
        <div className="card-container-recommended">
          {renderCards()}
        </div>
    </div>
  )
}

export default Recommended