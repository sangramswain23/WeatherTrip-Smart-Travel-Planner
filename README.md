# WeatherTrip – Smart Travel Planner

A full‑stack travel assistant that provides **real‑time weather**, **nearby attractions**, and an intelligent **best‑time‑to‑visit** recommendation for any city.

## Features

* Live weather data (OpenWeatherMap API)
* Nearby tourist attractions (Geoapify Places API)
* JWT‑based secure login & protected APIs
* Trip recommendation based on weather patterns
* Responsive UI using HTML, CSS, JavaScript
* REST API backend with Spring Boot

## Tech Stack

**Backend:** Java, Spring Boot, Spring Security, JWT, REST APIs
**Frontend:** HTML, CSS, JavaScript, Fetch API
**Tools:** Postman, Eclipse, VS Code, GitHub

## API Overview

* **GET** /api/weather/{city} — Fetch weather
* **GET** /api/attractions/{city} — Nearby places
* **POST** /api/auth/login — Login
* **POST** /api/auth/register — Register

## Project Architecture

```
/weathertrip
 ├── backend (Spring Boot)
 │    ├── src/main/java
 │    ├── src/main/resources
 │    ├── pom.xml
 ├── frontend
 │    ├── index.html
 │    ├── style.css
 │    ├── app.js
```

## Running the Project

**Backend:**

```
mvn spring-boot:run
```

**Frontend:** Open `index.html` in browser

## Author

Sangram Swain
