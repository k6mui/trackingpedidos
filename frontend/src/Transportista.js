import './css/Transportista.css';

export default function Transportista(props) {
    let productos = props.productos;

    return <div id="transporte">
        <div className='transporte_container'>
            <div className='card_encabezado'>
                <h1 className='titulo'>Envíos de hoy:</h1>
                <span>ID: {productos[0].transportista}</span>
            </div>

            <div className="iniciar_tray">
                <label htmlFor="boton_iniciar" className='label_iniciar'>Iniciar trayecto: </label>
                <button className='boton_iniciar'> Empezar </button>
            </div>           

            <div className="card_container">
            {productos
                .map((producto, index) => {
                    return <div className="card" key={index}>
                        <h2 className='identificador'>ID: {producto.id}</h2>

                        <div className='der_card'>
                            <button className='card_boton'> Entregado</button>
                        </div>
                    </div> 
                })
            }
            </ div>
            
        </div>

        
    </div>
} 