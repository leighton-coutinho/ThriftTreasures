import React, { useEffect, useState } from 'react';
import { useAuth } from '../common/AuthContext';
import SaleInfo from './SaleInfo';
import './AllSalesStyles.css';

const AllSales = () => {
    const [salesData, setSalesData] = useState([]);
    const authContext = useAuth();

    useEffect(() => {
        const fetchSales = async () => {
            try {
                const response = await fetch(`http://localhost:8081/${authContext['username']}/sales`);
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
                <h1>All Sales</h1>
                {salesData.map((sale, index) => (
                    <SaleInfo
                        key={index}
                        name={sale.name}
                        desc={sale.desc}
                        category={sale.category}
                        status={sale.status}
                        buyername={sale.buyername}
                    />
                ))}
            </div>
            <div className='push'></div>
        </main>
    );
};

export default AllSales;
