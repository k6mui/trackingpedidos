# Aplicación de Tracking de Pedidos
## Idea del proyecto
- La logística, o capacidad de transportar objetos de forma organizada es crucial en situaciones de emergencia como las pandemias, en las que resulta esencial el poder distribuir comida, medicinas u otros enseres a tiempo desde los almacenes de distribución hasta los puntos de recepción para su uso o consumo. 
Pero no sólo ocurre en pandemia; la capacidad de realizar entregas a tiempo es una parte crucial de los nuevos negocios digitales, como los servicios de venta a domicilio o empresas de comercio electrónico. Y estas son las organizaciones en las que se debe apoyar el proyecto para desarrollarse y crecer.
- En concreto, este proyecto trata sobre la construcción de una aplicación de seguimiento de pedidos. Tanto clientes particulares como corporaciones se pueden beneficiar de este sistema, aunque el servicio va más orientado a empresas que quieran dar la opción de seguimiento a los envíos que realicen a sus propios clientes. 
## Descripción del proyecto
- Como recién se ha mencionado, para desarrollar el sistema se usará un patrón de arquitectura cliente-servidor en tres niveles.
Se ha elegido esta arquitectura frente a otras arquitecturas como repositorio o variantes como la cliente-servidor de dos niveles debido a las prestaciones que ofrece.
En el apartado de fiabilidad la arquitectura permite escalabilidad y redundancia en la capa de aplicación y en la capa de datos, lo que ayuda a garantizar que el sistema siga funcionando incluso en caso de fallo en una capa. En cuanto a la seguridad, al separar la capa de presentación de la capa de datos, se puede reducir la exposición a amenazas de seguridad. Además la capa intermedia proporciona medidas de seguridad adicionales, como la autenticación y autorización de usuarios. También al tener separadas las tres capas, se pueden realizar cambios y actualizaciones en una capa sin afectar a las otras capas, lo que facilita el mantenimiento y la actualización del sistema sin interrumpir la operación. Otra gran ventaja de la separación de capas sería la portabilidad, ya que las capas pueden implementarse en diferentes plataformas y tecnologías según sea necesario. Por último la arquitectura de tres capas permite el monitoreo y la gestión independientes de cada capa, lo que permite garantizar una operación fluida y una gestión eficiente del sistema.
Pese a las ventajas que nos han decantado por elegir esta arquitectura, se es consciente de las dificultades añadidas de su implementación, como puede ser la sobrecarga en la red debido a la mayor cantidad de tráfico adicional o la mayor dificultad a nivel de programación. Además de la necesidad de realizar pruebas en la tercera capa, lo cual, no sería necesario en su homónima de dos capas.
- Algunos criterios adicionales que consideraremos en nuestro proyecto son: 
Biblioteca React de JavaScript para la implementación de la interfaz de usuario de la capa de presentación.
Framework Spring Boot de Java para la implementación de los servicios REST de la capa de servicios.
MySQL para la implementación de la base de datos relacional de la capa de persistencia de datos.
Herramienta Mockups para el diseño de la maqueta de las distintas interfaces y el flujo de navegación.
Herramienta Trello para el control de características de producto y la implementación de la metodología Scrum.
IDE Visual Studio Code para el desarrollo de software.
Repositorio de código en GitHub para el control de versiones.
Sistema de pruebas JUnit.
Según la planificación de entregas, el tiempo disponible para el desarrollo total del MVP es hasta el 5 de mayo y para las pruebas unitarias es hasta el 7 de mayo.
Los componentes del equipo encargados de realizar cada parte aún están por determinar. Hasta entonces todos los desarrollos se ejecutarán de forma conjunta y concurrente.

Por último, en cuanto a las restricciones legales se impone la siguiente:
Ley Orgánica 3/2018, de 5 de diciembre, de Protección de Datos Personales y garantía de derechos digitales.
## Instrucciones para lanzar el proyecto
- En primera instancia arrancar el backend en el archivo main del sistema *TrackingpedidosApplication.java*, y luego situarnos en la carpeta frontend y poner por consola npm install y npm start. El frontend cliente se lanzará de manera predeterminada en el puerto 3000 correspondiente a React y el backend en el puerto 8080.
- Para visualizar la base de datos acceder al path "http://localhost:8080/h2-console"
