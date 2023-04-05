import './css/Header.css';

export default function Header(props) {  
    return <div id="cabecera">
      <h1 className="cab_izq">OnTrack Logistics</h1>

      <div className="cab_der">
        <button className='but_bi-bell-fill'>
          <i className="bi bi-bell-fill"></i>
        </button>
        
        <div className="usuario">
          <h1 className="nombre">{props.email}</h1>
          <h1 className="rol">{props.rol}</h1>
        </div>
        
        <img className="foto_user" src={process.env.PUBLIC_URL + "/usuario.png"} alt="foto del usuario" />      
      </div>
           
    </div>
}