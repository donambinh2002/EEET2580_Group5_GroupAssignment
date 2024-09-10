
import React from 'react';
import { Link } from 'react-router-dom';
import './HomeHeader.module.css'; 
import Home from '../Home';

const HomeHeader = () => {
  return (
    // <div className="home-container">
      <header className="home-header">
        <div className="logo">logo</div>
        <nav>
          <ul className="nav-links">
            <li><Link to="/navlink1">navlink1</Link></li>
            <li><Link to="/about">About</Link></li>
            <li><Link to="/contact">Contact Us</Link></li>
          </ul>
        </nav>
        <div className="header-icons">
          <Link to="/login"><span className="icon-user"></span></Link>
          <Link to="/cart"><span className="icon-cart"></span></Link>
        </div>
      </header>
    // </div>
  );
};

export default HomeHeader;
