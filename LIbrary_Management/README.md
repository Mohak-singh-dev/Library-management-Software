# Library Management API

A Spring Boot REST API for managing college library books. Book records are kept in memory, so they reset whenever the application restarts.

## Run the application

```bash
./mvnw spring-boot:run
```

On Windows, use `mvnw.cmd spring-boot:run`.

## Book fields

| Field | Type |
| --- | --- |
| `id` | Integer (assigned by the API when creating a book) |
| `title` | String |
| `author` | String |
| `category` | String |
| `availableCopies` | Integer |

## Endpoints

All endpoints use the `/api/books` base path and JSON request/response bodies.

| Method | Path | Result |
| --- | --- | --- |
| `POST` | `/api/books` | Creates a book; returns `201 Created` |
| `GET` | `/api/books` | Returns all books |
| `GET` | `/api/books/{id}` | Returns one book; returns `404 Not Found` if absent |
| `PUT` | `/api/books/{id}` | Replaces a book's details; returns `404 Not Found` if absent |
| `DELETE` | `/api/books/{id}` | Deletes a book; returns `204 No Content` or `404 Not Found` |

Example create or update body:

```json
{
  "title": "Object-Oriented Programming",
  "author": "Jane Doe",
  "category": "Computer Science",
  "availableCopies": 5
}
```

The ID in a create request is ignored; the API assigns IDs automatically. Update requests use the ID in the URL.
