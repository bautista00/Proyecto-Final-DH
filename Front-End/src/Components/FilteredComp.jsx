import React, { useEffect, useState } from "react";
import { useContextGlobal } from "./utils/GlobalContext";
import RecommendedCard from "./RecommendedCard";
import { useParams } from "react-router-dom";
import { axiosInstance } from "../config";

const FilteredComp = () => {
  const { sport } = useParams();
  const { data } = useContextGlobal();
  const [currentPage, setCurrentPage] = useState(1);
  const cardsPerPage = 8;

  const [filtered, setFiltered] = useState([]);

  const fetchData = async (sport) => {
    const result = await axiosInstance.get(`/listxsportcanchas/${sport}`);
    setFiltered(result.data);
  };

  useEffect(() => {
    fetchData(sport);
  }, [sport]);

  const startIndex = (currentPage - 1) * cardsPerPage;
  const endIndex = startIndex + cardsPerPage;
  const totalPages = Math.ceil(
    data.filter((card) => card.kindOfSport === sport).length / cardsPerPage
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
      <h2>Canchas de {sport}</h2>
      <div className="card-container-recommended">
        {data
          .filter((card) => card.kindOfSport === sport)
          .slice(startIndex, endIndex)
          .map((card, index) => (
            <RecommendedCard
              key={index}
              id={card.id}
              name={card.name}
              location={card.location}
              image={card.image}
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

export default FilteredComp;
