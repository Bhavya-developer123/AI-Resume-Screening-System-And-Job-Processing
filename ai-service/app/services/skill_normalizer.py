class SkillNormalizer:
    def __init__(self):
        self.aliases = {
            "javascript": "JavaScript",
            "js": "JavaScript",
            "nodejs": "Node.js",
            "node.js": "Node.js",
            "postgres": "PostgreSQL",
            "postgresql": "PostgreSQL",
            "springboot": "Spring Boot",
            "spring boot": "Spring Boot"
        }
    def normalize_skills(self, skills: list) -> dict:
        if not isinstance(skills, list):
            raise ValueError("Skills must be provided as a list")
        normalized_skills = []
        for skill in skills:
            if not isinstance(skill, str) or not skill.strip():
                continue
            cleaned_skill = skill.strip().lower()
            normalized_skill = self.aliases.get(
                cleaned_skill,
                skill.strip()
            )
            if normalized_skill not in normalized_skills:
                normalized_skills.append(normalized_skill)
        return {
            "skills": normalized_skills
        }
