package org.example;

import java.util.Iterator;
import java.util.Scanner;

class MatrixIndexException extends RuntimeException {}
class MatrixOperationExeption extends RuntimeException {}

class Matrix implements Iterable<Double> {
    private double [][] tab;
    private int n,m;
    public Matrix(int n, int m) {
        if (n < 1 || n < 1)
            throw new MatrixIndexException();
        tab = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m ; ++j) {
                tab[i][j] = Math.random();

        }

    }

    public int getNumberOfCols(){
        return tab[0].length;
    }

    public int getNumberOfRows(){
        return tab.length;
    }

    public double get(int r, int c) {
        if(r<0 || r>= tab.length || c < 0 || c >= tab[0].length)
            throw new MatrixIndexException();
        return tab[r][c];
    }

    public Matrix add(Matrix m) {
        if(getNumberOfRows() != m.getNumberOfRows() || getNumberOfCols() !=m.getNumberOfCols())
            throw new MatrixOperationExeption();
        Matrix r = new Matrix(getNumberOfCols(), getNumberOfRows());
        for (int i = 0; i < getNumberOfRows(); ++i) {
            for (int j =0; i < getNumberOfCols(); ++j) {
                r.tab[i][j] = this.tab[i][j]+m.tab[i][j];
            }
        }
        return r;
    }

    private class ValueIterator implements Iterator<Double> {

        private int i, j;

        @Override
        public boolean hasNext() {
            return i < getNumberOfRows();
        }

        @Override
        public Double next() {
            double v = tab[i][j];
            if (j < getNumberOfCols() - 1) {
                ++j;
            } else {
                j = 0;
                ++i;
            }
            return v;
        }
    }

    @Override
    public Iterator<Double> iterator() {
        return new ValueIterator();
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Matrix m1 = new Matrix(1, 3);
        for (double v : m1) {
            System.out.println(v);
        }

        String option;
        do {
            System.out.println("Menu\n1 Wczytaj macierz A\n2 Wczytaj macierz B\n3 Wyświetl A+B\n4 Wyświetl A*B\n5 Wyjście");
            option = scanner.next();
            switch (option) {
                case "1":
                    System.out.println("Wczytaj macierz A");
                    break;
                case "2":
                    System.out.println("Wczytaj macierz B");
                    break;
                case "3":
                    System.out.println("Wyświetl A+B");
                    break;
                case "4":
                    System.out.println("Wyświetl A*B");
                    break;
                case "5":
                    System.out.println("Wyjście");
                    break;
                default:
                    System.out.println("Brak opcji "+option);
                    break;
            }
        }while (!"5".equals(option));

        }
    }