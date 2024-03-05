import java.util.Scanner;

public class GenericsKbArrayApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int user_input = 0;
        ///// intializing KB
        String[] kb_array = { null };

        while (user_input != 5) {
            ////////// Menu
            System.out.println("Choose an action from the menu:\n" +
                    "1. Load a knowledge base from a file\n" +
                    "2. Update statement in the knowledge base\n" +
                    "3. Search for an item in the knowledge base by term\n" +
                    "4. Search for an item in the knowledge base by term and sentence\n" +
                    "5. Quit\n" +
                    "Enter your choice:");
            ///// the option the user selected
            user_input = scanner.nextInt();

            switch (user_input) {
                case 1:

                    //// Load KB to Array/////////////////////////
                    kb_array = LoadKB.loadToArray("GenericsKB.txt");
                    System.out.println("\nKnowledge base loaded successfully.\n");
                    break;

                case 2:
                    // updateStatment()
                    if (kb_array[0] != null) {
                        String term, statement, cScore;
                        scanner.nextLine();

                        System.out.println("Enter the term: ");
                        term = scanner.nextLine();
                        System.out.println("Enter the statement: : ");
                        statement = scanner.nextLine();
                        System.out.println("Enter the confidence score: ");
                        cScore = scanner.nextLine();

                        // running the function to update KB
                        ArrayOps.updateStatment(term, statement, cScore, kb_array);
                        System.out.println("\nStatement for term " + term + " has been updated.\n");

                    } else {
                        System.out.println("\nKnowledge base has not been loaded.\n");

                    }
                    break;

                case 3:
                    // searchByTerm()
                    if (kb_array[0] != null) {

                        scanner.nextLine();
                        System.out.println("\nEnter the term to search: ");
                        String searchTermOnly = scanner.nextLine();
                        System.out.println();

                        String termOnlyResult = ArrayOps.searchByTerm(searchTermOnly, kb_array);
                        System.out.println(termOnlyResult);

                    } else {

                        System.out.println("\nKnowledge base has not been loaded.\n");
                    }
                    break;
                case 4:

                    if (kb_array[0] != null) {

                        scanner.nextLine();
                        System.out.println("\nEnter the term to search: ");
                        String searchTerm = scanner.nextLine();
                        System.out.println();
                        System.out.println("\nEnter the statement to search for: ");
                        String searchStatement = scanner.nextLine();
                        System.out.println();

                        String termStatementResult = ArrayOps.searchByTermSen(searchTerm, searchStatement,
                                kb_array);
                        System.out.println(termStatementResult);

                    } else {
                        System.out.println("\nKnowledge base has not been loaded.\n");

                    }
                    // searchByTermSen()
                    break;
                case 5:
                    // exitConfirm()
                    break;

                default:
                    break;
            }
        }
        scanner.close();
    }
}