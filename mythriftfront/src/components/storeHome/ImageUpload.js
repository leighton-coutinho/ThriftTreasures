// ImageUpload.js
import React from 'react';

const ImageUpload = ({ onChange }) => {
    return (
        <div className="image-upload">
            <label>Upload Image</label>
            <input type="file" accept="image/*" onChange={onChange} />
        </div>
    );
};

export default ImageUpload;
