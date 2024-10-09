package org.example;

import java.util.List;

public class Table {

    public static float[][] buildTable(List<Float> C, List<List<Float>> A, List<Float> b) {
        int tableWidth = C.size() + A.size() + 1;
        float[][] table = new float[b.size()+1][tableWidth];
        table[0] = buildRow(C, 0, (float) 0, tableWidth);

        for (int i = 1; i <= A.size(); i++) {
            table[i] = buildRow(A.get(i-1), i, b.get(i-1), tableWidth);
        }
        for (int i = 0; i < C.size(); i++) {
            table[0][i] *= -1;
        }
        return table;
    }

    private static float[] buildRow(List<Float> args, int rowNumber, float solution, int tableWidth) {
        float[] row = new float[tableWidth];
        for (int i = 0; i < tableWidth; i++) {
            if (i < args.size()) {
                row[i] = args.get(i);
            } else if ((i == args.size() + rowNumber - 1) && rowNumber != 0) {
                row[i] = ((float) 1);
            } else if (i == tableWidth - 1) {
                row[i] = (solution);
            } else {
                row[i] = ((float) 0);
            }
        }
        return row;
    }
}
