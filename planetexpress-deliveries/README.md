# 🛸 Planet Express Deliveries

**Intergalactic Delivery System powered by Spring Boot** 🚀

This project simulates a fun, animated delivery system in the style of Futurama's Planet Express. It's designed to showcase Spring Boot features, Native Images with GraalVM, Testcontainers, and terminal-based visualizations.

---

## 🚀 How to Run

### 🖥️ From IntelliJ (Recommended)

1. Open the folder `planetexpress-deliveries` directly in IntelliJ.
2. Wait for Maven to finish importing.
3. Right-click `PlanetExpressApplication.java` → Run
4. Or right-click `PlanetExpressCli.java` → Run to use the interactive console demo.

### 🔧 From Terminal

```bash
cd planetexpress-deliveries
mvn spring-boot:run       # Run web app
mvn compile exec:java \
  -Dexec.mainClass="com.planetexpress.deliveries.cli.PlanetExpressCli"  # Run CLI
```

> ✅ Ensure you have JDK 21+ and Maven installed.

---

## 🧪 Run Tests

```bash
mvn clean test
```

Runs:
- Unit tests with animation
- Integration tests using Testcontainers (PostgreSQL)

---

## 📦 Project Structure

```
planetexpress-deliveries/
├── src/main/java
│   └── com.planetexpress.deliveries
│       ├── cli/                  # CLI entrypoint
│       ├── console/              # Animations + ASCII art
│       ├── model/                # Domain model
│       ├── service/              # Core delivery logic
│       ├── api/                  # REST controllers
│       └── PlanetExpressApplication.java
├── src/test/java                # JUnit 5 tests
├── banner.txt                   # Custom startup banner
├── pom.xml                      # Maven config
```

---

## ✨ Features

- ✅ ASCII animations (colored with ANSI codes)
- ✅ Custom startup banner (Spring Boot style)
- ✅ Fun test scenarios (e.g. pizza cannot be delivered to Uranus 🍕🪐)
- ✅ Testcontainers for PostgreSQL
- ✅ Interactive CLI (no HTTP needed)

---

## 🧊 Native Image with GraalVM

_WIP — to be added before Spring.io talk._

---

## 🐳 Docker & Dev Containers

_WIP — Dockerfile and devcontainer.json coming next._

---

## 📖 License

MIT — Have fun and share the galaxy! 🌌

