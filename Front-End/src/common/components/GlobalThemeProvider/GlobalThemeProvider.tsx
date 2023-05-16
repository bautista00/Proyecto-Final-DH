import React, { FC } from "react";
import { ConfigProvider } from "antd";
import { ThemeProvider } from "styled-components";
import { GlobalThemeProviderProps } from "./types";

export const GlobalThemeProvider: FC<GlobalThemeProviderProps> = ({
  theme,
  children,
}) => {
  return (
    <ThemeProvider theme={theme}>
      <ConfigProvider theme={theme}>{children}</ConfigProvider>
    </ThemeProvider>
  );
};

export default GlobalThemeProvider;
