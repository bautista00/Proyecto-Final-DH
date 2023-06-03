import React, { useEffect, useState } from 'react'
import Card from './Card';
import { useContextGlobal } from './utils/GlobalContext';
import { useParams } from 'react-router-dom';

const Filters = () => {
  
    const [selectedSport, setSelectedSport] = useState('')
    const [selectedDate, setSelectedDate] = useState('');
    const [selectedLocation, setSelectedLocation] = useState('');

  const [nombreCategoria, setNombreCategoria] = useState('');
  const [imagenCategoria, setImagenCategoria] = useState('');
  const [descripcionCategoria, setDescripcionCategoria] = useState('');
  const [modalVisible, setModalVisible] = useState(false);
 
  const [cont, setCont] = useState(3)
  const {data} = useContextGlobal()
  const [deportes, setDeportes] = useState([
    {
      "id": "1",
      "name": "Ankor Futbol",
      "kindOfSport": "Futbol",
      "location": "San martin 444, Buenos Aires, Capital.",
      "schedule": "De lunes a domingo 12:00hs a 23:00hs.",
      "services": [
        { "name": "Vestuarios", "icon": "fa-solid fa-person-booth" },
        { "name": "duchas", "icon": "fa-solid fa-shower" },
        { "name": "Confiteria", "icon": "fa-solid fa-burger" },
        { "name": "bar", "icon": "fa-solid fa-martini-glass" },
        { "name": "Wifi", "icon": "fa-solid fa-wifi" },
        { "name": "parqueadero", "icon": "fa-solid fa-square-parking" }
      ],
      "contact": "1109808231.",
      "price": "15000.",
      "image": "/images/canchaFutbol7.png",
      "description": "Bienvenido a Ankor Fútbol, el destino ideal para los amantes del fútbol en busca de emociones y competencia. Ubicado en un entorno moderno y vibrante, Ankor Fútbol es un complejo de canchas de fútbol diseñado para ofrecer una experiencia excepcional a jugadores de todas las edades y niveles. Nuestras instalaciones cuentan con un conjunto de canchas de césped artificial de última generación, que ofrecen un rendimiento óptimo y una superficie de juego consistente durante todo el año. Cada cancha está meticulosamente cuidada y cumple con los estándares internacionales, brindando un escenario perfecto para disfrutar del deporte más apasionante del mundo. ¡Vení a jugar con nosotros!"
    },
  
    {
      "id": "2",
      "name": "Tenis Migueletes",
      "kindOfSport": "Tenis",
      "location": "Migueletes 654, Buenos Aires, Capital.",
      "schedule": "De lunes a domingo de 09:00hs a 20:30hs.",
      "services": [
        { "name": "Vestuarios", "icon": "fa-solid fa-person-booth" },
        { "name": "duchas", "icon": "fa-solid fa-shower" },
        { "name": "Confiteria", "icon": "fa-solid fa-burger" },
        { "name": "bar", "icon": "fa-solid fa-martini-glass" },
        { "name": "Wifi", "icon": "fa-solid fa-wifi" },
        { "name": "parqueadero", "icon": "fa-solid fa-square-parking" }
      ],
      "contact": "1108602242.",
      "price": "5000",
      "image": "/images/clubTenis.jpg",
      "description": "Bienvenido a Tenis Migueletes, te esperamos para disfrutar del tenis en un entorno excepcional. Nuestro complejo cuenta con modernas canchas de tenis en un ambiente acogedor y profesional. Ven y descubre un lugar donde la pasión por el tenis se encuentra con la comodidad y la diversión. ¡Te esperamos en Tenis Migueletes para jugar y disfrutar al máximo!"
    },
  
    {
      "id": "3",
      "name": "Pick and Roll Basket",
      "kindOfSport": "Basket",
      "location": "Av.Libertador 1500, Buenos Aires, Capital.",
      "schedule": "De martes a domingo de 11:00hs a 23:30hs.",
      "services": [
        { "name": "Vestuarios", "icon": "fa-solid fa-person-booth" },
        { "name": "duchas", "icon": "fa-solid fa-shower" },
        { "name": "Confiteria", "icon": "fa-solid fa-burger" },
        { "name": "bar", "icon": "fa-solid fa-martini-glass" },
        { "name": "Wifi", "icon": "fa-solid fa-wifi" },
        { "name": "parqueadero", "icon": "fa-solid fa-square-parking" }
      ],
      "contact": "1108903321.",
      "price": "17000.",
      "image": "/images/canchaBasket.png",
      "description": "Bienvenido a Pick and Roll Basket, el lugar perfecto para los amantes del baloncesto. Nuestro complejo cuenta con canchas de baloncesto de primer nivel, diseñadas para brindar una experiencia excepcional. Ven y muestra tus habilidades, organiza partidos emocionantes y vive la emoción del baloncesto en Pick and Roll Basket. Prepárate para hacer jugadas inolvidables y disfrutar con amigos, te esperamos!"
    },
  
    {
      "id": "4",
      "name": "Pilar Padel",
      "kindOfSport": "Padel",
      "location": "3 de Febrero 1500, Buenos Aires, Provincia.",
      "schedule": "De martes a domingo de 09:00hs a 23:30hs.",
      "services": [
        { "name": "Vestuarios", "icon": "fa-solid fa-person-booth" },
        { "name": "duchas", "icon": "fa-solid fa-shower" },
        { "name": "Confiteria", "icon": "fa-solid fa-burger" },
        { "name": "bar", "icon": "fa-solid fa-martini-glass" },
        { "name": "Wifi", "icon": "fa-solid fa-wifi" },
        { "name": "parqueadero", "icon": "fa-solid fa-square-parking" }
      ],
      "contact": "1127903577.",
      "price": "8500.",
      "image": "/images/canchaPadel.png",
      "description": "Esto es Pilar Padel, el distinguido complejo que se erige como el destino por excelencia para los apasionados del pádel en Argentina. Nuestras instalaciones ofrecen un entorno de primera categoría, diseñado meticulosamente para brindar una experiencia sobresaliente en este deporte tan apasionante. Con canchas de pádel de muy buena calidad y equipamiento moderno, Pilar Padel se posiciona como el escenario idóneo para competir, perfeccionar sus habilidades o simplemente disfrutar de momentos gratos con amigos y familiares. Acompáñenos y experimente la emoción inigualable del pádel en Pilar Padel, donde convergen la diversión y la competencia en una armonía perfecta. Nos complace enormemente recibirle y ofrecerle una experiencia única en el fascinante mundo del pádel."
    },
  {"id": "5",
  "name": "a",
  "kindOfSport": "PingPong",
  "location": "3 de Febrero 1500, Buenos Aires, Provincia.",
  "schedule": "De martes a domingo de 09:00hs a 23:30hs.",
  "services": [
    { "name": "Vestuarios", "icon": "fa-solid fa-person-booth" },
    { "name": "duchas", "icon": "fa-solid fa-shower" },
    { "name": "Confiteria", "icon": "fa-solid fa-burger" },
    { "name": "bar", "icon": "fa-solid fa-martini-glass" },
    { "name": "Wifi", "icon": "fa-solid fa-wifi" },
    { "name": "parqueadero", "icon": "fa-solid fa-square-parking" }
  ],
  "contact": "1127903577.",
  "price": "8500.",
  "image": "/images/tenisdemesa.jpg",
  "description": "asd"}])

  
  

  const handleSubmit = (e) => {
    e.preventDefault();

   
    setNombreCategoria('');
    setImagenCategoria('');
    setDescripcionCategoria('');


    setModalVisible(false);
  }

    const handleSportChange = (event) => {
        setSelectedSport(event.target.value);
      };
    
      const handleDateChange = (event) => {
        setSelectedDate(event.target.value);
      };
    
      const handleLocationChange = (event) => {
        setSelectedLocation(event.target.value);
      };

     
        const cambiar = () => {
          setCont(4)
        }

      return (
        <div className='divAllFilter'>
          <div className='divFilters'>
          <div className='onlyFilters'>
            <div>
                <select value={selectedSport} onChange={handleSportChange}>
                    <option value="futbol">⚽ Fútbol</option>
                    <option value="baloncesto">🏀 Basket</option>
                    <option value="tenis">🎾 Tenis</option>
                    <option value="padel">🏸 Padel</option>
                </select>
            </div>
            <div>
                <select value={selectedLocation} onChange={handleLocationChange}>
                    <option value="Almagro">Almagro</option>
                    <option value="Barracas">Barracas</option>
                    <option value="Belgrano">Belgrano</option>
                    <option value="Caballito">Caballito</option>
                    <option value="Colegiales">Colegiales</option>
                    <option value="La Boca">La Boca</option>
                    <option value="La Paternal">La Paternal</option>
                    <option value="Liniers">Liniers</option>
                    <option value="Mataderos">Mataderos</option>
                    <option value="Nuñez">Nuñez</option>
                    <option value="Palermo">Palermo</option>
                    <option value="Parque Avellaneda">Parque Avellaneda</option>
                </select>
            </div>
            <div>
                <input type="datetime-local" value={selectedDate} onChange={handleDateChange} />
            </div>
            <div>
              <button><img src="images\lupa.png" alt="" /></button>
            </div>
            </div>
          </div>
          <div className='card-title'>
            <h2> Busca tu cancha por deporte </h2>
            <button id='button-categorias'onClick={() => setModalVisible(true)}>Agregar</button>
            {modalVisible && (
        <div className="modal-cat">
          <div className="modal-content-cat">
            <span className="close-cat" onClick={() => setModalVisible(false)}>
              &times;
            </span>
            <form onSubmit={handleSubmit}>
              <h2>Crear nueva categoría</h2>
              <label>Nombre de categoría:</label>
              <input
                type="text"
                value={nombreCategoria}
                onChange={(e) => setNombreCategoria(e.target.value)}
              />

              <label>Imagen de la categoría:</label>
              <input
                type="file"
                value={imagenCategoria}
                onChange={(e) => setImagenCategoria(e.target.value)}
              />

              <label>Descripción:</label>
              <textarea
                value={descripcionCategoria}
                onChange={(e) => setDescripcionCategoria(e.target.value)}
              />

              <button onClick={cambiar} type="submit">Guardar</button>
            </form>
          </div>
        </div>
      )}
            <div className="card-container">
              {deportes.map((e, index)=>{
                if(index <= cont){
                  return(<Card key={index}id={e.id} image={e.image} sport={e.kindOfSport} />)     
                } 
              })}
            </div>
          </div>
        </div>
        
      )
  }

export default Filters