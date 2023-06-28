import React, { useEffect, useState } from "react";
import Card from "./Card";
import { useContextGlobal } from "./utils/GlobalContext";
import { Link, useParams } from "react-router-dom";
import { AutoComplete, Select } from "antd";
import ShareAppButton from "./ShareAppButton";
import { axiosInstance } from "../config";

const Filters = () => {
  const [selectedSport, setSelectedSport] = useState("Futbol");
  const [selectedDate, setSelectedDate] = useState("");
  const [selectedBarrio, setSelectedBarrio] = useState("Belgrano");

  // const [nombreCategoria, setNombreCategoria] = useState("");
  const [imagenCategoria, setImagenCategoria] = useState("");
  const [descripcionCategoria, setDescripcionCategoria] = useState("");
  const [modalVisible, setModalVisible] = useState(false);

  const [cont, setCont] = useState(3);
  const { data } = useContextGlobal();

  const [categorias, setCategorias] = useState([]);
  const [barrios, setBarrios] = useState([]);

  // const fetchSportData = async () => {
  //   const result = await axiosInstance.get("/listxsport");
  //   selectedSport(result.data);
  // };

  const fetchCategoriaData = async () => {
    const result = await axiosInstance.get("/findAllCategoria/");
    setCategorias(result.data);
  };

  const fetchBarrioData = async () => {
    const result = await axiosInstance.get("/listallbarrios");
    setBarrios(result.data);
  };

  useEffect(() => {
    // fetchSportData();
    fetchCategoriaData();
    fetchBarrioData();
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    setNombreCategoria("");
    setImagenCategoria("");
    setDescripcionCategoria("");

    setModalVisible(false);
  };

  const handleSportChange = (value) => {
    console.log(`selected ${value}`);
    setSelectedSport(value);
  };

  const handleDateChange = (event) => {
    setSelectedDate(event.target.value);
  };

  const handleLocationChange = (value) => {
    setSelectedBarrio(value);
  };

  const cambiar = () => {
    setCont(4);
  };

  /* cosas autocomplete ant */
  /*
  const onSearch = (value) => {
    console.log("search:", value);
  };
  */
 
  return (
    <div className="divAllFilter">
      <div className="divFilters">
        <div className="onlyFilters">
          <h2>Encontrá tu cancha ideal!</h2>
          <div className="autoCompleteInput">
            <Select onChange={handleSportChange} 
              showSearch
              placeholder="Que buscas hoy?"
              optionFilterProp="children">
              {categorias.map((categoria) => (
                <Select.Option key={categoria.id} value={categoria.nombre}>
                  {categoria.nombre}
                </Select.Option>
              ))}
            </Select>
          </div>
          <div className="DivWithSelectFilter">
            <Select onChange={handleLocationChange} showSearch optionFilterProp="children"  placeholder="Barrio">
              {barrios.map((barrio) => (
                <Select.Option key={barrio.id} value={barrio.nombre}>
                  {barrio.nombre}
                </Select.Option>
              ))}
            </Select>
          </div>
          <div>
            <input
              type="date"
              value={selectedDate}
              onChange={handleDateChange}
            />
          </div>
          <div>
            <Link to={`/Filtered/${selectedSport}/${selectedBarrio}`}>
              <button>
                <img src="images\lupa.png" alt="" />
              </button>
            </Link>
          </div>
        </div>
      </div>
      <div className="card-title">
        <h2> Busca tu cancha por deporte </h2>
        <ShareAppButton />
        <p className="carousel-p">¡Desliza para ver más categorías!</p>
        <div className="card-container">
          {categorias.map((e, index) => {
            if (index <= cont) {
              return (
                <Card
                  key={e.id}
                  image={e.images.url[0]}
                  sport={e.nombre}
                />
              );
            }
          })}
        </div>
      </div>
    </div>
  );
};

export default Filters;
