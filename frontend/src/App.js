import Header from "./Header";
import Barra from "./Barra.js";
import Pedidos from "./Pedidos";
import Productos from "./Productos";
import Detalle from "./Detalle";
import Transportista from "./Transportista";
import Login from "./Login";

import {useState, useEffect} from "react";
import { Route, Routes, useLocation } from "react-router-dom";

import 'bootstrap-icons/font/bootstrap-icons.css';

import CONFIG from "./config/config";
import { mockdata } from "./constants/productos";

import './css/App.css';

const USE_SERVER = CONFIG.use_server;

function App() {
  const [productos, setProductos] = useState();
  const [login, setLogin] = useState(true);
  const [email, setEmail] = useState('');
  const [rol, setRol] = useState('');


  let location = useLocation();

  const download = async () => {
    if (USE_SERVER){
      try {
        const res = await fetch("http://localhost:8080"+location.pathname);
        const data = await res.json();
        setProductos(data);
      } catch (e) {
        console.log("No se ha podido recuperar.");
      }
    }else{
      setProductos(mockdata.productos);
    }
  }
  
  useEffect ( () => {
    async function fetchData() {
      await download();	
    }
    if (!login)
      fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps 
  }, [login]);

  function handleEmail(event) {
    setEmail(event.target.value);
  }

  function handleRol(event) {
    setRol(event.target.value);
  };

  const cambio_estado = (value, id_prod, id_ped) => {
    let aux = productos.map(producto => {
      if (producto.id !== id_prod || producto.pedido !== id_ped) {
        return producto;
      }
      producto.estado=value;
      return producto;
    })
    setProductos(aux);
  };

  async function updateData(ruta, newData) {
    try {
      const response = await fetch("http://localhost:8080"+ruta, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newData)
      });
      const result = await response.json();
      console.log(result);
    } catch (error) {
      console.error(error);
    }
  }

  return(
  <div className="App">
    {login ? (
      < Login email={email} rol={rol} handleEmail={handleEmail} handleRol={handleRol} setLogin={setLogin}/>
    ) : (
      <>
        <div className="barra">
          < Barra email={email}/>
        </div>

        <div className="principal">
          < Header email={email} rol={rol}/>
        {productos && < Routes >
          < Route path="/cliente/:clienteId" element= { < Pedidos productos={productos} email={email} /> } />
          < Route path="/cliente/:clienteId/:pedidoId" element= { < Productos productos={productos} email={email} /> } />
          < Route path="/cliente/:clienteId/:pedidoId/:productoId" element= { < Detalle productos={productos} email={email} /> } />
          < Route path="/transportista/:matricula" element= { < Transportista productos={productos} cambio_estado={cambio_estado} actualiza={updateData} /> } />
        </Routes>
        }      
        </div>
    </>
    )}
  </div>
);
}

export default App;