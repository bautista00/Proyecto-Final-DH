import React from 'react'
import { useState } from 'react'
import { axiosInstance } from '../config';

const Favoritos = () => {
    const [canchasFav, setCanchasFav] = useState([])

    useEffect(() => { 
          axiosInstance.get("/user/listaFavs", {
            params: {
              token: localStorage.getItem("jwt"),
            },
          })
          .then((response) => {
            setCanchasFav(response.data);
            console.log(response.data);
          })

      }, [])
    // /user/listaFavs
    // param value token


  return (
    <div>Favoritos</div>
  )
}

export default Favoritos