import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import InputField from './InputField';
import ImageUpload from './ImageUpload';
import './AddNewItem.css';
import { useAuth } from '../common/AuthContext';
import axios from 'axios'; // Add axios for making HTTP requests

const AddNewItem = () => {
    const [itemData, setItemData] = useState({
        name: '',
        desc: '',
        category: '',
        price: '',
        image: null,
    });

    const authContext = useAuth();

    const [itemAdded, setItemAdded] = useState(false); // State to handle form visibility
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setItemData({ ...itemData, [name]: value });
    };

    const handleImageChange = (e) => {
        setItemData({ ...itemData, image: e.target.files[0] });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // Prepare the data to be sent
        const formData = new FormData();
        formData.append('name', itemData.name);
        formData.append('desc', itemData.desc);
        formData.append('category', itemData.category);
        formData.append('price', itemData.price);
        formData.append('image', itemData.image);

        try {
            await axios.put(`http://localhost:8081/${authContext['username']}/add`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            setItemAdded(true); // Set itemAdded to true after submission
            setTimeout(() => navigate('/store'), 3000); // Redirect after 3 seconds
        } catch (error) {
            console.error('There was an error adding the item!', error);
        }
    };

    return (
        <div className="add-new-item">
            {itemAdded ? (
                <div className="item-added-message">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRb3_pJsb6KhNN-oJW-u0vcbWkjhx9DbHBc5Q&s" alt="Success" />
                    <h2>Item Added!</h2>
                </div>
            ) : (
                <>
                    <h1>Add New Item</h1>
                    <form onSubmit={handleSubmit}>
                        <ImageUpload onChange={handleImageChange} />
                        <InputField
                            label="Name"
                            type="text"
                            name="name"
                            value={itemData.name}
                            onChange={handleChange}
                        />
                        <InputField
                            label="Description"
                            type="text"
                            name="desc"
                            value={itemData.desc}
                            onChange={handleChange}
                        />
                        <InputField
                            label="Category"
                            type="text"
                            name="category"
                            value={itemData.category}
                            onChange={handleChange}
                        />
                        <InputField
                            label="Price"
                            type="number"
                            name="price"
                            value={itemData.price}
                            onChange={handleChange}
                        />
                        <button type="submit">Add Item</button>
                    </form>
                </>
            )}
            <div className='push'></div>
        </div>
    );
};

export default AddNewItem;
