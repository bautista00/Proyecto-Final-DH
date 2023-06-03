import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get("user.json").then((response) => setData(response.data));
  }, []);

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const user = data.find(
      (user) => user.email === email && user.password === password
    );

    if (email == user.email && password == user.password) {
      setError("");
      setEmail("");
      setPassword("");
      localStorage.setItem("auth", JSON.stringify(true));
      localStorage.setItem("userId", JSON.stringify(user.id));
      window.location.href = "/";
    } else {
      setError("Intentalo de nuevo");
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
