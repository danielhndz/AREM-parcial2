# :hammer_and_wrench: Parcial 2do Tercio - Ejercicios de Diseño

## Arquitecturas Empresariales

### :pushpin: Daniel Felipe Hernández Mancipe

<br/>

Documentación del diseño, construcción y despliegue de una aplicación web para investigar la conjetura de Collatz. La aplicación está desplegada en un microcontenedor Docker corriendo en [AWS](http://ec2-44-211-207-246.compute-1.amazonaws.com:34000/). Las tecnologías usadas en la solución son Maven, Git, SparkJava, HTML5 y JS. No se usan librerías adicionales.

### Problema

La conjetura de Collatz dice que si usted crea una secuencia de números, a partir de cualquier entero positivo, siguiendo las reglas descritas abajo, siempre la secuencia terminará en el número 1. Esta conjetura aún no se ha demostrado. Las reglas son:

- Si $n$ es par, entonces $f(n)=n/2$
- Si $n$ es impar, entonces $f(n)=3n+1$

La secuencia se construye a partir de un número dado $k$ así:

- $a_0=k$
- $a_i=f(a_{i-1})$

Por ejemplo, dado el número $k=13$, la secuencia sería:

$13\rightarrow 40\rightarrow 20\rightarrow 10\rightarrow 5\rightarrow 16\rightarrow 8\rightarrow 4\rightarrow 2\rightarrow 1$

### Arquitectura

- Cliente asíncrono que corre en el Browser escrito en HTML5 y JS (sin librerías). El cliente recibe un número y muestra la secuencia de Collatz generada a partir de ese número.
- El cliente **NO COMPUTA** la secuencia directamente, sino que la delega a un servicio REST corriendo en AWS.
- Servicio REST para construcción de la secuencia de Collatz, el servicio puede ser GET o POST. El servicio recibe el número inicial en la variable del query con nombre "value".

Ejemplo de un llamado:

`http://amazonxxx.x.xxx.x.xxx:{port}/collatzsequence?value=13`

El formato de la salida y la respuesta es un JSON con el siguiente formato:

```json
{
  "operation": "collatzsequence",
  "input": 13,
  "output": "13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"
}
```

### Video

[OneDrive](https://pruebacorreoescuelaingeduco-my.sharepoint.com/:v:/g/personal/daniel_hernandez-ma_mail_escuelaing_edu_co/ET0189TLG79HjbT5YmZ5ZSkBjMpvKgbD9gG6ogXBb9FyLg?e=x2K6gQ)

## Getting Started

### Prerequisites

- Java >= 11.x
- Maven >= 3.x
- Git >= 2.x
- JUnit 4.x
- Docker

### Installing

Simplemente clone el repositorio:

```bash
git clone https://github.com/danielhndz/AREM-parcial2.git
```

Luego compile el proyecto con maven:

```bash
cd <project-folder>
mvn clean install
```

Si salió bien, debería tener una salida similar a esta:

![compile output 1](../media/mvn-clean-install1.png?raw=true)

![compile output 2](../media/mvn-clean-install2.png?raw=true)

![compile output 3](../media/mvn-clean-install3.png?raw=true)

### Using

Primero se construye la imagen con Docker:

```bash
sudo docker build -t web .
```

![docker build images](../media/docker-build-images.png?raw=true)

A partir de la imagen creada, se instancia un contenedor Docker independiente de la consola `-d` y con el puerto 6000 enlazado al puerto físico 34000 de la máquina `-p`:

```bash
sudo docker run -d -p 34000:6000 --name web web
```

![docker build run](../media/docker-build-run.png?raw=true)

De esta manera se puede usar el servidor de manera local:

![sample localhost](../media/sample-localhost.png?raw=true)

Ahora, se usa `docker-compose` para generar automáticamente una configuración Docker:

```bash
sudo docker-compose up -d --build
```

![docker-compose](../media/docker-compose.png?raw=true)

![docker-compose-2](../media/docker-compose-2.png?raw=true)

Para subir la imagen a DockerHub, en el motor de Docker local, se crea una referencia a la imagen con el nombre del repositorio a donde se desea subirla:

```bash
docker tag web danielhndz/arem-parcial2
```

![docker image dockerhub](../media/docker-image-dockerhub.png?raw=true)

Luego se sube la imagen:

![dockerhub push](../media/dockerhub-push.png?raw=true)

### Amazon Web Services

Primero se crea una instancia EC2:

![instancia EC2](../media/aws1.png?raw=true)

![instancia EC2 OS](../media/aws2.png?raw=true)

Luego, se crean un par de llaves de seguridad:

![instancia EC2 keys](../media/aws3.png?raw=true)

Luego, se crea un subgrupo de seguridad:

![instancia EC2 subgrupo](../media/aws4.png?raw=true)

Si la máquina se creó correctamente:

![instancia EC2 correcta](../media/aws5.png?raw=true)

Los datos que nos interesan de la instancia son:

![instancia EC2 datos](../media/aws6.png?raw=true)

Se accede a la instancia EC2 por `ssh`:

```bash
ssh -i AREM-parcial2.pem ec2-user@ec2-44-211-207-246.compute-1.amazonaws.com
```

![instancia EC2 ssh](../media/aws7.png?raw=true)

Se instala `Docker`:

```bash
sudo yum install docker
```

![instancia EC2 docker](../media/aws8.png?raw=true)

![instancia EC2 docker](../media/aws9.png?raw=true)

Se configura el servicio y se vuelve a conectar a la instancia:

![instancia EC2 setup](../media/aws10.png?raw=true)

A partir de la imagen en DockerHub, se instancia un contenedor Docker independiente de la consola `-d` y con el puerto 6000 enlazado al puerto físico 34000 de la máquina EC2 `-p`:

![instancia EC2 img](../media/aws11.png?raw=true)

Se abren los puertos para acceder al servicio:

![instancia EC2 port](../media/aws12.png?raw=true)

Finalmente, se puede acceder al servidor desde [AWS](http://ec2-44-211-207-246.compute-1.amazonaws.com:34000/):

![instancia EC2 using](../media/aws13.png?raw=true)

## Built With

- [Maven](https://maven.apache.org/) - Dependency Management
- [Git](https://git-scm.com/) - Version Management
- [JUnit4](https://junit.org/junit4/) - Unit testing framework for Java

## Authors

- **Daniel Hernández** - _Initial work_ - [danielhndz](https://github.com/danielhndz)

## License

This project is licensed under the GPLv3 License - see the [LICENSE.md](LICENSE.md) file for details

## Javadoc

Para generar Javadocs independientes para el proyecto en la carpeta `/target/site/apidocs` ejecute:

```bash
mvn javadoc:javadoc
```
