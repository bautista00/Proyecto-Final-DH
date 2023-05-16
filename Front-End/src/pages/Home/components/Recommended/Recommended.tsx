import React from "react";
import { Avatar, Card } from "antd";
import {
  RecommendedCardImage,
  RecommendedCardsContainer,
  RecommendedContainer,
  RecommendedTitle,
} from "./styled";

const { Meta } = Card;

const Recommended: React.FC = () => (
  <RecommendedContainer>
    <RecommendedTitle>Recomendaciones</RecommendedTitle>
    <RecommendedCardsContainer>
      <Card
        hoverable
        style={{ width: 300 }}
        cover={
          <RecommendedCardImage
            src="images/futbolRecommended.jpg"
            alt="Futbol"
          />
        }
      >
        <Meta
          avatar={
            <Avatar src="https://xsgames.co/randomusers/avatar.php?g=pixel" />
          }
          title="Canchas Sarmiento"
          description="Cancha de futbol "
        />
      </Card>

      <Card
        hoverable
        style={{ width: 300 }}
        cover={
          <RecommendedCardImage
            src="images/tenisRecommended.jpg"
            alt="Tenis "
          />
        }
      >
        <Meta
          avatar={
            <Avatar src="https://xsgames.co/randomusers/avatar.php?g=pixel" />
          }
          title="Canchas Montessori"
          description="Cancha de tenis "
        />
      </Card>

      <Card
        hoverable
        style={{ width: 300 }}
        cover={
          <RecommendedCardImage
            src="images/basquetbolRecommended.jpg"
            alt="Basquetbol "
          />
        }
      >
        <Meta
          avatar={
            <Avatar src="https://xsgames.co/randomusers/avatar.php?g=pixel" />
          }
          title="Canchas Quality Sport"
          description="Cancha de basquetbol "
        />
      </Card>

      <Card
        hoverable
        style={{ width: 300 }}
        cover={
          <RecommendedCardImage
            src="images/voleibolRecommended.jpg"
            alt="Voleibol "
          />
        }
      >
        <Meta
          avatar={
            <Avatar src="https://xsgames.co/randomusers/avatar.php?g=pixel" />
          }
          title="Canchas Panamericanas"
          description="Cancha de voleibol"
        />
      </Card>
    </RecommendedCardsContainer>
  </RecommendedContainer>
);

export default Recommended;
