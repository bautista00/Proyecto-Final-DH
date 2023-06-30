import React, { useEffect} from "react";
import { axiosInstance } from "../config";


const Verify = () => {

  const fetchVerify = async () => {
    const config = {        
      params: {
        email: localStorage.getItem("email")
      }
    };
    await axiosInstance.put("/verify", {}, config);
  };

  useEffect(() => { 
    fetchVerify()
}, [])

    const goLogin = () =>{
      localStorage.removeItem("email")
      window.location.replace("/Login");
    }

  return (
    <div>
        <div className="accSucced logInContent">
          <h2>Cuenta verificada con éxito</h2>
          <p>¡Tu cuenta se ha verificado correctamente!</p>
          <button onClick={goLogin}>Ir a Login</button>
        </div>        
    </div>
  )
}

export default Verify