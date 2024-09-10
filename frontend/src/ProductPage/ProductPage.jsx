import React, { useState } from 'react';
import Home from '../Home';
import styles from './ProductPage.module.css';
import HomeHeader from '../Components/HomeHeader';

function ProductPage() {
    const [quantity, setQuantity] = useState(1);
    const [subscription, setSubscription] = useState(false);
    const [deliveryInterval, setDeliveryInterval] = useState('4 weeks');

    const handleQuantityChange = (type) => {
        if (type === 'increment') {
            setQuantity(quantity + 1);
        } else if (type === 'decrement' && quantity > 1) {
            setQuantity(quantity - 1);
        }
    };

    return (
        <main className={styles.productPage}>
            < HomeHeader />
            <div className={styles.contentWrapper}>
                <section className={styles.productImage}>
                    <img src="path_to_candle_image" alt="Spiced Mint Candle" />
                </section>
                <aside>
                    <div className={styles.productInfo}>
                        <h2>Spiced Mint Candleaf®</h2>
                        <p className="price">$9.99</p>
                        <div className="subscription">
                            <input
                                type="radio"
                                checked={!subscription}
                                onChange={() => setSubscription(false)}
                            />
                            One time purchase
                            <br />
                            <input
                                type="radio"
                                checked={subscription}
                                onChange={() => setSubscription(true)}
                            />
                            Subscribe and delivery every{' '}
                            <select
                                value={deliveryInterval}
                                onChange={(e) => setDeliveryInterval(e.target.value)}
                                disabled={!subscription}
                            >
                                <option value="4 weeks">4 weeks</option>
                                <option value="8 weeks">8 weeks</option>
                                <option value="12 weeks">12 weeks</option>
                            </select>
                        </div>

                        <div className={styles.quantity}>
                            <button onClick={() => handleQuantityChange('increment')}>+</button>
                            <span>{quantity}</span>
                            <button onClick={() => handleQuantityChange('decrement')}>-</button>
                        </div>

                        <button className={styles.cartAdd}> + Add to cart</button>
                    </div>

                    <div className="product-info">
                        <p>Wax: Top grade Soy wax that delivers a smoke less, consistent burn</p>
                        <p>Fragrance: Premium quality ingredients with natural essential oils</p>
                        <p>Burning Time: 70-75 hours</p>
                        <p>Dimensions: 10cm x 5cm</p>
                        <p>Weight: 400g</p>
                    </div>
                </aside>
            </div>

            <footer>
                <div className="footer-left">
                    <h3>Candleaf</h3>
                    <p>Your natural candle made for your home and for your wellness.</p>
                </div>
                <div className="footer-links">
                    <h4>Discovery</h4>
                    <ul>
                        <li>New Season</li>
                        <li>Most searched</li>
                        <li>Most sold</li>
                    </ul>
                </div>
                <div className="footer-links">
                    <h4>About</h4>
                    <ul>
                        <li>Help</li>
                        <li>Shipping</li>
                        <li>Affiliate</li>
                    </ul>
                </div>
                <div className="footer-links">
                    <h4>Info</h4>
                    <ul>
                        <li>Contact us</li>
                        <li>Privacy Policies</li>
                        <li>Terms & Conditions</li>
                    </ul>
                </div>
            </footer>
        </main>
    );
}

export default ProductPage;
