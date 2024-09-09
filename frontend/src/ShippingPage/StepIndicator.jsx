import React from "react";
import styles from "./ShippingPage.module.css";

function StepIndicator() {
  const steps = [
    { name: "Cart", status: "completed" },
    { name: "Details", status: "current" },
    { name: "Shipping", status: "upcoming" },
    { name: "Payment", status: "upcoming" },
  ];

  return (
    <nav className={styles.stepIndicator}>
      {steps.map((step, index) => (
        <React.Fragment key={step.name}>
          <div className={`${styles.step} ${styles[step.status]}`}>
            {step.name}
          </div>
          {index < steps.length - 1 && (
            <img
              src={`http://b.io/ext_${index === 0 ? 2 : 3}-`}
              alt=""
              className={styles.separator}
            />
          )}
        </React.Fragment>
      ))}
    </nav>
  );
}

export default StepIndicator;
