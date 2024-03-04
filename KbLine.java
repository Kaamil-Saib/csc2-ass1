public class KbLine {
    private String term;
    private String statement;
    private double cScore;

    // Creates an object out of a line in the kb
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

    public String getTerm() {
        return term;
    }

    public String getStatement() {
        return statement;
    }

    public double getcScore() {
        return cScore;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setCScore(double cScore) {
        this.cScore = cScore;
    }

    @Override
    public String toString() {
        return term + "\t" + statement + "\t" + cScore;
    }
}
