import { createContext, useContext, useState, useEffect } from "react";
import axios from "axios";

const ContextGlobal = createContext();

export const ContextProvider = ({ children }) => {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get("../data.json").then((response) => {
      setData(response.data);
    });
  }, []);

  const [users, setUsers] = useState([]);

  useEffect(() => {
    axios.get("../user.json").then((response) => {
      setUsers(response.data);
    });
  }, []);

  return (
    <ContextGlobal.Provider value={{ data, setData, users }}>
      {children}
    </ContextGlobal.Provider>
  );
};

export const useContextGlobal = () => useContext(ContextGlobal);
