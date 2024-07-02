// SaleInfo.js
import React from 'react';
import './SaleInfoStyles.css';

const SaleInfo = ({ name, desc, category, price, status, buyername, saleId, onStatusChange }) => {
    return (
        <div className="sale-info">
            <div className="sale-row">
                <span className="sale-name">{name}</span>
                <span className="sale-desc">{desc}</span>
                <span className="sale-category">{category}</span>
                <span className="sale-price">${price}</span>
                <span className="sale-status">{status}</span>
                <span className="sale-buyername">{buyername}</span>
                {status === 'In Progress' && (
                    <input
                        type="checkbox"
                        onChange={() => onStatusChange(saleId)}
                        title="Mark as Completed"
                    />
                )}
            </div>
        </div>
    );
};

export default SaleInfo;
