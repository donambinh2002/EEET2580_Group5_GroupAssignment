import React, { useState } from 'react';
import Home from '../Home';
import styles from './ProductPage.module.css';
import HomeHeader from '../Components/HomeHeader';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import mintImage from './mint.png';

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
                    <img src={mintImage} alt="Spiced Mint Candle" />
                </section>
                <aside>
                    <div className={styles.productInfo}>
                        <h2>Spiced Mint Candleaf®</h2>
                        <p className={styles.price}>$9.99</p>
                        <div className="subscription-box">
                            <div className="subscription-option">
                                <input type="radio" id="one-time" name="purchase-type" value="one-time" />
                                <label htmlFor="one-time">One time purchase</label>
                            </div>

                            <div className="subscription-option">
                                <input type="radio" id="subscribe" name="purchase-type" value="subscribe" />
                                <label htmlFor="subscribe">Subscribe and delivery every</label>

                                <select>
                                    <option value="4 weeks">4 weeks</option>
                                    <option value="8 weeks">8 weeks</option>
                                    <option value="12 weeks">12 weeks</option>
                                </select>
                            </div>

                            <p className="subscription-text">
                                Subscribe now and get the 10% discount on every recurring order. The discount will be applied at checkout. <a href="#">See details</a>
                            </p>
                        </div>

                        <div className={styles.quantity}>
                            <button onClick={() => handleQuantityChange('increment')}>+</button>
                            <span>{quantity}</span>
                            <button onClick={() => handleQuantityChange('decrement')}>-</button>
                        </div>

                        <button className={styles.cartAdd}> + Add to cart</button>
                    </div>

                    <div className='productInfoContainer'>
                        <Card className={styles.productInfo}>
                            <CardContent>
                                <h3>Product Information</h3>
                                <p>
                                    Wax: Top grade Soy wax that delivers a smoke less, consistent burn
                                </p>
                                <p>
                                    Fragrance: Premium quality ingredients with natural essential oils
                                </p>
                                <p>Burning Time: 70-75 hours</p>
                                <p>Dimensions: 10cm x 5cm</p>
                                <p>Weight: 400g</p>
                            </CardContent>
                        </Card>
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
