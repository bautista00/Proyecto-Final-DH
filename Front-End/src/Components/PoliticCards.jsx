import React from "react";

const PoliticCards = ({ houseRules, safety, cancelation }) => {
  return (
    <section className="politicCards">
      <div>
        <h3>Reglas de la casa</h3>
        <p>{houseRules}</p>
      </div>
      <div>
        <h3>Salud y seguridad</h3>
        <p>{safety}</p>
      </div>
      <div>
        <h3>Política de cancelación</h3>
        <p>{cancelation}</p>
      </div>
    </section>
  );
};

export default PoliticCards;
