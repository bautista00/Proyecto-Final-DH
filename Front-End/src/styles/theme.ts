import { Breakpoints, Colors, CustomThemeConfig } from "./types";

const colors: Colors = {
  white: "#FFFFFF",
  footer: "#091D47",
  primary: "#3E79F7",
  secondary: "#FE7062",
};

const breakpoints: Breakpoints = {
  xs: {
    min: "0px",
    max: "575px",
  },
  sm: {
    min: "576px",
    max: "767px",
  },
  md: {
    min: "768px",
    max: "991px",
  },
  lg: {
    min: "992px",
    max: "1199px",
  },
  xl: {
    min: "1200px",
    max: "1599px",
  },
  xxl: {
    min: "1600px",
    max: "10000px",
  },
};

export const theme: CustomThemeConfig = {
  custom: {
    colors,
    breakpoints,
  },
};
