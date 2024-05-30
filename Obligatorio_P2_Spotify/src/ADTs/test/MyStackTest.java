package ADTs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ADTs.src.uy.edu.um.prog2.adt.Stack.MyStackImpl;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.EmptyStackException;


public class MyStackTest {
        private MyStackImpl<String> stackTest;
        private String elementTest1;
        private String elementTest2;
        private String elementTest3;

    @Before
    public void setUp() {
        stackTest = new MyStackImpl<>();
        elementTest1 = "First Element";
        elementTest2 = "Second Element";
        elementTest3 = "Third Element";
    }

    @Test
    public void testPushFunction() {
        stackTest.push(elementTest3);
        stackTest.push(elementTest2);
        stackTest.push(elementTest1);
        Assert.assertEquals(elementTest1, stackTest.getFirst().getValue());
        Assert.assertEquals(elementTest2, stackTest.getFirst().getNextNode().getValue());
        Assert.assertEquals(elementTest3, stackTest.getFirst().getNextNode().getNextNode().getValue());
    }

    @Test
    public void testSize() {
        try {
            Assert.assertEquals(0, stackTest.size());
            stackTest.push(elementTest1);
            Assert.assertEquals(1, stackTest.size());
            stackTest.pop();
            Assert.assertEquals(0, stackTest.size());
            stackTest.push(elementTest1);
            stackTest.push(elementTest2);
            stackTest.push(elementTest3);
            Assert.assertEquals(3, stackTest.size());
            stackTest.pop();
            Assert.assertEquals(2, stackTest.size());
            stackTest.pop();
            Assert.assertEquals(1, stackTest.size());
            stackTest.pop();
            Assert.assertEquals(0, stackTest.size());
            stackTest.push(elementTest3);
            stackTest.push(elementTest3);
            stackTest.push(elementTest3);
            stackTest.push(elementTest3);
            stackTest.push(elementTest3);
            stackTest.push(elementTest3);
            stackTest.push(elementTest3);
            stackTest.push(elementTest3);
            Assert.assertEquals(8, stackTest.size());
        } catch (EmptyStackException ignored) {
            Assert.fail("No se esperaba ninguna excepcion");
        }
    }

    @Test
    public void testPeekFunction() {
        stackTest.push(elementTest1);
        Assert.assertEquals(elementTest1, stackTest.getFirst().getValue());
        stackTest.push(elementTest2);
        Assert.assertEquals(elementTest2, stackTest.getFirst().getValue());
        stackTest.push(elementTest3);
        Assert.assertEquals(elementTest3, stackTest.getFirst().getValue());
    }

    @Test
    public void testPeekFunctionEmptyStackException() {
        Assert.assertThrows(EmptyStackException.class, () -> {
            stackTest.peek();
        });
        try {
            stackTest.push(elementTest1);
            Assert.assertEquals(elementTest1, stackTest.peek());
            stackTest.push(elementTest2);
            Assert.assertEquals(elementTest2, stackTest.peek());
            stackTest.pop();
            stackTest.pop();
            Assert.assertThrows(EmptyStackException.class, () -> {
                stackTest.peek();
            });
        } catch (EmptyStackException ignored) {
            Assert.fail("No debio entrar aca");
        }
    }

    @Test
    public void testPopFunctionEmptyStackException(){
            Assert.assertThrows(EmptyStackException.class, () -> {
                stackTest.pop();
            });
        try {
            stackTest.push(elementTest1);
            stackTest.push(elementTest2);
            stackTest.push(elementTest3);
            stackTest.pop();
            stackTest.pop();
            stackTest.pop();
            Assert.assertThrows(EmptyStackException.class, () -> {
                stackTest.pop();
            });
        } catch (EmptyStackException ignored) {
            Assert.fail("No debio entrar aca");
        }
    }

    @Test
    public void testPopFunction(){
        try {
            stackTest.push(elementTest1);
            Assert.assertEquals(elementTest1, stackTest.pop());
            Assert.assertEquals(0, stackTest.size());
            stackTest.push(elementTest2);
            stackTest.push(elementTest3);
            Assert.assertEquals(elementTest3, stackTest.pop());
            Assert.assertEquals(elementTest2, stackTest.pop());
            Assert.assertEquals(0, stackTest.size());
            stackTest.push(elementTest1);
            stackTest.push(elementTest3);
            Assert.assertEquals(elementTest3, stackTest.pop());
            Assert.assertEquals(elementTest1, stackTest.pop());
        } catch (EmptyStackException ignored) {
            Assert.fail("No se esperaba ninguna excepcion");
        }
    }

    @Test
    public void testContainsFunction() {
        Assert.assertFalse(stackTest.contains(elementTest1));
        stackTest.push(elementTest1);
        Assert.assertTrue(stackTest.contains(elementTest1));
        stackTest.push(elementTest2);
        stackTest.push(elementTest3);
        Assert.assertTrue(stackTest.contains(elementTest2));
        Assert.assertTrue(stackTest.contains(elementTest3));
        String elementTestAux = "Aux";
        Assert.assertFalse(stackTest.contains(elementTestAux));
        stackTest.push(elementTestAux);
        Assert.assertTrue(stackTest.contains(elementTestAux));
    }
}
