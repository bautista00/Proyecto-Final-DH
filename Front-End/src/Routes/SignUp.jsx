import React, { useState } from 'react';
import { Link } from 'react-router-dom';
const SignUp = () => {
const [email, setEmail] = useState('')
const [password, setPassword] = useState('')
const [nombre, setNombre] = useState('')
const [apellido, setApellido] = useState('')

const [emailError, setEmailError] = useState('')
const [passwordError, setPasswordError] = useState('')
const [nombreError, setNombreError] = useState('')
const [apellidoError, setApellidoError] = useState('')

const [isAdmin, setIsAdmin] = useState(false)

const handleSubmit = (e) => {
  e.preventDefault()

  if (!email || !validarMail(email)) {
    setEmailError('Ingrese un email válido.')
    return
  }
  if (!password || password.length < 8) {
    setPasswordError('La contraseña debe tener al menos 8 caracteres.')
    return
  }
  if (!nombre || !validarNombre(nombre)) {
    setNombreError('Ingrese un nombre válido (solo letras).')
    return
  }
  if (!apellido || !validarNombre(apellido)) {
    setApellidoError('Ingrese un apellido válido (solo letras).')
    return
  }
  

  // const data = {email: email, password: password, nombre: nombre, apellido: apellido}

  // axios.post('url', data)
  // .then(response=>{ 
  //   setEmail('')
  //   setPassword('')
  //   setNombre('')
  //   setApellido('')
  //   setEmailError('')
  //   setPasswordError('')
  //   setNombreError('')
  //   setApellidoError('')})
  // .catch(error=> console.error(error))

};

const validarMail = (value) => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(value);
};

const validarNombre = (value) => {
  const nameRegex = /^[A-Za-z]+$/;
  return nameRegex.test(value);
};

return (
    <>
    <form onSubmit={handleSubmit} className='formSignUp'>
    <div>
        <label htmlFor="nombre">Nombre:</label>
        <input
            type="text" id="nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} required/>
        {nombreError && <p>{nombreError}</p>}
        </div>

        <div>
        <label htmlFor="apellido">Apellido:</label>
        <input type="text" id="apellido" value={apellido} onChange={(e) => setApellido(e.target.value)} required/>
        {apellidoError && <p>{apellidoError}</p>}
        </div>
        
        <div>
        <label htmlFor="email">Email:</label>
        <input type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
        {emailError && <p>{emailError}</p>}
        </div>

        <div>
        <label htmlFor="password">Contraseña:</label>
        <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required
        />
        {passwordError && <p>{passwordError}</p>}
        </div>
        {/* LOGICA SI SE CHECKEA PARA REGISTRAR ADMIN */}
        {isAdmin && <div><div>
        <label htmlFor="nombre">Nombre:</label>
        <input
            type="text" id="nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} required/>
        {nombreError && <p>{nombreError}</p>}
        </div>

        <div>
        <label htmlFor="apellido">Apellido:</label>
        <input type="text" id="apellido" value={apellido} onChange={(e) => setApellido(e.target.value)} required/>
        {apellidoError && <p>{apellidoError}</p>}
        </div>
        
        <div>
        <label htmlFor="email">Email:</label>
        <input type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
        {emailError && <p>{emailError}</p>}
        </div>

        <div>
        <label htmlFor="password">Contraseña:</label>
        <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required
        />
        {passwordError && <p>{passwordError}</p>}
        </div></div>}


        <label htmlFor="admin">Registrarse como prestador?</label>
        <input type='checkbox' id="admin" onChange={()=> setIsAdmin(!isAdmin)}/>

        <button type="submit">Registrarse</button>
    </form>
    <div className='inicioSesion'>
        <p>Ya tenes una cuenta?</p>
        <Link to={`/Login`}><p className='inicioSesionLink'>Inicia Sesion</p></Link>
    </div>
  </>
);
}
export default SignUp