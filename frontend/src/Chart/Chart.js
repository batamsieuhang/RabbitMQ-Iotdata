import React, { useRef } from 'react';
import { useState } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS } from 'chart.js/auto';
import axios from 'axios';
import './Chart.css';

const WattChart = () => {
    const [id, setId] = useState('');
    const idRef = useRef('');

    const [userData, setUserData] = useState({
        datasets: [
            {
                lable: "",
                data: 0,
                backgroundColor: ["#99FFFF"],
                borderColor: ["black"],
            }
        ]
    });

    const handleInputChange = (event) => {
        setId(event.target.value);
    };

    const handleButtonClick = () => {
        const drawChart = async () => {
            const currentId = idRef.current;

            console.log(id);
            axios.get(`http://192.168.153.135:8080/api/v1/iotdata/${currentId}`).then((res) => {
                console.log(res.data)
                setUserData({
                    labels: res.data.slice(-300).map((item) => item.iotDate),
                    datasets: [
                        {
                            label: "Wattege",
                            data: res.data.slice(-300).map(item => item.watValue),
                            backgroundColor: ["#99FFFF"],
                            borderColor: ["black"],
                        }
                    ]
                })
            })
        };

        idRef.current = id;

        drawChart();

        const interval = setInterval(() => {

            drawChart();
        }, 1000); // 1 seconds in milliseconds

        return () => clearInterval(interval);
    };

    return (
        <>
            <div className="container">
                <div className="chart">
                    <div>
                        <Line data={userData} />
                    </div>
                    <div className="style">Graph showing the power consumption of a plug (W)</div>
                </div>

                <div className="form">
                    <input className="idInput" placeholder="Enter plug id" type="text" value={id} onChange={handleInputChange} />
                    <button className="draw" onClick={handleButtonClick}>Get Data</button>
                </div>
            </div>
        </>
    );
};

export default WattChart;