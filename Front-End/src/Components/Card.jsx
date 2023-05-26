import React from "react"
import { Link } from "react-router-dom"
const Card = ({name,location, image, id, sport}) => {
    return (
    <div className="card">
       <h3>{name}{sport}</h3>
        <p>{location}</p>
        <Link to={`/Detail/${id}`}> <img src={image} alt="imagenCancha" /></Link> 
    </div>
  )
}

export default Card