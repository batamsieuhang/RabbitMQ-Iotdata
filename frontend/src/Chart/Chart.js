import React, { useEffect } from 'react';
import { useState } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS } from 'chart.js/auto';
import axios from 'axios';
import './Chart.css';

const WattChart = () => {
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
    
      useEffect(() => {
        const drawChart = async () => {
          axios.get(`http://192.168.153.135:8080/api/v1/iotdata`).then((res) => {
            console.log(res.data)
            setUserData({
              labels: res.data.slice(-500).map((item) => item.iotDate),
              datasets: [
                {
                  label: "Wattege",
                  data: res.data.slice(-500).map(item => item.watValue),
                  backgroundColor: ["#99FFFF"],
                  borderColor: ["black"],
                }
              ]
            })
          })
        };
    
        drawChart();
    
        const interval = setInterval(() => {
          drawChart();
        }, 10000); // 10 seconds in milliseconds
    
        return () => clearInterval(interval);
      }, []);

    return (
        <>
            <div className="container">
                <div className="chart">
                    <Line data={userData} />
                </div>
            </div>
        </>
    );
};

export default WattChart;