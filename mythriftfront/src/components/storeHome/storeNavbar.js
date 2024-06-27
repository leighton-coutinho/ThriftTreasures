import React, { useState, useEffect, useContext } from 'react';
import '../../App.css';
import axios from 'axios';
import { Link } from 'react-router-dom';
import "./storeNavbarStyles.css";
import {storeMenuItems} from "./storeMenuItems";
import { useNavigate } from 'react-router-dom';
import { AuthContext, useAuth } from '../common/AuthContext';


function StoreNavbar() {
    const navigate = useNavigate();
    const [iconClassName, setIconClassName] = useState('fas fa-bars');
    const [menuClassName, setMenuClassName] = useState('nav-menu');
    const authContext = useAuth()
    console.log(authContext)

    const handleIconClick = () => {
        setIconClassName((prevClassName) => prevClassName === 'fas fa-bars' ? 'fas fa-times' : 'fas fa-bars');
        setMenuClassName((prevClassName) => prevClassName === 'nav-menu' ? 'nav-menu-active' : 'nav-menu');
    };

    const onLogoutClick = () => {
        navigate('/');
    }




    return (
        <nav className="NavbarItems">
            <Link to="/store" className="navbar-logo">
                <h1>ThriftTreasures</h1>
            </Link>

            <div className="menu-icons">
            <i className={iconClassName} onClick={handleIconClick}></i>
             </div>
            <ul className={menuClassName}>
                {storeMenuItems.map((item, index) => {
                    return (
                        <Link className={item.cName} key={index} to={item.url}>
                        <i className={item.icon}></i> {item.title}
                    </Link>
                    );
                })}
                <li>
                </li>
                <button onClick={onLogoutClick}>Logout</button>
            </ul>
        </nav>
    );
}

export default StoreNavbar;