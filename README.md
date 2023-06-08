# DAWFarmacia

Esta es una página web diseñada para el uso interno de una farmacia. La aplicación permite a los doctores iniciar sesión y acceder a la tabla de chips para controlar la medicación de sus pacientes. Además, los doctores tienen la capacidad de dar de alta un chip con un nuevo paciente que ya esté registrado en la base de datos.

## Características principales

-   **Inicio de sesión**: Los doctores pueden iniciar sesión en la aplicación.
-   **Tabla de chips**: Visualización y control de la medicación de los pacientes a través de una tabla de chips.
-   **Registro de chips**: Los doctores pueden dar de alta un chip con un nuevo paciente registrado.

## Estructura de carpetas

El proyecto está organizado en las siguientes carpetas:

- `DAWFarmacia`
  - `src/main/java`: Archivos del backend desarrollados en Java

- `DAWFarmacia_Frontend`: Archivos del frontend desarrollados en HTML, CSS y JavaScript.
  - `css`: Archivos css usados para el estilo
  - `javascript`: Archivos JavaScript usados para la interacción

## Tecnologías utilizadas

- Backend: Se ha desarrollado utilizando Java.

- Base de datos: Se ha creado una base de datos en MySQL.

- Frontend: El frontend de la aplicación se ha desarrollado utilizando HTML, CSS y JavaScript.

## Requisitos previos

Antes de ejecutar la aplicación, asegúrate de tener instalados los siguientes requisitos previos:

-   [Tomcat](https://tomcat.apache.org/download-90.cgi): Un servidor web Java para ejecutar aplicaciones web.

-   [MySQL](https://dev.mysql.com/downloads/connector/j/): Un sistema de gestión de bases de datos relacional.

## Instalación

1.  Clona este repositorio.
2.  Asegúrate de tener Tomcat instalado y configurado correctamente en tu entorno de desarrollo.
3.  Configura la base de datos MySQL ejecutando el script DDL que encontrarás en el repositorio con el nombre `Farmacia_DDL.sql`.
4.  Inicia el servidor backend ejecutando el servidor Tomcat.
5.  Para acceder a la aplicación abre en tu navegador el archivo `login.html` ubicado en la carpeta `DAWFarmacia_Frontend`.
