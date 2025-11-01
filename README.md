Brief: concise README describing project, how to build/run, API endpoints, validations, examples and notes.

```markdown
# Subscriber Management API

Simple in-memory Spring Boot service to manage telecom subscribers (create, read, update, delete, search).  
Language: Java 17, Build: Maven, IDE: IntelliJ IDEA.

## Features
- CRUD for `Subscriber`
- Search & filter endpoints
- Validation & normalization for `plan` and `status`
- Defaults: missing `status` -> `ACTIVE`

## Tech
- Java 17
- Spring Boot
- Maven

## Quick start

Prerequisites:
- JDK 17
- Maven
- `INTELLIJ IDEA 2025.2.4` (optional)

Build and run:
```bash
mvn -DskipTests package
java -jar target/*.jar
```
or in IDE run the Spring Boot application.

Run tests:
```bash
mvn test
```

## API (examples)
Base URL: `http://localhost:8080/api/subscribers`

- Create subscriber (POST)
  - Body example:
    ```json
    {
      "name":"John Doe",
      "email":"john@example.com",
      "phoneNumber":"1234567890",
      "plan":"basic"
    }
    ```
  - If `status` omitted, stored as `ACTIVE`.

- Get all (GET)
  - `GET /api/subscribers`

- Get by id (GET)
  - `GET /api/subscribers/{id}`

- Update (PUT)
  - `PUT /api/subscribers/{id}` (partial/fields in request)

- Delete (DELETE)
  - `DELETE /api/subscribers/{id}`

- Search / filters
  - Search by name: `GET /api/subscribers/search?name=joe`
  - Filter by plan: `GET /api/subscribers/plan/{plan}`
  - Filter by status: `GET /api/subscribers/status/{status}`
  - Filter by email domain: `GET /api/subscribers/domain/{domain}`

Curl example (create):
```bash
curl -H "Content-Type: application/json" -d '{"name":"John","email":"john@example.com","phoneNumber":"123","plan":"Basic"}' http://localhost:8080/api/subscribers
```

## Validation rules
- `plan` allowed values (case-insensitive): `Basic`, `Premium`, `Enterprise`  
  Stored/normalized to Title case.
- `status` allowed values (enum, case-insensitive): `ACTIVE`, `SUSPENDED`, `CANCELLED`  
  If omitted on create -> default `ACTIVE`. Invalid enum values will produce 400 (Jackson deserialization).

Files of interest:
- `src/main/java/com/telecom/submanapi/model/Subscriber.java`
- `src/main/java/com/telecom/submanapi/enums/SubscriberStatus.java`
- `src/main/java/com/telecom/submanapi/service/SubscriberService.java`
- `src/main/java/com/telecom/submanapi/controller/SubscriberController.java`

## Sonar / Code notes
- Replaced `Collectors.toList()` with `Stream.toList()` to satisfy Sonar rule `java:S6204` (unmodifiable list semantics).
- Defaulting of `status` enforced in `createSubscriber` and model to avoid `null` status on omitted payloads.

## Troubleshooting
- 400 on create: likely invalid enum/string for `status` or `plan` — check request JSON values.
- If you need lenient mapping or custom error messages, use a request DTO and map strings to `SubscriberStatus.fromString(...)`.

## Contributing
- Open issues or submit PRs. Keep changes small and add tests.

## License
Proprietary / internal
```
