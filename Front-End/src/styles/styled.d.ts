import "styled-components";
import { theme } from "./theme";

type ThemeInterface = typeof theme;

// and extend them!
declare module "styled-components" {
  export interface DefaultTheme extends ThemeInterface {}
}
