import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./AssignSprayer.css";
import Cookies from "js-cookie";

const AssignSprayer = () => {
  const token = Cookies.get("authToken");

  const { orderId } = useParams(); // Get orderId from the URL
  const navigate = useNavigate(); // To navigate back after assigning
  const [sprayers, setSprayers] = useState([]);
  // State to keep track of the assigned sprayer
  const [assignedSprayer, setAssignedSprayer] = useState(null);
  // Dummy sprayer data
  // const sprayers = [
  //   { id: 1, name: "Sprayer 1", expertise: "EXPERT" },
  //   { id: 2, name: "Sprayer 2", expertise: "ADEPT" },
  //   { id: 3, name: "Sprayer 3", expertise: "APPRENTICE" },
  // ];

  useEffect(() => {
    const fetchSprayers = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/v1/users/sprayers",
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`, // Pass token here
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to fetch sprayers");
        }

        const data = await response.json();
        console.log(data);
        setSprayers(data);
      } catch (error) {
        console.error("Error fetching sprayers:", error);
      }
    };
    fetchSprayers();
  }, [token]);

  const handleAssignSprayer = async (sprayer) => {
    console.log(orderId);
    try {
      const response = await fetch(`http://localhost:8080/v1/orders/assign`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`, // Pass token here
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ order_id: orderId, sprayer: sprayer.username }),
      });

      if (!response.ok) {
        throw new Error("Failed to assign sprayers");
      }

      const data = await response.json();
      console.log(data);
    } catch (error) {
      console.error("Error assign sprayers:", error);
    }
    setAssignedSprayer(sprayer);
    setTimeout(() => navigate("/spray-order-check"), 1000);
  };

  return (
    <div className="assign-sprayer-container">
      <div className="assign-sprayer-box">
        <h1 className="box-title">Assign Sprayer for Order #{orderId}</h1>

        <table className="sprayer-table">
          <thead>
            <tr>
              <th>Full Name</th>
              <th>Expertise</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {sprayers.map((sprayer) => (
              <tr key={sprayer.id}>
                <td>{sprayer.full_name}</td>
                <td>{sprayer.expertise}</td>
                <td>
                  <button
                    className="assign-button"
                    onClick={() => handleAssignSprayer(sprayer)}
                  >
                    Assign
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* Confirmation message */}
        {assignedSprayer && (
          <div className="message success">
            {assignedSprayer.full_name} has been assigned to Order #{orderId}.
          </div>
        )}
      </div>
    </div>
  );
};

export default AssignSprayer;
