import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css'; // Create a separate CSS file for Home-specific styles

const Home = () => {
  return (
    <div className="home-container">
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
      
      <main className="hero-section">
        <div className="hero-content">
          <h1>website name</h1>
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Nisi praesentium culpa fugit exercitationem qui, dolor corporis ullam, quasi quisquam maxime ipsa doloribus nobis? Reiciendis tempore sed quo at vero quis.</p>
          <Link to="/collection" className="hero-button">Discover </Link>
        </div>
      </main>
    </div>
  );
};

export default Home;
