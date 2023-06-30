import React from "react";
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
import UsersPermissions from "./Routes/UsersPermissions";
import Users from "./Routes/Users";
import Cities from "./Routes/Cities";
import Categories from "./Routes/Categories";
import Booking from "./Routes/Booking";
import Verify from "./Routes/Verify";
import CreateCategory from "./Routes/CreateCategory";
import DeleteCategory from "./Routes/DeleteCategory";
import BookingControl from "./Routes/BookingControl";
import Favoritos from "./Routes/Favoritos";

function App() {
  return (
    <>
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
        <Route path="/UsersPermissions" element={<UsersPermissions />} />
        <Route path="/Users" element={<Users />} />
        <Route path="/Cities" element={<Cities />} />
        <Route path="/Categories" element={<Categories />} />
        <Route path="/Booking" element={<Booking />} />
        <Route path="/Verify" element={<Verify />} />
        <Route path="/CreateCategory" element={<CreateCategory />} />
        <Route path="/DeleteCategory" element={<DeleteCategory />} />
        <Route path="/BookingControl" element={<BookingControl />} />
        <Route path="/Favoritos" element={<Favoritos />} />
        
      </Routes>
      <Footer />
    </>
  );
}

export default App;
