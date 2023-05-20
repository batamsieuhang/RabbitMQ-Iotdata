import React from 'react';
import './App.css';
import { Routes, Route, Link } from "react-router-dom";
import WattChart from './Chart/Chart';
import ConnectionTable from './ConnectionTable/ConnectionTable';

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<ConnectionTable />} />
        <Route exact path="/chart" element={<WattChart />} />
        <Route path="/table" element={<ConnectionTable />} />
      </Routes>

      <div>
        <nav>
          <ul>
            <li>
              <Link to="/chart">Chart</Link>
            </li>
            <li>
              <Link to="/table">Table</Link>
            </li>
          </ul>
        </nav>
      </div>
    </>
  );
}

export default App;
