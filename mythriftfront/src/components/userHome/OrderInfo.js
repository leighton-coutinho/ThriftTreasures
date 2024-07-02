// SaleInfo.js
import React from 'react';
import '../storeHome/SaleInfoStyles.css';

const OrderInfo = ({ name, desc, category, price, status, storename }) => {
    return (
        <div className="sale-info">
            <div className="sale-row">
                <span className="sale-name">{name}</span>
                <span className="sale-desc">{desc}</span>
                <span className="sale-category">{category}</span>
                <span className="sale-category">${price}</span>
                <span className="sale-status">{status}</span>
                <span className="sale-buyername">{storename}</span>
            </div>
        </div>
    );
};

export default OrderInfo;
