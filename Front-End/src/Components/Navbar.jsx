import React, { useEffect, useState, useMemo } from "react";
import "../index.css";
import { Link } from "react-router-dom";
import Avatar from "./Avatar";
import { axiosInstance } from "../config";
import { Popover, Divider } from "antd";

const Navbar = () => {
  const [loggedUser, setLoggedUser] = useState({});

  useEffect(() => {
    if (localStorage.getItem("auth") === "true") {
      axiosInstance
        .get("/all/getuser", {
          params: {
            token: localStorage.getItem("jwt"),
          },
        })
        .then((response) => {
          setLoggedUser(response.data);
        });
    }
  }, []);

  const renderSignLog = () => {
    return (
      <div className="divButtons">
        <div id="navbarButtonsLogin">
          <Link to={`/Login`}>
            <button>Iniciar Sesión</button>
          </Link>
          <Link to={`/SignUp`}>
            <button>Registrarse</button>
          </Link>
        </div>
        <Popover
          placement="bottom"
          title={"Bienvenido a Field Rent!"}
          content={
            <div>
              <Divider />
              <Link to={`/Login`}>
                <p>Iniciar Sesión</p>
              </Link>
              <Link to={`/SignUp`}>
                <p>Registrarse</p>
              </Link>
            </div>
          }
          arrow={mergedArrow}
        >
          <div className="navbarControlsLogin">
            <button id="buttonMenuLogin">
              <i id="menuLogin" className="fa-solid fa-bars"></i>
            </button>
            <i className="fa-solid fa-caret-down"></i>
          </div>
        </Popover>
      </div>
    );
  };

  const renderUOA = (name) => {
    if (localStorage.getItem("auth")) {
      if (localStorage.getItem("role") === "USER") {
        return (
          <div className="navbarControls">
                <Popover
                  placement="bottom"
                  title={titleMenu}
                  content={
                    <div>
                      <Divider />
                      {miCuentaButton}
                      <Divider/>
                      <Link to={`/BookingControl`}>
                        <p>Reservas</p>
                      </Link>
                      <Divider/>
                      <Link to={`/Favoritos`}>
                        <p>Favoritos</p>
                      </Link>
                      <Divider/>
                      {logOutButton}
                    </div>
                  }
                  arrow={mergedArrow}
                >
                  <div>
                    <p>{loggedUser.role}</p>
                    <Avatar name={name} image={""} />
                    <i className="fa-solid fa-caret-down"></i>
                  </div>
                </Popover>
          </div>
        );
      } else if (localStorage.getItem("role") === "OWNER") {
        return (
          <div className="navbarControls">
            <Popover
              placement="bottom"
              title={titleMenu}
              content={
                <div>
                  <Divider />
                  {miCuentaButton}
                  <Divider />
                  <Link to={`/DeleteProduct`}>
                    <p>Administrar Productos</p>
                  </Link>
                  <Divider />
                  <Link to={`/CreateProduct`}>
                    <p>Crear Producto</p>
                  </Link>
                  <Divider />
                  {logOutButton}
                </div>
              }
              arrow={mergedArrow}
            >
              <div>
                <p>{loggedUser.role}</p>
                <Avatar name={name} image={""} />
                <i className="fa-solid fa-caret-down"></i>
              </div>
            </Popover>
          </div>
        );
      } else if (localStorage.getItem("role") === "ADMIN") {
        return (
          <div className="navbarControls">
            <Popover
              placement="bottom"
              title={titleMenu}
              content={
                <div>
                  <Divider />
                  {miCuentaButton}
                  <Divider />
                  <Link to={`/UsersPermissions`}>
                    <p>Administrar Roles</p>
                  </Link>
                  <Divider />
                  <Link to={`/DeleteCategory`}>
                    <p>Administrar Categorias</p>
                  </Link>
                  <Divider />
                  <Link to={`/Cities`}>
                    <p>Administrar Barrios</p>
                  </Link>
                  <Divider />
                  {logOutButton}
                </div>
              }
              arrow={mergedArrow}
            >
              <div>
                <p>{loggedUser.role}</p>
                <Avatar name={name} image={""} />
                <i className="fa-solid fa-caret-down"></i>
              </div>
            </Popover>
          </div>
        );
      }
    }
  };
  const renderAvClose = () => {
    let name = `${loggedUser?.nombre} ${loggedUser?.apellido}`;

    return (
      <div className="divButtons">
        {JSON.parse(localStorage.getItem("auth")) ? renderUOA(name) : ""}
      </div>
    );
  };
  const logOut = () => {
    localStorage.removeItem("role")
    localStorage.removeItem("jwt")
    localStorage.setItem("auth", JSON.stringify(false));
    window.location.href = "/";
  };

  //Recursos de componente para menú de botones del admin,owner,user (de ant)
  const [showArrow, setShowArrow] = useState(true);
  const [arrowAtCenter, setArrowAtCenter] = useState(false);
  const titleMenu = (
    <span id="welcomeMessageMenu">Hola {loggedUser?.nombre}!</span>
  );
  //const iconMenuButton = <i className="fa-solid fa-bars"></i>
  const miCuentaButton = (
    <Link to={`/Account/${loggedUser.id}`}>Mi Cuenta</Link>
  );
  const logOutButton = (
    <p className="logOut-p" onClick={logOut}>
      Cerrar Sesión <i className="fa-solid fa-right-to-bracket"></i>
    </p>
  );

  const mergedArrow = useMemo(() => {
    if (arrowAtCenter)
      return {
        pointAtCenter: true,
      };
    return showArrow;
  }, [showArrow, arrowAtCenter]);

  return (
    <div className="divHeader">
      <div className="logoDiv">
        <a href="/">
          <i>
            <img
              className="logoHeader"
              src="/images/fieldRentlogo.png"
              alt="logo"
            />
          </i>
        </a>
        <a href="/">Tu cancha a un click</a>
      </div>
      {JSON.parse(localStorage.getItem("auth"))
        ? renderAvClose()
        : renderSignLog()}
    </div>
  );
};

export default Navbar;
