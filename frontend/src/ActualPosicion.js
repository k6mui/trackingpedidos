import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

export default function ActualPosicion(props) {
  // Variable que tiene la localización donde nos encontramos, se utiliza principalmente para saber nuestra ruta actual.
  let location = useLocation();

  // Hook que llama a la funcion enviarLocalizacion cada 30 minutos para actualizar la posicion del transportista.
  useEffect(() => {
    const intervalo = setInterval(() => {
      navigator.geolocation.getCurrentPosition( (posicion) => props.enviarLocalizacion(location.pathname, posicion) );
    }, 1800000);
    return () => clearInterval(intervalo);
    // eslint-disable-next-line react-hooks/exhaustive-deps 
  }, [props.enviarLocalización]);

  // Renderizado de la página.
  return null;
};

