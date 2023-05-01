import Header from "./Header";
import Barra from "./Barra.js";
import Pedidos from "./Pedidos";
import Productos from "./Productos";
import Detalle from "./Detalle";
import Transportista from "./Transportista";
import Login from "./Login";
import Historial from "./Historial";
import Resena from "./Resena";
import Admin from "./Admin";
import Actualizar from "./Actualizar";


import {useState, useEffect} from "react";
import { Route, Routes, useLocation } from "react-router-dom";

import 'bootstrap-icons/font/bootstrap-icons.css';
import './css/App.css';

import { mockdata } from "./constants/productos";
import CONFIG from "./config/config";
const USE_SERVER = CONFIG.use_server;

function App() {
  // Estado donde almacenamos los productos referidos a un usuario.
  const [productos, setProductos] = useState();

  let ejemplo = [
    {
      "id": 1,
      "latitud": 80,
      "longitud": 40,
      "matricula": "2341AAA"
    },
    {
      "id": 2,
      "latitud": 70,
      "longitud": 40,
      "matricula": "2341AAA"
    },
    {
      "id": 3,
      "latitud": 60,
      "longitud": 40,
      "matricula": "2341AAA"
    }];
  // Estado donde almacenamos los productos referidos a un usuario.
  const [ubicaciones, setUbicaciones] = useState(ejemplo);

  // Estado utilizado para mostar la pagina login en la ruta por defecto.
  const [login, setLogin] = useState(true);

  // Estados referidos al nombre del usuario y al rol que tiene dentro de la App.
  const [email, setEmail] = useState('');
  const [rol, setRol] = useState('');

  // Estado utilizado para la introducción de nuevos pedidos por parte de la empresa.
  const [actualizar, setActualizar] = useState({ pedido: "", producto: "", descripcion: "", cliente:"", transportista:""});

  // Estado utilizado para mostrar si un archivo se ha subido al introducir pedidos por la empresa.
  const [subido, setSubido] = useState(false);

  // Estado utilizado para enviar los pedidos de un archivo.
  const [jsonData, setJsonData] = useState(null);

  // Variable que tiene la localización donde nos encontramos, se utiliza principalmente para saber nuestra ruta actual.
  let location = useLocation();

  // Funcion para realizar un fetch a los pedidos y guardarlos en el estado productos.
  const download = async (objeto) => {
    if (USE_SERVER){
      try {
        const res = await fetch("http://localhost:8080"+location.pathname);
        const data = await res.json();
        objeto==="productos" ?
          setProductos(data)
        :
          setUbicaciones(data);
      } catch (e) {
        console.log("No se ha podido recuperar.");
      }
    }else{
      setProductos(mockdata.productos);
    }
  };
  
  // Hook que ejecuta la funcion download() al quitarse el login.
  useEffect ( () => {
    async function fetchData() {
      await download("productos");	
    }
    if (!login)
      fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps 
  }, [login]);

  // Setter para el estado email.
  function handleEmail(event) {
    setEmail(event.target.value);
  };

  // Setter para el estado rol.
  function handleRol(event) {
    setRol(event.target.value);
  };

  // Funcion utilizada en la interfaz del transportista para cambiar el estado del pedido en el frontend.
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

  // Funcion utilizada en la interfaz del usuario para cambiar las reseñas del pedido en el frontend.
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

  // Funcion que actualiza los campos de un pedido en el backend utilizado tanto en transportista como en reseña.
  const putDatos = async (ruta, newData) => {
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
  };

  // Funcion que envia nuevos pedidos al backend utilizado en admin.
  const enviarDatos = async (ruta, datos) => {
    console.log(datos);
    try {
      const response = await fetch("http://localhost:8080" + ruta, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      const respuesta = await response.json();
      console.log(respuesta);
    } catch (error) {
      console.error(error);
    }
  };

  // Funcion que envia la posicion actual del transportista al backend utilizado en transportista.
  const enviarLocalizacion = async (ruta, posicion) => {
    try {
      const response = await fetch("http://localhost:8080" + ruta, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          latitud: posicion.coords.latitude,
          longitud: posicion.coords.longitude,
        }),
      });
      const respuesta = await response.json();
      console.log(respuesta);
    } catch (error) {
      console.error(error);
    }
  };

  // Renderizado de la página.
  return(
  <div className="App">
    {login ? (
      < Login email={email} rol={rol} handleEmail={handleEmail} handleRol={handleRol} setLogin={setLogin} />
    ) : (
      <>
        <div className="barra">
          < Barra email={email} rol={rol} />
        </div>

        <div className="principal">
          < Header email={email} rol={rol} />
        {productos && < Routes >
          < Route path="/cliente/:clienteId" element= { < Pedidos productos={productos} email={email} /> } />
          < Route path="/cliente/:clienteId/:pedidoId" element= { < Productos productos={productos} email={email} /> } />
          < Route path="/cliente/:clienteId/:pedidoId/:productoId" element= { < Detalle productos={productos} email={email} ubicaciones={ubicaciones} download={download}/> } />
          < Route path="/transportista/:matricula" element= { < Transportista productos={productos} cambio_estado={cambio_estado} actualiza={putDatos} enviarLocalizacion={enviarLocalizacion}/> } />
          < Route path="/cliente/:clienteId/historial" element= { < Historial productos={productos} email={email} /> } />
          < Route path="/cliente/:clienteId/historial/:productoId" element= { < Resena productos={productos} email={email} cambio_res={cambio_res} actualiza={putDatos} /> } />
          < Route path="/empresa/:empresaId" element= { < Admin productos={productos} /> } />
          < Route path="/empresa/:empresaId/actualizar" element= { < Actualizar datos={actualizar} setDatos={setActualizar} enviarDatos={enviarDatos} 
              subido={subido} setSubido={setSubido} jsonData={jsonData} setJsonData={setJsonData}/> } />
        </ Routes >
        }      
        </div>
    </>
    )}
  </div>
);
}

export default App;