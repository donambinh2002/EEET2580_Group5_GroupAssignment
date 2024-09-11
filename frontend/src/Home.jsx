import React, { useRef } from 'react';
import { Link } from 'react-router-dom';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/navigation'; 
import 'swiper/css/pagination'; 
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
        <Swiper
          spaceBetween={20}
          slidesPerView={3}
          loop={true}
          pagination={{ clickable: true }}
          className="product-carousel"
        >
          <SwiperSlide>
            <Link to="/product/1">
              <div className="product-card">
                <img src="src/images/product.jpg" alt="product" />
                <div className="product-info">
                  <p>Product</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/2">
              <div className="product-card">
                <img src="src/images/product1.jpg" alt="product 1" />
                <div className="product-info">
                  <p>Product 1</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/3">
              <div className="product-card">
                <img src="src/images/product2.jpg" alt="product 2" />
                <div className="product-info">
                  <p>Product 2</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/4">
              <div className="product-card">
                <img src="src/images/product3.jpg" alt="product 3" />
                <div className="product-info">
                  <p>Product 3</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/4">
              <div className="product-card">
                <img src="src/images/product3.jpg" alt="product 4" />
                <div className="product-info">
                  <p>Product 4</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/4">
              <div className="product-card">
                <img src="src/images/product3.jpg" alt="product 5" />
                <div className="product-info">
                  <p>Product 5</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/4">
              <div className="product-card">
                <img src="src/images/product3.jpg" alt="product 6" />
                <div className="product-info">
                  <p>Product 6</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
        </Swiper>
      </section>

      {/* Testimonials Section */}
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
        </Swiper>
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
            <img src="src/images/learn_more.jpg" alt="Learn More" />
          </div>
        </div>
      </section>

      {/* Popular Products Section */}
      <section className="popular-section">
      <h2>Popular</h2>
        <Swiper
          spaceBetween={20}
          slidesPerView={3}
          loop={true}
          pagination={{ clickable: true }}
          className="product-carousel"
        >
          <SwiperSlide>
            <Link to="/product/1">
              <div className="product-card">
                <img src="src/images/product.jpg" alt="product" />
                <div className="product-info">
                  <p>Product</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/2">
              <div className="product-card">
                <img src="src/images/product1.jpg" alt="product 1" />
                <div className="product-info">
                  <p>Product 1</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/3">
              <div className="product-card">
                <img src="src/images/product2.jpg" alt="product 2" />
                <div className="product-info">
                  <p>Product 2</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/4">
              <div className="product-card">
                <img src="src/images/product3.jpg" alt="product 3" />
                <div className="product-info">
                  <p>Product 3</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/4">
              <div className="product-card">
                <img src="src/images/product3.jpg" alt="product 4" />
                <div className="product-info">
                  <p>Product 4</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/4">
              <div className="product-card">
                <img src="src/images/product3.jpg" alt="product 5" />
                <div className="product-info">
                  <p>Product 5</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
          </SwiperSlide>
          <SwiperSlide>
            <Link to="/product/4">
              <div className="product-card">
                <img src="src/images/product3.jpg" alt="product 6" />
                <div className="product-info">
                  <p>Product 6</p>
                  <p>$9.99</p>
                </div>
              </div>
            </Link>
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