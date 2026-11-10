# Smart City Waste & Recycling Manager

An intelligent, microservices-based waste and recycling management platform designed to monitor bin fill levels, optimize fleet routing, manage incidents, and broadcast real-time alerts across smart cities.

## Architecture Overview

The system is structured as an ecosystem of distributed Spring Boot microservices:

- **Identity Service (`Backend/identity-service`)**: Authentication, user roles, security, and profile management.
- **Bin Asset Service (`Backend/bin-asset-service`)**: Smart bin telemetry, IoT sensor integration, fill-level tracking, and asset metadata.
- **Fleet Routing Service (`Backend/fleet-routing-service`)**: Waste collection truck dispatch, dynamic route optimization, and vehicle tracking.
- **Incident Reporting Service (`Backend/incident-reporting-service`)**: Citizen and operator issue reporting, status workflow, and resolution tracking.
- **Real-Time Alerts Service (`Backend/real-time-alerts-service`)**: Notification engine, threshold-based triggers, and critical alerts broadcasting.

## Repository Structure

```text
.
├── Backend/
│   ├── identity-service/
│   ├── bin-asset-service/
│   ├── fleet-routing-service/
│   ├── incident-reporting-service/
│   └── real-time-alerts-service/
├── Documentation/
│   ├── Diagrams/
│   └── Presentation/
└── README.md
```

## Tech Stack

- **Java**: 17
- **Framework**: Spring Boot 3
- **Build Tool**: Apache Maven
