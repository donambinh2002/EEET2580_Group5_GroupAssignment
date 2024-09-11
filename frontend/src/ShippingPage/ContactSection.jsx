import React from "react";
import styles from "./ShippingPage.module.css";

function ContactSection() {
  return (
    <section className={styles.contactSection}>
      <div className={styles.header}>
        <h2 className={styles.title}>Contact</h2>
        <p className={styles.loginPrompt}>
          Do you have an account?{" "}
          <a href="http://localhost:5173/login" className={styles.loginLink}>
            Login
          </a>
        </p>
      </div>
      <form>
        <input
          type="text"
          id="emailInput"
          className={styles.emailInput}
          placeholder="Email or mobile phone number"
        />
      </form>
    </section>
  );
}

export default ContactSection;
