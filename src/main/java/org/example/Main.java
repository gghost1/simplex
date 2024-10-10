package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Program applicable only for maximization problems with bounds containing only <=");

        System.out.print("Enter the number of variables (coefficients of the objective function): ");
        int numVariables = scanner.nextInt();
        List<Float> C = new ArrayList<>();

        System.out.println("Enter the coefficients of the objective function (C): ");
        for (int i = 0; i < numVariables; i++) {
            System.out.print("C[" + i + "]: ");
            C.add(scanner.nextFloat());
        }

        System.out.print("Enter the number of restrictive functions: ");
        int numConstraints = scanner.nextInt();
        List<List<Float>> A = new ArrayList<>();
        List<Float> b = new ArrayList<>();

        System.out.println("Enter the coefficients of the restrictive functions (A):");
        for (int i = 0; i < numConstraints; i++) {
            System.out.println("Restrictive function " + (i + 1) + ":");
            List<Float> rowA = new ArrayList<>();
            for (int j = 0; j < numVariables; j++) {
                System.out.print("A[" + i + "][" + j + "]: ");
                rowA.add(scanner.nextFloat());
            }
            A.add(rowA);
        }

        System.out.println("Enter the values on the right side of the constraints (b): ");
        for (int i = 0; i < numConstraints; i++) {
            System.out.print("b[" + i + "]: ");
            b.add(scanner.nextFloat());
        }


        System.out.print("Enter the rounding precision: ");
        int precision = scanner.nextInt();

        scanner.close();
        Simplex simplex = new Simplex(C, A, b);
        List<Float> answer = simplex.apply(precision);
        System.out.println(answer);
        System.out.println(simplex.getAnswer());
    }
}
