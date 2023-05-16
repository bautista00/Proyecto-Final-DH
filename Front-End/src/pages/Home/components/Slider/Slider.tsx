import React from "react";
import { Carousel } from "antd";
import { SliderContainer, SliderImage } from "./styled";

const Slider: React.FC = () => (
  <SliderContainer>
    <Carousel autoplay>
      <SliderImage src="images/futbolSlider.png" alt="Futbol Slider" />
      <SliderImage src="images/tenisSlider.jpg" alt="Tenis Slider" />
      <SliderImage src="images/basquetbolSlider.jpg" alt="Basquetbol Slider" />
      <SliderImage src="images/voleibolSlider.png" alt="Voleibol Slider" />
    </Carousel>
  </SliderContainer>
);

export default Slider;
