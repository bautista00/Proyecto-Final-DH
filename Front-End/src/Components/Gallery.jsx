import React, {useState} from 'react'

const Gallery = ({images}) => {
    
    const [isOpen, setIsOpen] = useState(false);

    const openModal = () => {
      setIsOpen(true);
    };
  
    const closeModal = () => {
      setIsOpen(false);
    };
  
    return (
      <div className='gallery'>
        <button onClick={openModal}>Ver Galería</button>
        {isOpen && (
          <div className="modalBox">
            <div className="modalContent">
              <span className="close" onClick={closeModal}>&times;</span>
  
              <div className="imageGallery">
                {images.map((image, index) => (
                  <img key={index} src={image} alt={`Image ${index + 1}`} />
                ))}
              </div>
            </div>
          </div>
        )}
      </div>
    )
  }

export default Gallery