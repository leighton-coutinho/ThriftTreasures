import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { loadStripe } from '@stripe/stripe-js';
import './SingleProductStyles.css';

// Initialize Stripe with your public key
const stripePromise = loadStripe('pk_test_51PWLYAEzUy2D86I2aMW3bUXEYfaEWRoBWZ3CdXKwnWWn8xIs7VaadKPpvTwOna8oo5ZvLAvcPjC5nd1PThXIqQjV006iJR7EHx');

function SingleProduct() {
    const { username, itemid } = useParams();
    const [item, setItem] = useState(null);

    const handleBuy = async () => {
        try {
            const stripe = await stripePromise;
            const response = await fetch(`http://localhost:8081/api/payments/create-checkout-session?storeUsername=${username}&itemName=${item.name}&amount=${item.price}`, {
                method: 'POST',
            });
            const session = await response.json();

            // Redirect to Stripe Checkout
            const { error } = await stripe.redirectToCheckout({
                sessionId: session.id,
            });

            if (error) {
                console.error('Error redirecting to checkout:', error);
            }
        } catch (error) {
            console.error('Error creating checkout session:', error);
        }
    };

    useEffect(() => {
        const fetchItem = async () => {
            try {
                const response = await fetch(`http://localhost:8081/items/${username}/${itemid}`);
                const data = await response.json();
                setItem(data);
            } catch (error) {
                console.error('Error fetching item:', error);
            }
        };

        fetchItem();
    }, [itemid, username]);

    if (!item) {
        return <div>Loading...</div>;
    }

    return (
        <div className="single-product">
            <img src={`http://localhost:8081/${item.imageUrl}`} alt={item.name} />
            <h1>{item.name}</h1>
            <p>{item.description}</p>
            <p>Category: {item.category}</p>
            <p>Price: ${item.price}</p>
            <button onClick={handleBuy}>Buy Now</button>
        </div>
    );
}

export default SingleProduct;
