# AI Budgeting API

A REST API built with Spring Boot for registering and retrieving financial transactions.

The application supports manual transaction creation and AI-assisted creation from audio files. Audio is transcribed,
interpreted by an AI model and converted into a financial transaction stored in MySQL.

## Features

- Create financial transactions manually
- List transactions by category
- Transcribe expense audio
- Extract transaction information using AI
- Persist data in MySQL
- Run the database with Docker Compose

## Technologies

- Java
- Spring Boot
- Spring AI
- Spring Data JPA
- MySQL
- Docker
- Gradle
- Groq API

## API endpoints

The application runs by default at:

```text
http://localhost:8080
```

### Create a transaction

```http
POST /transactions
Content-Type: application/json
```

Example:

```json
{
  "description": "Lunch at a restaurant",
  "category": "GROCERIES",
  "amount": 4500
}
```

The category must match one of the values declared in the `Category` enum.

Response:

```text
201 Created
```

### List transactions by category

```http
GET /transactions/{category}
```

Example:

```http
GET http://localhost:8081/transactions/CATEGORY_NAME
```

Response:

```text
200 OK
```

### Create a transaction from audio

```http
POST /transactions/ai
Content-Type: multipart/form-data
```

IntelliJ HTTP Client example:

```http
###
POST http://localhost:8081/transactions/ai
Content-Type: multipart/form-data; boundary=boundary

--boundary
Content-Disposition: form-data; name="file"; filename="Audio01.mp3"
Content-Type: audio/mpeg

< ./src/test/resources/audio/Audio01.mp3
--boundary--
```

The application transcribes the audio, sends the transcription to the AI model and uses the extracted information to
register the transaction.

## Configuration

Set the following environment variable before starting the application:

```text
GROQ_API_KEY=your_api_key
```

Never commit your real API key.

## Running the database

Start Docker Desktop and run:

```bash
docker compose up -d
```

Check the container:

```bash
docker compose ps
```

## Running the application

On Windows:

```powershell
.\gradlew.bat bootRun
```

On Linux or macOS:

```bash
./gradlew bootRun
```

## Running the tests

On Windows:

```powershell
.\gradlew.bat test
```

On Linux or macOS:

```bash
./gradlew test
```

Integration tests that communicate with external AI services require a valid API key and internet connection.

## Project origin

This is an educational project developed by following the guided lessons from the **Itaú Java & AI Bootcamp**.

The course provided the main architecture and implementation. I reproduced the project locally to study its components,
configured the development environment, tested the endpoints and troubleshot integration issues involving Spring AI,
external APIs, Docker and MySQL.

## Author

**Neylon Eduardo**

Computer Science student focused on Java, Spring Boot and backend development.