public class Matrix {
    private int rows;
    private int columns;
    private double[][] array;
    private int rowFilled = 0;
    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        array = new double[columns][rows];
    }
    public boolean addRow(double[] row){
        try {
            array[rowFilled] = row;
            rowFilled++;
            return true;
        } catch (Exception e){
            System.out.println("Matrix full");
            return false;
        }
    }
    public String toString(){
        String tbr = "[";
        for (int i = 0; i < rows; i++) {
            tbr += "\n[";
            for (int j = 0; j < columns - 1; j++) {
                tbr += array[i][j] + ", ";
            }
            tbr += array[i][columns - 1] + "]";
        }
        tbr += "\n]";
        return tbr;
    }
    public void reduceToREF(){
        for (int i = 0; i < rows; i++) {
            reorder();
            int pivotNonZeroIndex = 0;
            for (int j = 0; j < columns; j++) {
                if (array[i][j] == 0) {
                    pivotNonZeroIndex++;
                } else {
                    break;
                }
            }
            scale(i, 1 / array[i][pivotNonZeroIndex]);
            for (int j = i + 1; j < rows; j++) {
                int nonZeroIndex = 0;
                for (int k = 0; k < columns; k++) {
                    if (array[j][k] == 0) {
                        nonZeroIndex++;
                    } else {
                        break;
                    }
                }
                if (nonZeroIndex == pivotNonZeroIndex) {
                    replace(j,-1 * array[j][nonZeroIndex],i);
                }
            }
        }

    }
    public void reduceToRREF(){
        reduceToREF();
        System.out.println("\n REDUCING TO RREF");
        for (int i = 1; i < rows; i++) {
            int pivotNonZeroIndex = 0;
            for (int j = 0; j < columns; j++) {
                if (array[i][j] == 0) {
                    pivotNonZeroIndex++;
                } else {
                    break;
                }
            }
            for (int j = 0; j < rows; j++) {
                if (i == j) { continue; }
                replace(j,-1 * array[j][pivotNonZeroIndex], i);
            }
        }
    }
    private void interchange(int rowIndex1, int rowIndex2){
        double[] temp = array[rowIndex1];
        array[rowIndex1] = array[rowIndex2];
        array[rowIndex2] = temp;
//        System.out.println();
//        System.out.println("Interchanged rows " + rowIndex1 + " & " + rowIndex2 + "\nResult:\n" + this);
//        System.out.println();
    }

    private void scale (int rowIndex, double scalar){
        for (int i = 0; i < columns; i++) {
            array[rowIndex][i] *= scalar;
        }
        System.out.println();
        System.out.println("Scaled row " + rowIndex + " by a factor of " + scalar + "\nResult:\n" + this);
        System.out.println();
    }

    private void replace( int rowIndex1, double scalar, int rowIndex2){
        for (int i = 0; i < columns; i++) {
            array[rowIndex1][i] += + scalar * array[rowIndex2][i];
        }
        System.out.println();
        System.out.println("Replaced row " + rowIndex1 + " with row " + rowIndex1 + " + " + scalar +  " * row " + rowIndex2 +  "\nResult:\n" + this);
        System.out.println();
    }
    private void reorder(){
        int[] numberOfZeros = new int[rows];
        for (int i = 0; i < rows; i++) {
            int zeros = 0;
            for (int j = 0; j < columns; j++) {
                if (array[i][j] == 0) {
                    zeros++;
                } else {
                    numberOfZeros[i] = zeros;
                    break;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows - 1; j++) {
                if (numberOfZeros[j] > numberOfZeros[j + 1]) {
                    interchange(j, j + 1);
                    int temp = numberOfZeros[j];
                    numberOfZeros[j] = numberOfZeros[j + 1];
                    numberOfZeros[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("Reordered matrix" +  "\nResult:\n" + this);
        System.out.println();
    }
}
