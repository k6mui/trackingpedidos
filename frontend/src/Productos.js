import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import './css/Productos.css';

export default function Productos(props) {
    // Variable que tiene la localización donde nos encontramos, se utiliza principalmente para saber nuestra ruta actual.
    let location = useLocation();

    // Variable utilizada para navegar entre paginas.
    let navigate = useNavigate();

    // Variables referidas al numero del pedido dentro de los pedidos, su ID de pedido y la empresa a la que pertenece. Todas obtenidas por el estado de la localización.
    let num_pedido = location.state.index;
    let pedido = location.state.pedido;
    let empresa = props.productos[0].empresa;

    // Variable que almacena solamente los productos en estado ENTREGADO de un ID de pedido exacto.
    let prod_pedido = props.productos
        .filter(producto => producto.pedido === pedido)
        .filter(producto => producto.estado !== "ENTREGADO");
        
    // Renderizado de la página.
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