import ButtonContainer from "./styled";
import { ButtonProps } from "./type";

const Button = ({ title }: ButtonProps) => {
  return <ButtonContainer>{title}</ButtonContainer>;
};

export default Button;
