import { useParams } from 'react-router-dom';
import Navbar from "../components/classicHome/navbar";
import Footer from "../components/classicHome/Footer";
import Products from "../components/classicHome/Products";
import { useAuth } from "../components/common/AuthContext"; 
import CustomerNavbar from "../components/userHome/customerNavbar";

function StoreProducts() {
    const { username } = useParams()
    const authContext = useAuth();
  
  return (
    <>
        {authContext.isAuthenticated ? <CustomerNavbar /> : <Navbar />}
        <Products username={username}/>
         <Footer/>
    </>
);
}

export default StoreProducts;
