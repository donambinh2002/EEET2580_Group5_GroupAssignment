# EEET2580 - Project - Group 5

## Frontend: React + Vite

- Languague: JavaScript, Library: ReactJS

## Backend

- Languague: Java, Framework: Spring Boot

## Development

### Prerequisites

- NodeJS
- Java JDK 22
- Docker
- MySQL 8.0 (optional)
- Redis (optional)

### Environment Variables

- Create .env file from the example template

```bash
cp .env.example .env
```

- do the same for each service if you want to test locally (without docker)

```bash
cp ./backend/.env.example ./backend/.env
cp ./frontend/.env.example ./frontend/.env
```

- Configure the environment variables as you see fit

### Deployment

#### Docker

```bash
docker-compose up -d
```

#### Local

1. Database and Cache

```bash
docker-compose up -d service-db service-redis
```

2. Backend

   - Build via Intellij IDEA run

3. Frontend

```bash
npm run dev
```

### Swagger UI

- [Backend Service](http://localhost:8080/swagger-ui/index.html)
