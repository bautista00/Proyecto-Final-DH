import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import { axiosInstance } from "../config";
import RecommendedCard from "./RecommendedCard";

const FilteredCompSportBarrio = () => {
    const { sport, barrio } = useParams();
    const [currentPage, setCurrentPage] = useState(1);
    const cardsPerPage = 5;
    const [filtered, setFiltered] = useState([]);
  
    const fetchData = async () => {
  
      try{
        
        const config = {        
          params: {
            barrio: barrio ,
            categoria : sport
          }
        }
        const result = await axiosInstance.get(`/buscarFiltradas`, config);
        setFiltered(result.data);
       
  
      }catch(e){
        console.log(e)
      }
    
    };
  
    useEffect(() => {
      fetchData();
    }, []);
  
    const startIndex = (currentPage - 1) * cardsPerPage;
    const endIndex = startIndex + cardsPerPage;
    const totalPages = Math.ceil(
      filtered.filter((card) => card.nombre === sport).length / cardsPerPage
    );
  
    const handleNextPage = () => {
      if (currentPage < totalPages) {
        setCurrentPage(currentPage + 1);
      }
    };
   
    const handlePreviousPage = () => {
      if (currentPage > 1) {
        setCurrentPage(currentPage - 1);
      }
    };
  
    const handlePageChange = (pageNumber) => {
      setCurrentPage(pageNumber);
    };
    
    return (
      <div>
        <h2>Canchas de {sport} en {barrio}</h2>
        <div className="card-container-recommended">
          {filtered
            .slice(startIndex, endIndex)
            .map((card, index) => (
              <RecommendedCard
                key={index}
                id={card?.id}
                name={card?.nombre}
                location={card?.domicilio?.barrio?.nombre}
                image={card?.images?.url[0]}
              />
            ))}
        </div>
        <div className="pagination">
          <button
            onClick={handlePreviousPage}
            disabled={currentPage === 1}
            className={currentPage === 1 ? "disabled" : ""}
          >
            Anterior
          </button>
          {Array.from({ length: totalPages }, (_, index) => index + 1).map(
            (pageNumber) => (
              <button
                key={pageNumber}
                onClick={() => handlePageChange(pageNumber)}
                className={currentPage === pageNumber ? "active" : ""}
              >
                {pageNumber}
              </button>
            )
          )}
          <button
            onClick={handleNextPage}
            disabled={currentPage === totalPages}
            className={currentPage === totalPages ? "disabled" : ""}
          >
            Siguiente
          </button>
        </div>
      </div>
    );
  };
export default FilteredCompSportBarrio