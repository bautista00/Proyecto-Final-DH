import React from 'react'
import DeleteCatCard from './DeleteCatCard';
import { useEffect, useState } from "react";
import { axiosInstance } from "../config";
import { Link } from "react-router-dom";

const DeleteCat = () => {

    const [deleteCat, setDeleteCat] = useState([]);

    const fetchData = async () => {
      const result = await axiosInstance.get("/findAllCategoria/");
      setDeleteCat(result.data);
    };

    useEffect(() => {
        fetchData();
      }, []);

    const borrarCat = async (id) => {
        try {
            const token = localStorage.getItem("jwt");
            console.log(token)
            const config = {
              headers: {
                Authorization: `Bearer ${token}`
              }
            }

          const response = await axiosInstance.delete(`/admin/deleteCategoria/${id}`, config);
          console.log("Response:", response.data);
          fetchData();
        } catch (e) {
          console.log(e);
        }
      };

      const renderCards = () => {
        return deleteCat.map((e, index) => (
          <DeleteCatCard
            key={index}
            image={e.images.url[0]}
            sport={e.nombre}
            func={borrarCat}
            id={e.id}
          />
        ));
      };
  return (
    <div className="card-title-recommended">
      <h2 className="banner-text">Administrar categorías</h2>
      <div className="card-container-recommended">{renderCards()}</div>      
      <Link to={`/CreateCategory`}><button className="buttonAgregar buttonCategory">Crear nueva Categoría</button></Link>
          
    </div>
  )
}

export default DeleteCat