
---

# Java Enterprise Applications Course Work

Welcome to the Java Enterprise Applications Course Work repository. This project is a Spring Boot application with several enhancements designed to demonstrate enterprise-level functionalities.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Usage](#usage)
- [Contributing](#contributing)
- [Acknowledgements](#acknowledgements)

## Project Overview

This project builds upon a basic Spring Boot application by adding features such as user role management, file uploads, and dynamic course listings.

## Features

1. **User Profile Management**
    - Add a profile picture for each user.

2. **Role Management**
    - Introduce a new role: `LECTURER`.
    - Lecturers can:
      - Log in and access a dashboard.
      - See all the courses they are responsible for.
      - Upload course materials (PDF only, max size 10MB).
      - Delete uploaded documents.
      - View a list of students enrolled in their courses.

3. **Administrator Features**
    - Register lecturers and assign them to specific courses.
    - Add courses with images and descriptions (max 600 characters).

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Git

### Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/elgalika/JavaEnterpriseApplicationsCourseWork.git
    ```

2. **Navigate to the project directory:**
    ```sh
    cd JavaEnterpriseApplicationsCourseWork
    ```

3. **Install dependencies and build the project:**
    ```sh
    mvn clean install
    ```

4. **Run the application:**
    ```sh
    mvn spring-boot:run
    ```

### Usage

1. **Access the Application:**
   Open a web browser and go to `http://localhost:8080`.

2. **Admin Login:**
   - Register lecturers and assign courses.
   - Manage course creation with images and descriptions.

3. **Lecturer Login:**
   - Upload course materials.
   - Manage course content and student lists.

## Contributing

1. **Fork the repository.**
2. **Create a new branch:**
    ```sh
    git checkout -b feature/your-feature-name
    ```
3. **Commit your changes:**
    ```sh
    git commit -m 'Add some feature'
    ```
4. **Push to the branch:**
    ```sh
    git push origin feature/your-feature-name
    ```
5. **Create a new Pull Request.**

## Acknowledgements

This coursework is part of the 'Enterprise Applications in Java' course. Special thanks to our instructor for the guidance and support.

---

Feel free to make any additional adjustments to suit your needs!
