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

            <button className='but_bi-house-door-fill' onClick={ () => navigate(`/cliente/${props.email}`) } >
                <i className="bi bi-house-door-fill"></i>
            </button>
            
            <button className='but_bi-box-seam-fill' disabled>
                <i className="bi bi-box-seam-fill"></i>
            </button>

            <button className='but_bi-gear-fill' disabled>
                <i className="bi bi-gear-fill"></i>
            </button>
        </div>

    </div>
}