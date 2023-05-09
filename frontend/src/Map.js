import { useMemo, useState } from 'react';

import { 
    GoogleMap, useLoadScript, DirectionsRenderer, DirectionsService, Marker
} from '@react-google-maps/api';

export default function Map(props) {
    // Función que inicializa el mapa.
    const { isLoaded, loadError} = useLoadScript({ googleMapsApiKey:"AIzaSyCHmpOZxtVieAFlMzFjSBcwzd-mpGvbdt8"});
    
    // Estado utilizado para renderizar las direcciones del mapa.
    const [directions, setDirections] = useState(null);

    // Hook utilizado para renderizar un centro del mapa base y que este se actualice al movernos por el mapa.
    const center = useMemo(() => ({  lat: 40.416775, lng: -3.703790  }), []);
    
    // Variables utilizadas para mostrar las direcciones.
    let sortedLocations = [];
    let origin = {};
    let destination = {};
    let waypoints = [];

    // Función que se ejecuta al renderizar las direcciones.
    const directionsCallback = (response) => {
        if (response !== null) {
            setDirections(response);
        }
    };

    // Logica para mostrar las direcciones deseadas en función del numero de direcciones dadas.
    if (props.ubicaciones && props.ubicaciones.length > 1) {
        sortedLocations = props.ubicaciones.sort((a, b) => b.id - a.id);
        origin = { lat: sortedLocations[sortedLocations.length-1].latitud, lng: sortedLocations[sortedLocations.length-1].longitud };
        destination = { lat: sortedLocations[0].latitud, lng: sortedLocations[0].longitud };
    
        if (props.ubicaciones.length > 2) {
            waypoints = sortedLocations.slice(1, -1).map((ubicacion) => ({
                location: { lat: ubicacion.latitud, lng: ubicacion.longitud },
                stopover: true,
            }));
        }
    }

    // Opciones del mapa.
    const mapOptions = {
        disableDefaultUI: true,
        clickableIcons: false,
    };

    // Opciones en función de la carga del mapa.
    if (loadError) return <div>Error al cargar el mapa</div>;
    if (!isLoaded) return <div>Cargando el mapa...</div>;

    // Solo se renderizará  en caso de que existen direcciones.
    const directionsRenderer = directions && <DirectionsRenderer directions={directions} />

    // Renderizado de la página.
    return (
        <div className="mapa" style={{ height: '500px', width: '70%', marginRight:'60px', marginBottom:'60px' }}>
        <GoogleMap zoom={15}  center={center} mapContainerClassName='mapa' options={mapOptions}>
            { props.ubicaciones && props.ubicaciones.length === 1 &&
                props.ubicaciones.map( (ubicacion, index) => <Marker key={index} position={{lat: ubicacion.latitud, lng: ubicacion.longitud}} />) 
            }
            { directionsRenderer }
            { props.ubicaciones && props.ubicaciones.length > 1 &&
                <DirectionsService
                    options={{
                        origin: origin,
                        destination: destination,
                        waypoints: waypoints,
                        travelMode: 'DRIVING',
                    }}
                    callback={directionsCallback}
                />
            }  
        </GoogleMap>
        </div>
    );
};


