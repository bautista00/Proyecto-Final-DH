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
  Upload,
} from "antd";
import React from "react";
import {
  CustomForm,
  CustomFormItem,
  ProductFormContainer,
  ProductFormInputContainer,
} from "./styled";

const { RangePicker } = DatePicker;
const { TextArea } = Input;

const normFile = (e: any) => {
  if (Array.isArray(e)) {
    return e;
  }
  return e?.fileList;
};

const ProductForm: React.FC = () => {
  return (
    <ProductFormContainer>
      <CustomForm
        layout="vertical"
        labelCol={{ span: 4 }}
        wrapperCol={{ span: 14 }}
        style={{ maxWidth: 600 }}
      >
        <ProductFormInputContainer>
          <CustomFormItem label="Nombre o Empresa">
            <Input />
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
          <Button>Confirmar</Button>
          <Button>Cancelar</Button>
        </CustomFormItem>
      </CustomForm>
    </ProductFormContainer>
  );
};

export default () => <ProductForm />;
