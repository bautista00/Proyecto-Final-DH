import React, { useState } from "react";
import { Rate } from "antd";
import CommentCard from "./CommentCard";
import { useEffect } from "react";
import { axiosInstance } from "../config";


const CommentInput = ({valoraciones, canchaId}) => {

  const [canComment, setCanComment] = useState(true);
  const [showBtn, setshowBtn] = useState(true);
  const [valorations, setValorations] = useState([])
  const [valoracionDTO, setValoracionDTO] = useState({descripcion: "", puntuacion: 5.00})
  const desc = ["Pesimo", "Malo", "Normal", "Bueno", "Excelente"];
  const[commentCreated, setCommentCreated] = useState(false)

  const [loggedUser, setLoggedUser] = useState({});

  useEffect(() => {
    if (localStorage.getItem("auth") === "true") {
      axiosInstance
        .get("/all/getuser", {
          params: {
            token: localStorage.getItem("jwt"),
          },
        })
        .then((response) => {
          setLoggedUser(response.data);
          console.log(response.data);
        });
    }
  }, []);

 useEffect(()=>{
    setValorations(valoraciones)
    
  }, [valoraciones]);

  console.log(valoraciones)

 const saveValoration = () => {
  

  const token = localStorage.getItem("jwt");
  const payload = {
    method: "POST",
    body: JSON.stringify(valoracionDTO),
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json"
    },
  };
  fetch(`http://3.19.232.248:8080/user/agregarValoracion?canchaId=${canchaId}&token=${localStorage.getItem("jwt")}`, payload)
    .then((response) => response.json())
    .then(() => {
      setValoracionDTO({});
      setCommentCreated(true)
    })
    .catch((error) => console.log(error));
};


  const handleSubmit = (e) => {
    e.preventDefault()
    saveValoration()
    setshowBtn(true)
    setCommentCreated(false)
    setTimeout(() => {
      window.location.reload();
    }, 2000);
  }
  
  const handleSetButton = () => {
    const hasCommented = valorations.some(
      (valoracion) => valoracion.userID === loggedUser.id
    );
  
    if (hasCommented) {
      setCanComment(false);
    } else {
      setshowBtn(false);
    }
  };
  
  const renderizarValoraciones = () => {
    if(valorations){
      return valorations.map((valoracion, index) => (
        <CommentCard
          key={index}
          userName={valoracion.userName}
          rating={valoracion.puntuacion}
          comment={valoracion.descripcion}
        />
      ));
    }
  }
  return (
    <>
      <div>{renderizarValoraciones()}</div>
      {canComment ? (
        <>
          {showBtn ? (

            <button onClick={handleSetButton} style={{
              backgroundColor: "#5d994b",
              color: "white",
              fontWeight: 500,
            }}> Agregar un comentario</button>
          ) : (
            
            <section className="commentInputSection">
              <form>
              <p>
                Que puntaje le darías a tu experiencia?
              </p>
              <span className="ratingSpan">
                <Rate tooltips={desc} onChange={(value) => setValoracionDTO({ descripcion: valoracionDTO.descripcion, puntuacion: value})} value={valoracionDTO?.puntuacion} />
                 {valoracionDTO.puntuacion ? <span className="ant-rate-text">{desc[valoracionDTO.puntuacion - 1]}</span> : ""}
              </span>
              <input id="inputCommentSection" value={valoracionDTO?.descripcion} type="text" onChange={(e)=>{setValoracionDTO({...valoracionDTO, descripcion : e.target.value})}} maxLength={300}/>
              <span>
                <button
                type="button"
                onClick={handleSubmit}
                style={{
                  backgroundColor: "#5d994b",
                  color: "white",
                  fontWeight: 500,
                }}
              >
                Guardar
              </button></span>
              
              </form>
              
            </section>
           
          )}
        </>
      ) : (
        <p>Ya comentaste esta publicación</p>
      )}
      {commentCreated && <p>Gracias por tu comentario!</p>}
    </>
  );
};

export default CommentInput;
