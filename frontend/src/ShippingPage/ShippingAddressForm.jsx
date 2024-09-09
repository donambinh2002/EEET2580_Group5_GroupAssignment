import React from "react";
import styles from "./ShippingPage.module.css";

function ShippingAddressForm() {
  return (
    <section className={styles.shippingAddressForm}>
      <h2 className={styles.title}>Shipping Address</h2>
      <form>
        <div className={styles.nameFields}>
          <div className={styles.inputWrapper}>
            <input
              type="text"
              id="firstName"
              className={styles.input}
              placeholder="First Name"
            />
          </div>
          <div className={styles.inputWrapper}>
            <input
              type="text"
              id="lastName"
              className={styles.input}
              placeholder="Last Name"
            />
          </div>
        </div>
        <div className={styles.inputWrapper}>
          <input
            type="text"
            id="address"
            className={styles.input}
            placeholder="Address and number"
          />
        </div>
        <div className={styles.inputWrapper}>
          <input
            type="text"
            id="shippingNote"
            className={styles.input}
            placeholder="Shipping note (optional)"
          />
        </div>
        <div className={styles.locationFields}>
          <div className={styles.inputWrapper}>
            <input
              type="text"
              id="city"
              className={styles.input}
              placeholder="City"
            />
          </div>
          <div className={styles.inputWrapper}>
            <input
              type="text"
              id="postalCode"
              className={styles.input}
              placeholder="Postal Code"
            />
          </div>
          <div className={styles.selectWrapper}>
            <input
              type="text"
              id="province"
              className={styles.input}
              placeholder="Province"
            />
          </div>
        </div>
        <div className={styles.selectWrapper}>
          <input
            type="text"
            id="country"
            className={styles.input}
            placeholder="Country"
          />
        </div>
        <div className={styles.saveInfoOption}>
          <input type="checkbox" id="saveInfo" className={styles.checkbox} />
          <label htmlFor="saveInfo" className={styles.checkboxLabel}>
            Save this information for a future fast checkout
          </label>
        </div>
      </form>
    </section>
  );
}

export default ShippingAddressForm;
