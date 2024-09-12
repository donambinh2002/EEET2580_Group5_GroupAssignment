import React, { useState } from "react";
import styles from "./CustomerFeedback.module.css";

const CustomerFeedback = () => {
  const [comment, setComment] = useState("");
  const [rating, setRating] = useState(0);
  const [hover, setHover] = useState(0);

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add logic for submitting the feedback (e.g., API call)
    console.log({ comment, rating });
    alert(
      `Thank you for your feedback!\nRating: ${rating} stars\nComment: ${comment}`
    );
  };

  return (
    <div className={styles.feedbackForm}>
      <h2>Customer Feedback</h2>

      <form onSubmit={handleSubmit}>
        {/* Star Rating System */}
        <div className={styles.starRating}>
          {[...Array(5)].map((star, index) => {
            const ratingValue = index + 1;

            return (
              <label key={index}>
                <input
                  type="radio"
                  name="rating"
                  value={ratingValue}
                  onClick={() => setRating(ratingValue)}
                />
                <svg
                  className="star"
                  height="40px"
                  width="40px"
                  fill={
                    ratingValue <= (hover || rating) ? "#ffc107" : "#e4e5e9"
                  }
                  viewBox="0 0 24 24"
                  onMouseEnter={() => setHover(ratingValue)}
                  onMouseLeave={() => setHover(0)}
                >
                  <path d="M12 .587l3.668 7.425 8.332 1.151-6.001 5.757 1.528 8.242L12 18.896l-7.527 4.266 1.528-8.242L.002 9.163l8.332-1.151z" />
                </svg>
              </label>
            );
          })}
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

      {/* Feedback Preview */}
      <div className="feedback-preview">
        <h3>Your Feedback:</h3>
        <p>
          <strong>Rating:</strong> {rating} stars
        </p>
        <p>
          <strong>Comment:</strong> {comment}
        </p>
      </div>
    </div>
  );
};

export default CustomerFeedback;
