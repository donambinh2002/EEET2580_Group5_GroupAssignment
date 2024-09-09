import React, { useRef } from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

const Home = () => {
  const productSectionRef = useRef(null);

  const scrollToProducts = () => {
    if (productSectionRef.current) {
      productSectionRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <div className="home-container">
      {/* Header Section */}
      <header className="home-header">
        <div className="logo">[LOGO]</div>
        <nav>
          <ul className="nav-links">
            <li><Link to="/discovery">Discovery</Link></li>
            <li><Link to="/about">About</Link></li>
            <li><Link to="/contact">Contact Us</Link></li>
          </ul>
        </nav>
        <div className="header-icons">
          <Link to="/login"><span className="icon-user"></span></Link>
          <Link to="/cart"><span className="icon-cart"></span></Link>
        </div>
      </header>

      {/* Hero Section */}
      <main className="hero-section">
        <div className="hero-content">
          <h1>[WEBSITE NAME]</h1>
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Neque quasi voluptatum accusantium ullam in architecto soluta eius. Aspernatur sunt facere suscipit architecto corporis soluta, placeat, maxime deleniti perspiciatis iste officiis.</p>
          <button onClick={scrollToProducts} className="hero-button">Discover our collection</button>
        </div>
      </main>

      {/* Products Section */}
      <section className="products-section" ref={productSectionRef}>
        <h2>Products</h2>
        <div className="product-grid">
          <div className="product-card">
            <img src="src\images\product.jpg" alt="product" />
            <div className="product-info"> 
              <p>product</p>
              <p>$9.99</p>
            </div>
          </div>
          <div className="product-card">
            <img src="src\images\product1.jpg" alt="product 1" />
            <div className="product-info">
              <p>product 1</p>
              <p>$9.99</p>
            </div>
          </div>
          <div className="product-card">
            <img src="src\images\product2.jpg" alt="product 2" />
            <div className="product-info">
              <p>product 2</p>
              <p>$9.99</p>
            </div>
          </div>
          <div className="product-card">
            <img src="src\images\product3.jpg" alt="product 3" />
            <div className="product-info">
              <p>product 3</p>
              <p>$9.99</p>
            </div>
          </div>
          <div className="product-card">
            <img src="product4.png" alt="product 4" />
            <div className="product-info">
              <p>product 4</p>
              <p>$9.99</p>
            </div>
          </div>
          <div className="product-card">
            <img src="product5.png" alt="product 5" />
            <div className="product-info">
              <p>product 5</p>
              <p>$9.99</p>
            </div>
          </div>
          <div className="product-card">
            <img src="product6.png" alt="product 6" />
            <div className="product-info">
              <p>product 6</p>
              <p>$9.99</p>
            </div>
          </div>
        </div>
      </section>

      {/* Testimonials Section */}
      <section className="testimonials-section">
        <h2>Testimonials</h2>
        <div className="testimonials-grid">
          <div className="testimonial-card">
            <p>"testimonials 1."</p>
            <p>- name</p>
          </div>
          <div className="testimonial-card">
            <p>"testimonials 2"</p>
            <p>- name</p>
          </div>
          <div className="testimonial-card">
            <p>"testimonials 3."</p>
            <p>- name</p>
          </div>
        </div>
      </section>

      {/* Learn More Section */}
      <section className="learn-more-section">
        <div className="learn-more-content">
          <div className="learn-more-text">
            <h2>Learn More</h2>
            <p>
              Lorem ipsum dolor sit amet consectetur, adipisicing elit. Cupiditate repudiandae minus, autem officiis nostrum deleniti reiciendis error maiores quidem, quis hic illo numquam molestiae obcaecati ad recusandae reprehenderit dolore omnis.
            </p>
            <Link to="/learn-more" className="learn-more-button">Learn More</Link>
          </div>
          <div className="learn-more-image">
            <img src='src\images\learn_more.jpg' alt="placeholder-learn-more" />
          </div>
        </div>
      </section>

      {/* Popular Products Section */}
      <section className="popular-section">
        <h2>Popular</h2>
        <div className="product-grid">
          <div className="product-card">
            <img src="product7.png" alt="product 7" />
            <div className="product-info">
              <p>product 7</p>
              <p>$9.99</p>
            </div>
          </div>
          <div className="product-card">
            <img src="product8.png" alt="product 8" />
            <div className="product-info">
              <p>product 8</p>
              <p>$9.99</p>
            </div>
          </div>
          <div className="product-card">
            <img src="product9.png" alt="product 9" />
            <div className="product-info">
              <p>product 9</p>
              <p>$9.99</p>
            </div>
          </div>
          <div className="product-card">
            <img src="product10.png" alt="product 10" />
            <div className="product-info">
              <p>product 10</p>
              <p>$9.99</p>
            </div>
          </div>
        </div>
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
