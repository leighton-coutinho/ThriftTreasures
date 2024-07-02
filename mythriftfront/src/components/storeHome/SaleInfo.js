// SaleInfo.js
import React, { useState } from 'react';
import './SaleInfoStyles.css';

const SaleInfo = ({ saleId, name, desc, category, price, status, buyername, itemId, customerId, storeId }) => {
    const [saleStatus, setSaleStatus] = useState(status);

    const handleStatusChange = async () => {
        if (saleStatus === 'In Progress') {
            try {
                await fetch(`http://localhost:8081/sales/${storeId}/${customerId}/${itemId}/complete`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                });
                setSaleStatus('Completed');
            } catch (error) {
                console.error('Error updating sale status:', error);
            }
        }
    };

    return (
        <div className="sale-info">
            <div className="sale-row">
                <span className="sale-name">{name}</span>
                <span className="sale-desc">{desc}</span>
                <span className="sale-category">{category}</span>
                <span className="sale-price">${price}</span>
                <span className="sale-status">{saleStatus}</span>
                <span className="sale-buyername">{buyername}</span>
                <input
                    type="checkbox"
                    checked={saleStatus === 'Completed'}
                    onChange={handleStatusChange}
                    title="Mark as Completed"
                />
            </div>
        </div>
    );
};

export default SaleInfo;

