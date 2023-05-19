import React, { useEffect } from 'react';
import './App.css';
import { useState } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS } from 'chart.js/auto';
import axios from 'axios';

function App() {
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
  //const [id, setId] = useState(1);

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
    }, 1000); // 1 seconds in milliseconds

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="App">
      <div className="chart">
        <Line data={userData} />
      </div>

    </div>
  );
}

export default App;
