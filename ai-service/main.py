from fastapi import FastAPI, UploadFile, File, HTTPException
import os
import shutil
import tempfile
from app.services.resume_parser import ResumeParser
from app.services.text_preprocessor import TextPreprocessor
from pydantic import BaseModel
from app.services.skill_extractor import SkillExtractor
from app.services.skill_normalizer import SkillNormalizer
app = FastAPI(
    title="AI Resume Screening Service",
    description="AI service for resume processing and job matching",
    version="1.0.0"
)
resume_parser = ResumeParser()
skill_extractor = SkillExtractor()
skill_normalizer = SkillNormalizer()
@app.get("/health")
def health_check():
    return {
        "status": "UP",
        "service": "AI Resume Screening Service"
    }
@app.post("/parse-resume")
async def parse_resume(file: UploadFile = File(...)):
    extension = os.path.splitext(file.filename)[1].lower()
    if extension not in [".pdf", ".docx"]:
        raise HTTPException(
            status_code=400,
            detail="Unsupported file format. Only PDF and DOCX are supported."
        )
    try:
        with tempfile.NamedTemporaryFile(
            delete=False,
            suffix=extension
        ) as temp_file:
            shutil.copyfileobj(file.file, temp_file)
            temp_file_path = temp_file.name
        text = resume_parser.parse(temp_file_path)
        return {
            "fileName": file.filename,
            "text": text
        }
    except FileNotFoundError as e:
        raise HTTPException(
            status_code=404,
            detail=str(e)
        )
    except ValueError as e:
        raise HTTPException(
            status_code=400,
            detail=str(e)
        )
    finally:
        if "temp_file_path" in locals() and os.path.exists(temp_file_path):
            os.remove(temp_file_path)


text_preprocessor = TextPreprocessor()
class PreprocessRequest(BaseModel):
    text: str


@app.post("/preprocess-text")
def preprocess_text(request: PreprocessRequest):

    try:
        result = text_preprocessor.preprocess(request.text)
        return result

    except ValueError as e:
        raise HTTPException(
            status_code=400,
            detail=str(e)
        )
class SkillExtractionRequest(BaseModel):
    text: str
@app.post("/extract-skills")
def extract_skills(request: SkillExtractionRequest):
    try:
        return skill_extractor.extract_skills(request.text)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))

class SkillNormalizationRequest(BaseModel):
    skills: list[str]
@app.post("/normalize-skills")
def normalize_skills(request: SkillNormalizationRequest):
    try:
        return skill_normalizer.normalize_skills(request.skills)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))