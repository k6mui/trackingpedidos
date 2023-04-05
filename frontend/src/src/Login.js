import { useNavigate } from 'react-router-dom';
import './css/Login.css';

export default function Login(props) {
    let navigate = useNavigate();  
    return <>
      <div className='log_lateral'></div>

      <div className='log_principal'>
        <h1>OnTrack Logistics:</h1>
        <div className='log_container'>
            <h1>Iniciar sesión:</h1>
            
            <div className='inputs_container'>
                <div className='inputs'>
                    <label htmlFor="log_email">Email:</label>
                    <input type="text" id="log_email" value={props.email} onChange={props.handleEmail}/>
                </div>
                
                <div className='inputs'>
                    <label htmlFor="log_rol">Rol:</label>
                    <input type="text" id="log_rol" value={props.rol} onChange={props.handleRol}/>
                </div>
            </div>

            <div className="log_iniciar">
                <button className='boton_iniciar' onClick={() => {
                    navigate(`/${props.rol}/${props.email}`);
                    props.setLogin(false);
                }} > Iniciar </button>
            </div>            
            
        </div>
      </div>

      <div className='log_lateral'></div>           
    </>
}