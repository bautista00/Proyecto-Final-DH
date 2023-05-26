import React, { useEffect, useState } from 'react';
import { useContextGlobal } from './utils/GlobalContext';

const Slider = () => {
  const [currentIndex, setCurrentIndex] = useState(0);

  const {data} = useContextGlobal()

  const nextSlide = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % data.length);
  };

  const previousSlide = () => {
    setCurrentIndex((prevIndex) => (prevIndex - 1 + data.length) % data.length);
  };

     useEffect(()=>{
        const timer = setInterval(nextSlide, 4000)
        return () => {clearInterval(timer)}
    }, [nextSlide])

  return (
    <div className="slider">
      <button onClick={previousSlide}><img src="images\arrow-89-128.png" alt="" /></button>
      <img src={data[currentIndex]?.image}/>
      <button onClick={nextSlide}><img src="images\arrow-24-128.png" alt="" /></button>
    </div>
  );
};

export default Slider;