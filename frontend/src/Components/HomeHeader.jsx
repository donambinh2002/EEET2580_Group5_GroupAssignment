import React from "react";
import { Link } from "react-router-dom";
import styles from "./HomeHeader.module.css";

const HomeHeader = () => {
  return (
    <header className={styles.homeHeader}>
      <div className={styles.logo}>[HoverSprite]</div>
      <nav>
        <ul className={styles.navLinks}>
          <li>
            <Link to="/discovery">Discovery</Link>
          </li>
          <li>
            <Link to="/about">About</Link>
          </li>
          <li>
            <Link to="/contact">Contact Us</Link>
          </li>
        </ul>
      </nav>
      <div className={styles.headerIcons}>
        <Link to="/login">
          <span className={styles.iconUser}></span>
        </Link>
        <Link to="/cart">
          <span className={styles.iconCart}></span>
        </Link>
      </div>
    </header>
  );
};

export default HomeHeader;
