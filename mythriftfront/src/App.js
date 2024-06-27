import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';

import CreatePatient from './components/storeHome/CreatePatient';
import Navbar from './components/classicHome/navbar';
import Home from './routes/Home';
import Contact from './routes/Contact';
import Stores from './routes/Stores';
import About from './routes/About';
import SignUp from './routes/SignUp';
import StoreHome from './routes/storeHome';
import LoginComponent from './components/storeHome/LoginComponent';
import AuthProvider from './components/common/AuthContext';
import Sales from './routes/Sales';
import AddNewItemR from './routes/AddNewItemR';
import StoreProducts from './routes/StoreProducts';
import SingleProductPage from './routes/SingleProductPage';


const App = () => {
  return (
    <AuthProvider>
      <Router>
        <div>
          <Routes>
            <Route exact path='/' element={<Home />} />
            <Route path='/store' element={<StoreHome />} />
            <Route path='/about' element={<About />} />
            <Route path='/service' element={<Stores />} />
            <Route path='/contact' element={<Contact />} />
            <Route path='/login' element={<LoginComponent />} />
            <Route path='/signup' element={<SignUp />} />
            <Route path='/sales' element={<Sales />} />
            <Route path='/add' element={<AddNewItemR />} />
            <Route path="/stores/:username/products" element={<StoreProducts />} />
            <Route path="/stores/:username/products/:itemid" element={<SingleProductPage />} />
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  );
};

export default App;
