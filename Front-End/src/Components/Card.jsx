import React from "react"
import { Link } from "react-router-dom"
const Card = ({image, sport}) => {
    return (
    <div className="card">
        <Link to={`/Filtered/${sport}`}> <img src={image} alt="imagenCancha" /></Link>
        <h3>{sport}</h3>
    </div>
  )
}

export default Card