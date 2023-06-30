import { useState } from "react";
import { Link } from "react-router-dom";

const Avatar = ({ name, image }) => {
  //const userId = localStorage.getItem("userId");

  const [imageloaded, setImageloaded] = useState(false);

  const initials = name
    ?.split(" ")
    .map((word) => word.charAt(0))
    .join("")
    .toUpperCase();

  return (
    <div className="avatar">
      {!imageloaded && (
          <span>{initials}</span>
      )}
      {imageloaded && (
        
          <img
            src={image}
            alt="Avatar"
            onLoad={() => setImageloaded(true)}
            onError={() => setImageloaded(false)}
            className="avatar-img"
          />
        
      )}
    </div>
  );
};

export default Avatar;
