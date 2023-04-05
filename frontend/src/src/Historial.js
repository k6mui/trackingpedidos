import './css/Historial.css';
import { useNavigate } from 'react-router-dom';

export default function Historial(props) { 
    let navigate = useNavigate();
    let prod_client = props.productos
        .filter(producto => producto.estado === "ENTREGADO");

    return <div id="historial">
        <h1 className='titulo'>Mi historial:</h1>
        <div className='card-container'>
        {(prod_client.length === 0)
            ? <h2 className='no_historial'>Lo sentimos, al parecer no has recibido ningún producto.</h2>
            : prod_client                       
                .map((producto, index) => {
                    return <div className="card" key={index}>
                        <div className='izq_card'>
                            <h2>{producto.nombre}</h2>
                        </div>

                        <div className='der_card'>
                            <div className='identificadores'>
                                <span>ID Pedido: {producto.pedido}</span>
                                <span>ID Producto: {producto.id}</span>
                            </div>
                            <button className='but_bi-caret-right' onClick={ () => 
                                    navigate(`/cliente/${props.email}/historial/${producto.id}`, {state: {prod_id: producto.id, pedido: producto.pedido}})} >
                                <i className="bi bi-caret-right"></i>
                            </button>
                        </div>
                    </div> 
        })}
        </div>
        
    </div>
}