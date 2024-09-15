import React, { useState } from "react";
import "./Auth.css";
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  const [credential, setCredential] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    const requestBody = {
      credential: credential,
      password: password,
    };

    console.log("Request Body:", requestBody); // Log the request body for debugging

    try {
      const response = await fetch("http://localhost:8080/v1/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestBody),
      });

      if (!response.ok) {
        const errorData = await response.json(); // Parse error message from the server
        console.error("Server Error:", errorData);
        throw new Error(`Error: ${errorData.message || response.statusText}`);
      }

      const data = await response.json();
      console.log("Login successful:", data);

      if (data.data.access_token) {
        localStorage.setItem("authToken", data.data.access_token);
      }

      console.log(data.data.access_token);

      handleGoHome();

      // Handle successful registration
    } catch (error) {
      console.error("Login failed:", error);
      alert(`Failed to login: ${error.message}`);
    }
  };

  const handleGoHome = () => {
    navigate("/");
  };

  return (
    <div className="auth-container">
      <div className="auth-box">
        <h2>Welcome Back!</h2>
        <form onSubmit={handleLogin}>
          <div className="input-group">
            <input
              type="credential"
              placeholder="Enter your email or username"
              value={credential}
              onChange={(e) => setCredential(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <input
              type={showPassword ? "text" : "password"}
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <span
              className="toggle-password"
              onClick={() => setShowPassword(!showPassword)}
            >
              {showPassword ? "Hide" : "Show"}
            </span>
          </div>
          <div className="auth-options">
            <label>
              <input type="checkbox" />
              Remember me
            </label>
            <Link to="/forgot-password">Forgot Password?</Link>
          </div>
          <button type="submit" className="auth-button">
            Log In
          </button>
        </form>
        <p>
          Don’t have an account? <Link to="/register">Sign up</Link>
        </p>
        <button className="back-to-home-button" onClick={handleGoHome}>
          Back to Home
        </button>
      </div>
    </div>
  );
};

export default Login;
