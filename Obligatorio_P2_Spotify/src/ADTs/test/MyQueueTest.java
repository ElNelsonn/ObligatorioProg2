package ADTs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ADTs.src.uy.edu.um.prog2.adt.Queue.MyQueueImpl;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.EmptyQueueException;

public class MyQueueTest {
    private MyQueueImpl<String> queueTest;
    private String elementTest1;
    private String elementTest2;
    private String elementTest3;
    private String elementTest4;

    @Before
    public void setUp() {
        queueTest = new MyQueueImpl<>();
        elementTest1 = "First Element";
        elementTest2 = "Second Element";
        elementTest3 = "Third Element";
        elementTest4 = "Fourth Element";
    }

    @Test
    public void testEnqueueFunction() {
        queueTest.enqueue(elementTest1);
        Assert.assertEquals(elementTest1, queueTest.getFirst().getValue());
        queueTest.enqueue(elementTest2);
        Assert.assertEquals(elementTest1, queueTest.getFirst().getValue());
        Assert.assertEquals(elementTest2, queueTest.getLast().getValue());
        queueTest.enqueue(elementTest3);
        Assert.assertEquals(elementTest1, queueTest.getFirst().getValue());
        Assert.assertEquals(elementTest2, queueTest.getFirst().getNextNode().getValue());
        Assert.assertEquals(elementTest3, queueTest.getLast().getValue());
        queueTest.enqueue(elementTest4);
        Assert.assertEquals(elementTest1, queueTest.getFirst().getValue());
        Assert.assertEquals(elementTest2, queueTest.getFirst().getNextNode().getValue());
        Assert.assertEquals(elementTest3, queueTest.getFirst().getNextNode().getNextNode().getValue());
        Assert.assertEquals(elementTest4, queueTest.getLast().getValue());
    }

    @Test
    public void testDequeueFunction() {
        try {
            queueTest.enqueue(elementTest1);
            Assert.assertEquals(elementTest1, queueTest.dequeue());
            queueTest.enqueue(elementTest1);
            queueTest.enqueue(elementTest2);
            Assert.assertEquals(elementTest1, queueTest.dequeue());
            Assert.assertEquals(elementTest2, queueTest.dequeue());
            queueTest.enqueue(elementTest1);
            queueTest.enqueue(elementTest2);
            queueTest.enqueue(elementTest3);
            Assert.assertEquals(elementTest1, queueTest.dequeue());
            Assert.assertEquals(elementTest2, queueTest.dequeue());
            Assert.assertEquals(elementTest3, queueTest.dequeue());
            queueTest.enqueue(elementTest1);
            queueTest.enqueue(elementTest2);
            Assert.assertEquals(elementTest1, queueTest.dequeue());
            queueTest.enqueue(elementTest3);
            Assert.assertEquals(elementTest2, queueTest.dequeue());
            Assert.assertEquals(elementTest3, queueTest.dequeue());
        } catch (EmptyQueueException ignore) {
            Assert.fail("No se esperaba ninguna excepcion");
        }

    }

    @Test
    public void testDequeueFunctionEmptyQueueException(){
        Assert.assertThrows(EmptyQueueException.class, () -> {
           queueTest.dequeue();
        });
        try {
            queueTest.enqueue(elementTest1);
            queueTest.enqueue(elementTest2);
            queueTest.dequeue();
            queueTest.dequeue();
            Assert.assertThrows(EmptyQueueException.class, () -> {
                queueTest.dequeue();
            });
        } catch (EmptyQueueException ignore) {
            Assert.fail("No debio entrar aca");
        }
    }

    @Test
    public void testFirstValueFunction() {
        try {
            queueTest.enqueue(elementTest1);
            Assert.assertEquals(elementTest1, queueTest.firstValue());
            queueTest.enqueue(elementTest2);
            Assert.assertEquals(elementTest1, queueTest.firstValue());
            queueTest.dequeue();
            Assert.assertEquals(elementTest2, queueTest.firstValue());
            queueTest.enqueue(elementTest3);
            queueTest.enqueue(elementTest4);
            Assert.assertEquals(elementTest2, queueTest.firstValue());
            queueTest.dequeue();
            queueTest.dequeue();
            Assert.assertEquals(elementTest4, queueTest.firstValue());
        } catch (EmptyQueueException ignore) {
            Assert.fail("No se esperaba ninguna excepcion");
        }
    }

    @Test
    public void testFirstValueFunctionEmptyQueueException() {
        Assert.assertThrows(EmptyQueueException.class, () -> {
            queueTest.firstValue();
        });
        try {
            queueTest.enqueue(elementTest1);
            queueTest.enqueue(elementTest2);
            queueTest.dequeue();
            queueTest.dequeue();
            Assert.assertThrows(EmptyQueueException.class, () -> {
                queueTest.firstValue();
            });
        } catch (EmptyQueueException ignore) {
            Assert.fail("No debio entrar aca");
        }
    }

    @Test
    public void testContainsFunction() {
        Assert.assertFalse(queueTest.contains(elementTest1));
        queueTest.enqueue(elementTest1);
        Assert.assertTrue(queueTest.contains(elementTest1));
        queueTest.enqueue(elementTest2);
        queueTest.enqueue(elementTest3);
        Assert.assertTrue(queueTest.contains(elementTest2));
        Assert.assertTrue(queueTest.contains(elementTest3));
        String elementTestAux = "Aux";
        Assert.assertFalse(queueTest.contains(elementTestAux));
        queueTest.enqueue(elementTestAux);
        Assert.assertTrue(queueTest.contains(elementTestAux));
    }

    @Test
    public void testSize() {
        try {
            Assert.assertEquals(0, queueTest.size());
            queueTest.enqueue(elementTest1);
            Assert.assertEquals(1, queueTest.size());
            queueTest.dequeue();
            Assert.assertEquals(0, queueTest.size());
            queueTest.enqueue(elementTest1);
            queueTest.enqueue(elementTest2);
            queueTest.enqueue(elementTest3);
            Assert.assertEquals(3, queueTest.size());
            queueTest.dequeue();
            Assert.assertEquals(2, queueTest.size());
            queueTest.dequeue();
            Assert.assertEquals(1, queueTest.size());
            queueTest.dequeue();
            Assert.assertEquals(0, queueTest.size());
            queueTest.enqueue(elementTest3);
            queueTest.enqueue(elementTest3);
            queueTest.enqueue(elementTest3);
            queueTest.enqueue(elementTest3);
            queueTest.enqueue(elementTest3);
            queueTest.enqueue(elementTest3);
            queueTest.enqueue(elementTest3);
            queueTest.enqueue(elementTest3);
            Assert.assertEquals(8, queueTest.size());
        } catch (EmptyQueueException ignore){
            Assert.fail("No se esperaba ninguna excepcion");
        }
    }
}
