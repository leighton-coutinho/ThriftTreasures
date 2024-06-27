import Navbar from "../components/classicHome/navbar";
import Hero from "../components/common/Hero";
import Footer from "../components/classicHome/Footer";
import ContactForm from "../components/classicHome/ContactForm";


function Contact () {
    return (
        <>
            <Navbar></Navbar>
            <Hero
            cName="hero-mid"
            heroImg="https://cpsns.ns.ca/wp-content/uploads/2023/03/AdobeStock_545330924-1.jpg"
            title="Contact Us"
            btnClass="hide"
             />
             <ContactForm/>
            <Footer/>
        </>
    );
}

export default Contact;