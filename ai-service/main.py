from fastapi import FastAPI

app = FastAPI(
    title="AI Resume Screening Service",
    description="AI service for resume processing and job matching",
    version="1.0.0"
)


@app.get("/health")
def health_check():
    return {
        "status": "UP",
        "service": "AI Resume Screening Service"
    }