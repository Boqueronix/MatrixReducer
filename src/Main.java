import java.util.Scanner;

public class Main {
    private static int rows;
    private static int columns;
    public static void main(String[] args) {
        System.out.println("Please enter the number of rows:");
        Scanner scanner = new Scanner(System.in);
        rows = scanner.nextInt();
        System.out.println("Please enter the number of columns");
        columns = scanner.nextInt();
        Matrix matrix = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            double[] row = new double[columns];
            System.out.println("Please enter row " + (i + 1));
            for (int j = 0; j < columns; j++) {
                row[j] = scanner.nextDouble();
            }
            if (!matrix.addRow(row)) {
                break;
            }
        }
        System.out.println(matrix);
        String response;
        boolean finding = true;
        while (finding) {
            System.out.println("Enter REF for Row Echelon Form or RREF for Reduced Row Echelon Form");
            response = scanner.next();
            switch (response) {
                case "REF" -> {
                    matrix.reduceToREF();
                    finding = false;
                }
                case "RREF" -> {
                    matrix.reduceToRREF();
                    finding = false;
                }
                default -> System.out.println("Failed. Try Again");
            }
        }
        System.out.println("END RESULT: \n" + matrix);
    }
}