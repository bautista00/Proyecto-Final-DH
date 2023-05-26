import { createContext, useContext ,useState, useEffect, useReducer} from "react";
import axios from "axios";


const ContextGlobal = createContext();


export const ContextProvider = ({ children }) => {
  
    const [data, setData] = useState([])
    
    useEffect(() => {
       axios.get('../data.json')
       .then(response=>{setData(response.data)})
      }, []);


  return (
    <ContextGlobal.Provider value={{data, setData}}>
      {children}
    </ContextGlobal.Provider>
  );
};

export const useContextGlobal = () => useContext(ContextGlobal);