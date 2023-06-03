import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./Routes/Home";
import Detail from "./Routes/Detail";
import Navbar from "./Components/Navbar";
import Footer from "./Components/Footer";
import SignUp from "./Routes/SignUp";
import Account from "./Routes/Account";
import Login from "./Routes/Login";
import Filtered from "./Routes/Filtered";
import CreateProduct from "./Routes/CreateProduct";
import DeleteProduct from "./Routes/DeleteProduct";

function App() {
  return (
    <div>
      <Navbar />
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route path="/Detail/:id" element={<Detail />} />
        <Route path="/SignUp" element={<SignUp />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/Account/:user" element={<Account />} />
        <Route path="/Filtered/:sport" element={<Filtered />} />
        <Route path="/CreateProduct" element={<CreateProduct />} />
        <Route path="/DeleteProduct" element={<DeleteProduct />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
