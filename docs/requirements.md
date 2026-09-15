# AI Resume Screening & Job Matching System

## 1. Problem Statement

Recruiters often need to manually review a large number of resumes and compare them with job descriptions. This process can be time-consuming and difficult to scale.

The AI Resume Screening & Job Matching System aims to automate the initial resume screening and job matching process using Natural Language Processing and AI-based similarity techniques.

The system extracts resume information and skills, processes job descriptions, compares candidate profiles with job requirements, calculates a hybrid match score, identifies matched and missing skills, and provides recommendations.

---

## 2. Objectives

* Allow candidates to upload resumes.
* Extract useful information from resumes.
* Extract and normalize technical skills.
* Process job descriptions.
* Match resumes with jobs.
* Calculate an AI-based match score.
* Identify matched skills.
* Identify missing skills.
* Provide recommendations to candidates.
* Allow recruiters to rank candidates.
* Provide authentication and role-based authorization.
* Test and containerize the application.
* Deploy the application using AWS.

---

## 3. User Roles

### USER / CANDIDATE

The candidate can:

* Register an account.
* Login.
* Upload resumes.
* View uploaded resumes.
* View available jobs.
* Analyze a resume against a job.
* View match scores.
* View matched skills.
* View missing skills.
* View recommendations.

### RECRUITER

The recruiter can:

* Register/login.
* Create jobs.
* Update jobs.
* Delete jobs.
* Manage jobs.
* View candidates.
* View candidate match results.
* Rank candidates.

### ADMIN

The administrator can:

* Manage users.
* Manage the system.
* Perform administrative operations.

---

## 4. Functional Requirements

### Authentication

* User registration
* User login
* JWT authentication
* Logout
* Role-based authorization

### Resume Management

* Upload resume
* Support PDF
* Support DOCX
* View resume
* View user's resumes
* Delete resume
* Validate uploaded files

### Job Management

* Create job
* View jobs
* View individual job
* Update job
* Delete job

### AI Resume Processing

* Extract resume text
* Preprocess resume text
* Extract skills
* Normalize skills
* Extract education
* Extract experience
* Extract projects
* Extract certifications

### Job Processing

* Process job description
* Extract required skills
* Extract preferred skills
* Extract experience requirements

### Resume-Job Matching

* Calculate skill match
* Calculate TF-IDF similarity
* Calculate semantic similarity
* Calculate hybrid match score
* Identify matched skills
* Identify missing skills
* Calculate skill gap
* Generate recommendations

### Recruiter Features

* View candidates
* View candidate match scores
* Rank candidates
* Manage job postings

---

## 5. Non-Functional Requirements

### Security

* Passwords must be securely hashed.
* Authentication must use JWT.
* APIs must have role-based authorization.
* Secrets must not be committed to Git.

### Performance

* Resume processing should complete within a reasonable response time.
* AI processing should be efficient enough for normal application usage.

### Reliability

* Invalid files should be handled safely.
* AI service failures should return meaningful errors.
* Database failures should be handled gracefully.

### Maintainability

* Follow clean backend architecture.
* Separate controllers, services, repositories, DTOs and entities.
* Keep AI processing separated from the Spring Boot backend.

### Scalability

* The architecture should allow migration from local file storage to AWS S3.
* The database should be deployable using AWS RDS.
* Backend and AI services should be containerized.

---

## 6. Technology Stack

### Frontend

* React.js
* Vite
* Axios
* React Router

### Backend

* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT
* MySQL

### AI Service

* Python
* FastAPI
* NLP
* PyPDF
* python-docx
* Scikit-learn
* Sentence Transformers

### Matching

* TF-IDF
* Cosine Similarity
* Sentence Transformers
* Hybrid scoring

### Testing

* Postman
* JUnit
* Mockito

### Deployment

* Docker
* Docker Compose
* AWS EC2
* AWS RDS
* AWS S3

### Version Control

* Git
* GitHub
