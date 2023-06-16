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
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import Banner from "../Components/Banner";
import { axiosInstance } from "../config";

const CreateProduct = () => {
  const [createProduct, setCreateProduct] = useState([]);
  const [api, contextHolder] = notification.useNotification();

  const {
    register,
    setValue,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const [barrios, setBarrios] = useState([]);
  const [provincias, setProvincias] = useState([]);
  const [categorias, setCategorias] = useState([]);

  const fetchBarriosData = async () => {
    const result = await axiosInstance.get("/listallbarrios");
    setBarrios(result.data);
  };

  const fetchCategoriasData = async () => {
    const result = await axiosInstance.get("/findAllCategoria/");
    setCategorias(result.data);
  };

  useEffect(() => {
    fetchBarriosData();
    fetchCategoriasData();
  }, []);

  const onChange = (value) => {
    console.log("changed", value);
    setValue("precio", value);
  };

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

  // const onSubmit = (values) => {
  //   const result = await axiosInstance.get("/admin/addcancha/")
  //     .then((response) => response.json())
  //     .then((data) => {
  //       const existingProduct = data.find(
  //         (product) => product.name === values.name
  //       );

  //       if (!existingProduct) {
  //         openNotification("top");
  //         return;
  //       }

  //       openNotification("top", "error");
  //     });
  // };

  const onSubmit = async (e) => {
    console.log(e);
    /*try {
      const requestData = {
        canchaDTO: {
          categoria: {
            nombre: e.name
          }
        }
        // pedir los campos que se necesitan
        //addcancha: e.addcancha, // este no va
      };
      const response = await axiosInstance.post("/admin/addcancha", requestData);
      console.log("Response:", response.data);
      form.resetFields();
      fetchData();
    } catch (e) {
      console.log(e);
    }*/
  };

  return (
    <div className="product-form-container">
      <Banner title={"Agregar Producto"} />
      {contextHolder}
      <div className="form-container">
        <Form className="custom-form" onFinish={handleSubmit(onSubmit)}>
          <div className="product-form-input-container">
            <Form.Item className="custom-form-item" label="Nombre o Empresa">
              <Input
                status={errors.name ? "error" : ""}
                {...register("nombre", {
                  required: {
                    value: true,
                    message: "Por favor ingrese el nombre o empresa",
                  },
                  onChange: (e) => setValue("nombre", e.target.value),
                })}
              />
              {errors.name && (
                <Typography.Text type="danger">
                  {errors.name?.message}
                </Typography.Text>
              )}
            </Form.Item>
            <Form.Item className="custom-form-item" label="Categoria:">
              <Select onChange={(value) => setValue("categoria.nombre", value)}>
                {categorias.map((categoria) => (
                  <Select.Option key={categoria.id} value={categoria.nombre}>
                    {categoria.nombre}
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>
          </div>

          <div className="product-form-input-container">
            <Form.Item className="custom-form-item" label="Precios:">
              <InputNumber
                defaultValue={0}
                formatter={(value) =>
                  `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ",")
                }
                parser={(value) => value.replace(/\$\s?|(,*)/g, "")}
                onChange={onChange}
              />
            </Form.Item>
            <Form.Item className="custom-form-item" label="Barrio: ">
              <Select onChange={(value) => setValue("domicilio.barrio", value)}>
                {barrios.map((barrio) => (
                  <Select.Option key={barrio.id} value={barrio.nombre}>
                    {barrio.nombre}
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>

            <Form.Item className="custom-form-item" label="Provincia: ">
              <Select
                onChange={(value) => setValue("domicilio.provincia", value)}
              >
                {barrios.map((barrio) => (
                  <Select.Option key={barrio.id} value={barrio.nombre}>
                    {barrio.nombre}
                  </Select.Option>
                ))}
              </Select>
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
              <TextArea
                placeholder="Descripción"
                {...register("categoria.descripcion", {
                  required: {
                    value: true,
                    message: "Por favor ingrese el nombre o empresa",
                  },
                  onChange: (e) =>
                    setValue("categoria.descripcion", e.target.value),
                })}
              />
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
            <Button className="buttonAgregar" htmlType="submit">
              Agregar producto
            </Button>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
};

export default () => <CreateProduct />;
