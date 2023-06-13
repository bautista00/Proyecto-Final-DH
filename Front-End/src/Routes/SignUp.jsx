import React, { useEffect, useState } from 'react';
import { Link} from 'react-router-dom';

const SignUp = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [nombre, setNombre] = useState('');
  const [apellido, setApellido] = useState('');

  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [nombreError, setNombreError] = useState('');
  const [apellidoError, setApellidoError] = useState('');

  const [isAdmin, setIsAdmin] = useState(false);
  const [accountCreated, setAccountCreated] = useState(false); // Nuevo estado para mostrar el mensaje de cuenta creada

  useEffect(() => {
    if (accountCreated) {
      setTimeout(() => {
        window.location.href = '/login';
      }, 3000);
    }
  }, [accountCreated]);

  const handleSubmit = (e) => {
    e.preventDefault();

    // Restablecer los mensajes de error
    setEmailError('');
    setPasswordError('');
    setNombreError('');
    setApellidoError('');

    if (!email || !validarMail(email)) {
      setEmailError('Ingrese un email válido.');
      return;
    }
    if (!password || password.length < 8) {
      setPasswordError('La contraseña debe tener al menos 8 caracteres.');
      return;
    }
    if (!nombre || !validarNombre(nombre)) {
      setNombreError('Ingrese un nombre válido (solo letras).');
      return;
    }
    if (!apellido || !validarNombre(apellido)) {
      setApellidoError('Ingrese un apellido válido (solo letras).');
      return;
    }

    // Realizar el registro exitoso
    setAccountCreated(true);

    // Restablecer los campos
    setEmail('');
    setPassword('');
    setNombre('');
    setApellido('');
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
    <div className='logInContent'>
      {accountCreated ? ( // Mostrar el mensaje de cuenta creada si está establecido
        <div className='accSucced'>
          <h2>Cuenta creada con éxito</h2>
          <p>¡Tu cuenta se ha creado correctamente!</p>
          <p>Redirigiendo a la página de inicio de sesión...</p>
          {/* Aquí puedes agregar lógica para redirigir automáticamente a la página de inicio de sesión */}
        </div>
      ) : (
        <form onSubmit={handleSubmit} className='formSignUp'>
          <div>
            <label htmlFor='nombre'>Nombre:</label>
            <input
              type='text'
              id='nombre'
              value={nombre}
              onChange={(e) => setNombre(e.target.value)}
              required
            />
            {nombreError && <p>{nombreError}</p>}
          </div>

          <div>
            <label htmlFor='apellido'>Apellido:</label>
            <input
              type='text'
              id='apellido'
              value={apellido}
              onChange={(e) => setApellido(e.target.value)}
              required
            />
            {apellidoError && <p>{apellidoError}</p>}
          </div>

          <div>
            <label htmlFor='email'>Email:</label>
            <input
              type='email'
              id='email'
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            {emailError && <p>{emailError}</p>}
          </div>

          <div>
            <label htmlFor='password'>Contraseña:</label>
            <input
              type='password'
              id='password'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            {passwordError && <p>{passwordError}</p>}
          </div>

          {/* LOGICA SI SE CHECKEA PARA REGISTRAR ADMIN */}
          {isAdmin && (
            <div>
              <div>
                <label htmlFor='nombre'>CUIL:</label>
                <input
                  type='text'
                  id='nombre'
                  value={nombre}
                  onChange={(e) => setNombre(e.target.value)}
                  required
                />
                {nombreError && <p>{nombreError}</p>}
              </div>

              <div>
                <label htmlFor='apellido'>CBU:</label>
                <input
                  type='text'
                  id='apellido'
                  value={apellido}
                  onChange={(e) => setApellido(e.target.value)}
                  required
                />
                {apellidoError && <p>{apellidoError}</p>}
              </div>

              <div>
                <label htmlFor='email'>Telefono:</label>
                <input
                  type='email'
                  id='email'
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
                {emailError && <p>{emailError}</p>}
              </div>

              <div>
                <label htmlFor='password'>Domicilio:</label>
                <input
                  type='password'
                  id='password'
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
                {passwordError && <p>{passwordError}</p>}
              </div>
            </div>
          )}

          <label htmlFor='admin'>¿Queres registrate para alquilar tus canchas?</label>
          <input
            type='checkbox'
            id='admin'
            onChange={() => setIsAdmin(!isAdmin)}
            className='adminCheckbox'
          />

          <button type='submit'>Registrarse</button>
        </form>
      )}

      <div className='inicioSesion'>
        <p>¿Ya tienes una cuenta?</p>
        <Link to={`/Login`}>
          <p className='inicioSesionLink'>Inicia Sesión</p>
        </Link>
      </div>
    </div>
  );
};

export default SignUp;
