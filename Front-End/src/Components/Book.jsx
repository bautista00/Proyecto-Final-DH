import { scheduler } from 'dhtmlx-scheduler'
import React, { useEffect, useState } from 'react'
import 'dhtmlx-scheduler'
import 'dhtmlx-scheduler/codebase/dhtmlxscheduler.css';
import 'dhtmlx-scheduler/codebase/dhtmlxscheduler';
import { axiosInstance } from "../config";

const Book = (props) => {

  const { detail } = props
 
  const [loggedUser, setLoggedUser] = useState({});
  const [bookCreated, setBookCreated] = useState(false)
  const [editedEmail, setEditedEmail] = useState('')

  useEffect(() => {
    if (bookCreated) {
      setTimeout(() => {
        window.location.href = "/";
      }, 7000);
    }
  }, [bookCreated]);
 
  useEffect(()=>{
    scheduler.config.header = [
      "day",
      "week",
      "month",
      "date",
      "prev",
      "today",
      "next"
    ];

    scheduler.init('scheduleContainer',new Date(), 'month')
    scheduler.config.first_hour = 8;
    scheduler.config.last_hour = 18;
    scheduler.config.time_step = 60;
    scheduler.config.drag_move = true;
    scheduler.templates.hour_scale = function(date) {
      return scheduler.date.date_to_str("%h:%i %A")(date);
    };

    scheduler.templates.event_bar_text = function(start, end, event) {
      return scheduler.date.date_to_str("%h:%i %A")(start) + " - " + scheduler.date.date_to_str("%h:%i %A")(end);
    };

    scheduler.attachEvent('onEventSave', async (data, is_new) => {
      try {
        const saveReservation = async (reservationData) => {
          try {
            const response = await axiosInstance.post('/user/createturno', reservationData);
            console.log('Reserva guardada:', response.data);
          } catch (error) {
            console.error('Error al guardar la reserva:', error);
          }
        };
    
        if (is_new) {
          const reservationData = {
            start_date: scheduler.date.format(data.start_date, 'yyyy-MM-dd HH:mm'),
            end_date: scheduler.date.format(data.end_date, 'yyyy-MM-dd HH:mm'),
          };
    
          saveReservation(reservationData);
        }
      } catch (e) {
        console.log(e);
      }
    });
      
  },[])

  useEffect(() => {
    axiosInstance
      .get("/all/getuser", {
        params: {
          token: localStorage.getItem("jwt"),
        },
      })
      .then((response) => {
        setLoggedUser(response.data);
        setEditedEmail(response.data.email)
      });
  }, []);

  const confirmBook  = () =>{
    setBookCreated(true)
  }
  console.log(detail)
  return (
    <div>
      {bookCreated ? ( 
        <div className="logInContent accSucced">
          <h2>Reserva creada con éxito</h2>
          <p>¡Tu reserva se ha creado correctamente!</p>
          <p>Redirigiendo al Home</p>
          
        </div>
      ) : (
        <div >
          <h1 className='bookTitle'>Reserva tu cancha</h1>
          <div className="bookContent">
            <h2>Datos del usuario</h2>
            <div>
              <p>Nombre: {loggedUser.nombre}</p>
              <p>Apellido: {loggedUser.apellido}</p>
              <p>Email: <input
                  type="text"
                  value={editedEmail}
                  onChange={(e) => setEditedEmail(e.target.value)}
                  style={{height: 20}}
                  />
              </p>
            </div>
            <h2>Datos de la cancha</h2>
              <p>Nombre Cancha: {detail?.canchaDTO.nombre}</p>
              <p>Precio: {detail?.canchaDTO.precio}</p>
              <p>Barrio: {detail?.canchaDTO.domicilio.barrio.nombre}</p>
            <h2>Fecha y hora de la reserva</h2>
            <div style={{ width: '80%', height: '350px', marginLeft:100}} id='scheduleContainer'></div>
            <button onClick={confirmBook}>Reservar</button>
          </div>
         
        </div>
        
        )}
    </div>
    
  )
}

export default Book