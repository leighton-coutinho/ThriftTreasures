import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import './SingleProductStyles.css';

function SuccessPage() {
    const location = useLocation();
    const sessionId = new URLSearchParams(location.search).get('session_id');
    const [item, setItem] = useState(null);

    useEffect(() => {
        const fetchItem = async () => {
            try {
                const response = await fetch(`http://localhost:8081/api/payments/get-item-by-session?session_id=${sessionId}`);
                const data = await response.json();
                setItem(data);
            } catch (error) {
                console.error('Error fetching item:', error);
            }
        };

        fetchItem();
    }, [sessionId]);

    if (!item) {
        return <div>Loading...</div>;
    }

    return (
        <div className="single-product">
            <img src={`http://localhost:8081/${item.imageUrl}`} alt={item.name} />
            <h1>Order Confirmed</h1>
            <h2>{item.name}</h2>
            <p>{item.description}</p>
            <p>Category: {item.category}</p>
            <p>Price: ${item.price}</p>
            <p>An email will be sent to you shortly</p>
        </div>
    );
}

export default SuccessPage;
