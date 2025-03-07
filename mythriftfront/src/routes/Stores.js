import React from "react";
import Navbar from "../components/classicHome/navbar";
import CustomerNavbar from "../components/userHome/customerNavbar";
import Hero from "../components/common/Hero";
import Footer from "../components/classicHome/Footer";
import Store from "../components/classicHome/Store";
import { useAuth } from "../components/common/AuthContext"; 

function Stores() {
    const authContext = useAuth();

    return (
        <>
            {authContext.isAuthenticated ? <CustomerNavbar /> : <Navbar />}
            <Hero
                cName="hero-mid"
                heroImg="https://www.news-journalonline.com/gcdn/authoring/authoring-images/2023/08/16/NDNJ/70602629007-img-3595.JPG"
                title="Stores"
                btnClass="hide"
            />
            <Store />
            <Footer />
        </>
    );
}

export default Stores;
