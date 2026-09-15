# AI Resume Screening & Job Matching System — Architecture

## High-Level Architecture

```text
                         INTERNET
                            |
                            v
                    +---------------+
                    | React Frontend|
                    +---------------+
                            |
                            | HTTP/REST
                            v
                    +---------------+
                    | Spring Boot   |
                    | Backend       |
                    +---------------+
                       |         |
                       |         |
                       v         v
                  +--------+  +-------------+
                  | MySQL  |  | FastAPI AI  |
                  +--------+  +-------------+
                                  |
                                  v
                         +----------------+
                         | NLP Processing |
                         +----------------+
                                  |
                    +-------------+-------------+
                    |             |             |
                    v             v             v
               Skill Match    TF-IDF       Semantic
                             Similarity     Matching
                    \             |             /
                     \            |            /
                      +-----------+-----------+
                                  |
                                  v
                         Hybrid Match Score
                                  |
                                  v
                          Match Result
```

## Main Components

### React Frontend

Responsible for:

* Registration
* Login
* Candidate dashboard
* Resume upload
* Job selection
* Match results
* Recruiter dashboard

### Spring Boot Backend

Responsible for:

* REST APIs
* Authentication
* Authorization
* User management
* Resume management
* Job management
* Match result management
* Database communication
* Communication with FastAPI

### FastAPI AI Service

Responsible for:

* Resume parsing
* Text preprocessing
* Skill extraction
* Skill normalization
* Job processing
* TF-IDF matching
* Semantic matching
* Hybrid scoring
* Skill gap analysis
* Recommendations

### MySQL

Stores:

* Users
* Resumes
* Jobs
* Match results

### AWS

Future deployment:

* EC2 → application services
* RDS → MySQL
* S3 → resume storage
