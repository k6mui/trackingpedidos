import Rating from 'react-rating';

import './css/Admin.css';

export default function Admin(props) {
    // Variable local que tiene los pedidos pasados por las props.
    let productos = props.productos;

    // Variable que almacena solamente los pedidos con estado ENTREGADO.
    let entregados = productos.filter(producto => producto.estado === "ENTREGADO");

    // Se crean todas las variables para el caso de que no se haya entregado aun ningun pedido.
    let clientes = 0;
    let res_prod = 0;
    let media_prod = 0;
    let res_envio = 0;
    let media_envio = 0;

    if (entregados.length !== 0){
        // Variable que contabiliza el numero de clientes que tiene una empresa.
        clientes = productos
        .reduce((acc, producto) => {
            if (!acc.find(item => item.cliente === producto.cliente)) 
                acc.push(producto);
            return acc;
        }, [])
        
        // Variable que almacena todas las reseñas referidas al producto para calcular posteriormente la media de estas.
        res_prod = entregados.map(producto => producto.res_prod);
        media_prod = (res_prod.reduce((accumulator, currentValue) => accumulator + currentValue))/ res_prod.length;

        // Variable que almacena todas las reseñas referidas al envio para calcular posteriormente la media de estas.
        res_envio = entregados.map(producto => producto.res_envio);
        media_envio = (res_envio.reduce((accumulator, currentValue) => accumulator + currentValue))/ res_envio.length;
    }

    // Renderizado de la página.
    return <div id="admin">
        { entregados.length === 0 ? (
            <h2 className="no_prod">
                Lo sentimos. Aún no se ha entregado ninguno producto.
            </h2>
        ) : (
        <>
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
                            initialRating={producto.res_prod}
                            readonly={true}
                        />
                        <Rating
                            emptySymbol={ < i className="bi bi-heart" /> }
                            fullSymbol={ < i className="bi bi-heart-fill" /> }
                            initialRating={producto.res_envio}
                            readonly={true}
                        />
                        </div>                          
                    </div> 
                })
            }
        </div>
        </>
    )}
    </div>
} 