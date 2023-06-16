import React from "react";
import { CheckOutlined, CloseOutlined } from "@ant-design/icons";
import { Space, Switch } from "antd";

const OwnerPermissions = () => {
  return (
    <div className="ownerPermissions">
      <h1>Tareas de Arrendador</h1>
      <Space direction="horizontal">
        <span>Editar detalle de cancha</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Agregar Cancha</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Eliminar Cancha</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Agregar arrendador</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Eliminar arrendador</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Agregar usuarios</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Eliminar usuarios</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Cambiar permisos de usuarios</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Editar la lista de canchas</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Agregar nuevas canchas a la lista</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Editar las categorias</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Agregar categorias</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <div className="OwnerPermissionsButton">
        <button className="buttonPermission">Guardar</button>
        <button className="buttonPermission">Cancelar</button>
      </div>
    </div>
  );
};

export default OwnerPermissions;
