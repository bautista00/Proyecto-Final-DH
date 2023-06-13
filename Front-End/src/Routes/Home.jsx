import React from "react";
import Slider from "../Components/Slider";
import Filters from "../Components/Filters";
import Recommended from "../Components/Recommended";
const Home = () => {
    return (
    <div className="divHome">
       <Slider/>
       <Filters/>
       <Recommended/> 
    </div>
    );
  };
  
  
  export default Home