import React, { useEffect, useState } from "react";
import DeleteCard from "./DeleteCard";
import { useContextGlobal } from "./utils/GlobalContext";
import { axiosInstance } from "../config";
import { Link } from "react-router-dom";


const Delete = () => {
  // const { data, setData } = useContextGlobal();

  const [deleteCard, setDeleteCard] = useState([]);    

  const fetchData = async () => {

    try{
      const token = localStorage.getItem("jwt")
      const formData = new FormData()
      formData.append("token", token)

      const config = {
        headers: {
        Authorization: `Bearer ${token}`
        },
        params: {
          token: token
        }
      }
      const result = await axiosInstance.get("/owner/listxownercanchas", config);
      setDeleteCard(result.data);
     

    }catch(e){
      console.log(e)
    }
    
  };
  
  useEffect(() => {
    fetchData();
  }, []);

  const borrarCard = async (id) => {
    try {

      const token = localStorage.getItem("jwt");
      console.log(token)
      const config = {
      headers: {
        Authorization: `Bearer ${token}`
        }
      }
      
      const response = await axiosInstance.delete(`/owner/deletecancha/${id}`, config);
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
        id={card?.id}
        name={card.nombre}
        location={card.domicilio.barrio.nombre}
        image={card.images.url[0]}
        func={borrarCard}
      />
    ));
  };

  return (
    <div className="card-title-recommended">
      <h2 className="banner-text">Administrar canchas</h2>
      <div className="card-container-recommended">{renderCards()}</div>
    </div>
  );
};

export default Delete;
