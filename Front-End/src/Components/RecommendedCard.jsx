import React from "react"
import { Link } from "react-router-dom"

const RecommendedCard = ({image, id, location, name}) => {
    return (
        <div className="card-recommended">
            <Link to={`/Detail/${id}`}> <img src={image} alt="imagenCancha" /></Link> 
            <h3>{name}</h3>
            <p>{location}</p>
        </div>
      )
    }

export default RecommendedCard