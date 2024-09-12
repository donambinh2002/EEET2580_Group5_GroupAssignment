import React from "react";
import { Link } from "react-router-dom";
import "./HomeHeader.css";

const HomeHeader = () => {
  return (
    <header className="home-header">
      <div className="logo">[LOGO]</div>
      <nav>
        <ul className="nav-links">
          <li>
            <Link to="/discovery">Discovery</Link>
          </li>
          <li>
            <Link to="/about">About</Link>
          </li>
          <li>
            <Link to="/contact">Contact Us</Link>
          </li>
        </ul>
      </nav>
      <div className="header-icons">
        <Link to="/login">
          <span className="icon-user"></span>
        </Link>
        <Link to="/cart">
          <span className="icon-cart"></span>
        </Link>
      </div>
    </header>
  );
};

export default HomeHeader;
