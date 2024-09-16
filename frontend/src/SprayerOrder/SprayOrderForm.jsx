import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import { Moon, Hemisphere } from "lunarphase-js";
import "react-calendar/dist/Calendar.css";
import "./SprayOrderForm.css";
import HomeHeader from "../Components/HomeHeader.jsx";
import Footer from "../Components/Footer.jsx";
import Cookies from "js-cookie";

const timeSlots = [
  "4:00 to 5:00",
  "5:00 to 6:00",
  "6:00 to 7:00",
  "7:00 to 8:00",
  "16:00 to 17:00",
  "17:00 to 18:00",
];

const SprayOrderForm = () => {
  const [cropType, setCropType] = useState("Fruit");
  const [area, setArea] = useState(0.0);
  const [location, setLocation] = useState("");
  const [date, setDate] = useState(new Date());
  const [time, setTime] = useState(timeSlots[0]);
  const [paymentType, setPaymentType] = useState("Cash");
  const [cost, setCost] = useState(0.0);
  const [isPopupVisible, setIsPopupVisible] = useState(false);

  const token = Cookies.get("authToken");

  useEffect(() => {
    if (area != 0 || area != "") {
      setCost(calculateCost(area));
    } else {
      setCost(0);
    }
  }, [area]);

  const calculateCost = (area) => {
    const costPerDecare = 30000; // Example cost per decare
    return area * costPerDecare;
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    // const decArea = parseFloat(area);
    // if (!isNaN(decArea)) {
    //   setCost(calculateCost(decArea));
    // } else {
    //   setCost(0);
    // }

    // Convert selected time to ISO 8601 format
    const [startTime, endTime] = time.split(" to ");
    const [hours, minutes] = startTime.split(":").map(Number);
    const timeISO = new Date(date);
    timeISO.setHours(hours, minutes, 0, 0); // Set time to date
    const isoString = timeISO.toISOString();

    console.log(`ISO 8601 format: ${isoString}`);

    const requestBody = {
      desiredStartTime: isoString,
      cropType: cropType.toUpperCase(),
      farmLandArea: area,
    };

    try {
      const response = await fetch("http://localhost:8080/v1/orders/create", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
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
      console.log("Create order successful:", data);
      setIsPopupVisible(true); // Show the success popup
    } catch (error) {
      console.error("Create order failed:", error);
      alert(`Failed create order: ${error.message}`);
    }
  };

  const handleDateChange = (newDate) => {
    setDate(newDate);
    console.log(date);
  };

  const tileContent = ({ date, view }) => {
    if (view === "month") {
      return (
        <small style={{ display: "block" }}>
          {Moon.lunarPhaseEmoji(date, Hemisphere.NORTHERN)}{" "}
          {Math.round(Moon.lunarAge(date, Hemisphere.NORTHERN))}
        </small>
      );
    }
  };

  const closePopup = () => {
    setIsPopupVisible(false);
  };

  return (
    <div>
      <HomeHeader />
      <div className="spray-order-form">
        <div className="form-and-summary-container">
          {/* Form Section */}
          <form
            onSubmit={handleSubmit}
            className="shared-container form-container"
          >
            <h1 className="form-title">Spray Order</h1>

            <div className="form-group">
              <label htmlFor="calendar">Date:</label>
              <div id="calendar">
                <Calendar
                  onChange={handleDateChange}
                  value={date}
                  tileContent={tileContent}
                />
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="time">Time:</label>
              <select
                id="time"
                value={time}
                onChange={(e) => setTime(e.target.value)}
              >
                {timeSlots.map((slot) => (
                  <option key={slot} value={slot}>
                    {slot}
                  </option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label htmlFor="location">Location:</label>
              <input
                id="location"
                type="text"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="cropType">Type of Crop:</label>
              <select
                id="cropType"
                value={cropType}
                onChange={(e) => setCropType(e.target.value)}
              >
                <option value="Fruit">Fruit</option>
                <option value="Cereal">Cereal</option>
                <option value="Vegetable">Vegetable</option>
              </select>
            </div>
            <div className="form-group">
              <label htmlFor="area">Farmland Area (decare):</label>
              <input
                id="area"
                type="number"
                value={area}
                onChange={(e) => {
                  setArea(e.target.value);
                }}
                min="0"
                step="0.01"
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="paymentType">Payment Type:</label>
              <select
                id="paymentType"
                value={paymentType}
                onChange={(e) => setPaymentType(e.target.value)}
              >
                <option value="Cash">Cash</option>
                <option value="Card">Card</option>
              </select>
            </div>
            <button type="submit">Submit</button>
          </form>

          {/* Summary Section */}
          <div className="shared-container summary">
            <h2 className="summary-title">Summary</h2>
            <p>
              <b>Date:</b> {date.toLocaleDateString("en-GB")}
            </p>
            <p>
              <b>Time:</b> {time}
            </p>
            <p>
              <b>Area:</b> {area} decares
            </p>
            <p>
              <b>Type of Crop:</b> {cropType}
            </p>
            <p>
              <b>Location:</b> {location}
            </p>
            <p>
              <b>Payment Type:</b> {paymentType}
            </p>
            <p>
              <b>Total Cost:</b>
              {cost.toLocaleString("vn-VN", {
                style: "currency",
                currency: "VND",
                currencyDisplay: "symbol",
              })}
            </p>
          </div>
        </div>
        {/* Popup for successful order creation */}
        {isPopupVisible && (
          <div className="popup">
            <div className="popup-content">
              <h3>Order Created Successfully!</h3>
              <p>Your order has been created.</p>
              <button onClick={closePopup}>Close</button>
            </div>
          </div>
        )}
      </div>
      <Footer />
    </div>
  );
};

export default SprayOrderForm;
