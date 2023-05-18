import { LogoContainer, LogoImage, LogoText } from "./styled";
import { LogoContainerProps } from "./types";

const Logo = ({ href }: LogoContainerProps) => {
  return (
    <LogoContainer href={href}>
      <LogoImage src="../images/logoPi.png" alt="logo" />
      <LogoText>Tu cancha a un click</LogoText>
    </LogoContainer>
  );
};

export default Logo;
