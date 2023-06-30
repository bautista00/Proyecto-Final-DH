import React, { useEffect } from "react";
import FilteredCompSportBarrio from "../Components/FilteredCompSportBarrio";

const FilteredSportBarrio = () => {
    useEffect(() => {
        window.scrollTo(0, 0)
      }, [])
    
      return (
        <div className="filtered">
          <FilteredCompSportBarrio />
        </div>
      )
    }
export default FilteredSportBarrio