import React from 'react';
import DeleteCard from './DeleteCard';
import { useContextGlobal } from './utils/GlobalContext';

const Delete = () => {
  const { data, setData } = useContextGlobal();

  const borrarCard = (id) => {
    setData(prevCard => prevCard.filter(card => card.id !== id));
  }

  const renderCards = () => {
    return data.map((card, index) => (
      <DeleteCard key={index} id={card.id} name={card.name} location={card.location} image={card.image} func={borrarCard} />
    ));
  };

  return (
    <div className="card-title-recommended">
      <h2>Eliminar canchas</h2>
      <div className="card-container-recommended">
        {renderCards()}
      </div>
    </div>
  );
}

export default Delete;
