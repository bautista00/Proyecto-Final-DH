import React from 'react'
import Book from '../Components/Book'
import { useLocation } from "react-router-dom";

const Booking = () => {

  const location = useLocation()
  const detail = location.state?.detail

  return (
    <div>
        <Book detail={detail}/>
    </div>
  )
}

export default Booking