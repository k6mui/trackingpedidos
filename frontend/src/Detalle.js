import Map from "./Map";

import { useLocation, useNavigate } from 'react-router-dom';

import './css/Detalle.css';

export default function Detalle(props) {
    // Variable que tiene la localización donde nos encontramos, se utiliza principalmente para saber nuestra ruta actual.
    let location = useLocation();
    // Variable utilizada para navegar entre paginas.
    let navigate = useNavigate();

    // Variables referidas al numero del pedido dentro de los pedidos y su ID de producto y de pedido. Todas obtenidas por el estado de la localización.
    let num_pedido = location.state.index;
    let prod_id = location.state.prod_id;
    let pedido = location.state.pedido;

    // Nos quedamos con el producto cuyo ID de producto y pedido coincide con el esperado.
    let array_productos = props.productos.filter(producto => (producto.pedido === pedido) && (producto.id === prod_id));
    let producto = array_productos[0];

    // Logica utilizada para mostrar el porcentaje de carga en la barra de progreso.
    let valor_estado = 0;
    switch(producto.estado) {
        case "INICIADO":
            valor_estado = 0;
            break;
        case "TRANSITO":
            valor_estado = 50;
            break;
        case "ENTREGADO":
            valor_estado = 100;
            break;
        default:
            valor_estado = 50;
    }

    // Renderizado de la página.
    return <div id="detalle">

        <button className='but_bi-caret-left' onClick={() => {
            props.setUbicaciones([]);
            navigate(`/cliente/${props.email}/${producto.pedido}`, {state: {pedido: producto.pedido, index: num_pedido}});
            return 0;
          }}> 
            <i className="bi bi-caret-left"></i>
        </button>

        <div className='detalle-container'>
            <div className='card_encabezado'>
                <h1 className='titulo'>Producto: {producto.nombre}</h1>
                <span>ID: {producto.id}</span>
            </div>
            
            <div className='card_descrip'>
                <h2 className='empresa'>Descripción del producto:</h2>
                <h3 className='descripcion'>{producto.descripcion}</h3>
            </div>
            
            <div className='card_info'>
                <h2>Información del producto:</h2>
                <h3>
                    Este producto ha sido comprado a "{producto.empresa}" en el pedido cuyo ID es {producto.pedido}.
                </h3>
            </div>
            
            <div className='card_progress'>
                <h2 className='estado'> <u> Estado de la entrega: </u> </h2>
                <div className='progreso'>
                    <i className="bi bi-flag-fill"></i>
                    <progress value={valor_estado} max="100" data-label={producto.estado}></progress>
                    <i className="bi bi-check-circle-fill"></i>
                </div>
            </div>

            <div className="container_map">
                <button className="boton_mapa" onClick={() => props.download("ubicaciones")}>Cargar ubicaciones</button>
                <Map ubicaciones={props.ubicaciones}/>
            </div>
            
        </div>
        
    </div>
} 