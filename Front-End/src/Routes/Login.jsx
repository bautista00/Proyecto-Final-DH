import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [jwt, setJwt] = useState();

  
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = async(e) => {
    e.preventDefault();
    try{
      const ec2InstanceIP = '3.19.232.248';
      const ec2InstancePort = 8080;
      const endpoint = `http://${ec2InstanceIP}:${ec2InstancePort}/api/login`;

      const requestData = {
      // Los datos que deseas enviar en la solicitud
      username:email,
      password:password
      };

      await axios.post(endpoint, requestData, {headers:{"Content-Type":"application/json"}})
      .then(response => {
        console.log('Response:', response.data);
        setJwt(response.data.jwt)
        console.log(jwt)
      })
    
      if(jwt){
        window.localStorage.setItem("jwt",jwt)
        setPassword("")
        setEmail("")
        setError("")
        window.location.href = "/"
      }

    }catch(e){
      console.log(e)
    }

    
  };

  return (
    <div className="logInContent">
      <form onSubmit={handleSubmit} className="formSignUp">
        <div>
          <label htmlFor="email">Correo electrónico:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={handleEmailChange}
          />
        </div>
        <div>
          <label htmlFor="password">Contraseña:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={handlePasswordChange}
          />
        </div>
        <button type="submit">Iniciar sesión</button>
        {error && <p>{error}</p>}
      </form>
      <div className="inicioSesion">
        <p>No tenes una cuenta?</p>
        <Link to={`/SignUp`}>
          <p className="inicioSesionLink">Registrate</p>
        </Link>
      </div>
    </div>
  );
};

export default Login;
