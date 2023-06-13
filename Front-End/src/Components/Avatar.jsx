import { useState } from "react";
import { Link } from "react-router-dom";

const Avatar = ({ name, image }) => {
  const userId = localStorage.getItem("userId");

  const [imageloaded, setImageloaded] = useState(false);

  const initials = name
    ?.split(" ")
    .map((word) => word.charAt(0))
    .join("")
    .toUpperCase();

  return (
    <div className="avatar">
      {!imageloaded && (
        <Link to={`/Account/${userId}`}>
          <span>{initials}</span>
        </Link>
      )}
      {imageloaded && (
        <Link to={`/Account/${userId}`}>
          <img
            src={image}
            alt="Avatar"
            onLoad={() => setImageloaded(true)}
            onError={() => setImageloaded(false)}
            className="avatar-img"
          />
        </Link>
      )}
    </div>
  );
};

export default Avatar;
