import React from "react";
import styles from "./ShippingPage.module.css";

function OrderSummary() {
  return (
    <section className={styles.orderSummary}>
      <div className={styles.productInfo}>
        <div className={styles.productImageWrapper}>
          <img
            src="\src\assets\react.svg"
            alt="Spiced Mint Candleaf"
            className={styles.productImage}
          />
          <span className={styles.productQuantity}>1</span>
        </div>
        <div className={styles.productDetails}>
          <h3 className={styles.productName}>Spiced Mint Candleaf®</h3>
          <p className={styles.productPrice}>$ 9.99</p>
        </div>
      </div>
      <hr className={styles.divider} />
      <form className={styles.couponForm}>
        <label htmlFor="couponCode" className="visually-hidden">
          Coupon code
        </label>
        <input
          type="text"
          id="couponCode"
          className={styles.couponInput}
          placeholder="Coupon code"
        />
        <button type="submit" className={styles.couponButton}>
          Add code
        </button>
      </form>
      <hr className={styles.divider} />
      <div className={styles.subtotal}>
        <div className={styles.subtotalLabels}>
          <p>Subtotal</p>
          <p>Shipping</p>
        </div>
        <div className={styles.subtotalValues}>
          <p className={styles.subtotalPrice}>$ 9.99</p>
          <p className={styles.shippingInfo}>Calculated at the next step</p>
        </div>
      </div>
      <hr className={styles.divider} />
      <div className={styles.total}>
        <h3 className={styles.totalLabel}>Total</h3>
        <p className={styles.totalPrice}>$ 9.99</p>
      </div>
    </section>
  );
}

export default OrderSummary;
