import React, { useRef } from "react";
import { Link } from "react-router-dom";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "./Home.css";
import HomeHeader from "./Components/HomeHeader";

const Home = () => {
  const scrollToLearnMore = () => {
    document.querySelector(".services-1-section").scrollIntoView({ behavior: "smooth" });
  };

  return (
    <div className="home-container">
      {/* Header Section */}
      <HomeHeader />

      {/* Hero Section */}
      <main className="hero-section">
        <div className="hero-content">
          <h1>HoverSprite</h1>
          <p>
          Specializes in organic fertilizer and pesticide spraying services for farmers, utilizing a fleet of 15
          spraying drones to serve orchards, cereal fields, and vegetable farms.
          </p>
          <button onClick={scrollToLearnMore} className="hero-button">
            What are we offering?
          </button>
        </div>
      </main>

      {/* New Spraying Service Section 1 (Image on Right) */}
      <section className="services-1-section">
        <div className="services-1-content">
          <div className="services-1-text">
            <h2>Our Services</h2>
            <p>
            HoverSprite provides efficient organic fertilizer and pesticide spraying with our fleet of advanced drones. 
            </p>

            <p>
            We cover orchards, cereal fields, and vegetable farms, ensuring precise and eco-friendly application.
            </p>

            <p>
            Our services enhance crop health and yield while supporting sustainable farming practices.
           </p>

          </div>
          <div className="services-1-image">
            <img src="src/images/sprayingAsset2.jpg" alt="droneSpraying" />
          </div>
        </div>
      </section>

      {/* New Spraying Service Section 2 (Image on Left) */}
      <section className="services-section">
        <div className="services-content reverse">
          <div className="services-image">
            <img src="src/images/sprayingAsset1.jpg" alt="droneSpraying2" />
          </div>
          <div className="services-text">
            <h2>Our Technology</h2>
            <p>
            We use state-of-the-art drones with advanced sensors for precise spraying. This technology reduces waste and improves the effectiveness of our treatments, supporting efficient and sustainable agriculture.
            </p>
          </div>
        </div>
      </section>

      {/* Create your own */}
      <section className="services-1-section">
        <div className="services-1-content">
          <div className="services-1-text">
            <h2>How can I order?</h2>
            <p>
            Place an order via our website by clicking the button below, fill out all infomation needed and you will be good!
            </p>
            <Link to="/services-1" className="services-1-button">
              Order your now!
            </Link>
          </div>
          <div className="services-1-image">
            <img src="src/images/sprayingAsset.jpg" alt="notDroneSpraying" />
          </div>
        </div>
      </section>

      {/* Footer Section */}
      <footer className="footer">
        <div className="footer-content">
          <p>Precision in Every Flight, Excellence in Every Spray.</p>
          <p>&copy; [HoverSprite] All Rights Reserved.</p>
        </div>
      </footer>
    </div>
  );
};

export default Home;

