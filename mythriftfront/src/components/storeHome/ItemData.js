import React from 'react';
import { Link } from 'react-router-dom';
import "./ItemStyles.css";

function ItemData(props) {
    return (
        <Link to={props.link} style={{ color: 'inherit', textDecoration: 'inherit' }}>
            <div className="r-card">
                <div className="r-image">
                    <img alt="image" src={props.image} />
                </div>
                <h4>{props.heading}</h4>
                <p>{props.text}</p>
                <div className="r-price">
                    ${props.price}
                </div>
            </div>
        </Link>
    );
}

export default ItemData;
