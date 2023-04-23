import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Rating from 'react-rating';

import './css/Resena.css';


export default function Reseña(props) {
    let location = useLocation();
    let navigate = useNavigate();

    let prod_id = location.state.prod_id;
    let pedido = location.state.pedido;

    let array_productos = props.productos.filter(producto => (producto.pedido === pedido) && (producto.id === prod_id));
    let producto = array_productos[0];

    return <div id="resena">
         <button className='but_bi-caret-left' onClick={ () => navigate(`/cliente/${props.email}/historial`)}>
            <i className="bi bi-caret-left"></i>
        </button>

        <div className='detalle-container'>
            <div className='card_encabezado'>
                <h1 className='titulo'>Producto: {producto.nombre}</h1>
                <div className='identificadores'>
                    <span>ID Pedido: {producto.pedido}</span>
                    <span>ID Producto: {producto.id}</span>
                </div>
            </div>

            <div className='card_valor'>
                <div className='valor_prod'>
                    <h2>Valoración del producto:</h2>
                    <div className='star_producto'>
                    <Rating
                        emptySymbol={ < i className="bi bi-star" /> }
                        fullSymbol={ < i className="bi bi-star-fill" /> }
                        initialRating={producto.res_prod}
                        onChange={(value) => props.cambio_res(value, prod_id, pedido, "producto")}
                        readonly={false}
                    />
                    </div>
                </div>

                <div className='valor_envio'>
                    <h2>Experiencia con el envio:</h2>
                    <div className='star_producto'>
                    <Rating
                        emptySymbol={ < i className="bi bi-heart" /> }
                        fullSymbol={ < i className="bi bi-heart-fill" /> }
                        initialRating={producto.res_envio}
                        onChange={(value) => props.cambio_res(value, prod_id, pedido, "envio")}
                        readonly={false}
                    />
                    </div>
                </div>                

            </div>

            <div className='card_escrito'>
                <h2>Cuentanos tu experiencia:</h2>
                <textarea className='escrito' placeholder='Escribe aqui... (máximo 500 caracteres)' maxLength={500} defaultValue={producto.res_esc}
                    onChange={(event) => props.cambio_res(event.target.value, prod_id, pedido, "escrito")}></textarea>
            </div>

            <div className="guardar_res">
                <button className='boton_guardar' onClick={() => props.actualiza(location.pathname, producto)}> Guardar </button>
            </div>                    
        </div>
    </div>
} 