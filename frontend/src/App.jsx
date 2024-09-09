import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./Login.jsx";
import Register from "./Register.jsx";
import Terms from "./Terms.jsx";
import ForgotPassword from "./ForgotPassword.jsx";
import Home from "./Home.jsx";
import ShippingPage from "./ShippingPage/ShippingPage.jsx";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} /> {}
        <Route path="/login" element={<Login />} /> {}
        <Route path="/register" element={<Register />} />
        <Route path="/terms" element={<Terms />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/shipping" element={<ShippingPage />} />
      </Routes>
    </Router>
  );
};

export default App;
