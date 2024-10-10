import org.example.Simplex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimplexTest {

    @Test
    public void testSimplex() {
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
        List<Float> answer = simplex.apply(1);
        assertEquals(answer, Arrays.asList(0f, 4.5f, 0f));
    }

    @Test
    public void test2Simplex() {
        List<Float> C = new ArrayList<>(){{
            add(9f);
            add(10f);
            add(16f);
        }};
        List<List<Float>> A = new ArrayList<>(){{
            add(Arrays.asList(18f, 15f, 12f));
            add(Arrays.asList(6f, 4f, 8f));
            add(Arrays.asList(5f, 3f, 3f));
        }};
        List<Float> b = new ArrayList<>(){{
            add(360f);
            add(192f);
            add(180f);
        }};
        Simplex simplex = new Simplex(C, A, b);
        List<Float> answer = simplex.apply(0);
        assertEquals(answer, Arrays.asList(0f, 8f, 20f));
    }

    @Test
    public void test3Simplex() {
        List<Float> C = new ArrayList<>(){
            {
                add(-7f);
                add(2f);
            }
        };
        List<List<Float>> A = new ArrayList<>(){
            {
                add(Arrays.asList(2f, 5f));
                add(Arrays.asList(-3f, 2f));
                add(Arrays.asList(1f, -7f));
            }
        };
        List<Float> b = new ArrayList<>(){
            {
                add(22f);
                add(15f);
                add(30f);
            }
        };
        Simplex simplex = new Simplex(C, A, b);
        List<Float> answer = simplex.apply(1);
        System.out.println(simplex.getAnswer());
        assertEquals(answer, Arrays.asList(0f, 4.4f));
    }

    @Test
    public void test4Simplex() {
        List<Float> C = new ArrayList<>(){
            {
                add(2f);
                add(4f);
                add(-6f);
                add(3f);
                add(-10f);
                add(1f);
            }
        };
        List<List<Float>> A = new ArrayList<>(){
            {
                add(Arrays.asList(0f, 1f, 3f, 0f, 0f, 0f));
                add(Arrays.asList(3f, 2f, 0f, -1f, 0f, 8f));
                add(Arrays.asList(0f, 0f, 0f, 3f, 2f, 1f));
                add(Arrays.asList(0f, 0f, 0f, 1f, 3f, -6f));
            }
        };
        List<Float> b = new ArrayList<>(){
            {
                add(46f);
                add(22f);
                add(20f);
                add(10f);
            }
        };
        Simplex simplex = new Simplex(C, A, b);
        List<Float> answer = simplex.apply(2);
        System.out.println(simplex.getAnswer());
        assertEquals(answer, Arrays.asList(0f, 14.33f, 0f, 6.67f, 0f, 0f));
    }

}
