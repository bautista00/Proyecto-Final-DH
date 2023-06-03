import { PlusOutlined } from "@ant-design/icons";
import {
  Button,
  Cascader,
  DatePicker,
  Form,
  Input,
  InputNumber,
  Select,
  Switch,
  TreeSelect,
  Typography,
  Upload,
  notification,
} from "antd";
import React from "react";
import { useForm } from "react-hook-form";
import Banner from "../Components/Banner";

const CreateProduct = () => {
  const [api, contextHolder] = notification.useNotification();
  const {
    register,
    setValue,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const { RangePicker } = DatePicker;
  const { TextArea } = Input;

  const normFile = (e) => {
    if (Array.isArray(e)) {
      return e;
    }
    return e?.fileList;
  };

  const openNotification = (placement, type) => {
    if (type === "error") {
      api.error({
        message: `Producto existente`,
        description: "Ya existe un producto con ese nombre.",
        placement,
      });
    } else {
      api.info({
        message: `Producto registrado`,
        description: "Su producto fue registrado correctamente.",
        placement,
      });
    }
  };

  const onSubmit = (values) => {
    fetch("../data/data.json")
      .then((response) => response.json())
      .then((data) => {
        const existingProduct = data.find(
          (product) => product.name === values.name
        );

        if (!existingProduct) {
          openNotification("top");
          return;
        }

        openNotification("top", "error");
      });
  };

  return (
    <div className="product-form-container">
      <Banner title={"Agregar Producto"} />
      {contextHolder}
      <div className="form-container">
        <Form className="custom-form" onSubmit={handleSubmit(onSubmit)}>
          <div className="product-form-input-container">
            <Form.Item className="custom-form-item" label="Nombre o Empresa">
              <Input
                status={errors.name ? "error" : ""}
                {...register("name", {
                  required: {
                    value: true,
                    message: "Por favor ingrese el nombre o empresa",
                  },
                  onChange: (e) => setValue("name", e.target.value),
                })}
              />
              {errors.name && (
                <Typography.Text type="danger">
                  {errors.name?.message}
                </Typography.Text>
              )}
            </Form.Item>
            <Form.Item className="custom-form-item" label="Categoria:">
              <Select>
                <Select.Option className="inputCreate" value="Futbol">Fútbol</Select.Option>
                <Select.Option value="Tenis">Tenis</Select.Option>
                <Select.Option value="Baquetbol">Basquetbol</Select.Option>
                <Select.Option value="Voleibol">Voleibol</Select.Option>
              </Select>
            </Form.Item>
          </div>

          <div className="product-form-input-container">
            <Form.Item className="custom-form-item" label="Precios:">
              <TreeSelect
                treeData={[
                  {
                    title: "4 horas",
                    value: "4 horas",
                    children: [{ title: "$60.000", value: "$60.000" }],
                  },
                  {
                    title: "6 horas",
                    value: "6 horas",
                    children: [{ title: "$80.000", value: "$80.000" }],
                  },
                ]}
              />
            </Form.Item>
            <Form.Item className="custom-form-item" label="Dirección: ">
              <Cascader
                options={[
                  {
                    value: "Colombia",
                    label: "Colombia",
                    children: [
                      {
                        value: "Cali",
                        label: "Cali",
                      },
                    ],
                  },
                  {
                    value: "Argentina",
                    label: "Argentina",
                    children: [
                      {
                        value: "Buenos Aires",
                        label: " Buenos Aires",
                      },
                    ],
                  },
                ]}
              />
            </Form.Item>
          </div>
          <div className="product-form-input-container">
            <Form.Item className="custom-form-item" label="Disponibilidad:">
              <RangePicker />
            </Form.Item>
            <Form.Item
              className="custom-form-item"
              label="Cantidad de jugadores:"
            >
              <InputNumber />
            </Form.Item>
          </div>

          <div className="product-form-input-container">
            <Form.Item className="custom-form-item" label="Descripción">
              <TextArea placeholder="Descripción" />
            </Form.Item>
          </div>

          <div className="product-form-input-container">
            <Form.Item
              className="custom-form-item"
              label="Estado"
              valuePropName="checked"
              style={{fontWeight:"bold"}}
            >
              Activo
              <Switch />
            </Form.Item>
          </div>

          <div className="product-form-input-container">
            <Upload action="/upload.do" listType="picture-card">
              <div>
                <PlusOutlined />
                <div style={{ marginTop: 4 }}>Subir Imagen</div>
              </div>
            </Upload>
          </div>

          <Form.Item className="custom-form-buttons">
            <Button className="buttonAgregar" htmlType="submit">Agregar producto</Button>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
};

export default () => <CreateProduct />;
