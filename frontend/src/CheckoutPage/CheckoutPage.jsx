import React from "react";
import styles from "./CheckoutPage.module.css";
import ContactSection from "../ShippingPage/ContactSection";
import NavigationButtons from "../ShippingPage/NavigationButtons";
import OrderSummary from "../ShippingPage/OrderSummary";
import CheckoutForm from "./CheckoutForm";
import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";

// Make sure to call `loadStripe` outside of a component’s render to avoid
// recreating the `Stripe` object on every render.
const stripePromise = loadStripe(
  "pk_test_51PwyL307YEaSrWAdk8KMGmoGO2GI1WYrkOAjdpn5SiVLwTBxrrdqW1zjg5vAVIMCljzS1OwjpZpvbc2ZLCqP0w6O00mQmuCHfS"
);

const mockClientSecret = "1_secret_3254sd";

function CheckoutPage() {
  const options = {
    mode: "payment",
    amount: 1099,
    currency: "usd",
    // Fully customizable with appearance API.
    appearance: {
      /*...*/
    },
  };

  return (
    <main className={styles.checkoutPage}>
      <div className={styles.contentWrapper}>
        <section className={styles.formSection}>
          <img
            src="\src\assets\react.svg"
            alt="Company Logo"
            className={styles.logo}
          />
          <Elements stripe={stripePromise} options={options}>
            <CheckoutForm />
          </Elements>

          <NavigationButtons />
        </section>
        <aside className={styles.orderSummarySection}>
          <OrderSummary />
        </aside>
      </div>
    </main>
  );
}

export default CheckoutPage;
