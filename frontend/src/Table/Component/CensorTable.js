import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './CensorTable.css';

const CensorTable = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
    const interval = setInterval(() => {
      fetchData();
    }, 1000); // 1 seconds in milliseconds
    return () => clearInterval(interval);
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get(`http://192.168.60.133:8080/api/v1/censor`);
      setData(response.data.filter((item) => item.id >= 1 && item.id <= 10)
      .slice().sort((a, b) => a.id - b.id));
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const handlePostData = async (rowData) => {
    try {
      const response = await axios.post(`http://192.168.60.133:8080/api/v1/check`, rowData);
      console.log("Post data:", response.data);
    } catch (error) {
      console.error("Error:", error);
    }
  };
  
  const handleButtonClick = (rowData) => {
    if (rowData.have_turn_on === "yes") {
        handlePostData(rowData);
      } else if (rowData.have_turn_on === "no") {
        handlePostData(rowData);
        // Xử lý thêm khi trạng thái là "no"
      }
  };

  const renderTableRows = () => {
    return data.map((row) => (
        <tr key={row.id}>
          <td>{row.id}</td>
          <td>{row.name}</td>
          <td>{row.have_turn_on === "yes" ? (
            <div>On</div>
          ) : (
            <div>Off</div>
          )}</td>
          <td>
            {row.have_turn_on === "yes" ? (
              <button onClick={() => handleButtonClick(row)}>-</button>
            ) : (
              <button onClick={() => handleButtonClick(row)}>+</button>
            )}
          </td>
        </tr>
      ));
  };

  return (
    <>
      <h1>Table Of Censors</h1>
      <table className="tableStyle">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {renderTableRows()}
        </tbody>
      </table>
    </>
  );
};

export default CensorTable;
