import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './ConnectionTable.css';

const ConnectionTable = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get(`http://192.168.153.135:15672/api/connections`);
      console.log(response.data)
      setData(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const renderTableRows = () => {
    let count = 1;
    return data.map((item) => (
      <tr key={count++}>
        <td>{count}</td>
        <td>{item.name}</td>
        <td>{item.node}</td>
        {/* Thêm các cột khác tùy theo dữ liệu từ API */}
      </tr>
    ));
  };

  return (
    <div>
      <h1>Table Of Connections</h1>
      <table class="tableStyle">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Node</th>
          </tr>
        </thead>
        <tbody>
        {renderTableRows()}
        </tbody>
      </table>
    </div>
  );
};

export default ConnectionTable;
