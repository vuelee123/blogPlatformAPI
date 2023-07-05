# Blog Post API

This is a simple blog post API using Spring Boot. It allows users to utilize CRUD operations such as Create, Read, Update, and Delete on blog posts.

To set up the project:
-
- clone the repository (https://github.com/vuelee123/blogPlatformAPI.git)  by using 'git clone'.
- import the project into Intellij as a Maven project.
- run the application from Intellij or use the command 'mvn spring-boot:run' in the terminal.
- open your web browser and navigate to '[http://localhost:8080](http://localhost:8080/)' on the url where it will take you to the home page.

Functionality:
-
Get All Blog Post with the endpoint GET/posts

- [localhost:8080/posts](http://localhost:8080/posts) will display a JSON array containing all of the blog posts.

Create a Blog Post with endpoint POST/posts

- [localhost:8080/posts](http://localhost:8080/posts) will display the blog post in JSON format, including the generated ID.

Get a specific Blog Post with endpoint GET/posts/{id}

- [localhost:8080/posts/{id}](http://localhost:8080/posts/{id}) returning the blog post with the specified ID in JSON format.**

Update a Blog Post with endpoint PUT/posts/{id}

- [localhost:8080/posts/{id}](http://localhost:8080/posts/{id}) will update the blog post with the specified id**

Delete a Blog Post with endpoint DELETE/posts/{id}

- [localhost:8080/posts/{id}](http://localhost:8080/posts/{id}) send a DELETE request to the specific id but indicating the deletion.**

### You can test the Blog Post API with the following methods, however, it is easily maneuver by implementing templates to easily navigate.
