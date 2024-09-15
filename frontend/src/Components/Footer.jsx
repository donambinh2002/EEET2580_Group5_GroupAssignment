import React from "react";
import { Link } from "react-router-dom";
import styles from "./HomeHeader.module.css";

const Footer = () => {
    return (
        < footer className={styles.footer} >
        <div className="footer-content">
            <p>slogan.</p>
            <nav className="footer-nav">
                <Link to="/nav-link-2">nav-link-2</Link>
                <Link to="/nav-link-3">nav-link-3</Link>
                <Link to="/nav-link-4">nav-link-4</Link>
            </nav>
            <p>&copy; [WEBSITE NAME] All Rights Reserved.</p>
        </div>
      </footer >
    );
};

export default Footer;