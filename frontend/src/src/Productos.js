import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import './css/Productos.css';

export default function Productos(props) {
    let location = useLocation();
    let navigate = useNavigate();

    let num_pedido = location.state.index;
    let pedido = location.state.pedido;
    let empresa = props.productos[0].empresa;


    let prod_pedido = props.productos
        .filter(producto => producto.pedido === pedido)
        .filter(producto => producto.estado !== "ENTREGADO");
        

    return <div id="producto">
        <button className='but_bi-caret-left' onClick={ () => navigate(`/cliente/${props.email}`) } >
            <i className="bi bi-caret-left"></i>
        </button>
        <div className='pedido-container'>
            <div className='card_encabezado'>
                <h1 className='titulo'>Pedido {num_pedido} a {empresa}:</h1>
                <span>ID: {pedido}</span>
            </div>
            <div className="card-container">
            {prod_pedido
                .map((producto, index) => {
                    return <div className="card" key={index}>
                        <div className='izq_card'>
                            <h2>{producto.nombre}</h2>
                        </div>

                        <div className='der_card'>
                            <span>ID: {producto.id}</span>
                            <button className='but_bi-caret-right' onClick={ () => 
                                    navigate(`/cliente/${props.email}/${producto.pedido}/${producto.id}`, {state: {prod_id: producto.id, pedido: producto.pedido, index: num_pedido}})} >
                                <i className="bi bi-caret-right"></i>
                            </button>
                        </div>
                    </div> 
                })
            }
            </div>
        </div>

    </div>
} 