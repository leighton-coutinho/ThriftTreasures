import Navbar from "../components/classicHome/navbar";
import Hero from "../components/common/Hero";
import Footer from "../components/classicHome/Footer";
import AboutUs from "../components/classicHome/AboutUs";

function About () {
    return (
        <>
            <Navbar></Navbar>
            <Hero
            cName="hero-mid"
            heroImg="https://assets.website-files.com/5e175ef7e2aa64fa499f34a0/5fc7e9e439e77a3d6169d85d_thrifting.jpg"
            title="Who Are We?"
            btnClass="hide"
             />
             <AboutUs/>
            <Footer/>
        </>
    );
}

export default About;