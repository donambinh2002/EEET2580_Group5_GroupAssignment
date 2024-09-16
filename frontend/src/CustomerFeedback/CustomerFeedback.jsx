import React, { useState, useEffect } from "react";
import styles from "./CustomerFeedback.module.css";
import Cookies from "js-cookie";

const CustomerFeedback = ({ orderId }) => {
  const [comment, setComment] = useState("");
  const [rating, setRating] = useState(1);
  const [aRating, setARating] = useState(1);
  const [fRating, setFRating] = useState(1);
  const [pRating, setPRating] = useState(1);

  const [overallHover, setOverallHover] = useState(0);
  const [attentiveHover, setAttentiveHover] = useState(0);
  const [friendlyHover, setFriendlyHover] = useState(0);
  const [professionalHover, setProfessionalHover] = useState(0);

  const [isSubmitted, setIsSubmitted] = useState(false);

  const token = Cookies.get("authToken");

  useEffect(() => {
    setIsSubmitted(false); // Reset isSubmitted when selectedOrderId changes
  }, [orderId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Add logic for submitting the feedback (e.g., API call)

    const requestBody = {
      order_id: orderId,
      text: comment,
      rating: rating,
      attentive_rating: aRating,
      friendly_rating: fRating,
      professional_rating: pRating,
    };

    try {
      const response = await fetch(
        `http://localhost:8080/v1/orders/feedbacks`,
        {
          method: "POST",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(requestBody),
        }
      );

      if (!response.ok) {
        const errorData = await response.json();
        console.error("Server Error:", errorData);
        throw new Error(`Error: ${errorData.message || response.statusText}`);
      }

      setIsSubmitted(true);
    } catch (error) {
      console.error("Failed to submit feedback:", error);
      alert(`Failed to submit feedback: ${error.message}`);
    }
  };

  const renderStars = (
    ratingValue,
    currentRating,
    setRatingFunc,
    hoverState,
    setHoverFunc
  ) => (
    <label key={ratingValue}>
      <input
        type="radio"
        name="rating"
        value={ratingValue}
        onClick={() => setRatingFunc(ratingValue)}
      />
      <svg
        className="star"
        height="40px"
        width="40px"
        fill={
          ratingValue <= (hoverState || currentRating) ? "#ffc107" : "#e4e5e9"
        }
        viewBox="0 0 24 24"
        onMouseEnter={() => setHoverFunc(ratingValue)}
        onMouseLeave={() => setHoverFunc(0)}
      >
        <path d="M12 .587l3.668 7.425 8.332 1.151-6.001 5.757 1.528 8.242L12 18.896l-7.527 4.266 1.528-8.242L.002 9.163l8.332-1.151z" />
      </svg>
    </label>
  );

  if (isSubmitted) {
    return <p>Thank you for your feedback!</p>; // Optionally, you can show a thank you message or redirect
  }

  return (
    <div className={styles.feedbackForm}>
      <h2>Customer Feedback</h2>

      <form onSubmit={handleSubmit}>
        {/* Star Rating for Overall Rating */}
        <div className={styles.starRating}>
          <h3>Overall Rating:</h3>
          {[...Array(5)].map((_, index) =>
            renderStars(
              index + 1,
              rating,
              setRating,
              overallHover,
              setOverallHover
            )
          )}
        </div>

        {/* Star Rating for Attentiveness */}
        <div className={styles.starRating}>
          <h3>Attentiveness Rating:</h3>
          {[...Array(5)].map((_, index) =>
            renderStars(
              index + 1,
              aRating,
              setARating,
              attentiveHover,
              setAttentiveHover
            )
          )}
        </div>

        {/* Star Rating for Friendliness */}
        <div className={styles.starRating}>
          <h3>Friendliness Rating:</h3>
          {[...Array(5)].map((_, index) =>
            renderStars(
              index + 1,
              fRating,
              setFRating,
              friendlyHover,
              setFriendlyHover
            )
          )}
        </div>

        {/* Star Rating for Professionalism */}
        <div className={styles.starRating}>
          <h3>Professionalism Rating:</h3>
          {[...Array(5)].map((_, index) =>
            renderStars(
              index + 1,
              pRating,
              setPRating,
              professionalHover,
              setProfessionalHover
            )
          )}
        </div>

        {/* Comment Input */}
        <div className={styles.commentBox}>
          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            placeholder="Leave your comment here..."
            rows="4"
            cols="50"
          />
        </div>

        {/* Submit Button */}
        <div>
          <button type="submit" className={styles.submitBtn}>
            Submit Feedback
          </button>
        </div>
      </form>
    </div>
  );
};

export default CustomerFeedback;
