# :hammer_and_wrench: Parcial 2do Tercio - Ejercicios de Diseño

## Arquitecturas Empresariales

### :pushpin: Daniel Felipe Hernández Mancipe

<br/>

Documentación del diseño, construcción y despliegue de una aplicación web para investigar la conjetura de Collatz. La aplicación está desplegada en un microcontenedor Docker corriendo en AWS. Las tecnologías usadas en la solución son Maven, Git, SparkJava, HTML5 y JS. No se usan librerías adicionales.

### Problema

La conjetura de Collatz dice que si usted crea una secuencia de números, a partir de cualquier entero positivo, siguiendo las reglas descritas abajo, siempre la secuencia terminará en el número 1. Esta conjetura aún no se ha demostrado. Las reglas son:

- Si $n$ es par, entonces $f(n)=n/2$
- Si $n$ es impar, entonces $f(n)=3n+1$

La secuencia se construye a partir de un número dado $k$ así:

- $a_0=k$
- $a_i=f(a_{i-1})$

Por ejemplo, dado el número $k=13$, la secuencia sería:

$13\rightarrow 40\rightarrow 20\rightarrow 10\rightarrow 5\rightarrow 16\rightarrow 8\rightarrow 4\rightarrow 2\rightarrow 1\rightarrow$

### Arquitectura

- Cliente asíncrono que corre en el Browser escrito en HTML5 y JS (sin librerías). El cliente recibe un número y muestra la secuencia de Collatz generada a partir de ese número.
- El cliente **NO COMPUTA** la secuencia directamente, sino que la delega a un servicio REST corriendo en AWS.
- Servicio REST para construcción de la secuencia de Collatz, el servicio puede ser GET o POST. El servicio recibe el número inicial en la variable del query con nombre "value".

Ejemplo de un llamado:

`http://amazonxxx.x.xxx.x.xxx:{port}/collatzsequence?value=13`

El formato de la salida y la respuesta debe ser un JSON con el siguiente formato:

```json
{
  "operation": "collatzsequence",
  "input": 13,
  "output": "13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"
}
```

## Getting Started

### Prerequisites

- Java >= 11.x
- Maven >= 3.x
- Git >= 2.x
- JUnit 4.x

### Installing

Simplemente clone el repositorio:

```bash
git clone https://github.com/danielhndz/AREM-parcial2.git
```

Luego compile el proyecto con maven:

```bash
cd <project-folder>
mvn compile
```

Si salió bien, debería tener una salida similar a esta:

![compile output](../media/mvn_compile.png?raw=true)

### Using

Using

## Running the tests

Running the tests

### Break down into end to end tests

Break down into end to end tests

## Built With

- [Maven](https://maven.apache.org/) - Dependency Management
- [Git](https://git-scm.com/) - Version Management
- [JUnit4](https://junit.org/junit4/) - Unit testing framework for Java

## Design Metaphor

- Autor: Daniel Hernández
- Última modificación: 31/03/2023

### Backend Class Diagram

Backend Class Diagram

## Authors

- **Daniel Hernández** - _Initial work_ - [danielhndz](https://github.com/danielhndz)

## License

This project is licensed under the GPLv3 License - see the [LICENSE.md](LICENSE.md) file for details

## Javadoc

Para generar Javadocs independientes para el proyecto en la carpeta `/target/site/apidocs` ejecute:

```bash
mvn javadoc:javadoc
```
