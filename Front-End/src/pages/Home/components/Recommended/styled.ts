import styled from "styled-components";
import { Typography } from "antd";

export const RecommendedContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

export const RecommendedTitle = styled(Typography.Title)`
  color: #468866 !important;
  display: flex;
  flex-direction: row;
`;

export const RecommendedCardsContainer = styled.div`
  /* display: flex;
  flex-direction: row;
  gap: 10px;
  align-self: center; */
  display: grid;
  grid-gap: 25px;
  padding: 0px 25px;
  justify-items: center;
  grid-template-rows: auto;
  grid-template-columns: repeat(auto-fill, minmax(min(100%, 300px), 1fr));
`;

export const RecommendedCardImage = styled.img`
  width: 300px;
  height: 150px;
`;
