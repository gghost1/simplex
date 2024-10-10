package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Simplex {

    private final float[][] table;
    private final int argsCount;
    private final HashMap<Integer, Integer> answerScales;

    private float answer;

    public Simplex(List<Float> C, List<List<Float>> A, List<Float> b) {
        argsCount = C.size();
        table = Table.buildTable(C, A, b);

        answerScales = new HashMap<>();
        for (int i = 0; i < C.size(); i++) {
            answerScales.put(i, -1);
        }
    }

    public List<Float> apply(int e) {

        int pivotCol = findPivotCol();
        while (pivotCol != -1) {
            int pivotRow = findPivotRow(pivotCol);
            if (pivotRow == -1) {
                throw new RuntimeException("The problem is unbounded");
            }

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
            if (answerScales.containsValue(pivotRow)) {
                for (Map.Entry<Integer, Integer> entry : answerScales.entrySet()) {
                    if (entry.getValue() == pivotRow) {
                        answerScales.put(entry.getKey(), -1);
                    }
                }
            }

            answerScales.put(pivotCol, pivotRow);
            pivotCol = findPivotCol();
        }

        List<Float> solution = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : answerScales.entrySet()) {
            if (entry.getValue() == -1) {
                solution.add(0f);
            } else {
                float arg = table[entry.getValue()][table[0].length-1];

                solution.add(BigDecimal.valueOf(arg).setScale(e, RoundingMode.HALF_UP).floatValue());
            }
        }

        answer = BigDecimal.valueOf(table[0][table[0].length - 1]).setScale(e, RoundingMode.HALF_UP).floatValue();
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

    public float getAnswer() {
        if (answer != 0) {
            return answer;
        }
        return Float.NaN;
    }
}
