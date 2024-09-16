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
          <h1>HOVER SPRITE</h1>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Neque quasi
            voluptatum accusantium ullam in architecto soluta eius. Aspernatur
            sunt facere suscipit architecto corporis soluta, placeat, maxime
            deleniti perspiciatis iste officiis.
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
            <h2>Spraying type 2</h2>
            <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatibus 
            ex praesentium sint dolor odio maiores beatae aliquid a in. Est dignissimos optio et placeat. Modi maxime deserunt animi amet officia? 
            </p>
          </div>
          <div className="services-1-image">
            <img src="src/images/sprayingAsset2.jpg" alt="Precision Spraying" />
          </div>
        </div>
      </section>

      {/* New Spraying Service Section 2 (Image on Left) */}
      <section className="services-section">
        <div className="services-content reverse">
          <div className="services-image">
            <img src="src/images/sprayingAsset1.jpg" alt="Residential Spraying" />
          </div>
          <div className="services-text">
            <h2>Spraying type 2</h2>
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatibus ex praesentium sint dolor odio maiores beatae aliquid a in. Est dignissimos optio et placeat. Modi maxime deserunt animi amet officia? 
            </p>
          </div>
        </div>
      </section>

      {/* Create your own */}
      <section className="services-1-section">
        <div className="services-1-content">
          <div className="services-1-text">
            <h2>Your order is here!</h2>
            <p>
              Lorem ipsum dolor sit amet consectetur, adipisicing elit. Cupiditate repudiandae minus,
              autem officiis nostrum deleniti reiciendis error maiores quidem, quis hic illo numquam
              molestiae obcaecati ad recusandae reprehenderit dolore omnis.
            </p>
            <Link to="/services-1" className="services-1-button">
              Order your now!
            </Link>
          </div>
          <div className="services-1-image">
            <img src="src/images/sprayingAsset.jpg" alt="Learn More" />
          </div>
        </div>
      </section>

      {/* Testimonials Section (Moved to Bottom) */}
      <section className="testimonials-section">
        <h2>Testimonials</h2>
        <Swiper
          spaceBetween={20}
          slidesPerView={3}
          loop={true}
          pagination={{ clickable: true }}
          className="testimonial-carousel"
        >
          <SwiperSlide>
            <div className="testimonial-card">
              <img src="src/images/product3.jpg" alt="product 6" />
              <p>"Testimonials 1."</p>
              <p>- Name</p>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="testimonial-card">
              <p>"Testimonials 2."</p>
              <p>- Name</p>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="testimonial-card">
              <p>"Testimonials 3."</p>
              <p>- Name</p>
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="testimonial-card">
              <p>"Testimonials 4."</p>
              <p>- Name</p>
            </div>
          </SwiperSlide>
        </Swiper>
      </section>

      {/* Footer Section */}
      <footer className="footer">
        <div className="footer-content">
          <p>slogan.</p>
          <nav className="footer-nav">
            <Link to="/nav-link-2">nav-link-2</Link>
            <Link to="/nav-link-3">nav-link-3</Link>
            <Link to="/nav-link-4">nav-link-4</Link>
          </nav>
          <p>&copy; [WEBSITE NAME] All Rights Reserved.</p>
        </div>
      </footer>
    </div>
  );
};

export default Home;

