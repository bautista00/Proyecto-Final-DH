import React from 'react'
import { useState } from "react";
import ReactModal from "react-modal";
import { Modal } from "react-modal";

const DeleteCatCard = ({image, sport, func, id}) => {
    const [modalOpen, setModalOpen] = useState(false);

    const handleClick = () => {
        setModalOpen(true);
    };

    const handleConfirm = () => {
        func(id);
        setModalOpen(false);
    };
  return (
    <div className="card-recommended" id="card-delete">
      <img
        id="imgDeleteButton"
        src="/images/deleteButton.png"
        alt="Botón para eliminar producto"
        onClick={handleClick}
      />
      <img src={image} alt="imagenCancha" />
      <h3>{sport}</h3>
      <div className="modal-container-delete">
        <ReactModal
          isOpen={modalOpen}
          onRequestClose={() => setModalOpen(false)}
          contentLabel="Confirmar Eliminación"
          className="modal-delete"
          overlayClassName="overlay"
        >
          <h2>Confirmar Eliminación</h2>
          <p>¿Estás seguro de que deseas eliminar la categoría?</p>
          <div className="modal-delete-buttons">
            <button onClick={handleConfirm}>Eliminar</button>

            <button onClick={() => setModalOpen(false)}>Cancelar</button>
          </div>
        </ReactModal>
      </div>
    </div>
  )
}

export default DeleteCatCard