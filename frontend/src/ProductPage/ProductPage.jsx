import React, { useState } from 'react';
import Home from '../Home';
import styles from './ProductPage.module.css';
import HomeHeader from '../Components/HomeHeader';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import mintImage from '../images/mint.png';
import { Box, Typography, Radio, FormControlLabel, RadioGroup, Paper, MenuItem, Select, Link } from "@mui/material";
import Footer from '../Components/Footer';

function ProductPage() {
    const [quantity, setQuantity] = useState(1);
    const [selectedValue, setSelectedValue] = useState("one-time");
    const [subscriptionInterval, setSubscriptionInterval] = useState('4 weeks');
    
    // Handles radio button selection
    const handleRadioChange = (event) => {
        setSelectedValue(event.target.value);
    };

    // Handles subscription interval selection
    const handleSubscriptionChange = (event) => {
        setSubscriptionInterval(event.target.value);
    };

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

                        <Box  bgcolor="white">
                            <Box padding={2}>

                                <RadioGroup defaultValue="one-time" onChange={handleRadioChange}>
                                    <FormControlLabel
                                        value="one-time"
                                        control={<Radio sx={{ color: "#dadada" }} />}
                                        label={
                                            <Typography
                                                variant="body2"
                                                sx={{
                                                    fontFamily: "Roboto, Helvetica, Arial, sans-serif",
                                                    color: "black",
                                                }}
                                            >
                                                One-time purchase
                                            </Typography>
                                        }
                                    />
                                    <FormControlLabel
                                        value="subscribe"
                                        control={<Radio sx={{ color: "#87d6ac" }} />}
                                        label={
                                            <Box display="flex" alignItems="center">
                                                <Typography
                                                    variant="body2"
                                                    sx={{
                                                        fontFamily: "Roboto, Helvetica, Arial, sans-serif",
                                                        color: "black",
                                                        marginRight: 1,
                                                    }}
                                                >
                                                    Subscribe and delivery every
                                                </Typography>
                                                <Select
                                                    value={subscriptionInterval}
                                                    onChange={handleSubscriptionChange}
                                                    displayEmpty
                                                    disabled={selectedValue !== "subscribe"}
                                                    sx={{
                                                        width: 120,
                                                        height: 21,
                                                        border: 1,
                                                        borderColor: "#e6e6e6",
                                                        fontSize: "0.875rem",
                                                    }}
                                                >
                                                    <MenuItem value="4 weeks">4 weeks</MenuItem>
                                                    <MenuItem value="8 weeks">8 weeks</MenuItem>
                                                    <MenuItem value="24 weeks">24 weeks</MenuItem>
                                                </Select>
                                            </Box>
                                        }
                                    />
                                </RadioGroup>

                                <Paper
                                    elevation={0}
                                    sx={{
                                        borderRadius: 1,
                                        border: 1,
                                        borderColor: "#e6e6e6",
                                        padding: 2,
                                        boxSizing: "border-box",
                                    }}
                                >
                                    <Typography
                                        variant="body2"
                                        sx={{
                                            color: "#808080",
                                            fontFamily: "Helvetica, Arial, sans-serif",
                                            marginBottom: 1,
                                        }}
                                    >
                                        Subscribe now and get the 10% discount on every recurring order. The discount will be applied at checkout.{" "}
                                        <Link href="#" sx={{ color: "#56b280", fontSize: "0.875rem" }}>
                                            See details
                                        </Link>
                                    </Typography>
                                </Paper>
                            </Box>
                        </Box>


                        <div className={styles.quantity}>
                            <button onClick={() => handleQuantityChange('increment')}>+</button>
                            <span>{quantity}</span>
                            <button onClick={() => handleQuantityChange('decrement')}>-</button>
                        </div>

                        <button className={styles.cartAdd}>+ Add to cart</button>
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

            <Footer />
        </main>
    );
}

export default ProductPage;
