import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import './Auth.css';

const Terms = () => {
  const navigate = useNavigate();
  return (
    <div className="auth-container">
      <div className="auth-box">
        <h2>Terms and Conditions</h2>
        <p>
          {}
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa.
        </p>
        <button onClick={() => navigate(-1)} className="auth-button back-button">
          Go Back
        </button>
      </div>
    </div>
  );
};

export default Terms;
