import { useNavigate } from 'react-router-dom';

import './css/Pedidos.css';

export default function Pedidos(props) {
    // Variable utilizada para navegar entre paginas.
    let navigate = useNavigate();

    // Variable que almacena solamente los productos en estado ENTREGADO.
    let prod_client = props.productos
        .filter(producto => producto.estado !== "ENTREGADO");

    // Renderizado de la página.
    return <div id="pedido">
        <h1 className='titulo'>Mis pedidos:</h1>
        <div className="card-container">
        {(prod_client.length === 0)
            ? <h2 className='no_pedido'>Lo sentimos, al parecer no tienes ningún pedido en activo.</h2>
            : prod_client
                .reduce((acc, producto) => {
                    if (!acc.find(item => item.pedido === producto.pedido)) 
                        acc.push(producto);
                    return acc;
                }, [])
                .map((producto, index)=> {
                    return <div className="card" key={index}>
                        <div className='izq_card'>
                            <h2>Pedido {index+1} a {producto.empresa}</h2>
                        </div>

                        <div className='der_card'>
                            <span>ID: {producto.pedido}</span>
                            <button className='but_bi-caret-right' onClick={ () =>
                                    navigate(`${producto.pedido}`, {state: {pedido: producto.pedido, index: index+1}}) }>
                                <i className="bi bi-caret-right"></i>
                            </button>
                        </div>
                    </div> 
                })
        }
        </div>
    </div>
} 