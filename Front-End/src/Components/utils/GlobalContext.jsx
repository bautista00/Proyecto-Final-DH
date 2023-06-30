import { createContext, useContext, useState, useEffect } from "react";


export const ContextGlobal = createContext()

export const ContextProvider = ({ children }) => {
  const [favs, setFavs] = useState(() => {
    const favsAlmacenados = localStorage.getItem("favoritos")
    return favsAlmacenados ? JSON.parse(favsAlmacenados) : []
  });

   useEffect(() => {
    localStorage.setItem("favoritos", JSON.stringify(favs))
  }, [favs])

  return (
    <ContextGlobal.Provider value={{favs, setFavs}}>
      {children}
    </ContextGlobal.Provider>
  )
}

export const useContextGlobal = () => useContext(ContextGlobal)
