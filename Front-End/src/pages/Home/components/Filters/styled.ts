import styled from "styled-components";
import { Typography } from "antd";

export const FilterContainer = styled.div`
  background-color: #468866;
  justify-content: center;
  display: flex;
  flex-direction: column;
`;
export const FiltersButtonsContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
  gap: 10px;

  ${({ theme }) =>
    theme.custom?.breakpoints?.lg &&
    `@media (max-width: ${theme.custom?.breakpoints?.lg?.min})`} {
    flex-direction: column;
    align-items: center;
  }
`;

export const FilterTitle = styled(Typography.Title)`
  flex-direction: column;
  display: flex;
  color: white !important;
  text-align: center;
  margin: 0px !important;
  margin-bottom: 10px !important;
`;
