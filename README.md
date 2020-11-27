# Proyecto final - Ciberseguridad 2020-2

## Integrantes

Juan David Paz 

Juan Camilo Vélez 

Brayan Garcés

Andres Felipe Aguirre



## Descripción general 

Este es el repositorio correspondiente al proyecto final de ciberseguridad. Incluye la explicación del procedimiento que se hizo para lograr el objetivo del proyecto,  los archivos necesarios para correr la aplicación, la forma correcta de probar la aplicación, y por ultimo, la documentación Javadoc.

**Procedimiento**

Partiendo del enunciado de nuestro proyecto ***Cifrador/descifrador de archivos*** Lo primero que realizamos fue separar nuestra solución en dos clases principales. La clase Encrypter y la clase Decrypter. En cada una de estas, están las variables y métodos necesarios para poder cifrar y descifrar. 

En el momento de continuar con los requerimientos del enunciado, vimos necesario crear dos clases más en nuestro modelo. La primera, la clase KeyGenerator. La cual nos ayuda a cumplir el requerimiento de: *"generar una clave de 128 bits empleando el algoritmo PBK2F2"*. La segunda clase que agregamos fue Checksum, Por medio de ella podemos usar el hash SHA-l para realizar los requerimientos *"el resultado debe escribirse a otro archivo, que debe contener también el hash SHA-1 del archivo sin cifrar*" y *"computar el hash SHA-1 del archivo descifrado y compararlo con el hash almacenado con el archivo cifrado."* 

Dicho lo anterior, nuestras clases del modelo quedaron de la siguiente manera: 

**-** *Encrypter*

Se encuentra el metodo encrypt. El cual, usando la ruta del archivo que se desea cifrar y la clave generada por la clase KeyGenerator, cifra el archivo y lo guarda en una carpeta llamada EncryptedFiles. Dicha carpeta se encuentra en el home del usuario. 

**-** *Decrypter*

Se encuentra el metodo decrypt. El cual, usando la ruta del archivo que se desea descifrar y la clave generada por la clase KeyGenerator (Debe ser igual a la que se usó para cifrar), descifra el archivo y lo guarda en una carpeta llamada DecryptedFiles. Dicha carpeta se encuentra en el home del usuario.

Ademas de esto, en el metodo decrypt. Se realiza la comparación entre el hash SHA-1 del archivo descifrado y el hash almacenado del archivo cifrado. 

**-** *KeyGenerator*

Se encuentra el método hashPassword. El cual, por medio de la clave ingresada por el usuario. Genera una clave de 128 bits, usando el  algoritmo PBK2F2. Esta clave es usada en la clase Encrypter y Decrypter.

**-** *Checksum*

Se encuentra el método checksum, El cual, conociendo la ruta del archivo en cuestión. Genera una cadena de texto que contiene el hash SHA-l del archivo. Este hash es usado en la clase Decrypter.

**Archivos**

En la parte superior se encuentra la carpeta [Encrypter-Decrypter](https://github.com/juanchovelezpro/Encrypter-Decrypter/tree/main/Encrypter-Decrypter). En ella estan los archivos necesarios para poder correr la aplicación en el lenguaje Java, y en el IDE que sea de preferencia. 


***Modo de uso para cifrar***

**1.** Descargar la carpeta donde se encuentran los archivos, importarla en el IDE de preferencia y correr la aplicación.

**2.** Una vez la aplicación este corriendo, observará dos botones, uno para cifrar y otro para decifrar. Para cifrar, seleccionamos el botón correspondiente, buscamos en los directorios de nuestro computador el archivo que deseamos cifrar, y lo seleccionamos.

**3.** A continuación, ingresar la contraseña con la que deseamos cifrar el archivo, y continuamos. 

**4.** Ya una vez aparezca el mensaje donde se  indique que el archivo se cifró correctamente, nos dirigimos a la carpeta home/EncryptedFiles y ahí estará el archivo cifrado.

***Modo de uso para descifrar***

**1.** Descargar la carpeta donde se encuentran los archivos, importarla en el IDE de preferencia y correr la aplicación.

**2.** Una vez la aplicación este corriendo, observará dos botones, uno para cifrar y otro para decifrar. Para descifrar, seleccionamos el botón correspondiente, buscamos en los directorios de nuestro computador el archivo que deseamos descifrar, y lo seleccionamos.

**3.** A continuación, ingresar la contraseña con la que deseamos descifrar el archivo, (Para esto, debemos conocer la contraseña con la que se cifró anteriormente) y continuamos. 

**4.** Ya una vez aparezca el mensaje donde se  indique que el archivo se descifró correctamente, nos dirigimos a la carpeta home/DecryptedFiles y ahí estará el archivo descifrado.

**Documentación**

Cada Clase, método y variables globales que se usaron en el código de la aplicación, esta debidamente documentada en la carpeta llamada Documentación Javadoc. Esto se realizó mediante la opción de generar Javadoc del IDE Eclipse.


## Problemas encontrados y conclusión

A lo largo del desarrollo del proyecto nos encontramos principalmente con dos problemas. 

El primero se produjo porque en primera instancia no tuvimos en cuenta los requerimientos correspondientes al hash SHA-l. Cuando nos dimos cuenta ya habíamos desarrollado gran parte de la solución. Con lo cual tuvimos que investigar sobre como podríamos hacer esta parte, crear la clase Checksum y por ultimo, modificar algunos métodos para poder cumplir con dichos requerimientos.

El segundo problema fue al momento de guardar los archivos cifrados y descifrados. Pues tuvimos problemas con las rutas o los nombres de los archivos. Para solucionar este problema decidimos utilizar una ruta predeterminada para guardar los archivos. Esto lo hicimos por medio de las instrucciones: 


    FileManager.PATH + "EncryptedFiles/"

Y 

    FileManager.PATH+ "DecryptedFiles/"

Este proyecto final fue un reto para nosotros, pues pusimos en práctica lo aprendido en las clases correspondientes al tema de cifrado de archivos. Aprendimos lo importante que es para nosotros, como ingenieros, conocer estos temas y saber aplicarlos, pues de esta manera mejoramos nuestras habilidades en seguridad. Realizando los métodos de cifrado y descifrado, aprendimos una herramienta más que podemos usar en nuestros trabajos y proyectos personales futuros.
