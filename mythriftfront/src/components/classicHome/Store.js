import React, { useEffect, useState } from 'react';
import StoreData from './StoreData';
import './StoreStyles.css';
import { useAuth } from '../common/AuthContext';

function Store() {
    const [storeData, setStoreData] = useState([]);
    const authContext = useAuth();

    useEffect(() => {
        const fetchStores = async () => {
            try {
                const response = await fetch('http://localhost:8081/stores');
                const data = await response.json();
                setStoreData(Array.isArray(data) ? data : []);
                storeData.forEach((store) => console.log(store.imagePath));
            } catch (error) {
                console.error('Error fetching stores:', error);
            }
        };

        fetchStores();
    }, [authContext]);

    return (
        <div className="review">
            <h1>Our Stores</h1>
            <p>Discover our current thrift stores</p>
            <div className="reviewcard">
                {
                    storeData.map((store, index) => (
                    <StoreData
                        key={index}
                        image={`http://localhost:8081/stores/${store.imagePath}`}
                        heading={store.name}
                        link={`http://localhost:3000/stores/${store.username}/products`}
                        text={store.address}
                    />
                ))}
            </div>
        </div>
    );
}

export default Store;
