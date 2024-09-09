// mockServer.js
const express = require("express");
const cors = require("cors");
const stripe = require("stripe")("sk_test_4eC39HqLyjWDarjtT1zdp7dc"); // Your test secret key

const app = express();
const port = 3001;

app.use(cors());
app.use(express.json());

app.post("/create-payment-intent", async (req, res) => {
  const { amount } = req.body;
  try {
    const paymentIntent = await stripe.paymentIntents.create({
      amount,
      currency: "usd",
    });
    res.json({ clientSecret: paymentIntent.client_secret });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

app.listen(port, () => {
  console.log(`Mock server running at http://localhost:${port}`);
});
