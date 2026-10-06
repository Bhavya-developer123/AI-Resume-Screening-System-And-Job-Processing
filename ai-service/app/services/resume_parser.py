import os
from pypdf import PdfReader
from docx import Document
class ResumeParser:
    def parse(self, file_path: str) -> str:
        if not os.path.exists(file_path):
            raise FileNotFoundError("Resume file not found")
        if os.path.getsize(file_path) == 0:
            raise ValueError("Resume file is empty")
        extension = os.path.splitext(file_path)[1].lower()
        if extension == ".pdf":
            return self._parse_pdf(file_path)
        elif extension == ".docx":
            return self._parse_docx(file_path)
        else:
            raise ValueError(
                "Unsupported file format. Only PDF and DOCX are supported."
            )

    def _parse_pdf(self, file_path: str) -> str:
        try:
            reader = PdfReader(file_path)
            text = ""
            for page in reader.pages:
                page_text = page.extract_text()
                if page_text:
                    text += page_text + "\n"
            text = text.strip()
            if not text:
                raise ValueError("PDF contains no extractable text")
            return text
        except ValueError:
            raise
        except Exception as e:
            raise ValueError(
                "Invalid or corrupted PDF file"
            ) from e
    def _parse_docx(self, file_path: str) -> str:
        try:
            document = Document(file_path)
            text = ""
            for paragraph in document.paragraphs:
                if paragraph.text.strip():
                    text += paragraph.text + "\n"
            text = text.strip()
            if not text:
                raise ValueError("DOCX contains no extractable text")
            return text
        except ValueError:
            raise
        except Exception as e:
            raise ValueError(
                "Invalid or corrupted DOCX file"
            ) from e