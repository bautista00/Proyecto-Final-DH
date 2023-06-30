import React, { useEffect, useState } from "react";
import { axiosInstance } from "../config";

const CreateProduct = () => {
  const cancha = {
    categoria: { nombre: "" },
    domicilio: {
      calle: "",
      numero: "",
      barrio: {
        nombre: "",
      },
      provincia: "Buenos Aires",
    },
    nombre: "",
    precio: 0,
    telefono: "",
    horaApertura: "",
    horaCierre: "",
    criteriosList: [
      { descripcion: "", criterioTitulo: "REGLAS_DE_LA_CANCHA" },
      { descripcion: "", criterioTitulo: "SALUD_Y_SEGURIDAD" },
      { descripcion: "", criterioTitulo: "POLITICAS_DE_CANCELACION" },
    ],
    servicioList: [],
    descripcion: "",
  };

  const [product, setProduct] = useState(cancha);
  const [files, setFiles] = useState([]);
  const [barrios, setBarrios] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [servicios, setServicios] = useState([]);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const formData = new FormData();
  formData.append("canchaDTO", JSON.stringify(product));
  files.map((file) => {
    formData.append("file", file);
  });
  formData.append("token", localStorage.getItem("jwt"));

  const fetchBarriosData = async () => {
    const result = await axiosInstance.get("/listallbarrios");
    setBarrios(result.data);
  };

  const fetchCategoriasData = async () => {
    const resultado = await axiosInstance.get("/findAllCategoria/");
    setCategorias(resultado.data);
  };

  const fetchServiciosData = async () => {
    const resultado = await axiosInstance.get("/findAllServicio");
    setServicios(resultado.data);
  };

  const postCancha = () => {
    const token = localStorage.getItem("jwt");
    const payload = {
      method: "POST",
      body: formData,
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    fetch("http://3.19.232.248:8080/owner/addcancha", payload)
      .then((response) => response.json())
      .then(() => {
        setSuccessMessage("La cancha se agregó con éxito.");
        setProduct(cancha);
        setFiles([]);
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    fetchCategoriasData();
    fetchBarriosData();
    fetchServiciosData();
  }, []);

  const handleFile = (e) => {
    const newFiles = Array.from(e.target.files);
    setFiles((prevFiles) => [...prevFiles, ...newFiles]);
  };
  const handleRemoveFile = (index) => {
    setFiles((prevFiles) => {
      const newFiles = [...prevFiles];
      newFiles.splice(index, 1);
      return newFiles;
    });
  };
  const handleNombre = (e) => {
    setProduct({ ...product, nombre: e.target.value });
  };

  const handleTelefono = (e) => {
    setProduct({ ...product, telefono: e.target.value });
  };

  const handleCalle = (e) => {
    setProduct({
      ...product,
      domicilio: { ...product.domicilio, calle: e.target.value },
    });
  };

  const handleAltura = (e) => {
    setProduct({
      ...product,
      domicilio: { ...product.domicilio, numero: e.target.value },
    });
  };

  const handleCategoria = (e) => {
    setProduct({
      ...product,
      categoria: { ...product.categoria, nombre: e.target.value },
    });
  };
  const handleBarrio = (e) => {
    setProduct({
      ...product,
      domicilio: { ...product.domicilio, barrio: { nombre: e.target.value } },
    });
  };
  const handleDescripcion = (e) => {
    setProduct({ ...product, descripcion: e.target.value });
  };
  const handleHoraApertura = (e) => {
    setProduct({ ...product, horaApertura: e.target.value });
  };
  const handleHoraCierre = (e) => {
    setProduct({ ...product, horaCierre: e.target.value });
  };
  const handleSalud = (e) => {
    const updatedCriteriosList = [...product.criteriosList];
    updatedCriteriosList[1].descripcion = e.target.value;
    setProduct({ ...product, criteriosList: updatedCriteriosList });
  };

  const handleReglas = (e) => {
    const updatedCriteriosList = [...product.criteriosList];
    updatedCriteriosList[0].descripcion = e.target.value;
    setProduct({ ...product, criteriosList: updatedCriteriosList });
  };
  const handlePoliticas = (e) => {
    const updatedCriteriosList = [...product.criteriosList];
    updatedCriteriosList[2].descripcion = e.target.value;
    setProduct({ ...product, criteriosList: updatedCriteriosList });
  };

  const handleServicios = (e) => {
    const servicio = e.target.value;

    setProduct((prevProduct) => {
      const isChecked = prevProduct.servicioList.some(
        (item) => item.nombre === servicio
      );

      let updatedServicioList;

      if (isChecked) {
        updatedServicioList = prevProduct.servicioList.filter(
          (item) => item.nombre !== servicio
        );
      } else {
        updatedServicioList = [
          ...prevProduct.servicioList,
          { nombre: servicio },
        ];
      }

      return {
        ...prevProduct,
        servicioList: updatedServicioList,
      };
    });
  };

  const handlePrecio = (e) => {
    setProduct({ ...product, precio: parseFloat(e.target.value) });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (files.length < 4) {
      setError("Por favor suba al menos 4 fotos e intente nuevamente.");
    } else {
      setError("");
      postCancha();
    }
  };

  return (
    <div className="product-form-container">
      <h1 className="banner-text">Agregar producto</h1>
      <form className="form-container" onSubmit={handleSubmit}>
        <div className="divsDataForm divDataForm1">
          <div className="data-info-container data-container-1">
            <h3>Datos de la cancha</h3>
            <div className="data-container ">
              <label className="custom-form">Nombre</label>
              <input
                type="text"
                value={product.nombre}
                onChange={handleNombre}
              />
            </div>
            <div className="data-container">
              <label className="custom-form">Descripción de la cancha</label>
              <textarea
                className="banner-container"
                type="text"
                value={product.descripcion}
                onChange={handleDescripcion}
              />
            </div>
            <div className="data-container">
              <label className="custom-form">Teléfono de la cancha</label>
              <input
                type="text"
                value={product.telefono}
                onChange={handleTelefono}
              />
            </div>

            <div className="data-container">
              <label className="custom-form">Categoría</label>
              <select onChange={handleCategoria}>
                <option>-Categorias-</option>
                {categorias.map((categoria, key) => {
                  return (
                    <option key={key} value={categoria.nombre}>
                      {categoria.nombre}
                    </option>
                  );
                })}
              </select>
            </div>

            <div className="data-container">
              <label className="custom-form">Imágenes (mínimo 4)</label>
              <input
                type="file"
                name="images"
                id=""
                accept="image/*"
                multiple
                onChange={handleFile}
              />
              <div className="imagenesVistaPrevia">
                {files.map((file, index) => (
                  <div className="vistaPrevia" key={index}>
                    <img
                      className="vistaPrevia"
                      src={URL.createObjectURL(file)}
                      alt={`Foto ${index}`}
                    />
                    <button onClick={() => handleRemoveFile(index)}>
                      <i className="fa-solid fa-rectangle-xmark"></i>
                    </button>
                  </div>
                ))}
              </div>
              {error && <p className="error-message">{error}</p>}
            </div>
          </div>

          <div className="data-info-container">
            <h3>Servicios y Políticas</h3>
            <div className="data-container">
              <label className="custom-form">Servicios</label>
              <div className="data-container-servicios">
                {servicios.map((servicio, index) => (
                  <div key={index} className="data-container ">
                    <input
                      type="checkbox"
                      id={`servicio-${index}`}
                      value={servicio.nombre}
                      checked={product.servicioList.some(
                        (item) => item.nombre === servicio.nombre
                      )}
                      onClick={handleServicios}
                    />
                    <label htmlFor={`servicio-${index}`}>
                      {servicio.nombre}
                    </label>
                  </div>
                ))}
              </div>
            </div>
            <div className="data-container">
              <label className="custom-form">Politicas de cancelacion</label>
              <textarea
                type="text"
                value={product.criteriosList[2].descripcion}
                onChange={handlePoliticas}
                className="politicaInput"
              />
            </div>
            <div className="data-container">
              <label className="custom-form">Reglas de la cancha</label>
              <textarea
                type="text"
                value={product.criteriosList[0].descripcion}
                onChange={handleReglas}
                className="politicaInput"
              />
            </div>
            <div className="data-container">
              <label className="custom-form">Salud y seguridad</label>
              <textarea
                type="text"
                value={product.criteriosList[1].descripcion}
                onChange={handleSalud}
                className="politicaInput"
              />
            </div>
          </div>
          <div className="data-info-container">
            <h3>Datos de dirección del lugar</h3>
            <div className="data-container">
              <label className="custom-form">Calle</label>
              <input
                type="text"
                value={product.domicilio.calle}
                onChange={handleCalle}
              />
            </div>
            <div className="data-container">
              <label className="custom-form">Número</label>
              <input
                type="text"
                value={product.domicilio.numero}
                onChange={handleAltura}
              />
            </div>
            <div className="data-container">
              <label className="custom-form">Provincia</label>
              <select>
                <option value="Buenos Aires">Buenos Aires</option>
              </select>
            </div>
            <div className="data-container">
              <label className="custom-form">Barrio</label>
              <select onChange={handleBarrio}>
                <option>-Barrios-</option>
                {barrios.map((barrio, key) => {
                  return (
                    <option key={key} value={barrio.nombre}>
                      {barrio.nombre}
                    </option>
                  );
                })}
              </select>
            </div>
          </div>

          <div className="data-info-container">
            <h3>Horarios y Precio</h3>
            <div className="data-container">
              <label className="custom-form">Hora de Apertura</label>
              <input
                type="time"
                value={product.horaApertura}
                step="3600"
                onChange={handleHoraApertura}
              />
            </div>
            <div className="data-container">
              <label className="custom-form">Hora de Cierre</label>
              <input
                type="time"
                value={product.horaCierre}
                step="3600"
                onChange={handleHoraCierre}
              />
            </div>
            <div className="data-container">
              <label className="custom-form">Precio en pesos</label>
              <input
                type="number"
                step="0.01"
                value={product.precio}
                onChange={handlePrecio}
              />
            </div>
          </div>
        </div>
        <button className="buttonAgregar" type="submit">
          {" "}
          Agregar Producto{" "}
        </button>
        {successMessage && <p className="success-message">{successMessage}</p>}
      </form>
    </div>
  );
};

export default CreateProduct;
