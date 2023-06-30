import React, { useEffect, useState } from "react";
import FilteredComp from "../Components/FilteredComp";

const Filtered = () => {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <div className="filtered">
      <FilteredComp />
    </div>
  );
};

export default Filtered;
