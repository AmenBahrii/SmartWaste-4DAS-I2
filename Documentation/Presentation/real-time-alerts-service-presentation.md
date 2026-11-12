# Real-Time Alerts Service — Session 4 Presentation & Oral Defense Guide

> **Presenter:** Amen Bahri  
> **Service:** `real-time-alerts-service` (MS5)  
> **Duration Allocated:** 1 minute maximum (individual speaking time)  
> **Session:** Validation 1 (Architecture & Microservices Modeling)

---

## 1. Slide Content (To Include in the Team Presentation)

### Slide Title: **Microservice 5 — Real-Time Alerts & Notification Engine**

#### Left Column: Service Identity & Bounded Context
- **Name & Port:** `real-time-alerts-service` (:8085)
- **Database:** Dedicated H2 In-Memory DB (`alerts_db`)
- **Technology:** Spring Boot 3.3.5, Java 17, Spring Data JPA, Eureka Client, Lombok
- **Responsibility:** Ingest critical events, classify alert severity (`INFO`, `WARNING`, `CRITICAL`), enforce idempotent persistence, and provide REST endpoints for notification consumption.

#### Middle Column: Data Model (Alert Entity)
- **`Alert`**: `id` (Long), `eventId` (UUID - idempotency), `sourceId` (String), `message` (String), `severity` (Enum), `targetRole` (String), `createdAt` (Timestamp)
- **Idempotency Guarantee:** Prevents duplicate alert display if broker redelivers messages.

#### Right Column: Microservice Interactions
- **Asynchronous Consumption (RabbitMQ - `smartwaste.events` topic):**
  - Consumes `BinFullEvent` from **MS2 (`bin-asset-service`)** ➔ triggers `CRITICAL` alert to **DRIVER**.
  - Consumes `IncidentReportedEvent` from **MS4 (`incident-reporting-service`)** ➔ triggers `WARNING` alert to **OPERATOR**.
- **REST Endpoints Exposed:**
  - `GET /api/alerts` (list with filters)
  - `GET /api/alerts/{id}` (detail)
  - `GET /api/alerts/severity/{level}`
  - `DELETE /api/alerts/{id}` (acknowledge)

---

## 2. One-Minute Speaking Script (French & English)

### 🎙️ French Script (Recommended for ESPRIT AWD Defense — ~55 seconds)

> *"Bonjour à tous. Je m'appelle Amen Bahri et je vous présente le microservice **Real-Time Alerts Service**, situé sur le port 8085.*
>
> *Dans notre architecture, ce service constitue le **moteur de notification et de gestion des alertes critiques** de la ville intelligente.*
>
> *Au niveau du **modèle de données**, l'entité centrale est **Alert**, stockée dans sa propre base **H2 en mémoire**. Elle comporte un champ clé : l'**eventId** au format UUID, qui assure une **consommation idempotente** pour éviter tout doublon d'alerte.*
>
> *Concernant les **interactions**, mon service privilégie la **communication asynchrone via RabbitMQ** :*
> *Il s'abonne aux événements `BinFullEvent` émis par le **Bin Asset Service** pour alerter immédiatement les chauffeurs d'un débordement, et aux `IncidentReportedEvent` émis par l'**Incident Reporting Service** pour avertir les opérateurs.*
>
> *Enfin, le service expose une **API REST** sous `/api/alerts` permettant au tableau de bord de consulter et d'acquitter les alertes. Le squelette Spring Boot est initialisé, configuré avec Java 17 et Eureka, et démarre sans aucune erreur. Merci."*

---

### 🎙️ English Script (~55 seconds)

> *"Hello everyone. I am Amen Bahri, responsible for the **Real-Time Alerts Service**, running on port 8085.*
>
> *Within our SmartWaste platform, this service acts as the **event-driven notification and alerts engine**.*
>
> *Regarding the **data model**, the core entity is **Alert**, stored in its dedicated **H2 in-memory database**. It features a crucial field: `eventId` (UUID), guaranteeing **idempotent message processing** so no alert is ever duplicated.*
>
> *For **interactions**, our service relies primarily on **asynchronous messaging via RabbitMQ**:*
> *It subscribes to `BinFullEvent` from the **Bin Asset Service** to instantly alert truck drivers when a bin overflows, and to `IncidentReportedEvent` from the **Incident Reporting Service** to notify city operators.*
>
> *Finally, it exposes a clean **REST API** under `/api/alerts` for querying and acknowledging active alerts. The Spring Boot 3 skeleton is fully initialized with Java 17, compiles, and starts without any errors. Thank you."*

---

## 3. Defense Q&A Cheat Sheet (Criterion I4 — 2 Points)

Be ready for questions from the jury. Here are the expected questions and exact answers:

### Q1: Why did you choose asynchronous communication (RabbitMQ) instead of synchronous (OpenFeign) for alerts?
> **Answer:**
> *"When a bin overflows or an incident is reported, the producing service should not be blocked waiting for notification delivery (principle of loose coupling and non-blocking I/O). Furthermore, a single `BinFullEvent` needs to be broadcast to both my Alerts service and the Fleet Routing service simultaneously (Pub/Sub pattern). Asynchronous messaging via RabbitMQ provides resilience: if my service is temporarily down, messages wait safely in the queue without any loss."*

### Q2: Why is the database choice H2, and does it respect "Database per Service"?
> **Answer:**
> *"Yes, it strictly respects the 'Database per Service' pattern: `alerts_db` belongs exclusively to `real-time-alerts-service` and no other service can access its tables directly. For Validation 1, we selected embedded H2 to guarantee a zero-dependency, instantaneous startup during demonstration. In a production environment, we will seamlessly switch the JDBC driver to PostgreSQL without changing our JPA entity model."*

### Q3: What is the role of `eventId` in your Alert entity?
> **Answer:**
> *"It serves as an **idempotency token**. In distributed messaging architectures (RabbitMQ with at-least-once delivery), network retries can deliver the exact same event twice. By checking if the incoming `eventId` already exists in `alerts_db`, my service discards duplicates and acknowledges the broker without creating redundant alerts."*

### Q4: How does your service integrate with the API Gateway and Eureka?
> **Answer:**
> *"At startup, my service registers itself with the Eureka Service Registry under the name `ALERTS`. The Spring Cloud Gateway (port 8080) validates the client's JWT token (via Keycloak), queries Eureka to resolve the service instance dynamically (`lb://ALERTS`), and routes client requests matching `/api/alerts/**` with client-side load balancing."*

### Q5: What happens if your service is down when a bin becomes full?
> **Answer:**
> *"Because RabbitMQ queues are marked as durable, the `BinFullEvent` message will sit safely in the `alerts.bin-full` queue. As soon as my service restarts and reconnects, it immediately drains the queue and generates the pending alerts. The `bin-asset-service` is never affected."*
