import { useNavigate } from 'react-router-dom';


import './css/Barra.css'

export default function Barra(props) {  
    let navigate = useNavigate();


    return <div className='lateral'>
        <div className='desplegar'>
            <button className='bot_desplegar'>
                <div></div>
                <div></div>
                <div></div>
            </button>
        </div>

        <div className='iconos'>
            <button className='but_bi-search' disabled>
                <i className="bi bi-search"></i>
            </button>

            {(props.rol=="cliente" || props.rol=="empresa") && 
                <button className='but_bi-house-door-fill' onClick={ () => navigate(`/${props.rol}/${props.email}`) } >
                    <i className="bi bi-house-door-fill"></i>
                </button>
            }
            
            {props.rol=="cliente" && 
                <button className='but_bi-box-seam-fill' onClick={ () => navigate(`/cliente/${props.email}/historial`) } >
                    <i className="bi bi-box-seam-fill"></i>
                </button>
            }

            {props.rol=="empresa" && 
                <button className='but_bi-box-seam-fill' onClick={ () => navigate(`/empresa/${props.email}/actualizar`) } >
                    <i className="bi bi-cloud-arrow-up-fill"></i>
                </button>
            }

            <button className='but_bi-gear-fill' disabled>
                <i className="bi bi-gear-fill"></i>
            </button>
        </div>

    </div>
}