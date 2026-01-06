# Application Tracking System (ATS)

## 📌 Project Overview

Job seekers often apply to multiple jobs across platforms like Naukri, LinkedIn, referrals, and company career portals but lose track of their application status. The most frustrating part is not rejection, but **no response**.

At the same time, recruiters and HR teams rely on spreadsheets or disconnected tools, which leads to poor visibility of candidates, hiring stages, and pipeline progress.

This project solves both problems by providing a **role-based Application Tracking System** where:

* **Candidates** can track all their job applications in one place
* **Recruiters** can manage job openings, candidates, and the hiring pipeline efficiently

---

## 🎯 Objectives

* Provide clarity and transparency in job application tracking
* Allow candidates to record and monitor applications from external platforms
* Help recruiters manage job openings and candidate pipelines
* Eliminate hiring silence using automated status handling

---

## 🧩 Modules Implemented

### 1️⃣ Authentication & Authorization

* User registration as **Candidate** or **Recruiter**
* Secure login using **JWT authentication**
* Role-Based Access Control (RBAC)
* Secure logout

---

### 2️⃣ Candidate Module

#### Job Application Management

* Add job applications manually
* Track applications from:

  * Naukri
  * LinkedIn
  * Company websites
  * Referrals
* Edit and delete applications
* Store application details:

  * Company name
  * Job role
  * Platform
  * Applied date
  * Status
  * Notes

#### Application Status Tracking

* Predefined statuses:

  * Applied
  * Under Review
  * Shortlisted
  * Selected
  * Rejected
  * No Response
* Manual status updates
* Automatic marking as **No Response** after configurable days using scheduler

#### Candidate Dashboard

* Total applications
* Applications grouped by status
* Recent applications
* Search and filter by:

  * Company
  * Job role
  * Platform
  * Status
  * Date range

---

### 3️⃣ Recruiter Module

#### Job Opening Management

* Create job openings
* Update or close job openings
* Store job details:

  * Job title
  * Department
  * Location
  * Employment type
  * Job status

#### Candidate Management

* Add candidates manually
* Upload and store resumes
* Edit candidate details
* Assign candidates to job openings

#### Hiring Pipeline & Status Management

* Move candidates through stages:

  * Resume Received
  * Screening
  * Interview Round 1
  * Interview Round 2
  * HR Round
  * Selected
  * Rejected
  * On Hold
* Add remarks and feedback at each stage

#### Recruiter Dashboard & Reports

* Open positions count
* Total candidates per job
* Candidates grouped by hiring stage
* Filter candidates by job opening and status
* Identify hiring bottlenecks

---

## 🛠 Tech Stack

* **Backend:** Java, Spring Boot
* **Security:** Spring Security, JWT
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Validation:** Hibernate Validator
* **Logging:** SLF4J
* **Testing:** Postman
* **Build Tool:** Maven

---

## ⚙️ Key Features

* JWT-based secure authentication
* Role-based API access
* Pagination and filtering
* Scheduler-based automation
* Centralized exception handling
* Production-grade logging

---

## 🗂 Database Design

* Users
* Candidate Applications
* Job Openings
* Recruiter Candidates
* Hiring Stages & Remarks

(Relationships implemented using JPA mappings)

---

## 🔐 Security

* Password encryption using BCrypt
* JWT token-based authentication
* Role-based endpoint protection
* Unauthorized access handling

---

## 🧪 API Testing

All APIs were tested using **Postman** with JWT authorization.

* Separate flows tested for Candidate and Recruiter
* Pagination and filtering verified
* Role-based access validated

---

## 🚀 Future Enhancements

* Email notifications for status changes
* Resume parsing
* Analytics dashboards
* Deployment on AWS

---

## 👩‍💻 Author

**Sayali Kamble**
Java Backend Developer (Fresher)

---

## 📌 Note

This project was built with a focus on **real-world hiring workflows**, clean architecture, and interview readiness.
