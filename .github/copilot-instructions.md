# Instrucciones para Copilot en el Proyecto SELECTO

## Visión General del Proyecto
Este proyecto es una plataforma web dedicada a la selección de personal inteligente, utilizando tecnología y análisis de datos para conectar candidatos con empresas. La estructura del proyecto se basa en HTML, CSS y JavaScript, con un enfoque en la responsividad y la experiencia del usuario.

## Arquitectura del Proyecto
- **Estructura de Archivos**: El proyecto está organizado en carpetas para HTML, CSS y JavaScript. Los archivos CSS están divididos en componentes y estilos generales.
- **Componentes Clave**:
  - `index.html`: Página principal que incluye la estructura básica y enlaces a otros recursos.
  - `css/main.css`: Contiene las variables de estilo y estilos generales.
  - `js/main.js`: Maneja la interactividad del menú y animaciones.

## Flujos de Trabajo del Desarrollador
- **Construcción y Pruebas**: No hay un sistema de construcción automatizado en este momento. Las pruebas se realizan manualmente al verificar la funcionalidad en el navegador.
- **Depuración**: Utiliza las herramientas de desarrollo del navegador para depurar JavaScript y CSS. Asegúrate de revisar la consola para errores.

## Convenciones y Patrones del Proyecto
- **Nomenclatura de Clases**: Se utiliza un enfoque BEM (Block Element Modifier) para la nomenclatura de clases en CSS, facilitando la comprensión de la estructura de los estilos.
- **Estilos Responsivos**: Se implementan media queries para asegurar que el diseño sea responsivo en dispositivos móviles.

## Puntos de Integración y Dependencias Externas
- **Dependencias**: Actualmente, el proyecto no utiliza bibliotecas externas, pero se puede considerar la integración de frameworks como Bootstrap o jQuery en el futuro.
- **Comunicación entre Componentes**: La comunicación entre componentes se realiza a través de eventos DOM, como el evento de clic en el menú.

## Ejemplos de Patrones Específicos
- **Menú Responsivo**: El menú se activa mediante un evento de clic que alterna la clase `active` en el menú, mostrando u ocultando los elementos de navegación.
- **Animaciones**: Se utiliza CSS para animaciones simples, como la clase `fade-in` para transiciones suaves al cargar la página.

## Archivos Clave
- `index.html`: Estructura principal de la aplicación.
- `css/layout.css`: Estilos de diseño y responsividad.
- `js/main.js`: Lógica de interactividad y animaciones.