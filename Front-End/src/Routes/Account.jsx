
import Avatar from "../Components/Avatar";
import UserInformation from "../Components/UserInformation";
import { axiosInstance } from "../config";
import Banner from "../Components/Banner";
import { useState, useEffect} from "react";

const Account = () => {
  const [loggedUser, setLoggedUser] = useState()
  useEffect(() => {
      axiosInstance
        .get("/all/getuser", {
          params: {
            token: localStorage.getItem("jwt"),
          },
        })
        .then((response) => {
          setLoggedUser(response.data);
         
        });
        
  }, []);

  return (
    <div className="account-container">
      <Banner title={"Cuenta de " + loggedUser?.role} />

      <div className="account-content">
        <div className="account-controls">
          <div className="card-avatar">
            <Avatar
              name={`${loggedUser?.nombre} ${loggedUser?.apellido}`}
              image={""}
            />
            <h3>{`${loggedUser?.nombre}  ${loggedUser?.apellido}`}</h3>
            <p>{loggedUser?.role}</p>
          </div>
         
        </div>
        <UserInformation user={loggedUser} />
      </div>
    </div>
  );
};

export default Account;
