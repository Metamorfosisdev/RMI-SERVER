import java.util.concurrent.RecursiveAction;
import java.util.ArrayList;
import java.util.List;
/*
 * Clase para calcular la multiplicacion de matrices
 */

public class Calc extends RecursiveAction implements java.io.Serializable{
    
    private Matrix a, b, c;  
    private int row;

    Calc(Matrix a, Matrix b, Matrix c) {
        this(a, b, c, -1);
    }

    Calc(Matrix a, Matrix b, Matrix c, int row) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.row = row;
    } 

    @Override
    public void compute() {
        if (row == -1) {
            List<Calc> tasks = new ArrayList<>();
            for (int row = 0; row < a.getRows(); row++) {
                tasks.add(new Calc(a, b, c, row));
            }
            invokeAll(tasks);
        } else {
            multiplyRowByColumn(a, b, c, row);
        }
    }

    void multiplyRowByColumn(Matrix a, Matrix b, Matrix c, int row) {
        for (int j = 0; j < b.getCols(); j++) {
            for (int k = 0; k < a.getCols(); k++) {
                c.setValue(row, j, (int)(c.getValue(row, j) +  a.getValue(row, k)* b.getValue(k, j)));
            }
        }
    }
}
