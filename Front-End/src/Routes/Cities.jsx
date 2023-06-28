import { Button, Form, Input, Space } from "antd";
import React, { useEffect, useState } from "react";
import { Table } from "antd";
import { axiosInstance } from "../config";

const Cities = () => {
  const [form] = Form.useForm();
  const [cities, setCities] = useState([]);

  const fetchData = async () => {
    const result = await axiosInstance.get("/listallbarrios");
    setCities(result.data);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const columns = [
    {
      key: "nombre",
      title: "Nombre",
      dataIndex: "nombre",
      render: (text) => <a>{text}</a>,
    },

    {
      title: "",
      key: "",
      render: (_, record) => {
        const handleDelete = async (id) => {
          try {
            const response = await axiosInstance.delete(
              `/admin/deletebarrio/${id}`
            );
            console.log("Response:", response.data);
            fetchData();
          } catch (e) {
            console.log(e);
          }
        };

        return (
          <Space size="middle">
            <Button onClick={() => handleDelete(record.id)}>Eliminar</Button>
          </Space>
        );
      },
    },
  ];

  const handleSubmit = async (e) => {
    try {
      const requestData = {
        nombre: e.nombre,
      };
      const response = await axiosInstance.post(
        "/admin/addbarrios",
        requestData
      );
      console.log("Response:", response.data);
      form.resetFields();
      fetchData();
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <>
      <h1 className="tituloCity">Crear barrio</h1>

      <Form
        form={form}
        layout="vertical"
        autoComplete="on"
        className="citiesForm"
        onFinish={handleSubmit}
      >
        <Form.Item className="labelCity" name="nombre" label="Nombre de barrio">
          <Input className="inputRol" name="nombre" />
        </Form.Item>

        <Form.Item>
          <div>
            <Button className="buttonCity" htmlType="submit">
              Agregar
            </Button>
          </div>
        </Form.Item>
      </Form>
      <Table
        className="tableCity"
        rowKey={(record) => record.id}
        columns={columns}
        dataSource={cities}
      />
      <div className="usersButton">
        <Button className="buttonCity">Guardar</Button>
        <Button className="buttonCityRight">Cancelar</Button>
      </div>
    </>
  );
};
export default Cities;
