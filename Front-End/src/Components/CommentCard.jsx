import React from "react";
import { Rate } from "antd";

const CommentCard = () => {
  return (
    <div className="testimonialBox">
        <div className="boxTop">
            <div className="profileComment">
            <strong>Sebastián</strong> <span>@SebaM</span>
            </div>
            <Rate disabled defaultValue={4} style={{ color: "#fadb14", fontSize: 20 }}/>
        </div>
      <div className="clientComment">
        <p>Muy buenas canchas, excelente servicio. Falta alguna que otra luz de noche pero todo bien en general. Volveríamos a jugar con mis amigos sin dudas. Que el dueño se la juegue un poco y nos invite un asadito, abrazo.</p>
      </div>
    </div>
  );
};

export default CommentCard;
