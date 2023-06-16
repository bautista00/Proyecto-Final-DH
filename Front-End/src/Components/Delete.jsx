import React, { useEffect, useState } from "react";
import DeleteCard from "./DeleteCard";
import { useContextGlobal } from "./utils/GlobalContext";
import { axiosInstance } from "../config";

const Delete = () => {
  // const { data, setData } = useContextGlobal();

  const [deleteCard, setDeleteCard] = useState([]);

  const fetchData = async () => {
    const result = await axiosInstance.get("/listxsportcanchas");
    setDeleteCard(result.data);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const borrarCard = async (id) => {
    try {
      const response = await axiosInstance.delete(`/admin/deletecancha/${id}`);
      console.log("Response:", response.data);
      fetchData();
    } catch (e) {
      console.log(e);
    }
  };

  const renderCards = () => {
    return deleteCard.map((card, index) => (
      <DeleteCard
        key={index}
        id={card.id}
        name={card.name}
        location={card.location}
        image={card.image}
        func={borrarCard}
      />
    ));
  };

  return (
    <div className="card-title-recommended">
      <h2>Eliminar canchas</h2>
      <div className="card-container-recommended">{renderCards()}</div>
    </div>
  );
};

export default Delete;
