import React from "react";
import styles from "./ShippingPage.module.css";

function NavigationButtons() {
  return (
    <nav className={styles.navigationButtons}>
      <a href="#cart" className={styles.backLink}>
        Back to cart
      </a>
      <button type="button" className={styles.nextButton}>
        Go to shipping
      </button>
    </nav>
  );
}

export default NavigationButtons;
