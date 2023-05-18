import { LeftOutlined } from "@ant-design/icons";
import { BackButtonContainer } from "./styled";
import { BackButtonContainerProps } from "./types";

const Button = ({ href }: BackButtonContainerProps) => {
  return (
    <BackButtonContainer href={href}>
      <LeftOutlined />
    </BackButtonContainer>
  );
};
export default Button;
