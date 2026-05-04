# University TaskFlow

Este repositorio contiene el código de University TaskFlow, un sistema de gestión de tareas desarrollado puramente como un proyecto de aprendizaje. Surgió como un desafío personal para salir de mi zona de confort habitual en el desarrollo backend, enfrentarme directamente al ecosistema de Java y aplicar conceptos sólidos de diseño de software.

## Arquitectura del Proyecto

El sistema está diseñado bajo un enfoque de servicios distribuidos e implementa una arquitectura en capas clásica que separa las responsabilidades en controladores, servicios, repositorios y el uso de DTOs para la transferencia de datos. Se compone de tres módulos principales:

* **Task Service**: Es el motor central de la aplicación. Maneja toda la lógica de negocio vinculada a la creación, administración y gestión de los estados de las tareas.
* **Audit Service**: Un servicio independiente enfocado en la trazabilidad del sistema. Su función principal es registrar los eventos y mantener un log de auditoría detallado en su propio repositorio.
* **Light Client**: Actúa como la capa de presentación. Es un cliente web ligero que, mediante el uso de plantillas HTML y un cliente web interno, consume la información del backend para que el usuario pueda interactuar con el sistema.

## Stack Tecnológico y Herramientas

El proyecto integra tecnologías estándar de la industria para la construcción, despliegue y observabilidad de aplicaciones backend:

* **Lenguaje y Base**: Java gestionado a través de Maven.
* **Infraestructura**: Docker y Docker Compose para aislar y orquestar los servicios en contenedores.
* **Monitorización**: Prometheus configurado para recolectar métricas operativas del entorno.

## Cómo ejecutar el proyecto

Gracias a la configuración de contenedores incluida, levantar el ecosistema completo en un entorno local de desarrollo o pruebas es un proceso directo.

1.  Asegúrate de tener Docker y Docker Compose instalados en tu sistema operativo.
2.  Clona este repositorio en tu máquina local.
3.  Abre una terminal en la raíz del proyecto y ejecuta el archivo de orquestación utilizando el comando: `docker-compose up -d --build`.
4.  Una vez que los contenedores estén en ejecución, podrás acceder a la interfaz web del cliente ligero y a las métricas de Prometheus en sus respectivos puertos.
