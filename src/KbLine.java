package src;

public class KbLine implements Comparable<KbLine> {
    private String term;
    private String statement;
    private double cScore;

    public KbLine(String term, String statement, double cScore) {
        this.term = term;
        this.statement = statement;
        this.cScore = cScore;
    }

    /**
     * Creates an object out of a line in the kb
     * 
     * @param line
     */
    public KbLine(String line) {
        String[] parts = line.split("\t");
        if (parts.length == 3) {
            this.term = parts[0];
            this.statement = parts[1];
            this.cScore = Double.parseDouble(parts[2]);
        } else {
            throw new IllegalArgumentException("Invalid input line: " + line);
        }
    }

    /**
     * 
     * @return term
     */
    public String getTerm() {
        return term;
    }

    /**
     * 
     * @return statement
     */
    public String getStatement() {
        return statement;
    }

    /**
     * 
     * @return confidence score
     */
    public double getcScore() {
        return cScore;
    }

    /**
     * 
     * @param term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 
     * @param statement
     */
    public void setStatement(String statement) {
        this.statement = statement;
    }

    /**
     * 
     * @param cScore
     */
    public void setCScore(double cScore) {
        this.cScore = cScore;
    }

    /**
     * overrides toString method
     */
    @Override
    public String toString() {
        return term + "\t" + statement + "\t" + cScore;
    }

    /**
     * overrides compareTo method
     */
    @Override
    public int compareTo(KbLine other) {
        /////////// compare based on the term
        return this.term.compareTo(other.term);
    }
}
