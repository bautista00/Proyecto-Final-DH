import React, { useState } from "react";

const ShareAppButton = () => {
  const [modalVisible, setModalVisible] = useState(false);
  return (
    <>
      <button id="button-Compartir" onClick={() => setModalVisible(true)}>
        Compartir
      </button>
      {modalVisible && (
        <div className="modal-cat">
          <div className="modal-content-cat-footer">
            <span className="close-cat" onClick={() => setModalVisible(false)}>
              &times;
            </span>
            <h2>Compartinos en todas tus redes!</h2>
            <div className="shareAppIcons">
              <a
                href="https://api.whatsapp.com/send?text=Te invitamos a visitar nuestra app, FieldRent - tu cancha a un click www.fieldrent.com"
                target="_blank"
                class="whatsapp"
              >
                <i class="fa-brands fa-whatsapp"></i>
              </a>
              <a
                href="http://www.facebook.com/sharer.php?u=www.fieldrent.com&t=Te invitamos a visitar nuestra app, FieldRent - tu cancha a un click"
                target="_blank"
                class="facebook"
              >
                <i class="fa-brands fa-facebook"></i>
              </a>
              <a
                href="https://twitter.com/intent/tweet?text=Te invitamos a visitar nuestra app, FieldRent - tu cancha a un click &url=www.fieldrent.com&hashtags=#VeniAJugarConNosotros"
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

export default ShareAppButton;
