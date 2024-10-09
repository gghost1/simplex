package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simplex {

    private final float[][] table;
    private final int argsCount;
    private final HashMap<Integer, Integer> answer;

    public Simplex(List<Float> C, List<List<Float>> A, List<Float> b) {
        argsCount = C.size();
        table = Table.buildTable(C, A, b);

        answer = new HashMap<>();
        for (int i = 0; i < C.size(); i++) {
            answer.put(i, -1);
        }
    }

    public List<Float> apply() {

        int pivotCol = findPivotCol();
        while (pivotCol != -1) {
            int pivotRow = findPivotRow(pivotCol);
            // exception if pivotRow == -1

            final float pivotArg = table[pivotRow][pivotCol];
            for (int i = 0; i < table[0].length; i++) {
                table[pivotRow][i] /= pivotArg;
            }

            for (int i = 0; i < table.length; i++) {
                float scale = table[i][pivotCol];
                if (i == pivotRow) continue;
                for (int j = 0; j < table[0].length; j++) {
                    float arg = table[i][j];
                    table[i][j] = arg - scale * table[pivotRow][j];
                }
            }
            if (answer.containsValue(pivotRow)) {
                for (Map.Entry<Integer, Integer> entry : answer.entrySet()) {
                    if (entry.getValue() == pivotRow) {
                        answer.put(entry.getKey(), -1);
                    }
                }
            }

            answer.put(pivotCol, pivotRow);
            pivotCol = findPivotCol();
        }

        List<Float> solution = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : answer.entrySet()) {
            if (entry.getValue() == -1) {
                solution.add(0f);
            } else {
                solution.add(table[entry.getValue()][table[0].length-1]);
            }
        }


        return solution;
    }

    private int findPivotCol() {
        float[] firstRow = table[0];
        for (int i = 0; i < argsCount; i++) {
            if (firstRow[i] < 0) {
                return i;
            }
        }
        return -1;
    }

    private int findPivotRow(int pivotCol) {
        float min = Float.MAX_VALUE;
        int pivotRow = -1;
        for (int i = 1; i < table.length; i++) {
            if (table[i][pivotCol] > 0) {
                float ratio = table[i][table[0].length-1] / table[i][pivotCol];
                if (ratio < min) {
                    min = ratio;
                    pivotRow = i;
                }
            }
        }
        return pivotRow;
    }

}
