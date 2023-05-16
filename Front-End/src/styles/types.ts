import { ThemeConfig } from "antd/es/config-provider/context";

type Breakpoint = {
  min: string;
  max: string;
};

export type Colors = {
  white?: string;
  primary?: string;
  secondary?: string;
  footer?: string;
};

export type Breakpoints = {
  xs?: Breakpoint;
  sm?: Breakpoint;
  md?: Breakpoint;
  lg?: Breakpoint;
  xl?: Breakpoint;
  xxl?: Breakpoint;
};

export interface CustomThemeConfig extends ThemeConfig {
  custom?: {
    colors?: Colors;
    breakpoints?: Breakpoints;
  };
}
