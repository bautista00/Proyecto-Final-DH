import React, { useEffect, useState } from "react";
import Card from "./Card";
import { Select } from "antd";
import ShareAppButton from "./ShareAppButton";
import { axiosInstance } from "../config";
import RecommendedCard from "./RecommendedCard";

const Filters = () => {
  const [selectedSport, setSelectedSport] = useState("Futbol");
  const [selectedDate, setSelectedDate] = useState("");
  const [selectedBarrio, setSelectedBarrio] = useState("Belgrano");
  const [categorias, setCategorias] = useState([]);
  const [barrios, setBarrios] = useState([]);
  const [filtered, setFiltered] = useState([])
  const [showFiltered, setShowFiltered] = useState(false)

  const fetchCategoriaData = async () => {
    const result = await axiosInstance.get("/findAllCategoria/");
    setCategorias(result.data);
  };

  const fetchBarrioData = async () => {
    const result = await axiosInstance.get("/listallbarrios");
    setBarrios(result.data);
  };

  useEffect(() => {
    fetchCategoriaData();
    fetchBarrioData();
  }, []);


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

  
        // codigo nuevo


        const fetchCanchasCatSport = async () => {
  
          try{
            
            const config = {        
              params: {
                barrio: selectedBarrio ,
                categoria : selectedSport
              }
            }
            const result = await axiosInstance.get(`/buscarFiltradas`, config)
            setFiltered(result.data)
            setShowFiltered(true)
          }catch(e){
            console.log(e)
          }
        }

        const renderCardFiltered = () => {
          return filtered.map((card, index)=>(
            <RecommendedCard
            key={index}
            id={card?.id}
            name={card?.nombre}
            location={card?.domicilio?.barrio?.nombre}
            image={card?.images?.url[0]}
          />
          ))
        }
 
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
              <button onClick={fetchCanchasCatSport}>
                <img src="images\lupa.png" alt="" />
              </button>
          </div>
        </div>
      </div>
      <div className="card-container-recommended">
        {showFiltered && renderCardFiltered()}
      </div>
      <div className="card-title">
        <h2> Busca tu cancha por deporte </h2>
        <ShareAppButton />
        <p className="carousel-p">¡Desliza para ver más categorías!</p>
        <div className="card-container">
          {categorias.map((e, index) => {
           
              return (
                <Card
                  key={e.id}
                  image={e.images.url[0]}
                  sport={e.nombre}
                />
              );
            
          })}
        </div>
      </div>
    </div>
  );
};

export default Filters;
