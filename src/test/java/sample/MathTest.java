package sample;

import org.junit.Assert;
import org.junit.Test;
import sun.rmi.runtime.Log;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MathTest {
    @Test
    public void testEquals() {
        assertEquals(4, 2 + 2);
        assertTrue(4 == 2 + 2);

    }

    @Test
    public void testNotEquals() {
        assertFalse(5 == 2 + 2);
    }
}