// SaleInfo.js
import React from 'react';
import './SaleInfoStyles.css';

const SaleInfo = ({ name, desc, category, status,buyername }) => {
    return (
        <div className="sale-info">
            <div className="sale-row">
                <span className="sale-name">{name}</span>
                <span className="sale-desc">{desc}</span>
                <span className="sale-category">{category}</span>
                <span className="sale-status">{status}</span>
                <span className="sale-buyername">{buyername}</span>
            </div>
        </div>
    );
};

export default SaleInfo;
