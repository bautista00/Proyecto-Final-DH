import { useState } from 'react'
import { Routes, Route } from 'react-router-dom'
import Home from './Routes/Home'
import Detail from './Routes/Detail'
import Navbar from './Components/Navbar'
import Footer from './Components/Footer'
import SignUp from './Routes/SignUp'
import Login from './Routes/Login'


function App() {
  return(
    <div className='allPage'>
      <Navbar/>
        <Routes>
         <Route exact path="/" element={<Home/>}/>
         <Route path='/Detail/:id' element={<Detail/>}/>
         <Route path= '/SignUp' element={<SignUp/>}/>
         <Route path= '/Login' element={<Login/>}/>
        </Routes>
      <Footer/>
    </div>
  ) 
}


export default App
