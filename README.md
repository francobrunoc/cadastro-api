# cadastro-api

Cadastro de clientes, produtos e pedidos:

JDK 1.8 or later needed!

For the sake of simplicity the jar file is already created on the uploaded folder cadastro-api/build/libs.
So you can go directly to it and:

```
$java -jar cadastro-0.0.1-SNAPSHOT.jar
```

To build and run the project locally you can download Gradle (see how to install here: https://gradle.org/install):

###After installed, running directly via gradle command:


```
$cd cadastro-api
$gradle bootRun
```

###Running via .jar file:

If the jar is NOT already created on cadastro-api/build/libs:

```
$cd cadastro-api
```
```
$gradle task bootJar
```
or
```
$gradle build
```

The jar file will be created at cadastro-api/build/libs

Then:

```
$java -jar cadastro-api/build/libs/cadastro-0.0.1-SNAPSHOT.jar
```


