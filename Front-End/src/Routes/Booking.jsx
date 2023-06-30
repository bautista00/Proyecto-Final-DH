import React from "react";
import Book from "../Components/Book";
import { useLocation } from "react-router-dom";

const Booking = () => {
  const location = useLocation();
  const { detail, selectedDate } = location.state || {};

  return (
    <div>
      <Book detail={detail} selectedDate={selectedDate} />
    </div>
  );
};

export default Booking;
