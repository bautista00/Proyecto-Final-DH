import React, { useState } from "react";
import { Button, Input } from "antd";
import StarRating from "./StarRating";
import { useContextGlobal } from "./utils/GlobalContext";
import { useParams } from "react-router-dom";
const { TextArea } = Input;

const CommentInput = () => {
  const { id } = useParams();
  const { data } = useContextGlobal();

  const [canComment, setCanComment] = useState(true);

  const [showBtn, setshowBtn] = useState(true);

  const handleSetButton = () => {
    setshowBtn(false);
  };

  return (
    <>
      {canComment ? (
        <>
          {showBtn ? (
            <Button
              onClick={handleSetButton}
              type="text"
              style={{
                backgroundColor: "#5d994b",
                color: "white",
                fontWeight: 500,
              }}
            >
              Agregar un comentario
            </Button>
          ) : (
            <section className="commentInputSection">
              <p>
                Que puntaje le darías a tu experiencia con "{data[id].name}"?
              </p>
              <StarRating />
              <TextArea
                showCount
                maxLength={300}
                style={{
                  height: 120,
                  resize: "none",
                  margin: "1rem",
                }}
                /*onChange={onChange}*/
                placeholder="Contanos tu opinión acá ..."
              />
              <span><Button
                onClick={handleSetButton}
                type="text"
                style={{
                  backgroundColor: "#5d994b",
                  color: "white",
                  fontWeight: 500,
                }}
              >
                Guardar
              </Button></span>
              
            </section>
          )}
        </>
      ) : (
        <p>Ya comentaste esta publicación</p>
      )}
    </>
  );
};

export default CommentInput;
