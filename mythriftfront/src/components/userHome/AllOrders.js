import React, { useEffect, useState } from 'react';
import { useAuth } from '../common/AuthContext';
import OrderInfo from './OrderInfo';
import '../storeHome/AllSalesStyles.css';

const AllOrders = () => {
    const [salesData, setSalesData] = useState([]);
    const authContext = useAuth();

    useEffect(() => {
        const fetchSales = async () => {
            try {
                const response = await fetch(`http://localhost:8081/${authContext['username']}/orders`);
                const data = await response.json();
                setSalesData(Array.isArray(data) ? data : []);
            } catch (error) {
                console.error('Error fetching sales:', error);
            }
        };

        fetchSales();
    }, [authContext]);

    return (
        <main>
            <div className="all-sales">
                <h1>All Orders</h1>
                {salesData.map((sale, index) => (
                    <OrderInfo
                        key={index}
                        name={sale.name}
                        desc={sale.desc}
                        category={sale.category}
                        status={sale.status}
                        storename={sale.storename}
                        price={sale.price}
                    />
                ))}
            </div>
            <div className='push'></div>
        </main>
    );
};

export default AllOrders;
