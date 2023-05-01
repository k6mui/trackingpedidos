// import { useState, useEffect } from 'react';
import { GoogleMap, Marker, useLoadScript } from '@react-google-maps/api';
import { useMemo } from 'react';
// import { Loader } from '@googlemaps/js-api-loader';

export default function Map(props) {
    const { isLoaded, loadError} = useLoadScript({ googleMapsApiKey:"AIzaSyBtydSBXGb0aWENK7o34Y-_0OyiJ6X0NMc"});
    
    let aux = props.ubicaciones.sort((a, b) => b.id - a.id);
    const center = useMemo(() => ({lat: aux[0].latitud, lng: aux[0].longitud}), []);

    const mapOptions = {
        disableDefaultUI: true,
    };

    if (loadError) return <div>Error al cargar el mapa</div>;
    if (!isLoaded) return <div>Cargando el mapa...</div>;

    // Renderizado de la página.
    return (
        <div className="mapa" style={{ height: '500px', width: '100%' }}>
        <GoogleMap zoom={10}  center={center} mapContainerClassName='mapa' options={mapOptions}>
            { props.ubicaciones &&
                props.ubicaciones.map( (ubicacion, index) => <Marker key={index} position={{lat: ubicacion.latitud, lng: ubicacion.longitud}} />) }
        </GoogleMap>
        </div>
    );
};

