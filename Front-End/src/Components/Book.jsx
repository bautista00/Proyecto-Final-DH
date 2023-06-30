import React, { useEffect, useState } from "react";
import { axiosInstance } from "../config";

const Book = (props) => {
  const { detail, selectedDate } = props;

  const [loggedUser, setLoggedUser] = useState({});
  const [bookCreated, setBookCreated] = useState(false);
  const [editedEmail, setEditedEmail] = useState("");

  useEffect(() => {
    if (bookCreated) {
      setTimeout(() => {
        window.location.href = "/";
      }, 7000);
    }
  }, [bookCreated]);

  useEffect(() => {
    axiosInstance
      .get("/all/getuser", {
        params: {
          token: localStorage.getItem("jwt"),
        },
      })
      .then((response) => {
        setLoggedUser(response.data);
        setEditedEmail(response.data.email);
      });
  }, []);

  const confirmBook = async () => {
    try {
      await axiosInstance.post(
        "/user/createturno",
        {
          horas: selectedDate.hours,
          fecha: selectedDate.date,
          idCancha: detail.id,
        },
        {
          params: {
            token: localStorage.getItem("jwt"),
          },
        }
      );
      setBookCreated(true);
    } catch (e) {
      console.log(e);
    }
  };
  console.log(detail, selectedDate, loggedUser);
  return (
    <div>
      {bookCreated ? (
        <div className="logInContent accSucced">
          <h2>Reserva creada con éxito</h2>
          <p>¡Tu reserva se ha creado correctamente!</p>
          <p>Redirigiendo al Home</p>
        </div>
      ) : (
        <div>
          <h1 className="bookTitle">Reserva tu cancha</h1>
          <div className="bookContent">
            <h2>Datos del usuario</h2>
            <div>
              <p>Nombre: {loggedUser.nombre}</p>
              <p>Apellido: {loggedUser.apellido}</p>
              <p>
                Email:{" "}
                <input
                  type="text"
                  value={editedEmail}
                  onChange={(e) => setEditedEmail(e.target.value)}
                  style={{ height: 20 }}
                />
              </p>
            </div>
            <h2>Datos de la cancha</h2>
            <div className="container-data-book">
              <div>
                <p>Nombre Cancha: {detail?.canchaDTO.nombre}</p>
                <p>Precio: {detail?.canchaDTO.precio}</p>
                <p>
                  Domicilio: <span>{detail?.canchaDTO.domicilio.calle} -</span>{" "}
                  <span> {detail?.canchaDTO.domicilio.numero}</span>
                </p>
                <p>Barrio: {detail?.canchaDTO.domicilio.barrio.nombre}</p>
              </div>
              <div className="image-grid">
                <img
                  style={{ height: 200, width: 250 }}
                  src={detail?.canchaDTO.images.url[0]}
                  alt="foto cancha"
                />
                <img
                  style={{ height: 200, width: 250 }}
                  src={detail?.canchaDTO.images.url[1]}
                  alt="foto cancha"
                />
              </div>
            </div>

            <h2>Fecha y hora de la reserva</h2>
            {selectedDate?.date}
            <button onClick={confirmBook}>Reservar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Book;
