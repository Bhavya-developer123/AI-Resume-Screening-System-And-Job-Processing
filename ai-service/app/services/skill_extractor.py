import re
class SkillExtractor:
    def __init__(self):
        self.skills = [
            "Java",
            "Python",
            "C++",
            "JavaScript",
            "React",
            "Spring Boot",
            "MySQL",
            "PostgreSQL",
            "AWS",
            "Docker",
            "Git",
            "Machine Learning",
            "Data Structures"
        ]
    def extract_skills(self, text: str) -> dict:
        if not isinstance(text, str):
            raise ValueError("Input must be a string")
        if not text.strip():
            raise ValueError("Resume text cannot be empty")
        extracted_skills = []
        for skill in self.skills:
            pattern = (
                r"(?<!\w)"
                + re.escape(skill)
                + r"(?!\w)"
            )
            if re.search(pattern, text, re.IGNORECASE):
                extracted_skills.append(skill)
        return {
            "skills": extracted_skills
        }
