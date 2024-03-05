package src.test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import src.ArrayOps;
import src.BST;

public class tests {
    // ---------- BST ----------
    // Test populating BST KB with 100 Lines
    @Test
    public void testLoading100LineBST() {
        BST bst_100 = BST.loadToBST("KB-100Lines.txt");
        Assertions.assertEquals(100, bst_100.countLines());
    }

    // Test populating BST KB with 1000 Lines
    @Test
    public void testLoading1000LineBST() {
        BST bst_1000 = BST.loadToBST("KB-1000Lines.txt");
        Assertions.assertEquals(1000, bst_1000.countLines());
    }

    // Test populating BST KB with 10000 Lines
    @Test
    public void testLoading10000LineBST() {
        BST bst_10000 = BST.loadToBST("KB-10000Lines.txt");
        Assertions.assertEquals(10000, bst_10000.countLines());
    }

    //
    //
    //
    // ---------- Array ----------
    // Test populating Array KB with 100 Lines
    @Test
    public void testLoading100LineArray() {
        String[] arr_100 = ArrayOps.loadToArray("KB-100Lines.txt");
        Assertions.assertEquals(100, arr_100);
    }

    // -- Test update 100 Lined Array KB
    @Test
    public void testUpdate100LineArray() {
        String[] arr_100 = ArrayOps.loadToArray("KB-100Lines.txt");
        ArrayOps.updateStatment("maple syrup", "Used as a salad dressing", "1.0", arr_100);
        String[] updatedArr = ArrayOps.loadToArray("KB-100Lines.txt");
        Assertions.assertFalse(ArrayOps.searchByTermSen("maple syrup", "Used as a salad dressing",
                updatedArr) == "Term or statement not found.\n");
    }

    // -- Test searching 100 Lined Array KB
    @Test
    public void testSearch100LineArray() {
        // closer Closers are magazines. 1.0
        String[] arr_100 = ArrayOps.loadToArray("KB-100Lines.txt");
        String searchTerm = "closer";
        String result = ArrayOps.searchByTerm(searchTerm, arr_100);
        String expected = "Statement found: Closers are magazines. (Confidence interval: 1.0)";
        Assertions.assertEquals(expected, result);
    }

    // Test populating Array KB with 1000 Lines
    @Test
    public void testLoading1000LineArray() {
        String[] arr_1000 = ArrayOps.loadToArray("KB-1000Lines.txt");
        Assertions.assertEquals(1000, arr_1000.length);
    }

    @Test
    public void testUpdate1000LineArray() {
        String[] arr_1000 = ArrayOps.loadToArray("KB-1000Lines.txt");
        ArrayOps.updateStatment("maple syrup", "Used as a salad dressing", "1.0", arr_1000);
        String[] updatedArr = ArrayOps.loadToArray("KB-1000Lines.txt");
        Assertions.assertFalse(ArrayOps.searchByTermSen("maple syrup", "Used as a salad dressing",
                updatedArr).equals("Term or statement not found.\n"));
    }

    // -- Test searching 1000 Lined Array KB
    @Test
    public void testSearch1000LineArray() {
        String[] arr_1000 = ArrayOps.loadToArray("KB-1000Lines.txt");
        String searchTerm = "closer";
        String result = ArrayOps.searchByTerm(searchTerm, arr_1000);
        String expected = "Statement found: Closers are magazines. (Confidence interval: 1.0)";
        Assertions.assertEquals(expected, result);
    }

    // Test populating Array KB with 10000 Lines
    @Test
    public void testLoading10000LineArray() {
        String[] arr_10000 = ArrayOps.loadToArray("KB-10000Lines.txt");
        Assertions.assertEquals(10000, arr_10000.length);
    }

    // -- Test update 10000 Lined Array KB
    @Test
    public void testUpdate10000LineArray() {
        String[] arr_10000 = ArrayOps.loadToArray("KB-10000Lines.txt");
        ArrayOps.updateStatment("maple syrup", "Used as a salad dressing", "1.0", arr_10000);
        String[] updatedArr = ArrayOps.loadToArray("KB-10000Lines.txt");
        Assertions.assertFalse(ArrayOps.searchByTermSen("maple syrup", "Used as a salad dressing",
                updatedArr).equals("Term or statement not found.\n"));
    }

    // -- Test searching 10000 Lined Array KB
    @Test
    public void testSearch10000LineArray() {
        String[] arr_10000 = ArrayOps.loadToArray("KB-10000Lines.txt");
        System.out.println(arr_10000);

        String searchTerm = "closer";
        String result = ArrayOps.searchByTerm(searchTerm, arr_10000);
        String expected = "Statement found: Closers are magazines. (Confidence interval: 1.0)";
        Assertions.assertEquals(expected, result);
    }
}