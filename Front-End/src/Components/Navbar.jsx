import React from 'react'
import '../index.css'
import { Link } from 'react-router-dom'


const Navbar = () => {



  return (
    <div className='divHeader'>
        <div className='logoDiv'>
            <Link to={`/`}><i><img className= 'logoHeader'src="/images/logoFieldRent.png" alt="logo"/></i></Link>
        </div>
        <div className='divButtons'>
            <Link to={`/Login`}><button>Iniciar Sesion</button></Link>
            <Link to={`/SignUp`}><button>Registrarse</button></Link>
        </div>
    </div>
  )
}

export default Navbar