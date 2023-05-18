import { HeartOutlined, ShareAltOutlined } from "@ant-design/icons";
import { Button } from "antd";
import { ControlsContainer } from "./styled";

const Controls = () => {
  return (
    <ControlsContainer className="controls">
      <Button icon={<ShareAltOutlined />}></Button>
      <Button icon={<HeartOutlined />}></Button>
    </ControlsContainer>
  );
};

export default Controls;
