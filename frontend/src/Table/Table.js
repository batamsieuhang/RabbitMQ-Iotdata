import React, { useEffect, useState } from "react";
import ConnectionTable from "./Component/ConnectionTable";
import CensorTable from "./Component/CensorTable";
import Backdrop from "./Component/BackDrop";
import axios from 'axios';
import './Table.css';

const Table = () => {
    const [showBackdrop, setShowBackdrop] = useState(false);
    const [existingCensor, setExistingCensor] = useState();

    useEffect(() => {
        getData();
        const interval = setInterval(() => {
            getData();
        }, 1000); // 1 seconds in milliseconds
        return () => clearInterval(interval);
    }, []);

    const getData = () => {
        axios.get(`http://192.168.60.133:8080/api/v1/censor`).then((res) => {
            setExistingCensor(res.data.filter((item) => item.id >= 1 && item.id <= 10).length);
        })
    }

    const handleAddData = () => {
        setShowBackdrop(true);
    };

    const handlePostData = async (rowData) => {
        if (existingCensor === 10) {
            alert("Bạn đã thêm tối đa 10 Censor.");
            return;
        }

        if (existingCensor < 10) {

            try {
                const response = await axios.post(`http://192.168.60.133:8080/api/v1/censor`, rowData);
                console.log("Post data:", response.data);
            } catch (error) {
                console.error("Error:", error);
            }

        } else {
            alert("Bạn đã thêm tối đa 10 Censor.");
        }
    };

    return (
        <div>
            <ConnectionTable />
            <div className="censor">
                <div className="censorTable"><CensorTable /></div>
                <div className="form">
                    <div className="addButton">
                        <button onClick={handleAddData}>Add censor</button>
                        <p>
                            {existingCensor} / 10 Censors
                        </p>
                    </div>
                    <Backdrop show={showBackdrop} onClose={() => setShowBackdrop(false)} onPostData={handlePostData} />
                </div>
            </div>
        </div>
    )
};

export default Table;