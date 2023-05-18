import { Typography } from "antd";
import { styled } from "styled-components";

export const LocationHeaderContainer = styled.div`
  display: flex;
  padding: 14px;
  flex-direction: column;
  background-color: #52a479;
  color: white;
`;

export const LocationHeaderText = styled(Typography.Text)`
  display: flex;
  color: white;
  gap: 10px;
`;
