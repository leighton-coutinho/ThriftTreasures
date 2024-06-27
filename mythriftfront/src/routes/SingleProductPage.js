import { useParams } from 'react-router-dom';
import Navbar from "../components/classicHome/navbar";
import Footer from "../components/classicHome/Footer";
import SingleProduct from '../components/classicHome/SingleProduct';

function SingleProductPage() {
    const { username, itemid } = useParams()
  
  return (
    <>
        <Navbar></Navbar>
        <SingleProduct username={username} itemid={itemid} />
         <Footer/>
    </>
);
}

export default SingleProductPage;