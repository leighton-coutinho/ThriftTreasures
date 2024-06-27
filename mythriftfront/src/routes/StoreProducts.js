import { useParams } from 'react-router-dom';
import Navbar from "../components/classicHome/navbar";
import Footer from "../components/classicHome/Footer";
import Products from "../components/classicHome/Products";

function StoreProducts() {
    const { username } = useParams()
  
  return (
    <>
        <Navbar></Navbar>
        <Products username={username}/>
         <Footer/>
    </>
);
}

export default StoreProducts;
