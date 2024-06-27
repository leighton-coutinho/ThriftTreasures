import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './SingleProductStyles.css';

function SingleProduct() {
    const { username, itemid } = useParams();
    console.log(username);
    console.log(itemid)
    const [item, setItem] = useState(null);

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
    }, [itemid]);

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
            <button>Buy Now</button>
        </div>
    );
}

export default SingleProduct;
