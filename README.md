# Message Standardizer

A Spring Boot 3.0 application that standardizes messages from different providers into a common format.

## Overview

This application receives messages from two different providers (Alpha and Beta) with different formats and transforms them into a standardized format before sending to a queue.

### Supported Message Types

**Provider Alpha:**
- `odds_update` → `ODDS_CHANGE`
- `settlement` → `BET_SETTLEMENT`

**Provider Beta:**
- `ODDS` → `ODDS_CHANGE`
- `SETTLEMENT` → `BET_SETTLEMENT`

## Endpoints

- `POST /provider-alpha/feed` - Accepts Alpha provider messages
- `POST /provider-beta/feed` - Accepts Beta provider messages

## Running the Application

# Prerequisite
# Java (jdk) 17
# Maven 3+

First navigate to the <PROJECT_DIR> eg /home/yash/message-standardizer and then run the below commands

```bash
# Build the application
mvn clean compile

# Run the application
mvn spring-boot:run
```

The application will start on port 8080.

## Testing

Use the provided test data files in the `<PROJECT_DIR>/test-data/` directory:
```bash
# Test Alpha odds message
curl -X POST http://localhost:8080/provider-alpha/feed \
  -H "Content-Type: application/json" \
  -d @test-data/alpha-odds.json

# Test Alpha settlement message
curl -X POST http://localhost:8080/provider-alpha/feed \
  -H "Content-Type: application/json" \
  -d @test-data/alpha-settlement.json

# Test Beta odds message
curl -X POST http://localhost:8080/provider-beta/feed \
  -H "Content-Type: application/json" \
  -d @test-data/beta-odds.json

# Test Beta settlement message
curl -X POST http://localhost:8080/provider-beta/feed \
  -H "Content-Type: application/json" \
  -d @test-data/beta-settlement.json
```

## Message Transformation

The application automatically detects message types and transforms them:

- Generates unique message IDs
- Adds timestamps in ISO 8601 format
- Maps Alpha outcomes (1→home, X→draw, 2→away) to standard format
- Beta messages already use standard home/draw/away format

Transformed messages are sent to a dummy queue service that prints them to the console.
