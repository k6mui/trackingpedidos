import React from 'react';
import ReactDOM from 'react-dom/client';

import App from './App.js';

import {BrowserRouter} from "react-router-dom";

import './css/index.css';

// Creamos un DOM virtual para la utilizacion de React.
const root = ReactDOM.createRoot(document.getElementById('root'));

// Renderizado de la App dentro de un Router ya que navegaremos entre paginas.
root.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>
);
