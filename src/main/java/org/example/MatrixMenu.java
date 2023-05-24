package org.example;

import java.util.Scanner;

public class MatrixMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Matrix a = null;
        Matrix b = null;

        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Wczytaj macierz a");
            System.out.println("2. Wczytaj macierz b");
            System.out.println("3. Wyświetl a + b");
            System.out.println("4. Wyświetl a * b");
            System.out.println("5. Wyjście");
            System.out.print("Wybierz opcję: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    a = readMatrix(scanner);
                    break;
                case 2:
                    b = readMatrix(scanner);
                    break;
                case 3:
                    if (a != null && b != null) {
                        try {
                            Matrix sum = Matrix.add(a, b);
                            System.out.println("Suma a + b:");
                            sum.display();
                        } catch (MatrixOperationException e) {
                            System.out.println("Błąd: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Wczytaj najpierw macierze a i b.");
                    }
                    break;
                case 4:
                    if (a != null && b != null) {
                        try {
                            Matrix product = Matrix.multiply(a, b);
                            System.out.println("Iloczyn a * b:");
                            product.display();
                        } catch (MatrixOperationException e) {
                            System.out.println("Błąd: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Wczytaj najpierw macierze a i b.");
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Niepoprawna opcja. Wybierz ponownie.");
                    break;
            }
        }
    }

    private static Matrix readMatrix(Scanner scanner) {
        System.out.print("Wprowadź liczbę wierszy: ");
        int rows = scanner.nextInt();
        System.out.print("Wprowadź liczbę kolumn: ");
        int columns = scanner.nextInt();

        int[][] data = new int[rows][columns];

        System.out.println("Wprowadź elementy macierzy:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Element [" + (i + 1) + "," + (j + 1) + "]: ");
                data[i][j] = scanner.nextInt();
            }
        }

        return new Matrix(data);
    }
}

class MatrixOperationException extends Exception {
    public MatrixOperationException(String message) {
        super(message);
    }
}

class Matrix {
    private int[][] data;
    private int rows;
    private int columns;

    public Matrix(int[][] data) {
        this.data = data;
        this.rows = data.length;
        this.columns = data[0].length;
    }

    public static Matrix add(Matrix a, Matrix b) throws MatrixOperationException {
        if (a.rows != b.rows || a.columns != b.columns) {
            throw new MatrixOperationException("Niepoprawne rozmiary macierzy!");
        }

        int[][] resultData = new int[a.rows][a.columns];

        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.columns; j++) {
                resultData[i][j] = a.data[i][j] + b.data[i][j];
            }
        }

        return new Matrix(resultData);
    }

    public static Matrix multiply(Matrix a, Matrix b) throws MatrixOperationException {
        if (a.columns != b.rows) {
            throw new MatrixOperationException("Niepoprawne rozmiary macierzy!");
        }

        int[][] resultData = new int[a.rows][b.columns];

        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < b.columns; j++) {
                int sum = 0;
                for (int k = 0; k < a.columns; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                resultData[i][j] = sum;
            }
        }

        return new Matrix(resultData);
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
}
