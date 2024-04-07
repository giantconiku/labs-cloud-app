# spring-boot-rest-api-postgresql

#### Plain REST API CRUD with Spring Boot and PostgreSQL.

Technology stack:

* Spring Boot;
* Spring Web;
* Spring Data;
* PostgreSQL database;
* Hibernate;
* Spring Security (as basic authentication).

##### To run this application use:

`mvn clean spring-boot:run`

The view in the Postman:

Add new Author

`POST /api/authors`

http://localhost:8080/api/authors

![Add New Author](img/AddAuthor.png "Add New Author")

Get All Authors

`GET /api/authors`

http://localhost:8080/api/authors

![Get All Authors](img/GetAllAuthors.png "Get All Authors")

Get Author By Id

`GET /api/authors/{id}`

http://localhost:8080/api/authors/{id}

![Get Author By Id](img/GetAuthorById.png "Get Author By Id")

Get Author By First Name

`GET /api/authors?firstName=firstName`

http://localhost:8080/api/authors?firstName=firstName

![Get Author By First Name](img/GetAuthorsByFirstName.png "Get Author By First Name")

Edit Author

`PUT /api/authors/{id}`

http://localhost:8080/api/authors/{id}

![Edit Author](img/EditAuthor.png "Edit Author")

Delete Author By Id

`DELETE /api/authors/{id}`

http://localhost:8080/api/authors/{id}

![Delete Author By Id](img/DeleteAuthorById.png "Delete Author By Id")

Delete All Authors

`DELETE /api/authors`

http://localhost:8080/api/authors

![Delete All Authors](img/DeleteAllAuthors.png "Delete All Authors")





/////////////

kubectl config current-context -- To view your current context
kubectl config get-contexts -- To list all your contexts
kubectl config use-context my-context -- To switch to a different context:
