import React from "react";
import { Rate } from "antd";

const CommentCard = ({userName, rating, comment}) => {
  return (
    <div id="testimonialBox">
        <div className="boxTop">
            <div className="profileComment">
            <strong>{userName}</strong> <span>@{userName}</span>
            </div>
            <Rate disabled defaultValue={rating} style={{ color: "#fadb14", fontSize: 20 }}/>
        </div>
      <div className="clientComment">
        <p>{comment}</p>
      </div>
    </div>
  );
};

export default CommentCard;
