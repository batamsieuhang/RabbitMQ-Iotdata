import React, { useState } from 'react';
import "./BackDrop.css"

const Backdrop = ({ show, onClose, onPostData }) => {
    const [formData, setFormData] = useState({ id: "", name: "", status: "on", have_turn_on: "no" });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSave = () => {
        const { id, name } = formData;
        if (id >= 1 && id <= 10 && name.length > 0) {
            onPostData(formData);

            setFormData({ id: "", name: "", status: "on", have_turn_on: "no" });

            onClose();
        } else {
            alert("Vui lòng nhập ID từ 1 đến 10 và Name không được để trống.");
        }
    };

    return (
        show && (
            <div className="backdrop">
                <div className="backdrop-content">
                    <h2>Enter new data information</h2>
                    <input
                        type="text"
                        name="id"
                        value={formData.id}
                        onChange={handleChange}
                        placeholder="ID"
                    />
                    <input
                        type="text"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        placeholder="Name"
                    />
                    <div className="listButton">
                        <div><button onClick={onClose}>Cancle</button></div>
                        <div><button onClick={handleSave}>Add</button></div>
                    </div>
                </div>
            </div>
        )
    );
};

export default Backdrop;