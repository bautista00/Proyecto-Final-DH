import { Button, Form, Input, Space } from "antd";
import React from "react";
import { Table, Tag } from "antd";

const Cities = () => {
  const [form] = Form.useForm();

  const columns = [
    {
      title: "Nombre",
      dataIndex: "name",
      key: "name",
      render: (text) => <a>{text}</a>,
    },

    {
      title: "",
      key: "",
      render: (_, record) => (
        <Space size="middle">
          <a>Editar</a>
          <a>Eliminar</a>
        </Space>
      ),
    },
  ];
  const data = [
    {
      key: "1",
      name: "Colombia",
      address: "New York No. 1 Lake Park",
    },
    {
      key: "2",
      name: "Argentina",
      address: "London No. 1 Lake Park",
    },
  ];

  return (
    <>
      <h1>Crear ciudad</h1>

      <Form
        className="citiesForm"
        form={form}
        layout="vertical"
        autoComplete="on"
      >
        <Form.Item name="nombre" label="Nombre de ciudad">
          <Input />
        </Form.Item>

        <Form.Item>
          <div>
            <Button>Agregar</Button>
          </div>
        </Form.Item>
      </Form>
      <Table columns={columns} dataSource={data} />
      <div>
        <Button>Guardar</Button>
        <Button>Cancelar</Button>
      </div>
    </>
  );
};
export default Cities;
