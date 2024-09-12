import React from "react";
import styles from "./ShippingPage.module.css";
import ContactSection from "./ContactSection";
import ShippingAddressForm from "./ShippingAddressForm";
import OrderSummary from "../Components/OrderSummary";
import HomeHeader from "../Components/HomeHeader";

function ShippingPage() {
  return (
    <div>
      <HomeHeader />
      <main className={styles.shippingPage}>
        <div className={styles.contentWrapper}>
          <section className={styles.formSection}>
            <img
              src="\src\assets\react.svg"
              alt="Company Logo"
              className={styles.logo}
            />
            {/* <StepIndicator /> */}
            <ContactSection />
            <ShippingAddressForm />
          </section>
          <aside className={styles.orderSummarySection}>
            <OrderSummary />
          </aside>
        </div>
      </main>
    </div>
  );
}

export default ShippingPage;
