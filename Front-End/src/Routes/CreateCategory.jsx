import React, { useState, useEffect } from 'react'
import { axiosInstance } from "../config"
import { Link } from "react-router-dom";

const CreateCategory = () => {

  
    const categoria = {
        nombre: ""
    }

    const [product, setProduct] = useState(categoria);
    const [images, setImages] = useState([])
    const [err, setErr] = useState(false)
    const [catCreated, setCatCreated] = useState(false)

               
    const handleFileChange = (e) => {
        setImages([...e.target.files]);
      }

    const handleInputChange = (e) => {
        setProduct({ ...product, [e.target.name]: e.target.value });
      };
    
    
    const formData = new FormData()
    formData.append("categoria", JSON.stringify(product))
    for (let i = 0; i < images.length; i++) {
        formData.append("file", images[i]);
      }
      
    const handleSubmit = async (e) => {
        e.preventDefault();
        
        try{
            const token = localStorage.getItem("jwt");
            console.log(token)
            const config = {
              headers: {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'multipart/form-data'
              }
            }
        
            console.log(formData); 
            const response = await axiosInstance.post(
              "/admin/addcategoria",
              formData,
              config
            )

            
      
          }catch(e){
            console.log(e)
            setErr(true)
          } 

        setCatCreated(true)
          
        setProduct(categoria);
        setImages([]);
        setErr(false);
    }
    console.log(formData)
    
  return (
    <div className="product-form-container">
        <h1 className="banner-text">Nueva categoría</h1>
        <form className="form-container form-category" onSubmit={handleSubmit}>
            <div className='div-category'>
                <label className="custom-form">Nombre de categoría:</label>
                    <input
                    type="text"
                    name="nombre"
                    value={product.nombre}
                    onChange={handleInputChange}
                    />
            </div>
            
            <div className='div-category'>
                <label className="custom-form">Imágenes</label>
                <input 
                    type="file" 
                    name="images"
                    accept="image/*" 
                    multiple
                    onChange={handleFileChange} />
            </div>               
                
            <button className="buttonAgregar" type="submit">Guardar</button>
            {err && <p style={{color:"red"}}>Error al intentar crear una categoría</p>}
            {catCreated && <p>Categoría creada exitosamente</p>}
            </form>
            
            <Link to={`/Categories`}><button className="buttonLight">Ver Categorías</button></Link>
    </div>
  )
}

export default CreateCategory