Overview

The Manga Review API is a RESTful web application designed to manage and share reviews for manga (japanese comic books). It serves as a backend service, enabling users to explore manga, view genres, learn about mangakas (authors), and read reviews. This project is built using Java, Spring Boot, and MySQL.



Features

1. Manga Management

Create, read, update, and delete manga entries.

Associate manga with multiple genres.

Link manga to their respective mangakas.

2. Genre Management

Manage a collection of genres.

Support for many-to-many relationships with manga.

3. Mangaka Management

Manage mangaka details including their country of origin.

Establish a many-to-one relationship with manga.

4. Manga Reviews

Submit, view, and manage reviews for specific manga.

Support for one-to-many relationships between manga and reviews.



Technologies Used

Programming Language: Java

Framework: Spring Boot

Database: MySQL

Logging: SLF4J with Logback



Setup Instructions

Prerequisites

JDK 17 or higher

Maven

MySQL Server

An IDE like IntelliJ, IDEA, or Eclipse



Steps to Run the Application

1. Clone the repository:

git clone https://github.com/davidjo8/Manga-Review-API
cd manga-review-api

2. Configure the database:

Create a new MySQL database named manga_review.

Update the application.properties file with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/manga_review
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.jpa.hibernate.ddl-auto=update

3. Build and run the application:

mvn clean install
mvn spring-boot:run

4. Access the API:

The API will run locally at http://localhost:8080.



API Endpoints

Request mapping: /manga

Manga Endpoints

GET: Retrieve all manga entries.

GET /{mangaId}: Retrieve a specific manga by ID.

POST: Add a new manga.


Genre Endpoints

GET /genre: Retrieve all genres.

POST /genre: Add a new genre.


Mangaka Endpoints

GET /mangaka: Retrieve all mangakas.

POST /mangaka: Add a new mangaka.


Manga Review Endpoints

GET /mangareview: Retrieve reviews for a specific manga.

POST /{mangaId}/mangareview: Add a review for a manga.

PUT /{mangaId}/mangareview/{mangaReviewId}: Update a manga entry.

DELETE /mangareview/{mangaReviewId}: Delete a manga entry.

Manga_Genre Join Table Endpoints

GET /{mangaId}/genres Get all the genres within a manga entry.

POST /{mangaId}/genre/{genreId} Add a genre to a manga.


Database Schema

Tables

1. Manga: Stores manga details such as title, description, and mangaka reference.

2. Genre: Stores genre information.

3. Mangaka: Stores mangaka details like name and country.

4. MangaReview: Stores user reviews for manga.


Relationships

Manga and Genre: Many-to-Many

Manga and Mangaka: Many-to-One

Manga and MangaReview: One-to-Many


Future Enhancements

Implement user authentication and authorization.

Add rating functionality for manga.

Integrate external APIs for manga metadata.

Develop a front-end interface.


License

This project is licensed under the MIT License. See the LICENSE file for details.

Contact

For questions or contributions, please contact David Canarsky at davidjosephgpa@gmail.com.