import re
from app.services.skill_extractor import SkillExtractor
from app.services.skill_normalizer import SkillNormalizer
class ResumeInformationExtractor:
    def __init__(self):
        self.skill_extractor = SkillExtractor()
        self.skill_normalizer = SkillNormalizer()
        self.section_aliases = {
            "education": "education",
            "academic qualifications": "education",
            "experience": "experience",
            "work experience": "experience",
            "professional experience": "experience",
            "projects": "projects",
            "personal projects": "projects",
            "certifications": "certifications",
            "certificates": "certifications",
            "skills": "skills",
            "technical skills": "skills"
        }
    def extract_information(self, text: str) -> dict:
        if not isinstance(text, str):
            raise ValueError("Resume text must be a string")
        if not text.strip():
            raise ValueError("Resume text cannot be empty")
        information = {
            "skills": [],
            "education": [],
            "experience": [],
            "projects": [],
            "certifications": []
        }
        current_section = None
        section_content = {
            "education": [],
            "experience": [],
            "projects": [],
            "certifications": []
        }
        lines = text.splitlines()
        for line in lines:
            line = line.strip()
            if not line:
                continue
            heading = re.sub(r"[:\s]+$", "", line).strip().lower()
            if heading in self.section_aliases:
                current_section = self.section_aliases[heading]
                continue
            if current_section in section_content:
                section_content[current_section].append(line)
        extracted = self.skill_extractor.extract_skills(text)
        normalized = self.skill_normalizer.normalize_skills(
            extracted["skills"]
        )
        information["skills"] = normalized["skills"]
        for section in section_content:
            information[section] = section_content[section]
        return information
