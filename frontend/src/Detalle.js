import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';


import './css/Detalle.css';

export default function Detalle(props) {
    let location = useLocation();
    let navigate = useNavigate();

    let num_pedido = location.state.index;
    let prod_id = location.state.prod_id;
    let pedido = location.state.pedido;

    let array_productos = props.productos.filter(producto => (producto.pedido === pedido) && (producto.id === prod_id));
    let producto = array_productos[0];

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

    return <div id="detalle">

        <button className='but_bi-caret-left' onClick={ () => navigate(`/cliente/${props.email}/${producto.pedido}`, {state: {pedido: producto.pedido, index: num_pedido}}) } >
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
            
        </div>

        
    </div>
} 