import React from "react";
import { Avatar, Card } from "antd";
import {
  FieldTypeSearchCardImage,
  FieldTypeSearchCardsContainer,
  FieldTypeSearchContainer,
  FieldTypeSearchTitle,
} from "./styled";

const { Meta } = Card;

const FieldTypeSearch: React.FC = () => (
  <FieldTypeSearchContainer>
    <FieldTypeSearchTitle>Buscar por tipo de cancha</FieldTypeSearchTitle>
    <FieldTypeSearchCardsContainer>
      <Card
        hoverable
        style={{ width: 300 }}
        cover={
          <FieldTypeSearchCardImage
            src="images/futbolFieldSearch1.jpg"
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
          <FieldTypeSearchCardImage
            src="images/tenisFieldSearch1.jpg"
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
          <FieldTypeSearchCardImage
            src="images/basquetbolFieldSearch1.png"
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
          <FieldTypeSearchCardImage
            src="images/voleibolFieldSearch1.png"
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
    </FieldTypeSearchCardsContainer>
  </FieldTypeSearchContainer>
);

export default FieldTypeSearch;
