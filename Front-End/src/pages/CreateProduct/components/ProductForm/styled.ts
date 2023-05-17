// import { Form } from "antd";
import { Form } from "antd";
import styled from "styled-components";

export const ProductFormContainer = styled.div`
  display: flex;
  height: 100vh;
`;

export const ProductFormInputContainer = styled.div`
  display: flex;
  flex-direction: row;
`;

export const CustomForm = styled(Form)`
  width: 100%;
  display: flex;
  flex-direction: column;
  max-width: 100% !important;
`;

export const CustomFormItem = styled(Form.Item)`
  width: 50%;
  &.ant-form-item {
    & > .ant-form-item-row {
      & > div.ant-form-item-label {
        max-width: 100%;
      }
    }
  }
`;
