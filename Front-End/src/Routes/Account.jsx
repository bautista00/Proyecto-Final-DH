import { Link, useParams } from "react-router-dom";
import Avatar from "../Components/Avatar";
import UserInformation from "../Components/UserInformation";
import { useContextGlobal } from "../Components/utils/GlobalContext";
import Banner from "../Components/Banner";

const Account = () => {
  const { user } = useParams();
  const userId = parseInt(user);

  const { users = [] } = useContextGlobal();
  const loggedUser = users.find((usr) => usr.id === userId);

  return (
    <div className="account-container">
      <Banner title={"Cuenta de " + loggedUser.role} />

      <div className="account-content">
        <div className="account-controls">
          <div className="card-avatar">
            <Avatar
              name={`${loggedUser?.name} ${loggedUser?.lastname}`}
              image={""}
            />
            <h3>{`${loggedUser?.name}  ${loggedUser?.lastname}`}</h3>
            <p>{`${loggedUser?.country} ${loggedUser?.city}`}</p>
            <p>{loggedUser?.role}</p>
          </div>
          <div className="account-button">
            {loggedUser?.role === "Administrador" && (
              <>
                <Link to={`/CreateProduct`}>
                  <button>Cargar Productos</button>
                </Link>
                <Link to={`/DeleteProduct`}>
                <button>Eliminar producto</button>
                </Link>
              </>
            )}

            {loggedUser?.role === "Usuario" && (
              <>
                <Link to={`/`}>
                  <button>Reservar chanchas</button>
                </Link>
                {/* <button>Canchas Reservadas</button>
               
                <button>Eliminar reserva</button> */}
                
              </>
            )}
          </div>
        </div>
        <UserInformation user={loggedUser} />
      </div>
    </div>
  );
};

export default Account;
