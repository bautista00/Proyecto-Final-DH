import React, { useState } from "react";

const ShareProductButton = (props) => {

  
  const [modalVisible, setModalVisible] = useState(false);
  return (
    <>
      <button id="button-Compartir-producto" onClick={() => setModalVisible(true)}>
        <i className="fa-solid fa-share-nodes"></i>
      </button>
      {modalVisible && (
        <div className="modal-cat">
          <div className="modal-content-cat-footer">
            <div className="modalSharedTitle">
              <h2>Comparte nuestro producto en redes</h2>
              <span className="close-cat" onClick={() => setModalVisible(false)}>
              &times;
            </span>  
            </div>
          <div className="modalSharedBody">
          <i>
            <img              
              src={props.imagen}
              alt="logo"
              className="vistaPrevia"
            />
          </i>
                      
            <p>{props.descripcion}</p>            
            <div className="shareAppIcons-Product">
              <a
                href={`https://api.whatsapp.com/send?text=Te invitamos a visitar nuestra cancha, FieldRent - tu cancha a un click%0Ahttp://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/detail/${props.id}`}
                target="_blank"
                className="whatsapp"
              >
                <i className="fa-brands fa-whatsapp"></i>
              </a>
              <a
                href={`http://www.facebook.com/sharer.php?u=http://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/detail/${props.id}&t=Te invitamos a visitar nuestra cancha, FieldRent - tu cancha a un click%0Ahttp://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/detail/${props.id}`}
                target="_blank"
                className="facebook"
              >
                <i className="fa-brands fa-facebook"></i>
              </a>
              <a
                href={`https://twitter.com/intent/tweet?text=Te invitamos a visitar nuestra cancha, FieldRent - tu cancha a un click%0Ahttp://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/detail/${props.id}`}
                target="_blank"
                className="twitter"
              >
                <i className="fa-brands fa-twitter"></i>
              </a>
          </div>
          
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default ShareProductButton;