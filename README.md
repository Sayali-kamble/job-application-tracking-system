# Job Application Tracking System

## 📌 Project Overview

Job seekers often apply to multiple jobs across platforms like Naukri, LinkedIn, referrals, and company career portals but lose track of their application status. 
At the same time, recruiters use spreadsheets or other tools, which makes it difficult to track candidates, hiring stages, and overall progress.

This project solves both problems by providing a **role-based Application Tracking System** where:
* **Candidates** can track all their job applications in one place
* **Recruiters** can manage job openings, candidates, and the hiring pipeline efficiently

---

## 🎯 Objectives

* Provide clarity and transparency in job application tracking
* Allow candidates to record and monitor applications from external platforms
* Help recruiters manage job openings and candidate pipelines
  
---

## Modules Implemented

### 1️⃣ Authentication & Authorization

* User registration as **Candidate** or **Recruiter**
* Secure login using **JWT authentication**
* Role-Based Access Control (RBAC)

---

### 2️⃣ Candidate Module

 Job Application Management
 Application Status Tracking
 Candidate Dashboard

---

### 3️⃣ Recruiter Module

 Job Opening Management
 Candidate Management
 Hiring Pipeline & Status Management
 Recruiter Dashboard & Reports
---

## 🛠 Tech Stack

* **Backend:** Java, Spring Boot
* **Security:** Spring Security, JWT
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Testing:** Postman
* **Build Tool:** Maven

---

## ⚙️ Key Features

* JWT-based secure authentication
* Role-based API access
* Pagination and filtering
* Centralized exception handling
* Production-grade logging

---

## 🔐 Security

* Password encryption using BCrypt
* JWT token-based authentication
* Role-based endpoint protection
* Unauthorized access handling

---

API Endpoints
##Authentication
POST /api/auth/register – Register as Candidate or Recruiter
POST /api/auth/login – Login and receive JWT token

##Candidate APIs
POST /api/candidate/applications – Add a new job application
GET /api/candidate/applications – View applications 
PATCH /api/candidate/applications/{id}/status – Update application status

##Recruiter APIs
POST /api/recruiter/jobs – Create a job opening
PATCH /api/recruiter/candidates/{id}/stage – Move candidate through hiring stages
GET /api/recruiter/dashboard – View open positions, candidates per job

---

## 🚀 Future Enhancements

* Email notifications for status changes
* Deployment on AWS

---

## 👩‍💻 Author

**Sayali Kamble**
Java Developer 

---

