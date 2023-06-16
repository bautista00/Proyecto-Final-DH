import React, { useState } from "react";

const ShareProductButton = () => {
  const [modalVisible, setModalVisible] = useState(false);
  return (
    <>
      <button id="button-Compartir-producto" onClick={() => setModalVisible(true)}>
        Compartir
      </button>
      {modalVisible && (
        <div className="modal-cat">
          <div className="modal-content-cat-footer">
            <span className="close-cat" onClick={() => setModalVisible(false)}>
              &times;
            </span>
            <h2>Compartinos en todas tus redes!</h2>
            <div className="shareAppIcons-Product">
              <a
                href="https://api.whatsapp.com/send?text=Te invitamos a visitar nuestra cancha, FieldRent - tu cancha a un click www.fieldrent.com/product"
                target="_blank"
                class="whatsapp"
              >
                <i class="fa-brands fa-whatsapp"></i>
              </a>
              <a
                href="http://www.facebook.com/sharer.php?u=www.fieldrent.com/product&t=Te invitamos a visitar nuestra cancha, FieldRent - tu cancha a un click"
                target="_blank"
                class="facebook"
              >
                <i class="fa-brands fa-facebook"></i>
              </a>
              <a
                href="https://twitter.com/intent/tweet?text=Te invitamos a visitar nuestra cancha, FieldRent - tu cancha a un click &url=www.fieldrent.com/product&hashtags=#VeniAJugarConNosotros"
                target="_blank"
                class="twitter"
              >
                <i class="fa-brands fa-twitter"></i>
              </a>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default ShareProductButton;