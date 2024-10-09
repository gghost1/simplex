package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Float> C = new ArrayList<>(){{
            add(-4f);
            add(1f);
            add(-2f);
        }};
        List<List<Float>> A = new ArrayList<>(){{
            add(Arrays.asList(1f, 1f, -1f));
            add(Arrays.asList(2f, -2f, 1f));
            add(Arrays.asList(6f, 10f, 0f));
        }};
        List<Float> b = new ArrayList<>(){{
            add(10f);
            add(30f);
            add(45f);
        }};
        Simplex simplex = new Simplex(C, A, b);
        List<Float> answer = simplex.apply();
        System.out.println(answer);

    }
}
