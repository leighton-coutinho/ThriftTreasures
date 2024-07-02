import Destination from "../components/classicHome/Submission";
import Footer from "../components/classicHome/Footer";
import Hero from "../components/common/Hero";
import Store from "../components/classicHome/Store";
import Navbar from "../components/classicHome/navbar";
import Submission from "../components/classicHome/Submission";

function Home () {


    return (
        <>
            <Navbar></Navbar>
            <Hero
            cName="hero"
            heroImg="https://img.freepik.com/premium-photo/fashion-store-advertisment-background-with-copy-space_118124-98655.jpg?w=1060"
            title="The platform for listing"
            text="List your best pieces"
            buttonText="Sign In As Store"
            url="/login"
            btnClass="show"
            url2="/login2"
            buttonText2="Sign In As User"
             />
             <Submission/>
             <Store/>
             <Footer/>
        </>
    );
}

export default Home;