import React, { useState } from "react";
import { Link } from "react-router-dom";
import styles from "./CartPage.module.css";
import HomeHeader from "../Components/HomeHeader";
import Footer from "../Components/Footer";
import mintImage from "../images/mint.png";
import { StyledEngineProvider } from "@mui/material";

// Sample data for cart items
// const initialCartItems = [
//     {
//       id: 1,
//       name: "Spiced Mint Candleaf®",
//       image: "./images/mint.png",
//       price: 9.99,
//       quantity: 1,
//     },
//     {
//       id: 2,
//       name: "Lavender Dream Candleaf®",
//       image: "./images/lavender.png",
//       price: 12.99,
//       quantity: 1,
//     },
//   ];

const CartPage = () => {
  const [quantity, setQuantity] = useState(1);
  //   const [cartItems, setCartItems] = useState(initialCartItems);

  const handleQuantityChange = (type) => {
    if (type === "increment") {
      setQuantity(quantity + 1);
    } else if (type === "decrement" && quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  //    // Handle removing an item from the cart
  const handleRemoveItem = () => {
    console.log("Item removed");
  };

  // Calculate total price of all cart items
  //    const getTotalPrice = () => {
  //     return cartItems.reduce((total, item) => total + item.price * item.quantity, 0).toFixed(2);
  //   };

  return (
    <div className="cart-page">
      {/* Header */}
      <HomeHeader />

      <div className={styles.mainContainer}>
        {/* Cart Items Section */}
        <div className={styles.cartHeader}>
          <h2>Your cart items</h2>
          <Link to="/" className={`back-link ${styles.backLink}`}>
            Back to shopping
          </Link>
        </div>

        {/* Cart Table */}
        <table className={styles.cartTable}>
          <thead className={styles.headingTable}>
            <tr>
              <th className={styles.product}>Product</th>
              <th className={styles.productRowName}></th>
              <th className="price">Price</th>
              <th className="quantity">Quantity</th>
              <th className="total">Total</th>
            </tr>
          </thead>
          <tbody>
            {/* {cartItems.map((item) => ( */}
            <tr>
              {/* Product details */}
              <td className="cart-product">
                <div className={styles.productImage}>
                  <img src={mintImage} alt="mint" />
                </div>
              </td>

              {/* Product Name */}
              <td>
                <div className={`productName ${styles.productName}`}>
                  <p>Spiced Mint Candleaf®</p>
                  <button onClick={handleRemoveItem} className="remove-link">
                    Remove
                  </button>
                </div>
              </td>

              {/* Price */}
              <td>$ 9.99</td>

              {/* Quantity controls */}
              <td>
                <div className={styles.quantity}>
                  <button onClick={() => handleQuantityChange("increment")}>
                    +
                  </button>
                  <span>{quantity}</span>
                  <button onClick={() => handleQuantityChange("decrement")}>
                    -
                  </button>
                </div>
              </td>

              {/* Total for each item */}
              <td>$ 9.99</td>
            </tr>
            {/* ))} */}
          </tbody>
        </table>

        {/* Subtotal and Checkout */}
        <div className={`CartSumary ${styles.cartSumary}`}>
          <div className={`subtotal ${styles.subtotal}`}>
            <h3>Sub-total</h3>
            <p className={`sumPrice ${styles.sumPrice}`}>$ 99.9</p>
            <p className={`cartNotice ${styles.cartNotice}`}>
              Tax and shipping cost will be calculated later
            </p>
          </div>
          <div className={`checkout ${styles.checkout}`}>
            <Link to="/shipping">
              <button className={`checkout-btn ${styles.checkoutButton}`}>
                Check-out
              </button>
            </Link>
          </div>
        </div>
      </div>

      {/* Footer */}
      <Footer />
    </div>
  );
};

export default CartPage;
