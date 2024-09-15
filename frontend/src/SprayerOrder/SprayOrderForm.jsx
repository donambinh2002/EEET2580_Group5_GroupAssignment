import React, { useState } from 'react';
import Calendar from 'react-calendar';
import { Moon, Hemisphere } from 'lunarphase-js';
import 'react-calendar/dist/Calendar.css';
import './SprayOrderForm.css';

const timeSlots = [
  '6:00 to 7:00', '7:00 to 8:00', '8:00 to 9:00', '9:00 to 10:00',
  '10:00 to 11:00', '11:00 to 12:00', '12:00 to 13:00', '13:00 to 14:00',
  '14:00 to 15:00', '15:00 to 16:00', '16:00 to 17:00', '17:00 to 18:00'
];

const SprayOrderForm = () => {
  const [cropType, setCropType] = useState('Fruit');
  const [area, setArea] = useState('');
  const [location, setLocation] = useState('');
  const [date, setDate] = useState(new Date());
  const [time, setTime] = useState(timeSlots[0]);
  const [paymentType, setPaymentType] = useState('Cash');
  const [cost, setCost] = useState(0);

  const calculateCost = (area) => {
    const costPerDecare = 100; // Example cost per decare
    return area * costPerDecare;
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const decArea = parseFloat(area);
    if (!isNaN(decArea)) {
      setCost(calculateCost(decArea));
    } else {
      setCost(0);
    }
  };

  const handleDateChange = (newDate) => {
    setDate(newDate);
  };

  const tileContent = ({ date, view }) => {
    if (view === 'month') {
      return (
        <small style={{ display: 'block' }}>
          {Moon.lunarPhaseEmoji(date, Hemisphere.NORTHERN)}{' '}
          {Math.round(Moon.lunarAge(date, Hemisphere.NORTHERN))}
        </small>
      );
    }
  };

  return (
    <div className="spray-order-form">
      <div className="form-and-summary-container">
        
        {/* Form Section */}
        <form onSubmit={handleSubmit} className="shared-container form-container">
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
            <select id="time" value={time} onChange={(e) => setTime(e.target.value)}>
              {timeSlots.map((slot) => (
                <option key={slot} value={slot}>{slot}</option>
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
            />
          </div>
          <div className="form-group">
            <label htmlFor="cropType">Type of Crop:</label>
            <select id="cropType" value={cropType} onChange={(e) => setCropType(e.target.value)}>
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
              onChange={(e) => setArea(e.target.value)}
              min="0"
              step="0.01"
            />
          </div>
          <div className="form-group">
            <label htmlFor="paymentType">Payment Type:</label>
            <select id="paymentType" value={paymentType} onChange={(e) => setPaymentType(e.target.value)}>
              <option value="Cash">Cash</option>
              <option value="Card">Card</option>
            </select>
          </div>
          <button type="submit">Submit</button>
        </form>

        {/* Summary Section */}
        <div className="shared-container summary">
          <h2 className="summary-title">Summary</h2>
          <p><b>Date:</b> {date.toLocaleDateString('en-GB')}</p>
          <p><b>Time:</b> {time}</p>
          <p><b>Area:</b> {area} decares</p>
          <p><b>Type of Crop:</b> {cropType}</p>
          <p><b>Location:</b> {location}</p>
          <p><b>Payment Type:</b> {paymentType}</p>
          <p><b>Total Cost:</b> ${cost}</p>
        </div>

      </div>
    </div>
  );
};

export default SprayOrderForm;
