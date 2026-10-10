
import re


class TextPreprocessor:

    def preprocess(self, text: str) -> dict:

        if not isinstance(text, str):
            raise ValueError("Input must be a string")

        if not text.strip():
            raise ValueError("Resume text cannot be empty")

        protected_terms = {
            "C++": "techtermcpp",
            "C#": "techtermcsharp",
            ".NET": "techtermdotnet",
            "Node.js": "techtermnodejs",
            "React.js": "techtermreactjs",
            "Spring Boot": "techtermspringboot",
            "SQL": "techtermsql"
        }

        cleaned_text = text

        # Step 1: Protect technical terms
        for term, placeholder in protected_terms.items():
            cleaned_text = re.sub(
                re.escape(term),
                placeholder,
                cleaned_text,
                flags=re.IGNORECASE
            )

        # Step 2: Convert to lowercase
        cleaned_text = cleaned_text.lower()

        # Step 3: Remove URLs
        cleaned_text = re.sub(
            r"https?://\S+|www\.\S+",
            " ",
            cleaned_text
        )

        # Step 4: Remove email addresses
        cleaned_text = re.sub(
            r"\b[\w.+-]+@[\w.-]+\.[a-zA-Z]{2,}\b",
            " ",
            cleaned_text
        )

        # Step 5: Remove unwanted punctuation
        cleaned_text = re.sub(
            r"[^\w\s]",
            " ",
            cleaned_text
        )

        # Step 6: Normalize whitespace
        cleaned_text = re.sub(
            r"\s+",
            " ",
            cleaned_text
        ).strip()

        # Step 7: Restore technical terms
        restore_terms = {
            "techtermcpp": "c++",
            "techtermcsharp": "c#",
            "techtermdotnet": ".net",
            "techtermnodejs": "node.js",
            "techtermreactjs": "react.js",
            "techtermspringboot": "spring boot",
            "techtermsql": "sql"
        }

        for placeholder, term in restore_terms.items():
            cleaned_text = cleaned_text.replace(
                placeholder, term
            )

        # Step 8: Tokenization
        tokens = cleaned_text.split()

        return {
            "cleaned_text": cleaned_text,
            "tokens": tokens
        }
