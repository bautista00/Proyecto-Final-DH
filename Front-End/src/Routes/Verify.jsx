import React from 'react'
import { Link } from "react-router-dom";

const Verify = () => {

  return (
    <div>
        <div className="accSucced logInContent">
          <h2>Cuenta verificada con éxito</h2>
          <p>¡Tu cuenta se ha verificado correctamente!</p>
          <Link to={`/Login`}><button>Ir a Login</button></Link>
        </div>        
    </div>
  )
}

export default Verify