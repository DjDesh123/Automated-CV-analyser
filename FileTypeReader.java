import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class FileTypeReader {

    // General method to process any file type
    public static String extractText(String filePath) {
        if (filePath.endsWith(".pdf")) {
            return extractTextFromPDF(filePath);
        } else if (filePath.endsWith(".docx")) {
            return extractTextFromDOCX(filePath);
        } else if (filePath.endsWith(".txt")) {
            return extractTextFromTXT(filePath);
        } else {
            System.out.println("Unsupported file type: " + filePath);
            return null;
        }
    }

    // PDF Reader
    private static String extractTextFromPDF(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // DOCX Reader
    private static String extractTextFromDOCX(String filePath) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             XWPFDocument document = new XWPFDocument(fis)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                text.append(paragraph.getText()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return text.toString();
    }

    // TXT Reader
    private static String extractTextFromTXT(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
