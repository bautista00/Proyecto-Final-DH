// import { Form } from "antd";
import { Form } from "antd";
import styled from "styled-components";

export const ProductFormContainer = styled.div`
  display: flex;
  height: 100vh;
  margin: 20px;
`;

export const ProductFormInputContainer = styled.div`
  display: flex;
  flex-direction: row;
  gap: 20px;
`;

export const CustomForm = styled.form`
  width: 100%;
  display: flex;
  flex-direction: column;
  max-width: 100% !important;
`;

export const CustomFormItem = styled(Form.Item)`
  width: 50%;
  &.ant-form-item {
    & > .ant-form-item-row {
      display: flex;
      flex-direction: column;
      & > div.ant-form-item-label {
        display: flex;
        max-width: 100%;
      }
    }
  }
`;
