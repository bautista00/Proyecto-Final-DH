import styled from "styled-components";
import { Typography } from "antd";

export const FieldTypeSearchContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

export const FieldTypeSearchTitle = styled(Typography.Title)`
  color: #468866 !important;
  display: flex;
  flex-direction: row;
`;

export const FieldTypeSearchCardsContainer = styled.div`
  /* gap: 10px;
  display: flex;
  align-self: center;
  flex-direction: row; */
  display: grid;
  grid-gap: 25px;
  padding: 0px 25px;
  justify-items: center;
  grid-template-rows: auto;
  grid-template-columns: repeat(auto-fill, minmax(min(100%, 300px), 1fr));
`;

export const FieldTypeSearchCardImage = styled.img`
  width: 300px;
  height: 150px;
`;
