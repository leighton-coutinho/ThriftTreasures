import React, { useEffect, useState } from 'react';
import ItemData from '../storeHome/ItemData';
import '../storeHome/ItemStyles.css';

function Products({ username }) {
    const [items, setItems] = useState([]);

    useEffect(() => {
        const fetchItems = async () => {
            try {
                const response = await fetch(`http://localhost:8081/${username}/items`);
                const data = await response.json();
                setItems(Array.isArray(data) ? data : []);
            } catch (error) {
                console.error('Error fetching items:', error);
            }
        };

        fetchItems();
    }, [username]);

    return (
        <div className="review">
            <h1>Current Products</h1>
            <div className="reviewcard">
                {items.map((item, index) => (
                    <ItemData
                        key={index}
                        image={`http://localhost:8081/${item.imageUrl}`}
                        heading={item.name}
                        text={item.desc}
                        price={item.price}
                        link={`http://localhost:3000/stores/${username}/products/${item.id}`}
                    />
                ))}
            </div>
        </div>
    );
}

export default Products;
