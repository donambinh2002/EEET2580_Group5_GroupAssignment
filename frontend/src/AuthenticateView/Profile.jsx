import React, { useState, useEffect } from "react";
import "./Auth.css";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

function Profile() {
  const token = Cookies.get("authToken");
  const [fullName, setFullName] = useState("");
  const [role, setRole] = useState("");
  const [email, setEmail] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    const getProfile = async () => {
      try {
        const response = await fetch("http://localhost:8080/v1/users/profile", {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          const errorData = await response.json(); // Parse error message from the server
          console.error("Server Error:", errorData);
          throw new Error(`Error: ${errorData.message || response.statusText}`);
        }

        const data = await response.json();
        console.log("Get profile successful:", data);

        setRole(data.roles[0]);
        setFullName(data.full_name);
        setEmail(data.email);

        // Handle successful registration
      } catch (error) {
        console.error("Get profile failed:", error);
        alert(`Failed to get profile: ${error.message}`);
      }
    };

    getProfile();
  }, [token]);

  const handleLogout = async () => {
    try {
      const response = await fetch("http://localhost:8080/v1/auth/logout", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        const errorData = await response.json(); // Parse error message from the server
        console.error("Server Error:", errorData);
        throw new Error(`Error: ${errorData.message || response.statusText}`);
      }

      const data = await response.json();
      console.log("Logout successful:", data);
      Cookies.remove("authToken");
      navigate("/login");

      // Handle successful registration
    } catch (error) {
      console.error("Logout failed:", error);
      alert(`Failed to logout: ${error.message}`);
    }
  };

  const handleGoHome = () => {
    navigate("/");
  };

  return (
    <div className="auth-container">
      <div className="auth-box">
        <h2>{role}</h2>
        <div className="profileDetail">{fullName}</div>
        <div className="profileDetail">{email}</div>

        <button type="submit" className="auth-button" onClick={handleLogout}>
          Sign out
        </button>

        <button className="back-to-home-button" onClick={handleGoHome}>
          Back to Home
        </button>
      </div>
    </div>
  );
}

export default Profile;
