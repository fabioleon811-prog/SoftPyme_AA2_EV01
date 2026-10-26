
# SoftPyme - Evidencia GA7-220501096-AA2-EV01

## Descripción

Módulo desarrollado en Java para gestionar registros de peso
del proyecto SoftPyme, orientado al seguimiento de hábitos
saludables.

## Tecnologías

- Java 17
- Maven
- MySQL
- JDBC
- Git

## Funcionalidades

- Insertar registros de peso.
- Consultar todos los registros.
- Consultar un registro por su ID.
- Actualizar registros existentes.
- Eliminar registros con confirmación.

## Requisitos previos

- JDK 17.
- Maven.
- MySQL Server.
- Un cliente de MySQL, como MySQL Workbench.

## Configuración

1. Ejecutar el script sql/softpyme.sql en MySQL.
2. Configurar las variables de entorno:

   SOFTPYME_DB_URL=jdbc:mysql://localhost:3306/softpyme
   SOFTPYME_DB_USER=root
   SOFTPYME_DB_PASSWORD=TU_CLAVE

3. Abrir la carpeta del proyecto con un IDE compatible con Maven.
4. Esperar a que Maven descargue las dependencias.
5. Ejecutar la clase com.softpyme.app.Main.

También se puede ejecutar desde la terminal, dentro de
la carpeta del proyecto:

    mvn clean compile
    mvn exec:java

## Estándares de codificación

- Las clases utilizan UpperCamelCase.
- Las variables y métodos utilizan lowerCamelCase.
- Los paquetes usan nombres en minúscula.
- Las constantes utilizan MAYÚSCULAS_CON_GUIONES.
- Las operaciones SQL se parametrizan con PreparedStatement.
- Las conexiones y consultas utilizan try-with-resources.

## Limitaciones

Esta entrega implementa el módulo de registro de peso.
La aplicación utiliza una consola y no una interfaz gráfica.
Para una versión de producción se requiere autenticación,
autorización y asociación de cada registro con el usuario
autenticado. Las credenciales deben configurarse de forma segura.

## Repositorio

El enlace real del repositorio se encuentra en repositorio.txt.
