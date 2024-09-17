import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./AuthenticateView/Login.jsx";
import Register from "./AuthenticateView/Register.jsx";
import Terms from "./AuthenticateView/Terms.jsx";
import ForgotPassword from "./AuthenticateView/ForgotPassword.jsx";
import Home from "./Home.jsx";
import ShippingPage from "./ShippingPage/ShippingPage.jsx";
import CheckoutPage from "./CheckoutPage/CheckoutPage.jsx";
import ProductPage from "./ProductPage/ProductPage.jsx";
import CustomerFeedback from "./CustomerFeedback/CustomerFeedback.jsx";
import SprayOrderForm from "./SprayerOrder/SprayOrderForm.jsx";
import SprayOrderCheck from "./SprayerOrder/SprayerOrderCheck.jsx";
// import CartPage from "./CartPage/CartPage.jsx";
import AssignSprayer from "./SprayerOrder/AssignSprayer.jsx";
import Profile from "./AuthenticateView/Profile.jsx";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/terms" element={<Terms />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/shipping" element={<ShippingPage />} />
        <Route path="/checkout" element={<CheckoutPage />} />
        <Route path="/product" element={<ProductPage />} />
        <Route path="/feedback" element={<CustomerFeedback />} />
        <Route path="/services-1" element={<SprayOrderForm />} />
        <Route path="/spray-order-check" element={<SprayOrderCheck />} />
        <Route path="/cart" element={<SprayOrderCheck />} />
        <Route
          path="/assign-sprayer/:orderId"
          element={<AssignSprayer />}
        />{" "}
        {/* Added new route */}
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </Router>
  );
};

export default App;
