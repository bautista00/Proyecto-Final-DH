import React, { useState } from "react";
import { Rate } from "antd";

const StarRating = () => {
  const desc = ["Pésimo", "Malo", "Normal", "Bueno", "Excelente"];

  const [value, setValue] = useState(5);
  return (
    <span className="ratingSpan">
      <Rate tooltips={desc} onChange={setValue} value={value} />
      {value ? <span className="ant-rate-text">{desc[value - 1]}</span> : ""}
    </span>
  );
};

export default StarRating;
