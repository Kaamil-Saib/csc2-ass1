public class ArrayOps {

    ////// Search by Term Only
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

    ////// Search by Term and Sentence
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

    /////// Update statements in the KB given the term
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
        }
        if (termPosition == -1) {
            System.out.println("\nTerm not found.\n");
        }

    }
}