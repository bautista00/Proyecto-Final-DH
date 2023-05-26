import React, { useState } from 'react'
import Card from './Card';
import { useContextGlobal } from './utils/GlobalContext';

const Filters = () => {
    const [selectedSport, setSelectedSport] = useState('')
    const [selectedDate, setSelectedDate] = useState('');
    const [selectedLocation, setSelectedLocation] = useState('');


    const handleSportChange = (event) => {
        setSelectedSport(event.target.value);
      };
    
      const handleDateChange = (event) => {
        setSelectedDate(event.target.value);
      };
    
      const handleLocationChange = (event) => {
        setSelectedLocation(event.target.value);
      };

      const {data} = useContextGlobal()
    
      return (
        <div className='divAllFilter'>
          <div className='divFilters'>
          <h2>Filtra por deporte, barrio, fecha y hora</h2>
          <div className='onlyFilters'>
            <div>
                <select value={selectedSport} onChange={handleSportChange}>
                    <option value="futbol">Futbol</option>
                    <option value="baloncesto">Basket</option>
                    <option value="tenis">Tenis</option>
                    <option value="padel">Padel</option>
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
            <div className="card-container">
              {data.map((e, index)=>{
                if(index <= 3){
                  return(<Card key={index}id={e.id} image={e.image} sport={e.kindOfSport} />)     
                } 
              })}
            </div>
          </div>
        </div>
        
      )
  }

export default Filters