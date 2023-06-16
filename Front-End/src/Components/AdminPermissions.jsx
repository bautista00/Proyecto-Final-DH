import React from "react";
import { CheckOutlined, CloseOutlined } from "@ant-design/icons";
import { Space, Switch } from "antd";

const AdminPermissions = () => {
  return (
    <div className="adminPermissions">
      <h1>Tareas de Administrador</h1>
      <Space direction="horizontal">
        <div>
          <span>Editar detalle de cancha</span>
        </div>

        <div>
          <Switch
            checkedChildren={<CheckOutlined />}
            unCheckedChildren={<CloseOutlined />}
            defaultChecked
          />
        </div>
      </Space>

      <Space direction="horizontal">
        <div>
          <span>Agregar Cancha</span>
        </div>
        <div>
          <Switch
            checkedChildren={<CheckOutlined />}
            unCheckedChildren={<CloseOutlined />}
            defaultChecked
          />
        </div>
      </Space>

      <Space direction="horizontal">
        <div>
          <span>Eliminar Cancha</span>
        </div>

        <div>
          <Switch
            checkedChildren={<CheckOutlined />}
            unCheckedChildren={<CloseOutlined />}
            defaultChecked
          />
        </div>
      </Space>

      <Space direction="horizontal">
        <div>
          <span>Agregar arrendador</span>
        </div>

        <div>
          <Switch
            checkedChildren={<CheckOutlined />}
            unCheckedChildren={<CloseOutlined />}
            defaultChecked
          />
        </div>
      </Space>

      <Space direction="horizontal">
        <div>
          <span>Eliminar arrendador</span>
        </div>

        <div>
          <Switch
            checkedChildren={<CheckOutlined />}
            unCheckedChildren={<CloseOutlined />}
            defaultChecked
          />
        </div>
      </Space>

      <Space direction="horizontal">
        <div>
          <span>Agregar usuarios</span>
        </div>

        <div>
          <Switch
            checkedChildren={<CheckOutlined />}
            unCheckedChildren={<CloseOutlined />}
            defaultChecked
          />
        </div>
      </Space>

      <Space direction="horizontal">
        <div>
          <span>Eliminar usuarios</span>
        </div>

        <div>
          <Switch
            checkedChildren={<CheckOutlined />}
            unCheckedChildren={<CloseOutlined />}
            defaultChecked
          />
        </div>
      </Space>

      <Space direction="horizontal">
        <div>
          <span>Cambiar permisos de usuarios</span>
        </div>

        <div>
          <Switch
            checkedChildren={<CheckOutlined />}
            unCheckedChildren={<CloseOutlined />}
            defaultChecked
          />
        </div>
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

      <div className="adminPermissionsButton">
        <button className="buttonPermission">Guardar</button>
        <button className="buttonPermission">Cancelar</button>
      </div>
    </div>
  );
};

export default AdminPermissions;
