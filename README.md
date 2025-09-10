# ArquitecturasWeb_2025

Este proyecto incluye un helper (`HelperMySQL.java`) para conectarse a la base de datos.  

Antes de ejecutar el proyecto, revisá la **línea 132** del archivo `HelperMySQL.java` y asegurate de que la ruta esté configurada correctamente según tu sistema operativo.

## Configuración de la ruta

En la línea 132 vas a encontrar algo como:

```java
String path = "src/main/resources/csv_files/" + archivo;
```
En caso de ser usuario de Linux/macOS ya se puede ejecutar, en caso de ser usuario de Windows tenes que usar doble backslash para la ruta, de la siguiente manera:
```java 
String path = "src\\main\\resources\\csv_files\\" + archivo;
```

## Ejecucion

### Preparar la base de datos
Si utilizas Docker como motor de bases de datos, desde la carpeta raiz del proyecto ejecutar
``` bash 
 docker-compose up
```
Si se utiliza **XAMPP** solo iniciar **MySQL**. 

Se puede visualizar cuando esten pobladas las bases de datos desde localhost/phpmyadmin/


### Ejecutar el programa
Desde la clase **Main** ejecutar el metodo main() 