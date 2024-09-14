import React, { useState } from 'react';
import './SprayerOrderCheck.css';

const SprayerOrderCheck = () => {
  // Example data for multiple orders
  const orders = [
    {
      id: 1,
      date: '15/09/2024',
      time: '9:00 to 10:00',
      location: 'Field A, Farm Lane',
      cropType: 'Vegetable',
      area: 10,
      paymentType: 'Card',
      cost: 1000,
      status: 'Pending',
    },
    {
      id: 2,
      date: '16/09/2024',
      time: '10:00 to 11:00',
      location: 'Field B, Green Road',
      cropType: 'Fruit',
      area: 5,
      paymentType: 'Cash',
      cost: 500,
      status: 'Pending',
    },
    {
      id: 3,
      date: '17/09/2024',
      time: '11:00 to 12:00',
      location: 'Field C, Red Valley',
      cropType: 'Cereal',
      area: 15,
      paymentType: 'Card',
      cost: 1500,
      status: 'Pending',
    },
  ];

  // State to track the selected order by its id
  const [selectedOrderId, setSelectedOrderId] = useState(orders[0].id);
  const [messages, setMessages] = useState({});

  // Find the currently selected order
  const selectedOrder = orders.find(order => order.id === selectedOrderId);

  // Function to handle status update for the selected order
  const updateStatus = (newStatus) => {
    const updatedOrders = orders.map(order =>
      order.id === selectedOrderId ? { ...order, status: newStatus } : order
    );
    setMessages({
      ...messages,
      [selectedOrderId]: `Order status updated to "${newStatus}".`,
    });
  };

  return (
    <div className="sprayer-order-check">
      {/* Dropdown to select an order */}
      <div className="select-order-container">
        <h2>Select Order</h2>
        <select
          value={selectedOrderId}
          onChange={(e) => setSelectedOrderId(parseInt(e.target.value))}
        >
          {orders.map((order) => (
            <option key={order.id} value={order.id}>
              Order #{order.id} - {order.date} at {order.time}
            </option>
          ))}
        </select>
      </div>

      {/* Order Details Section for the selected order */}
      <div className="order-details-container">
        <h1 className="order-title">Spray Order Details (Order #{selectedOrder.id})</h1>
        
        <table className="order-table">
          <tbody>
            <tr>
              <th>Date:</th>
              <td>{selectedOrder.date}</td>
            </tr>
            <tr>
              <th>Time:</th>
              <td>{selectedOrder.time}</td>
            </tr>
            <tr>
              <th>Location:</th>
              <td>{selectedOrder.location}</td>
            </tr>
            <tr>
              <th>Type of Crop:</th>
              <td>{selectedOrder.cropType}</td>
            </tr>
            <tr>
              <th>Farmland Area (decare):</th>
              <td>{selectedOrder.area} decares</td>
            </tr>
            <tr>
              <th>Payment Type:</th>
              <td>{selectedOrder.paymentType}</td>
            </tr>
            <tr>
              <th>Total Cost:</th>
              <td>${selectedOrder.cost}</td>
            </tr>
            <tr>
              <th>Status:</th>
              <td>{selectedOrder.status}</td>
            </tr>
          </tbody>
        </table>
      </div>

      {/* Status Update Section for the selected order */}
      <div className="status-container">
        <h2>Update Status</h2>
        <p><strong>Current Status:</strong> {selectedOrder.status}</p>
        
        <div>
          <button className="status-button" onClick={() => updateStatus('In Progress')}>
            In Progress
          </button>
          <button className="status-button" onClick={() => updateStatus('Completed')}>
            Completed
          </button>
        </div>

        {/* Show success or warning message based on the update */}
        {messages[selectedOrderId] && (
          <div className={`message ${selectedOrder.status === 'Completed' ? 'success' : 'warning'}`}>
            {messages[selectedOrderId]}
          </div>
        )}
      </div>
    </div>
  );
};

export default SprayerOrderCheck;
