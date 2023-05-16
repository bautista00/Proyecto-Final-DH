import { ReactNode } from "react";
import { DefaultTheme } from "styled-components";

export interface GlobalThemeProviderProps {
  theme: DefaultTheme;
  children: ReactNode;
}
