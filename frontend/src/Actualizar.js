import { useLocation } from 'react-router-dom';
import { useEffect, useRef } from 'react';

import './css/Actualizar.css';

export default function Actualizar(props) {
  // Variable que tiene la localización donde nos encontramos, se utiliza principalmente para saber nuestra ruta actual.
  let location = useLocation();

  // Hook utilizado para enlazar el input type file oculto al div.
  const fileInput = useRef(null);

  // Funcion utilizada para determinar si se ha introducido un archivo. Si es asi, se introducen los datos en el estado pertinente.
  function handleFileChange(event) {
    const file = event.target.files[0];
    if (!file) {
      return;
    }
    const reader = new FileReader();
    reader.readAsText(file);
    reader.onload = () => {
      const content = reader.result;
      const jsonData = JSON.parse(content);
      props.setJsonData(jsonData);
      props.setSubido(true);
    };
  }

  // Funcion utilizada para detectar el clic en el div como si fuera el input type file. 
  const handleClick = (event) => {
    event.preventDefault();
    fileInput.current.click();
    props.setSubido(false);
  };

  // Funcion utilizada para detectar el arrastre del archivo en el div como si fuera el input type file. 
  const handleDragOver = (event) => {
    event.preventDefault();
    props.setSubido(false);
  };

  // Funcion utilizada para detectar el soltar el archivo en el div como si fuera el input type file. 
  const handleDrop = (event) => {
    event.preventDefault();
    const file = event.dataTransfer.files[0];
    const reader = new FileReader();
    reader.readAsText(file);
    reader.onload = () => {
      const content = reader.result;
      const jsonData = JSON.parse(content);
      props.setJsonData(jsonData);
    };
  };

  // Hook utilizado para realizar una peticion POST mediante la funcion enviarDatos cuando se modifique el estado jsonData.
  useEffect(() => {
    if (props.jsonData) {
      props.jsonData.map(json => {
        props.enviarDatos(location.pathname, json)
        return null;
        } );
    }
    props.setJsonData(null);
    // eslint-disable-next-line react-hooks/exhaustive-deps 
  }, [props.jsonData]);

  // Renderizado de la página.
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
            return 0;
          }}>Enviar</button>

        </form>
    </div>
    <div className="drag" onMouseDown={handleClick} onDragOver={handleDragOver} onDrop={handleDrop}>
      <h1 className='seleccionar'>Seleccione el archivo:</h1>
      < i className="bi bi-cloud-arrow-up-fill" />
      <input type="file" accept=".json" ref={fileInput} onChange={handleFileChange} hidden />
      {props.subido && <h1 className='subido'> Archivo subido con exito</h1>}
    </div>
</div>
}