# SimpleTaskBackend

The backend to my simple task tracking app.  A DropWizard application, built following [this tutorial](https://howtodoinjava.com/dropwizard/tutorial-and-hello-world-example/)

## Usage

Build the application

```
$ mvn clean package
```

Start the server

```
$ java -jar target/SimpleJApplication-1.0-SNAPSHOT.jar server
```


Hit the endpoints, for ex:

```
GET localhost:8080/clients
```

Returns a list of clients