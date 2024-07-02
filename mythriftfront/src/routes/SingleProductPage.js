import { useParams } from 'react-router-dom';
import Navbar from "../components/classicHome/navbar";
import Footer from "../components/classicHome/Footer";
import SingleProduct from '../components/classicHome/SingleProduct';
import { useAuth } from "../components/common/AuthContext"; 
import CustomerNavbar from "../components/userHome/customerNavbar";

function SingleProductPage() {
    const { username, itemid } = useParams()
    const authContext = useAuth();
  
  return (
    <>
        {authContext.isAuthenticated ? <CustomerNavbar /> : <Navbar />}
        <SingleProduct username={username} itemid={itemid} />
         <Footer/>
    </>
);
}

export default SingleProductPage;