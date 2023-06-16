import React, { useState } from "react";
import { Button, Input, Rate } from "antd";
import { useContextGlobal } from "./utils/GlobalContext";
import { useParams } from "react-router-dom";
import CommentCard from "./CommentCard";
const { TextArea } = Input;

const CommentInput = () => {
  const { id } = useParams();
  const { data } = useContextGlobal();

  const [canComment, setCanComment] = useState(true);

  const [showBtn, setshowBtn] = useState(true);

  const handleSetButton = () => {
    setshowBtn(false);
  };

  const [valoraciones, setValoraciones] = useState([{userName: "Aldo", rating: 5, comment: "Genial la cancha, muy bien cuidado y una atención excelente, Saludos."}])
  const desc = ["Pesimo", "Malo", "Normal", "Bueno", "Excelente"];
  const [value, setValue] = useState(3);
  const [comment, setComment] = useState("")

  localStorage.setItem("valoraciones", JSON.stringify(valoraciones))

  const saveValoration = () =>{
    const valoracion = {
      userName: "Seba",
      rating: value,
      comment: comment
  }
    setValoraciones([...valoraciones, valoracion])
    alert("Se ha guardado tu valoración correctamente")
  }

  console.log(valoraciones)
  
  const onChange = (e) => {
    setComment(e.target.value);
  }
  
  const renderizarValoraciones = () => {
    return valoraciones.map((valoracion, index) => (
      <CommentCard
        key={index}
        userName={valoracion.userName}
        rating={valoracion.rating}
        comment={valoracion.comment}
      />
    ));
  }
  // Calcular el promedio del atributo "rating"
  const ratings = valoraciones.map(valoracion => valoracion.rating);
  const promedio = ratings.reduce((total, rating) => total + rating, 0) / ratings.length;
  return (
    <>
      <div>{renderizarValoraciones()}</div>
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
              <span className="ratingSpan">
                <Rate tooltips={desc} onChange={setValue} value={value} />
                 {value ? <span className="ant-rate-text">{desc[value - 1]}</span> : ""}
              </span>
              <TextArea
                showCount
                maxLength={300}
                style={{
                  height: 120,
                  resize: "none",
                  margin: "1rem",
                }}
                onChange={onChange}
                placeholder="Contanos tu opinión acá ..."
              />
              <span><Button
                onClick={saveValoration}
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
