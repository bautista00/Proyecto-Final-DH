import React, { useEffect, useState } from 'react';


const Slider = () => {

  const images = "/images/fotoCanchaFutbol1banner.jpg"

  return (
    <div className="slider">
      <img src={images}/>
    </div>
  );
};

export default Slider;