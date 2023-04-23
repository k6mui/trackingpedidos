import { useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';

import './css/Actualizar.css';

export default function Actualizar(props) {
  let location = useLocation();

  const [jsonData, setJsonData] = useState(null);

  function handleFileChange(event) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.readAsText(file);
    reader.onload = () => {
      const content = reader.result;
      const jsonData = JSON.parse(content);
      setJsonData(jsonData);
    };
  }

  useEffect(() => {
    if (jsonData) {
      jsonData.map(json => {
        props.enviarDatos(location.pathname, json)
        } );
    }
  }, [jsonData]);

  return <div id="actualizar">
    <div className="autorrellenar">
      <h1>Introduzca el pedido que desea añadir</h1>
        <form>
          <input type="text" placeholder="ID del pedido" value={props.datos.pedido} onChange={e => props.setDatos({ ...props.datos, pedido: e.target.value })} />
          <input type="text" placeholder="Nombre del producto" value={props.datos.producto} onChange={e => props.setDatos({ ...props.datos, producto: e.target.value })} />
          <input type="text" placeholder="Descripcion del producto" value={props.datos.descripcion} onChange={e => props.setDatos({ ...props.datos, descripcion: e.target.value })} />
          <input type="text" placeholder="Cliente" value={props.datos.cliente} onChange={e => props.setDatos({ ...props.datos, cliente: e.target.value })} />
          <input type="text" placeholder="Matrícula del transporte" value={props.datos.transportista} onChange={e => props.setDatos({ ...props.datos, transportista: e.target.value })} />

          <button type="button" onClick={() => {
            props.enviarDatos(location.pathname, props.datos);
            props.setDatos({ pedido: "", producto: "", descripcion: "", cliente:"", transportista:""});
          }}>Enviar</button>
        </form>
    </div>
    <div className="drag">
      <input className="fichero" type="file" accept=".json" onChange={handleFileChange}/>
    </div>
</div>
}



