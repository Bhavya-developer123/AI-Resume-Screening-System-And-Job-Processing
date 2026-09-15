# AI Resume Screening & Job Matching System — Database Design

## Database

```text
Database Name: resume_screening_db
```

## Tables

The system contains four primary tables:

1. users
2. resumes
3. jobs
4. match_results

## Entity Relationship

```text
                 users
                /     \
               /       \
              ▼         ▼
          resumes      jobs
              \         /
               \       /
                ▼     ▼
             match_results
```

## Relationships

### User → Resume

```text
User 1 ───── * Resume
```

One user can have multiple resumes.

Foreign key:

```text
resumes.user_id → users.id
```

### User → Job

```text
User 1 ───── * Job
```

A recruiter can create multiple jobs.

Foreign key:

```text
jobs.recruiter_id → users.id
```

### Resume → MatchResult

```text
Resume 1 ───── * MatchResult
```

A resume can have multiple matching results for different jobs.

Foreign key:

```text
match_results.resume_id → resumes.id
```

### Job → MatchResult

```text
Job 1 ───── * MatchResult
```

A job can have multiple candidate matching results.

Foreign key:

```text
match_results.job_id → jobs.id
```

## users

| Column     | Type         | Constraint           |
| ---------- | ------------ | -------------------- |
| id         | BIGINT       | Primary Key          |
| name       | VARCHAR(100) | NOT NULL             |
| email      | VARCHAR(150) | UNIQUE               |
| password   | VARCHAR(255) | NOT NULL             |
| role       | VARCHAR(20)  | NOT NULL             |
| created_at | TIMESTAMP    | Default current time |

## resumes

| Column      | Type         | Constraint           |
| ----------- | ------------ | -------------------- |
| id          | BIGINT       | Primary Key          |
| user_id     | BIGINT       | Foreign Key          |
| file_name   | VARCHAR(255) | NOT NULL             |
| file_path   | VARCHAR(500) | NOT NULL             |
| uploaded_at | TIMESTAMP    | Default current time |

## jobs

| Column           | Type         | Constraint           |
| ---------------- | ------------ | -------------------- |
| id               | BIGINT       | Primary Key          |
| title            | VARCHAR(200) | NOT NULL             |
| description      | TEXT         | NOT NULL             |
| required_skills  | TEXT         |                      |
| preferred_skills | TEXT         |                      |
| experience       | VARCHAR(100) |                      |
| location         | VARCHAR(150) |                      |
| employment_type  | VARCHAR(100) |                      |
| recruiter_id     | BIGINT       | Foreign Key          |
| created_at       | TIMESTAMP    | Default current time |

## match_results

| Column          | Type         | Constraint           |
| --------------- | ------------ | -------------------- |
| id              | BIGINT       | Primary Key          |
| resume_id       | BIGINT       | Foreign Key          |
| job_id          | BIGINT       | Foreign Key          |
| match_score     | DECIMAL(5,2) |                      |
| matched_skills  | TEXT         |                      |
| missing_skills  | TEXT         |                      |
| recommendations | TEXT         |                      |
| created_at      | TIMESTAMP    | Default current time |

## Complete Relationship Diagram

```text
                    ┌──────────────┐
                    │    users     │
                    │--------------│
                    │ id (PK)      │
                    │ name         │
                    │ email        │
                    │ password     │
                    │ role         │
                    └──────┬───────┘
                           │
                ┌──────────┴──────────┐
                │                     │
                │                     │
                ▼                     ▼
       ┌────────────────┐    ┌────────────────┐
       │    resumes     │    │      jobs      │
       │----------------│    │----------------│
       │ id (PK)        │    │ id (PK)        │
       │ user_id (FK)   │    │ recruiter_id   │
       │ file_name      │    │ title          │
       │ file_path      │    │ description    │
       └───────┬────────┘    └────────┬───────┘
               │                      │
               │                      │
               └──────────┬───────────┘
                          ▼
                 ┌──────────────────┐
                 │  match_results   │
                 │------------------│
                 │ id (PK)          │
                 │ resume_id (FK)   │
                 │ job_id (FK)      │
                 │ match_score      │
                 │ matched_skills   │
                 │ missing_skills   │
                 │ recommendations  │
                 └──────────────────┘
```
