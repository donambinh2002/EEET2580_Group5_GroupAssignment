import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; 
import './Auth.css';

const ForgotPassword = () => {
  const [email, setEmail] = useState('');
  const navigate = useNavigate();

  const handleForgotPassword = (e) => {
    e.preventDefault();
    console.log('Password reset link sent to:', email);
  };

  return (
    <div className="auth-container">
      <div className="auth-box">
        <h2>Forgot Password</h2>
        <p>Please enter your email address to receive a password reset link.</p>
        <form onSubmit={handleForgotPassword}>
          <div className="input-group">
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="auth-button">
            Send Reset Link
          </button>
        </form>
        <button onClick={() => navigate(-1)} className="auth-button back-button">
          Go Back
        </button>
      </div>
    </div>
  );
};

export default ForgotPassword;
