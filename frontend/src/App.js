import Header from "./Header";
import Barra from "./Barra.js";
import Pedidos from "./Pedidos";
import Productos from "./Productos";
import Detalle from "./Detalle";
import Transportista from "./Transportista";
import Historial from "./Historial";
import Resena from "./Resena";
import Admin from "./Admin";

import {useState, useEffect} from "react";
import { Route, Routes, useLocation } from "react-router-dom";

import 'bootstrap-icons/font/bootstrap-icons.css';

import CONFIG from "./config/config";
import { mockdata } from "./constants/productos";

import './css/App.css';

const USE_SERVER = CONFIG.use_server;

function App() {
  const [productos, setProductos] = useState();
  let location = useLocation();

  const download = async () => {
    if (USE_SERVER){
      try {
        const res = await fetch("http://localhost:8083"+location.pathname);
        const data = await res.json();
        setProductos(data);
        console.log("ok");
      } catch (e) {
        alert("No se ha podido recuperar.");
      }
    }else{
      setProductos(mockdata.productos);
    }
  }
  
  useEffect ( () => {
    async function fetchData() {
      await download();	
      return clearInterval;	
    }
    fetchData();  
  }, [location.pathname]);

  const cambio_res = (value, id_prod, id_ped, reseña) => {
    let aux = productos.map(producto => {
      if (producto.id !== id_prod || producto.pedido !== id_ped) {
        return producto;
      }

      switch (reseña) {
        case "producto":
          producto.res_prod = value;
          break;
        case "envio":
          producto.res_envio = value;
          break;
        case "escrito":
          producto.res_esc = value;
          break;
        default:
          break;
      }
      return producto;
    })
    setProductos(aux);
  };

  return <div className="App">
    <div className="barra">
      < Barra />
    </div>
    
    <div className="principal">
      < Header />
      {productos && < Routes >
        < Route path="/cliente/javi" element= { < Pedidos productos={productos} /> } />
        < Route path="/cliente/javi/:pedidoId" element= { < Productos productos={productos} /> } />
        < Route path="/cliente/javi/:pedidoId/:productoId" element= { < Detalle productos={productos} /> } />
        < Route path="/transportista" element= { < Transportista productos={productos} /> } />
        < Route path="/historial" element= { < Historial productos={productos} /> } />
        < Route path="/historial/producto=:productoId/resena" element= { < Resena productos={productos} cambio_res={cambio_res} /> } />
        < Route path="/admin" element= { < Admin productos={productos} /> } />
      </Routes>
      }      
    </div>
</div>
}

export default App;