# VanillaPulse

> A modular analytics platform for Fabric Minecraft servers.

VanillaPulse is an open-source analytics platform designed for Fabric-based Minecraft servers. Instead of focusing only on statistics, the project aims to provide a complete ecosystem for server monitoring, player achievements, rankings, Discord integration, and historical event tracking.

The project is composed of independent modules that communicate through a shared data layer, allowing new features to be added without impacting the existing architecture.

---

## ✨ Features

### Minecraft Mod

* Event-driven architecture
* Real-time player statistics
* Custom achievement system
* SQLite persistence
* Server lifecycle monitoring
* Configurable settings
* Extensible event listeners

### Discord Bot

* Player statistics
* Leaderboards
* Achievement notifications
* Server timeline
* Administrative commands

### Future

* REST API
* Web Dashboard
* PostgreSQL support
* WebSocket events
* Plugin API
* Multi-server support

---

# Architecture

```text
                Minecraft Server
                       │
                VanillaPulse Mod
                       │
         ┌─────────────┴─────────────┐
         │                           │
         ▼                           ▼
      SQLite                    REST API (Future)
         │
         ▼
    Discord Bot
         │
         ▼
       Discord
```

The mod is responsible for collecting events from the Minecraft server.

The bot consumes the collected data and presents statistics, rankings, and achievements to Discord users.

The communication between both applications happens through the persistence layer, keeping them fully decoupled.

---

# Project Goals

* Learn and apply software engineering concepts.
* Build a scalable Fabric mod.
* Practice event-driven architecture.
* Develop a maintainable Java project.
* Integrate multiple applications.
* Create a reusable analytics platform for Minecraft servers.

---

# Technologies

## Backend

* Java 25
* Fabric API
* Fabric Loom
* Gradle

## Database

* SQLite

## Bot

* Python
* discord.py

## Version Control

* Git
* GitHub

---

# Project Structure

```text
VanillaPulse/
│
├── mod/
│   ├── achievement/
│   ├── command/
│   ├── config/
│   ├── database/
│   ├── event/
│   ├── listener/
│   ├── model/
│   ├── service/
│   ├── stats/
│   ├── util/
│   └── VanillaPulse.java
│
├── bot/
│   ├── commands/
│   ├── database/
│   ├── repositories/
│   ├── services/
│   └── main.py
│
├── docs/
│
├── roadmap/
│
└── README.md
```

---

# Development Roadmap

## v0.1 — Foundation

* [ ] Project setup
* [ ] Hello World
* [ ] Fabric initialization
* [ ] Logging system
* [ ] Basic project structure

---

## v0.2 — Events

* [ ] Player Join
* [ ] Player Quit
* [ ] Block Break
* [ ] Entity Kill
* [ ] Player Death

---

## v0.3 — Persistence

* [ ] SQLite integration
* [ ] Database manager
* [ ] Player repository
* [ ] Statistics repository

---

## v0.4 — Statistics

* [ ] Death counter
* [ ] Mob kills
* [ ] Mining statistics
* [ ] Playtime
* [ ] Distance traveled

---

## v0.5 — Achievement System

* [ ] Achievement registry
* [ ] Achievement manager
* [ ] Custom achievements
* [ ] Notifications

---

## v0.6 — Discord Integration

* [ ] Discord bot
* [ ] Player profile
* [ ] Rankings
* [ ] Timeline
* [ ] Achievement feed

---

## v0.7 — REST API

* [ ] Player endpoints
* [ ] Statistics endpoints
* [ ] Leaderboard endpoints

---

## v1.0

* Stable release
* Complete documentation
* Public release
* GitHub Releases

---

# Design Principles

* Single Responsibility Principle
* Dependency Inversion
* Event-Driven Architecture
* Modular Design
* Clean Code
* Extensibility
* Maintainability

---

# Long-Term Vision

VanillaPulse is designed to become more than a Minecraft mod.

The long-term goal is to build a complete platform capable of collecting, storing, and exposing server analytics through multiple interfaces, including Discord, REST APIs, and future web dashboards.

Every module should remain independent so that the project can evolve without major architectural changes.

---

# License

This project is licensed under the MIT License.
