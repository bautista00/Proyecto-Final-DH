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
            <div className="modalSharedTitle">
              <span className="close-cat" onClick={() => setModalVisible(false)}>
                &times;
              </span>
            </div>
            
            <div className="modalSharedBody">
            <i className="logoShared">
              <img
                src="/images/fieldRentlogo3.png"
                alt="logo"
                style={{width:100}}
              />
            </i>
            <h2>Compartinos en todas tus redes!</h2>
            <div className="shareAppIcons">
              <a
                href={`https://api.whatsapp.com/send?text=Te invitamos a visitar nuestra app, FieldRent - tu cancha a un click%0Ahttp://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com`}
                target="_blank"
                class="whatsapp"
              >
                <i className="fa-brands fa-whatsapp"></i>
              </a>
              <a
                href={`http://www.facebook.com/sharer.php?u=http://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com&t=Te invitamos a visitar nuestra app, FieldRent - tu cancha a un click%0Ahttp://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com`}
                target="_blank"
                class="facebook"
              >
                <i className="fa-brands fa-facebook"></i>
              </a>
              <a
                href={`https://twitter.com/intent/tweet?text=Te invitamos a visitar nuestra app, FieldRent - tu cancha a un click%0Ahttp://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com`}
                target="_blank"
                class="twitter"
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

export default ShareAppButton;
