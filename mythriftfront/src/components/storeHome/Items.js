import React, { useEffect, useState } from 'react';
import { useAuth } from '../common/AuthContext';
import ItemData from './ItemData';
import './ItemStyles.css';

function Items() {
    const [items, setItems] = useState([]);
    const authContext = useAuth();

    useEffect(() => {
        const fetchItems = async () => {
            try {
                const response = await fetch(`http://localhost:8081/${authContext['username']}/items`);
                const data = await response.json();
                setItems(Array.isArray(data) ? data : []);
            } catch (error) {
                console.error('Error fetching items:', error);
            }
        };

        fetchItems();
    }, [authContext]);

    return (
        <div className="review">
            <h1>Your Current Products</h1>
            <div className="reviewcard">
                {items.map((item, index) => (
                    <ItemData
                        key={index}
                        image={`http://localhost:8081/${item.imageUrl}`}
                        heading={item.name}
                        text={item.desc}
                        price={item.price}
                    />
                ))}
            </div>
        </div>
    );
}

export default Items;
