import { useLocation } from "react-router-dom";
import "./css/Transportista.css";

export default function Transportista(props) {
  let productos = props.productos.filter( (producto) => producto.estado !== "ENTREGADO" );
  let location = useLocation();

  const initializer = () => {
    productos.map((producto) => {
        producto.estado = "TRANSITO";
        props.actualiza(location.pathname, producto);
        props.cambio_estado(producto.estado, producto.id, producto.pedido);
        return producto;
    });
  }

  return (
    <div id="transporte">
      {productos.length === 0 ? (
        <h2 className="no_prod">
          ¡Enhorabuena! Has entregado todos los productos.
        </h2>
      ) : (
          <div className="transporte_container">
            <div className="card_encabezado">
              <h1 className="titulo">Envíos de hoy:</h1>
              <span>ID: {productos[0].transportista}</span>
            </div>

            <div className="iniciar_tray">
              <label htmlFor="boton_iniciar" className="label_iniciar">
                Iniciar trayecto:{" "}
              </label>
              <button className="boton_iniciar" onClick = {() => initializer()} > Empezar </button>
            </div>

            <div className="card_container">
              {productos.map((producto, index) => {
                return (
                  <div className="card" key={index}>
                    <h2 className="identificador">ID: {producto.id}</h2>

                    <div className="der_card">
                      <button
                        className="card_boton"
                        onClick={() => {
                          producto.estado = "ENTREGADO";
                          props.actualiza(location.pathname, producto);
                          props.cambio_estado(
                            producto.estado,
                            producto.id,
                            producto.pedido
                          );
                        }}
                      >
                        Entregado
                      </button>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
      )}
    </div>
  );
}
