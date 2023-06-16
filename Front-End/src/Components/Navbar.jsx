import React, { useEffect, useState } from "react";
import "../index.css";
import { Link } from "react-router-dom";
import Avatar from "./Avatar";
import { useContextGlobal } from "./utils/GlobalContext";
import { axiosInstance } from "../config";

const Navbar = () => {
  const userId = parseInt(localStorage.getItem("userId"));
  const [loggedUser, setLoggedUser] = useState({});
  const { users } = useContextGlobal();

  useEffect(() => {
    axiosInstance
      .get("/all/getuser", {
        params: {
          token: localStorage.getItem("jwt"),
        },
      })
      .then((response) => {
        console.log("Response:", response.data);
        setLoggedUser(response.data);
      });
  }, [userId]);

  //const loggedUser = users.find((usr) => usr.id === userId);

  const renderSignLog = () => {
    return (
      <div className="divButtons">
        <Link to={`/Login`}>
          <button>Iniciar Sesión</button>
        </Link>
        <Link to={`/SignUp`}>
          <button>Registrarse</button>
        </Link>
      </div>
    );
  };

  const renderAvClose = () => {
    let name = `${loggedUser?.nombre} ${loggedUser?.apellido}`;
    name = localStorage.getItem("role") === "GOD" ? "Super Admin" : name;

    return (
      <div className="divButtons">
        <button onClick={logOut}>Cerrar Sesión</button>
        <Avatar name={name} image={""} />
      </div>
    );
  };
  const logOut = () => {
    localStorage.setItem("auth", JSON.stringify(false));
    window.location.href = "/";
  };

  return (
    <div className="divHeader">
      <div className="logoDiv">
        <Link to={`/`}>
          <i>
            <img
              className="logoHeader"
              src="/images/fieldRentlogo.png"
              alt="logo"
            />
          </i>
        </Link>
        <a href="/">Tu cancha a un click</a>
      </div>
      {JSON.parse(localStorage.getItem("auth"))
        ? renderAvClose()
        : renderSignLog()}
    </div>
  );
};

export default Navbar;
