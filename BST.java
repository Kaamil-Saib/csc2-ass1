import java.util.ArrayList;
import java.util.List;

public class BST {
    private TreeNode root;

    public BST() {
        this.root = null;
    }

    public void insert(KbLine kbLine) {
        root = insertRec(root, kbLine);
    }

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

    public TreeNode getRoot() {
        return root;
    }

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
    //////// remove this when complete
    public KbLine[] getFirstNLines(int n) {
        List<KbLine> linesList = new ArrayList<>();
        getFirstNLines(root, n, linesList);
        return linesList.toArray(new KbLine[0]);
    }

    private int getFirstNLines(TreeNode root, int n, List<KbLine> linesList) {
        if (root != null && n > 0) {
            // In-order traversal for the left subtree
            n = getFirstNLines(root.left, n, linesList);

            // Add the current line to the list
            if (n > 0) {
                linesList.add(root.kbLine);
                n--;
            }

            // In-order traversal for the right subtree
            n = getFirstNLines(root.right, n, linesList);

            return n;
        }
        return n;
    }

    //
    //
    //
    ///////////////////// Searching by Term and Sentence
    public static String searchByTermSen(String searchTerm, String searchStatement, BST kbBST) {
        return searchByTermSen(kbBST.getRoot(), searchTerm, searchStatement);
    }

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
    ///////////////////// Searching by Term only
    public static String searchByTerm(String searchTerm, BST kbBST) {
        TreeNode resultNode = searchByTerm(kbBST.getRoot(), searchTerm);
        if (resultNode != null) {
            KbLine kbLine = resultNode.kbLine;
            return "Statement found: " + kbLine.getStatement() + " (Confidence interval: " + kbLine.getcScore() + ")\n";
        } else {
            return "Term not found.\n";
        }
    }

    private static TreeNode searchByTerm(TreeNode root, String searchTerm) {
        if (root == null) {
            return null; // Term not found
        }

        int termComparison = searchTerm.compareTo(root.kbLine.getTerm());

        if (termComparison == 0) {
            return root; // Term found
        } else if (termComparison < 0) {
            return searchByTerm(root.left, searchTerm); // Search in the left subtree
        } else {
            return searchByTerm(root.right, searchTerm); // Search in the right subtree
        }
    }

    //
    //
    //
    ///////////////////////// Adding or updating a term
    public static void addOrUpdateTerm(BST kbBST, String term, String statement, double cScore) {
        KbLine newKbLine = new KbLine(term, statement, cScore);
        kbBST.setRoot(addOrUpdateTerm(kbBST.getRoot(), newKbLine));
    }

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
}
