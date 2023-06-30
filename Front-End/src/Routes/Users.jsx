import { Button, Select, Space, Table, Form, Input } from "antd";
import { useEffect, useState } from "react";
import { axiosInstance } from "../config";

const Users = () => {
  const [users, setUsers] = useState([]);

  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      render: (text) => <a>{text}</a>,
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
    },

    {
      title: "Permisos",
      key: "permisos",
      dataIndex: "permisos",
      render: (_, { tags }) => (
        <>
          <a href="/UsersPermissions">
            <Button>Permisos</Button>
          </a>
        </>
      ),
    },
    {
      title: "Rol",
      key: "rol",
      render: (_, record) => {
        const handleDelete = async (id) => {
          try {
            const response = await axiosInstance.delete(`/admin/deleteClient`);
            console.log("Response:", response.data);
            fetchData();
          } catch (e) {
            console.log(e);
          }
        };

        return (
          <Space size="middle">
            <Select
              placeholder="Seleccione un rol"
              options={[
                {
                  value: "jack",
                  label: "Administrador",
                },
                {
                  value: "lucy",
                  label: "Arrendador",
                },
                {
                  value: "tom",
                  label: "Usuario",
                },
              ]}
            />
            <Button onClick={() => handleDelete(record.id)}>Eliminar</Button>
          </Space>
        );
      },
    },
  ];

  const handleSubmit = async (e) => {
    try {
      const requestData = {
        rol: e.rol,
      };

      // falta el endpoint del rol
      const response = await axiosInstance.post("", requestData);
      console.log("Response:", response.data);
      form.resetFields();
      fetchData();
    } catch (e) {
      console.log(e);
    }
  };

  const fetchData = async () => {
    const result = await axiosInstance.get("/admin/getallusers");
    setUsers(result.data);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const [form] = Form.useForm();
  return (
    <>
      <h1 className="tituloRol">Crear Rol</h1>

      <Form
        className="roleForm"
        form={form}
        layout="vertical"
        autoComplete="on"
        onFinish={handleSubmit}
      >
        <Form.Item className="labelRol" name="nombre" label="Nombre del rol">
          <Input className="inputRol" name="rol" />
        </Form.Item>

        <Form.Item>
          <div>
            <Button className="buttonRol" htmlType="submit">
              Agregar
            </Button>
          </div>
        </Form.Item>
      </Form>

      <Table
        className="tableRol"
        rowKey={(record) => record.id}
        columns={columns}
        dataSource={users}
      />
      <div className="usersButton">
        <Button className="buttonRol">Guardar</Button>
        <Button className="buttonRolRight">Cancelar</Button>
      </div>
    </>
  );
};

export default Users;
