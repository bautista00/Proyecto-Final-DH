import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { axiosInstance } from "../config";

const SignUp = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");

  const [cuil, setCuil] = useState("");
  const [cbu, setCbu] = useState("");
  const [domicilio, setDomicilio] = useState("");
  const [telefono, setTelefono] = useState("");

  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [nombreError, setNombreError] = useState("");
  const [apellidoError, setApellidoError] = useState("");
  const [cuilError, setCuilError] = useState("");
  const [cbuError, setCbuError] = useState("");
  const [telefonoError, setTelefonoError] = useState("");
  const [domicilioError, setDomicilioError] = useState("");

  const [isAdmin, setIsAdmin] = useState(false);
  const [accountCreated, setAccountCreated] = useState(false); // Nuevo estado para mostrar el mensaje de cuenta creada

  // useEffect(() => {
  //   if (accountCreated) {
  //     setTimeout(() => {
  //       window.location.href = "/login";
  //     }, 20000);
  //   }
  // }, [accountCreated]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Restablecer los mensajes de error
    setEmailError("");
    setPasswordError("");
    setNombreError("");
    setApellidoError("");
    setCuilError("");
    setCbuError("");

    if (!email || !validarMail(email)) {
      setEmailError("Ingrese un email válido.");
      return;
    }
    if (!password || password.length < 8) {
      setPasswordError("La contraseña debe tener al menos 8 caracteres.");
      return;
    }
    if (!nombre || !validarNombre(nombre)) {
      setNombreError("Ingrese un nombre válido (solo letras).");
      return;
    }
    if (!apellido || !validarNombre(apellido)) {
      setApellidoError("Ingrese un apellido válido (solo letras).");
      return;
    }

    if (isAdmin){
      if (!cuil || !validarCuil(cuil)) {
        setCuilError("Ingrese un cuil válido");
        return;
      }
      if (!cbu || !validarCbu(cbu)) {
        setCuilError("Ingrese un cbu válido. Debe contener 22 dígitos");
        return;
      }
      if (!telefono || !validarTelefono(telefono)) {
        setTelefonoError(
          "Ingrese un teléfono válido en Argentina. Debe contener 10 dígitos"
        );
        return;
      }
      if (!domicilio || !validarDomicilio(domicilio)) {
        setDomicilioError("Ingrese un domicilio válido.");
        return;
      }
    }

    //Logica

    try{
      const requestData = {
        // Los datos que deseas enviar en la solicitud
        nombre: nombre,
        apellido: apellido,
        username: email,
        password: password
      };

      if (isAdmin) {
        requestData.cuil = cuil;
        requestData.cbu = cbu;
        requestData.telefono = telefono
      }

      
      await axiosInstance.post("/sign-up", requestData)
      .then((response) => {
        console.log("Response:", response.data);
        localStorage.setItem("email", response.data.email)
      });

    }catch(e){
      console.log(e)
    }  
    

    // Realizar el registro exitoso
    setAccountCreated(true);

    // Restablecer los campos
    setEmail("");
    setPassword("");
    setNombre("");
    setApellido("");
    setCuil("");
    setCbu("");
    setTelefono("");
    setDomicilio("");
  };

  const validarMail = (value) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(value);
  };

  const validarNombre = (value) => {
    const nameRegex = /^[A-Za-z]+$/;
    return nameRegex.test(value);
  };

  const validarCuil = (value) => {
    const cuilRegex = /^\d+$/;
    return cuilRegex.test(value);
  };

  const validarCbu = (value) => {
    const cbuRegex = /^\d{22}$/;
    return cbuRegex.test(value);
  };

  const validarTelefono = (value) => {
    const telefonoRegex = /^\d{10}$/;
    return telefonoRegex.test(value);
  };

  const validarDomicilio = (value) => {
    const domicilioRegex = /^[a-zA-Z0-9\s.,-]{1,100}$/;
    return domicilioRegex.test(value);
  };

  return (
    <div className="logInContent">
      {accountCreated ? ( // Mostrar el mensaje de cuenta creada si está establecido
        <div className="accSucced">
          <h2>Cuenta creada con éxito</h2>
          <p>¡Tu cuenta se ha creado correctamente!</p>
          <p>Redirigiendo a la página de inicio de sesión...</p>
          {/* Aquí puedes agregar lógica para redirigir automáticamente a la página de inicio de sesión */}
        </div>
      ) : (
        <form onSubmit={handleSubmit} className="formSignUp">
          <div>
            <label htmlFor="nombre">Nombre:</label>
            <input
              type="text"
              id="nombre"
              value={nombre}
              onChange={(e) => setNombre(e.target.value)}
              required
            />
            {nombreError && <p>{nombreError}</p>}
          </div>

          <div>
            <label htmlFor="apellido">Apellido:</label>
            <input
              type="text"
              id="apellido"
              value={apellido}
              onChange={(e) => setApellido(e.target.value)}
              required
            />
            {apellidoError && <p>{apellidoError}</p>}
          </div>

          <div>
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            {emailError && <p>{emailError}</p>}
          </div>

          <div>
            <label htmlFor="password">Contraseña:</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            {passwordError && <p>{passwordError}</p>}
          </div>

          {/* LOGICA SI SE CHECKEA PARA REGISTRAR ADMIN */}
          {isAdmin && (
            <div>
              <div>
                <label htmlFor="cuil">CUIL:</label>
                <input
                  type="text"
                  id="cuil"
                  value={cuil}
                  onChange={(e) => setCuil(e.target.value)}
                  required
                />
                {cuilError && <p>{cuilError}</p>}
              </div>

              <div>
                <label htmlFor="cbu">CBU:</label>
                <input
                  type="text"
                  id="cbu"
                  value={cbu}
                  onChange={(e) => setCbu(e.target.value)}
                  required
                />
                {cbuError && <p>{cbuError}</p>}
              </div>

              <div>
                <label htmlFor="telefono">Telefono:</label>
                <input
                  type="text"
                  id="telefono"
                  value={telefono}
                  onChange={(e) => setTelefono(e.target.value)}
                  required
                />
                {telefonoError && <p>{telefonoError}</p>}
              </div>

              <div>
                <label htmlFor="domicilio">Domicilio:</label>
                <input
                  type="text"
                  id="domicilio"
                  value={domicilio}
                  onChange={(e) => setDomicilio(e.target.value)}
                  required
                />
                {domicilioError && <p>{domicilioError}</p>}
              </div>
            </div>
          )}

          <label htmlFor="admin">
            ¿Querés registrarte para alquilar tus canchas?
          </label>
          <div id="adminCheckBoxDiv">
            <span>Haz click aquí</span>
            <input
              type="checkbox"
              id="admin"
              onChange={() => setIsAdmin(!isAdmin)}
              className="adminCheckbox"
            />
          </div>

          <button type="submit">Registrarse</button>
        </form>
      )}

      <div className="inicioSesion">
        <p>¿Ya tenés una cuenta?</p>
        <Link to={`/Login`}>
          <p className="inicioSesionLink">Inicia Sesión</p>
        </Link>
      </div>
    </div>
  );
};

export default SignUp;
