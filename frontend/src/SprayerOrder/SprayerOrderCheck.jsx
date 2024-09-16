import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom"; // For navigation
import "./SprayerOrderCheck.css";
import HomeHeader from "../Components/HomeHeader.jsx";
import Footer from "../Components/Footer.jsx";
import Cookies from "js-cookie";
import CustomerFeedback from "../CustomerFeedback/CustomerFeedback.jsx";

const SprayerOrderCheck = () => {
  const token = Cookies.get("authToken");

  const [orders, setOrders] = useState([]);
  const [selectedOrderId, setSelectedOrderId] = useState(null);
  const [messages, setMessages] = useState({});
  const [sprayerAssigned, setSprayerAssigned] = useState(false); // Track if sprayer is assigned
  const [isConfirmed, setIsConfirmed] = useState(false); // Track if the order is confirmed
  const [userRole, setRole] = useState("");
  const navigate = useNavigate(); // Navigation hook
  const [hasFeedback, setHasFeedback] = useState(false);
  const [currentStatus, setCurrentStatus] = useState("PENDING");

  const fetchFeedback = async (currentFeedback) => {
    try {
      const response = await fetch(
        `http://localhost:8080/v1/orders/feedbacks/${currentFeedback}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`, // Pass token here
            "Content-Type": "application/json",
          },
        }
      );

      if (!response.ok) {
        throw new Error("Failed to fetch feedback");
      }

      const data = await response.json();

      console.log(data);
      if (data) {
        setHasFeedback(true); // Set to true if feedback exists
      } else {
        setHasFeedback(false); // Set to false if feedback doesn't exist
      }
    } catch (error) {
      setHasFeedback(false);
      console.log("Order has no feedback");
    }
  };

  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const response = await fetch("http://localhost:8080/v1/users/profile", {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`, // Pass token here
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error("Failed to fetch user profile");
        }

        const data = await response.json();

        // You can now store this in a variable or use it for other logic
        setRole(data.roles[0]);
        console.log(data.roles[0]);
      } catch (error) {
        console.error("Error fetching user profile:", error);
      }
    };

    const fetchOrders = async () => {
      try {
        const response = await fetch(`http://localhost:8080/v1/orders`, {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          const errorData = await response.json();
          console.error("Server Error:", errorData);
          throw new Error(`Error: ${errorData.message || response.statusText}`);
        }

        const data = await response.json();
        setOrders(data); // Set orders after successful fetch

        // Set the first order as selected if there are orders
        if (data.length > 0) {
          setSelectedOrderId(data[0].id);
        }
      } catch (error) {
        console.error("Failed to get orders:", error);
        alert(`Failed to get orders: ${error.message}`);
      }
    };

    fetchUserProfile();
    fetchOrders();
  }, [token]);

  useEffect(() => {
    if (selectedOrderId !== null) {
      const selectedOrder = orders.find(
        (order) => order.id === selectedOrderId
      );
      if (selectedOrder) {
        setIsConfirmed(selectedOrder.status === "CONFIRMED");
        fetchFeedback(selectedOrderId);
      }
    } else {
      setHasFeedback(false);
      setIsConfirmed(false);
    }
  }, [selectedOrderId, orders, token]);

  // Check if there are no orders
  if (!orders || orders.length === 0) {
    return (
      <div>
        <HomeHeader />
        <div className="sprayer-order-check">
          <h2>No Orders Available</h2>
          <p>There are currently no orders to display.</p>
        </div>
        <Footer />
      </div>
    );
  }

  const selectedOrder = orders.find((order) => order.id === selectedOrderId);

  const updateStatus = (newStatus) => {
    const updatedOrders = orders.map((order) =>
      order.id === selectedOrderId ? { ...order, status: newStatus } : order
    );
    setOrders(updatedOrders);

    setMessages({
      ...messages,
      [selectedOrderId]: `Order status updated to "${newStatus}".`,
    });

    if (newStatus === "CONFIRMED") {
      handleConfirm(selectedOrderId);
      setIsConfirmed(true); // Enable the "Assign Sprayer" button when confirmed
    } else {
      handleCancel(selectedOrderId);
      setIsConfirmed(false); // Disable the button if not confirmed
    }
  };

  const handleConfirm = async (selectedOrderId) => {
    try {
      const response = await fetch(
        `http://localhost:8080/v1/orders/confirm/${selectedOrderId}`,
        {
          method: "PUT",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (!response.ok) {
        const errorData = await response.json(); // Parse error message from the server
        console.error("Server Error:", errorData);
        throw new Error(`Error: ${errorData.message || response.statusText}`);
      }

      const data = await response.json();
      console.log(`Confirm order ${selectedOrderId} successful:`, data);
      // Handle successful registration
    } catch (error) {
      console.error("Confirm order failed:", error);
      alert(`Failed to confirm order ${selectedOrderId}: ${error.message}`);
    }
  };

  const handleCancel = async (selectedOrderId) => {
    try {
      const response = await fetch(
        `http://localhost:8080/v1/orders/cancel/${selectedOrderId}`,
        {
          method: "PUT",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (!response.ok) {
        const errorData = await response.json(); // Parse error message from the server
        console.error("Server Error:", errorData);
        throw new Error(`Error: ${errorData.message || response.statusText}`);
      }

      const data = await response.json();
      console.log(`Cancel order ${selectedOrderId} successful:`, data);
      // Handle successful registration
    } catch (error) {
      console.error("Cancel order failed:", error);
      alert(`Failed to cancel order ${selectedOrderId}: ${error.message}`);
    }
  };

  const assignSprayer = () => {
    navigate(`/assign-sprayer/${selectedOrderId}`); // Redirect to the Assign Sprayer page with order ID
  };

  const selectedDate = selectedOrder.desired_time.split("T")[0];
  const selectedTime = selectedOrder.desired_time.split("T")[1].split(".")[0];

  return (
    <div>
      <HomeHeader />
      <div className="sprayer-order-check">
        {/* Dropdown to select an order */}
        <div className="select-order-container">
          <h2>Select Order</h2>
          <select
            value={selectedOrderId}
            onChange={(e) => {
              setSelectedOrderId(parseInt(e.target.value));
            }}
          >
            {orders.map((order) => (
              <option key={order.id} value={order.id}>
                Order #{order.id} - made on: {order.order_time.split("T")[0]} at{" "}
                {order.order_time.split("T")[1].split(".")[0]}
              </option>
            ))}
          </select>
        </div>

        {/* Order Details Section */}
        <div className="order-details-container">
          <h1 className="order-title">
            Spray Order Details (Order #{selectedOrder.id})
          </h1>
          <table className="order-table">
            <tbody>
              {/* Display order details */}
              <tr>
                <th>Date:</th>
                <td>{selectedDate}</td>
              </tr>
              <tr>
                <th>Time:</th>
                <td>{selectedTime}</td>
              </tr>
              <tr>
                <th>Location:</th>
                <td>{selectedOrder.location}</td>
              </tr>
              <tr>
                <th>Type of Crop:</th>
                <td>{selectedOrder.crop_type}</td>
              </tr>
              <tr>
                <th>Farmland Area (decare):</th>
                <td>{selectedOrder.farm_land_area} decares</td>
              </tr>
              <tr>
                <th>Payment Type:</th>
                <td>{selectedOrder.paymentType}</td>
              </tr>
              <tr>
                <th>Total Cost:</th>
                <td>${selectedOrder.total_cost}</td>
              </tr>
              <tr>
                <th>Status:</th>
                <td>{selectedOrder.status}</td>
              </tr>
            </tbody>
          </table>
        </div>

        {/* Status Update Section */}
        <div className="status-container">
          <h2>Update Status</h2>
          <p>
            <strong>Current Status:</strong> {selectedOrder.status}
          </p>

          {userRole === "RECEPTIONIST" && (
            <div>
              <button
                className="status-button"
                onClick={() => updateStatus("CONFIRMED")}
              >
                Confirm
              </button>
              <button
                className="status-button"
                onClick={() => updateStatus("CANCELLED")}
              >
                Cancel
              </button>
              <button
                className="status-button"
                onClick={assignSprayer}
                disabled={!isConfirmed} // Button is disabled unless the order is confirmed
              >
                Assign Sprayer
              </button>
            </div>
          )}

          {/* Success message */}
          {messages[selectedOrderId] && (
            <div
              className={`message ${
                selectedOrder.status === "Confirmed" ||
                selectedOrder.status === "Cancelled"
                  ? "success"
                  : "warning"
              }`}
            >
              {messages[selectedOrderId]}
            </div>
          )}
        </div>
        {/* Feedback */}
        {(selectedOrder.status === "CONFIRMED" ||
          selectedOrder.status === "ASSIGNED") &&
          userRole === "FARMER" &&
          !hasFeedback && <CustomerFeedback orderId={selectedOrderId} />}
      </div>
      <Footer />
    </div>
  );
};

export default SprayerOrderCheck;
