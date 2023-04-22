import Rating from 'react-rating';

import './css/Admin.css';

export default function Admin(props) {
    let productos = props.productos;
    let entregados = productos.filter(producto => producto.estado === "ENTREGADO");
    
    let clientes = productos
        .reduce((acc, producto) => {
            if (!acc.find(item => item.cliente === producto.cliente)) 
                acc.push(producto);
            return acc;
        }, [])

    let res_prod = entregados.map(producto => producto.res_prod);
    let media_prod = (res_prod.reduce((accumulator, currentValue) => accumulator + currentValue))/ res_prod.length;
    
    let res_envio = entregados.map(producto => producto.res_envio);
    let media_envio = (res_envio.reduce((accumulator, currentValue) => accumulator + currentValue))/ res_envio.length;

    return <div id="admin">
        <h1 className='titulo'>Estadísticas Empresa:</h1>
        <div className="resumen">
            <div className="card">
                <div className="card_izq">
                    <h2 className='tit_card'>Clientes</h2>
                    <h3 className='subtit_card'>Usuarios con pedidos en activo</h3>
                </div>
                <div className="card_der">
                    <h2 className='datos'>{clientes.length}</h2>
                </div>
            </div>

            <div className="card">
                <div className="card_izq">
                    <h2 className='tit_card'>Productos</h2>
                    <h3 className='subtit_card'>Cantidad de productos en activo</h3>
                </div>
                <div className="card_der">
                    <h2 className='datos'>{productos.length}</h2>
                </div>
            </div>

            <div className="card">
            <h2 className='tit_med'>Media de valoraciones</h2>
                <div className="medias">
                        <div className='media_prod'>
                            <h3>Productos</h3>
                            <Rating
                                emptySymbol={ < i className="bi bi-star" /> }
                                fullSymbol={ < i className="bi bi-star-fill" /> }
                                initialRating={media_prod}
                                readonly={true}
                            />
                        </div>
                        <div className='media_env'>
                            <h3>Envíos</h3>
                            <Rating
                                emptySymbol={ < i className="bi bi-heart" /> }
                                fullSymbol={ < i className="bi bi-heart-fill" /> }
                                initialRating={media_envio}
                                readonly={true}
                            />
                        </div>
                </div>
            </div>

        </div>

        <h1 className='subtitulo'>Valoraciones por ID del producto:</h1>
        <div className='valoraciones'>
            {entregados
                .map((producto, index) => {
                    return <div className="tarjeta" key={index}>
                        <span>ID: {producto.id}</span>
                        <div className='reseña'>
                        <Rating
                            emptySymbol={ < i className="bi bi-star" /> }
                            fullSymbol={ < i className="bi bi-star-fill" /> }
                            initialRating={media_prod}
                            readonly={true}
                        />
                        <Rating
                            emptySymbol={ < i className="bi bi-heart" /> }
                            fullSymbol={ < i className="bi bi-heart-fill" /> }
                            initialRating={media_envio}
                            readonly={true}
                        />
                        </div>                          
                    </div> 
                })
            }
        </div>

        
    </div>
} 