/*
 * Clase Matrix con getter y setters de las filas y columnas
 */

public class Matrix implements java.io.Serializable{

    public int[][] doubleArray;
  
    Matrix(int nrows, int ncols) {
      doubleArray = new int[nrows][ncols];
    }
  
  
    int getCols() { //obtener columnas
      return doubleArray[0].length;
    }
  
    int getRows() { //obtner filas 
      return doubleArray.length;
    }
  
    int getValue(int row, int col) {
      //System.out.println(doubleArray[row][col]);
      return doubleArray[row][col];
    }
  
    void setValue(int row, int col, int value) {
      doubleArray[row][col] = value;
    }
  }