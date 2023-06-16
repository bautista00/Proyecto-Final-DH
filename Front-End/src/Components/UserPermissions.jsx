import React from "react";
import { CheckOutlined, CloseOutlined } from "@ant-design/icons";
import { Space, Switch } from "antd";

const UserPermissions = () => {
  return (
    <div className="userPermissions">
      <h1>Tareas de Usuario</h1>
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

      <Space direction="horizontal">
        <span>Reservar Cancha</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Cancelar reserva</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Calificar cancha</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <Space direction="horizontal">
        <span>Dejar comentarios de cancha</span>
        <Switch
          checkedChildren={<CheckOutlined />}
          unCheckedChildren={<CloseOutlined />}
          defaultChecked
        />
      </Space>

      <div className="userPermissionsButton">
        <button className="buttonPermission">Guardar</button>
        <button className="buttonPermission">Cancelar</button>
      </div>
    </div>
  );
};

export default UserPermissions;
