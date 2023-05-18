import { PlusOutlined } from "@ant-design/icons";
import {
  Button,
  Cascader,
  DatePicker,
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
import {
  CustomForm,
  CustomFormItem,
  ProductFormContainer,
  ProductFormInputContainer,
} from "./styled";
import { NotificationPlacement } from "antd/es/notification/interface";
import { useForm } from "react-hook-form";

interface Inputs {
  name: string;
  price: string;
  location: string;
  kindOfSport: string;
}

const ProductForm: React.FC = () => {
  const [api, contextHolder] = notification.useNotification();
  const {
    register,
    setValue,
    handleSubmit,
    formState: { errors },
  } = useForm<Inputs>();

  const { RangePicker } = DatePicker;
  const { TextArea } = Input;

  const normFile = (e: any) => {
    if (Array.isArray(e)) {
      return e;
    }
    return e?.fileList;
  };

  const openNotification = (
    placement: NotificationPlacement,
    type: "error" | "success" = "success"
  ) => {
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

  const onSubmit = (values: Inputs) => {
    fetch("../data/data.json")
      .then((response) => response.json())
      .then((data) => {
        const existingProduct = data.find(
          (product: any) => product.name === values.name
        );

        if (!existingProduct) {
          openNotification("top");
          return;
        }

        openNotification("top", "error");
      });
  };

  return (
    <ProductFormContainer>
      {contextHolder}
      <CustomForm onSubmit={handleSubmit(onSubmit)}>
        <ProductFormInputContainer>
          <CustomFormItem label="Nombre o Empresa">
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
          </CustomFormItem>
          <CustomFormItem label="Categoria:">
            <Select>
              <Select.Option value="Futbol">Futbol</Select.Option>
              <Select.Option value="Tenis">Tenis</Select.Option>
              <Select.Option value="Baquetbol">Basquetbol</Select.Option>
              <Select.Option value="Voleibol">Voleibol</Select.Option>
            </Select>
          </CustomFormItem>
        </ProductFormInputContainer>

        <ProductFormInputContainer>
          <CustomFormItem label="Precios:">
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
          </CustomFormItem>
          <CustomFormItem label="Direccion: ">
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
          </CustomFormItem>
        </ProductFormInputContainer>
        <ProductFormInputContainer>
          <CustomFormItem label="Disponibilidad:">
            <RangePicker />
          </CustomFormItem>
          <CustomFormItem label="Cantidad de jugadores:">
            <InputNumber />
          </CustomFormItem>
        </ProductFormInputContainer>
        <CustomFormItem label="Descripcion">
          <TextArea rows={4} />
        </CustomFormItem>
        <CustomFormItem label="Estado" valuePropName="checked">
          Activo
          <Switch />
        </CustomFormItem>

        <CustomFormItem
          label="Cargar Imagenes"
          valuePropName="fileList"
          getValueFromEvent={normFile}
        >
          <Upload action="/upload.do" listType="picture-card">
            <div>
              <PlusOutlined />
              <div style={{ marginTop: 8 }}>Upload</div>
            </div>
          </Upload>
        </CustomFormItem>

        <CustomFormItem>
          <Button htmlType="submit">Confirmar</Button>
          <Button>Cancelar</Button>
        </CustomFormItem>
      </CustomForm>
    </ProductFormContainer>
  );
};

export default () => <ProductForm />;
