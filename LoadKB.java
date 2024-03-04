import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadKB {

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

}