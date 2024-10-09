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
        List<Float> answer = simplex.apply();
        assertEquals(answer, Arrays.asList(0f, 4.5f, 0f));
    }
}
