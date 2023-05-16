import styled from "styled-components";

export const HeaderContainer = styled.header`
  background-color: #468866;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  position: fixed;
  width: 100%;
  top: 0;
  padding: 0 5px 0px 0px;
  height: 70px;
  z-index: 16;
`;

export const ButtonsContainer = styled.div`
  display: flex;
  gap: 10px;
`;
