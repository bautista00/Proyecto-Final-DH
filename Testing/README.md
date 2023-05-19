## Testing del Proyecto Integrador, Grupo 3 "Field Rent"
<hr>

# Sprint 1

Los casos de prueba no funcionales planificados para cada historia de usuario del sprint 1 dieron como resultado:

1 - Colocar encabezado:
* Header que ocupa el 100% de la pantalla en todas las páginas de la aplicación. ✔ OK
* Header que debe estar fijo en la parte superior de la página siempre visible y accesible para el usuario (también haciendo scroll). ✔ OK
* Header consistente en todas las páginas de la aplicación e inteligible para el usuario. ✔ OK
* Header responsive. ✔ OK
Dentro del header debe existir un bloque alineado a la izquierda que contiene el logotipo y el lema de la empresa. Y un bloque alineado a la derecha que contiene los botones: “Crear cuenta” e “Iniciar sesión” (Sin funcionalidad). ✔ OK
* Al interactuar con el logotipo o el lema, que redirija al usuario a la página principal de la aplicación. ✔ OK

2 - Definir el cuerpo del sitio 
* Body con un color de background que corresponda a la identidad de marca (color verde #468866 o similares y blanco). ✔ OK 
* Body que ocupe el 100 % del alto de la pantalla. ✔ OK
* Body responsive. ✔ OK
* Tres secciones o bloques visibles:  buscador, categorías y recomendaciones de productos. ✔ OK

3 - Colocar pie de página
* Footer que ocupe el 100% del ancho de la pantalla y ubicado en el pie de página en todas las vistas.✔ OK
* Dentro del footer, un bloque alineado a la izquierda que contiene el isologotipo de la empresa, el año y el copyright correspondiente. ✘Fail - sólo falta el isologotipo.
* El isologotipo, el año y el copyright deben ser legibles y estar diseñados de acuerdo con la identidad visual de la empresa.✔ OK

4 - Registrar producto
* Panel para el administrador con la opción  "Agregar producto”. ✘Fail - se puede agregar producto pero mediante el botón “Iniciar sesión”.
* En "Agregar producto" incluir campos para ingresar información relevante del producto.✔ OK
* El administrador puede subir una imagen del producto.✔ OK
* El administrador puede guardar el producto y debe ser agregado correctamente a la base de datos del sitio.✔ OK
* Si se agrega un producto con un nombre que ya existe, mostrar un mensaje de error indicando que ya está en uso.✔ OK

5 - Visualizar productos en el home
* Una lista aleatoria de productos en el home.✔ OK
* La lista de productos aleatorios es realmente aleatoria, sin repetir y sin seguir un patrón previsible.✔ OK

6 - Visualizar detalle del producto
* Header que cubre el 100 % del ancho de la pantalla.✔ OK
* El título del producto está alineado a la izquierda.✔ OK
* La flecha para volver atrás está alineada a la derecha.✔ OK
* En el body hay un texto descriptivo del producto y su imagen.✔ OK

(Información resumida del Test Plan Frontend del grupo fecha: 16/05/23)

Las historias de usuario #7 y #8 no se incorporaron en el Planing a realizar durante el sprint. 

## Puntos heurísticos de Nielsen

Con respecto a los 10 puntos heurísticos de Nielsen que se enfocan en evaluar el diseño de la interfaz de usuario:
1. Visibilidad del Estado del Sistema:
Al usuario de nuestro sitio se le proporciona feedback cuando realiza una acción de agregar producto (correcto o fallido). 
2. Coincidencia del sistema con el mundo real:
El sitio está escrito enteramente en español y busca ser lo más familiar posible, contiene buscador con fechas actuales y lugares aledaños.
3. Darle al usuario el control y la libertad:
El usuario puede navegar libremente, hay opción de volver a la página anterior o home, aún no hay funcionalidad para deshacer cambios en los productos.
4. Consistencia y Estándares:
El sitio web es bastante consistente en todas sus páginas, con el header, footer, paleta de colores, etc. Falta unificar las fuentes de letras para el sitio.
5. Prevención de errores:
En el registro de producto aparecen leyendas que indican lo que se precisa en cada campo.
6. Reconocer antes que memorizar:
Si bien muchas acciones en la página son intuitivas como la búsqueda o el acceso a un detalle de producto, hay que pulir el acceso a la creación de un producto para un administrador.
7. Flexibilidad y eficiencia de uso:
Es fácil observar los productos disponibles y recomendados al entrar al sitio.
8. Estética y diseño minimalista:
El diseño visual apoya a la función de los elementos, no se nota un ambiente recargado de información.
9. Ayudar a reconocer, diagnosticar y solucionar problemas:
Los mensajes de error están escritos en un lenguaje comprensible para el usuario. Por ejemplo a la hora de crear un producto ya existente aparece un Alert que indica el error de forma clara.
10. Ayuda y Documentación
Por el momento hay algunos posibles errores que los usuarios pueden cometer, entregamos información pertinente al equipo y tratamos de hacer recomendaciones para resolverlos. Se verán para corregir el sitio durante el Sprint 2.
<hr>

# Sprint 2