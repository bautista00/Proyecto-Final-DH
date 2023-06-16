import { Card } from "antd";
import { useState } from "react";
import AdminPermissions from "../Components/AdminPermissions";
import OwnerPermissions from "../Components/OwnerPermissions";
import UserPermissions from "../Components/UserPermissions";

const tabListNoTitle = [
  {
    key: "Administrador",
    tab: "Administrador",
  },
  {
    key: "Arrendador",
    tab: "Arrendador",
  },
  {
    key: "Usuario",
    tab: "Usuario",
  },
];

const contentListNoTitle = {
  Administrador: <AdminPermissions />,
  Arrendador: <OwnerPermissions />,
  Usuario: <UserPermissions />,
};

const UsersPermissions = () => {
  const [activeTabKey2, setActiveTabKey2] = useState("Administrador");

  const onTab2Change = (key) => {
    setActiveTabKey2(key);
  };

  return (
    <div className="UsersPermissionsContainer">
      <h1>Permisos de Acceso</h1>
      <div className="UserRoles">
        <div className="CardRole">
          <Card
            style={{
              width: "50%",
            }}
            tabList={tabListNoTitle}
            activeTabKey={activeTabKey2}
            tabBarExtraContent={<a href="#">More</a>}
            onTabChange={onTab2Change}
          >
            {contentListNoTitle[activeTabKey2]}
          </Card>
        </div>
      </div>
    </div>
  );
};
export default UsersPermissions;
