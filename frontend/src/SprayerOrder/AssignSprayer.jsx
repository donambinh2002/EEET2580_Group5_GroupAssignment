import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./AssignSprayer.css";

const AssignSprayer = () => {
  const { orderId } = useParams(); // Get orderId from the URL
  const navigate = useNavigate(); // To navigate back after assigning

  // Dummy sprayer data
  const sprayers = [
    { id: 1, name: "Sprayer 1", expertise: "EXPERT" },
    { id: 2, name: "Sprayer 2", expertise: "ADEPT" },
    { id: 3, name: "Sprayer 3", expertise: "APPRENTICE" },
  ];

  // State to keep track of the assigned sprayer
  const [assignedSprayer, setAssignedSprayer] = useState(null);

  // Handle assigning a sprayer
  const assignSprayer = (sprayer) => {
    setAssignedSprayer(sprayer); // Set the assigned sprayer
    // Redirect back to the order check page after assigning
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
                <td>{sprayer.name}</td>
                <td>{sprayer.expertise}</td>
                <td>
                  <button
                    className="assign-button"
                    onClick={() => assignSprayer(sprayer)}
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
            {assignedSprayer.name} has been assigned to Order #{orderId}.
          </div>
        )}
      </div>
    </div>
  );
};

export default AssignSprayer;
