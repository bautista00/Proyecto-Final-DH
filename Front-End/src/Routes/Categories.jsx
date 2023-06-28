import { useState, useEffect } from "react";
import { axiosInstance } from "../config";
import Card from "../Components/Card";
import { Link } from "react-router-dom";

const Categories = () => {
  
   const [deportes, setDeportes] = useState([]);

   useEffect(() => {
    axiosInstance.get("/findAllCategoria/").then((response) => {
      setDeportes(response.data); 
    });
  }, []);

  console.log(deportes)
  return (
    <div className="container-categories">
      <h1 className="banner-text">Categorias</h1>
      
        <div className="card-container">
          {deportes.map((e) => {           
              return (
                <Card
                  key={e.id}
                  image={e.images.url[0]}
                  sport={e.nombre}
                />
              );
            
          })}
        </div>

        <div className="buttons-Categories">
          <Link to={`/CreateCategory`}> <button className="buttonAgregar buttonCategory">Crear categoría</button></Link>
          <Link to={`/DeleteCategory`}> <button className="buttonAgregar buttonCategory">Eliminar categoría</button></Link>
        </div>

      </div>
    
  );
};

export default Categories;
