import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { axiosInstance } from "../config";
import { useLocation } from 'react-router-dom';

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const location = useLocation()
  const searchParams = new URLSearchParams(location.search)
  const message = searchParams.get('message')

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const requestData = {
        // Los datos que deseas enviar en la solicitud
        username: email,
        password: password,
      };

      await axiosInstance.post("/login", requestData).then((response) => {
        console.log("Response:", response.data);
        const jwt = response.data.jwt;
        const role = response.data.rol;
        console.log(jwt);
        if (jwt) {
          window.localStorage.setItem("jwt", jwt);
          window.localStorage.setItem("auth", "true");
          window.localStorage.setItem("role", role);
          setPassword("");
          setEmail("");
          setError("");
          window.location.href = "/";
        }
      });
    } catch (e) {
      console.log(e);
      setError("Credenciales inválidas, por favor intente nuevamente")
    }
  };

  return (
    <div className="logInContent">
      
      <form onSubmit={handleSubmit} className="formSignUp">
        <div>
          {message && <p>{message}</p>}
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
        <p>No tenés una cuenta?</p>
        <Link to={`/SignUp`}>
          <p className="inicioSesionLink">Registrate</p>
        </Link>
      </div>
    </div>
  );
};

export default Login;
