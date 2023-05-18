import React, { useEffect, useState } from "react";
import { Avatar, Card } from "antd";
import {
  RecommendedCardImage,
  RecommendedCardsContainer,
  RecommendedContainer,
  RecommendedTitle,
} from "./styled";
import { Link } from "react-router-dom";

const { Meta } = Card;

const Recommended: React.FC = () => {
  const [cardData, setCardData] = useState([]);

  useEffect(() => {
    fetch("data/data.json")
      .then((response) => response.json())
      .then((data) => setCardData(data));
  }, []);

  const getRandomCards = (count: number) => {
    const shuffledData = [...cardData].sort(() => Math.random() - 0.5);
    return shuffledData.slice(0, count);
  };

  return (
    <RecommendedContainer>
      <RecommendedTitle>Recomendaciones</RecommendedTitle>
      <RecommendedCardsContainer>
        {getRandomCards(4).map((card: any) => (
          <Link to={`/Detail/${card.id}`}>
            <Card
              hoverable
              key={card.id}
              style={{ width: 300 }}
              cover={<RecommendedCardImage src={card.image} alt="Futbol" />}
            >
              <Meta
                avatar={
                  <Avatar src="https://xsgames.co/randomusers/avatar.php?g=pixel" />
                }
                title={card.name}
                description={card.description}
              />
            </Card>
          </Link>
        ))}
      </RecommendedCardsContainer>
    </RecommendedContainer>
  );
};

export default Recommended;
