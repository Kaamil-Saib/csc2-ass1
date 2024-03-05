package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BST {
    /**
     * populates a BST with contents from the textfile
     * 
     * @param fileName
     * @return BST with all the contents from the given textfile
     */
    public static BST loadToBST(String fileName) {
        BST bst = new BST();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                KbLine kbLine = new KbLine(line);
                bst.insert(kbLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bst;
    }

    private TreeNode root;

    /**
     * BST
     */
    public BST() {
        this.root = null;
    }

    /**
     * insert kbline into bst
     * 
     * @param kbLine
     */
    public void insert(KbLine kbLine) {
        root = insertRec(root, kbLine);
    }

    /**
     * 
     * @param root
     * @param kbLine
     * @return root
     */
    private TreeNode insertRec(TreeNode root, KbLine kbLine) {
        if (root == null) {
            root = new TreeNode(kbLine);
            return root;
        }

        if (kbLine.compareTo(root.kbLine) < 0) {
            root.left = insertRec(root.left, kbLine);
        } else if (kbLine.compareTo(root.kbLine) > 0) {
            root.right = insertRec(root.right, kbLine);
        }

        return root;
    }

    /**
     * 
     * @return root node
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Sets root
     * 
     * @param newRoot
     */
    public void setRoot(TreeNode newRoot) {
        this.root = newRoot;
    }

    public static class TreeNode {
        KbLine kbLine;
        TreeNode left, right;

        public TreeNode(KbLine kbLine) {
            this.kbLine = kbLine;
            this.left = this.right = null;
        }
    }

    //
    //
    //
    /**
     * Searching by Term and Sentence
     * 
     * @param searchTerm
     * @param searchStatement
     * @param kbBST
     * @return message that the statement was found or not with the statement and
     *         its c score
     */
    public static String searchByTermSen(String searchTerm, String searchStatement, BST kbBST) {
        return searchByTermSen(kbBST.getRoot(), searchTerm, searchStatement);
    }

    /**
     * helper method for searchByTermSen
     * 
     * @param root
     * @param searchTerm
     * @param searchStatement
     * @return message that the statement was found or not with the statement and
     *         its c score
     */
    private static String searchByTermSen(TreeNode root, String searchTerm, String searchStatement) {
        if (root == null) {
            return "Term or statement not found.\n";
        }

        int termComparison = searchTerm.compareTo(root.kbLine.getTerm());
        int statementComparison = searchStatement.compareTo(root.kbLine.getStatement());

        if (termComparison == 0 && statementComparison == 0) {
            KbLine kbLine = root.kbLine;
            return "The statement was found and has a confidence score of " + kbLine.getcScore() + ".\n";
        } else if (termComparison < 0 || (termComparison == 0 && statementComparison < 0)) {
            return searchByTermSen(root.left, searchTerm, searchStatement); // Search in the left subtree
        } else {
            return searchByTermSen(root.right, searchTerm, searchStatement); // Search in the right subtree
        }
    }

    //
    //
    //
    /**
     * Searching by Term only
     * 
     * @param searchTerm
     * @param kbBST
     * @return message that statement found -> prints statement and c score or Term
     *         not found
     */
    public static String searchByTerm(String searchTerm, BST kbBST) {
        TreeNode resultNode = searchByTerm(kbBST.getRoot(), searchTerm);
        if (resultNode != null) {
            KbLine kbLine = resultNode.kbLine;
            return "Statement found: " + kbLine.getStatement() + " (Confidence interval: " + kbLine.getcScore() + ")\n";
        } else {
            return "Term not found.\n";
        }
    }

    /**
     * helper method for searchByTerm
     * 
     * @param root
     * @param searchTerm
     * @return message that statement found -> prints statement and c score or Term
     *         not found
     */
    private static TreeNode searchByTerm(TreeNode root, String searchTerm) {
        if (root == null) {
            return null; // not found
        }

        int termComparison = searchTerm.compareTo(root.kbLine.getTerm());

        if (termComparison == 0) {
            return root; // found
        } else if (termComparison < 0) {
            return searchByTerm(root.left, searchTerm); // search left subtree
        } else {
            return searchByTerm(root.right, searchTerm); // search right subtree
        }
    }

    //
    //
    //
    /**
     * Adding or updating a term
     * 
     * @param kbBST
     * @param term
     * @param statement
     * @param cScore
     */
    public static void addOrUpdateTerm(BST kbBST, String term, String statement, double cScore) {
        KbLine newKbLine = new KbLine(term, statement, cScore);
        kbBST.setRoot(addOrUpdateTerm(kbBST.getRoot(), newKbLine));
    }

    /**
     * helper method for addOrUpdateTerm
     * 
     * @param root
     * @param newKbLine
     * @return
     */
    private static TreeNode addOrUpdateTerm(TreeNode root, KbLine newKbLine) {
        if (root == null) {
            // The term is not in the BST, add a new tree node
            return new TreeNode(newKbLine);
        }

        int termComparison = newKbLine.compareTo(root.kbLine);

        if (termComparison == 0) {
            // Term is already present, update the information
            root.kbLine.setStatement(newKbLine.getStatement());
            root.kbLine.setCScore(newKbLine.getcScore());
            System.out.println("\nKnowledge base updated.\n");
        } else if (termComparison < 0) {
            // Term is smaller, go to the left subtree
            root.left = addOrUpdateTerm(root.left, newKbLine);
        } else {
            // Term is larger, go to the right subtree
            root.right = addOrUpdateTerm(root.right, newKbLine);
        }

        return root;
    }

    /**
     * Counts the number of lines in KB)
     *
     * @return The number of lines in the KB
     */
    public int countLines() {
        return countLines(root);
    }

    private int countLines(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // recursive in-order traversal to count lines
        int leftCount = countLines(root.left);
        int rightCount = countLines(root.right);

        return 1 + leftCount + rightCount;
    }

}
