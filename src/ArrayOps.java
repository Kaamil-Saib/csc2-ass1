import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArrayOps {

    /**
     * populates an array with contents from the textfile
     * 
     * @param fileName
     * @return array with all the contents from the given textfile
     */
    public static String[] loadToArray(String fileName) {
        // get size of array .ie number of lines in KB
        int size = 0;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                size++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] kb_array = new String[size];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                kb_array[count++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return kb_array;
    }

    /**
     * Search by Term Only
     * 
     * @param searchTerm
     * @param kb
     * @return Term info
     */
    public static String searchByTerm(String searchTerm, String[] kb) {
        String result = null;
        for (int i = 0; i < kb.length; i++) {
            KbLine kbLine = new KbLine(kb[i]);
            if (kbLine.getTerm().equals(searchTerm)) {
                result = kbLine.getStatement() + " " + "(Confidence interval: " + kbLine.getcScore() + ")";
            }
        }
        if (result == null) {
            return "\nTerm not found.\n";
        } else {
            return "Statement found: " + result + "\n";
        }
    }

    /**
     * Search by Term and Sentence
     * 
     * @param searchTerm
     * @param searchStatement
     * @param kb
     * @return Term info
     */
    public static String searchByTermSen(String searchTerm, String searchStatement, String[] kb) {
        String result = null;
        for (int i = 0; i < kb.length; i++) {
            KbLine kbLine = new KbLine(kb[i]);
            if (kbLine.getTerm().equals(searchTerm) && kbLine.getStatement().equals(searchStatement)) {
                result = "The statement was found and has a confidence score of " + kbLine.getcScore() + ".";
            }
        }
        if (result == null) {
            return "Term or statement not found.\n";
        } else {
            return result + "\n";
        }
    }

    /**
     * Update statements in the KB given the term
     * 
     * @param term
     * @param statement
     * @param cScore
     * @param kb
     */
    public static void updateStatment(String term, String statement, String cScore, String[] kb) {
        int termPosition = -1;
        for (int i = 0; i < kb.length; i++) {
            KbLine kbLine = new KbLine(kb[i]);
            if (kbLine.getTerm().equals(term)) {
                termPosition = i;
                kbLine.setStatement(statement);
                kbLine.setCScore(Double.parseDouble(cScore));
                kb[i] = kbLine.toString();
            }
            System.out.println("\nKnowledge base updated.\n");

        }
        if (termPosition == -1) {
            System.out.println("\nTerm not found.\n");
        }

    }
}