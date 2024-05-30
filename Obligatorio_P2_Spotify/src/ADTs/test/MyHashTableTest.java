package ADTs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ADTs.src.uy.edu.um.prog2.adt.HashTable.MyHashTableImpl;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementAlreadyInHash;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementNotFound;

public class MyHashTableTest<K,V> {
    private MyHashTableImpl<String, String> hashTest;
    private String elementTest1;
    private String elementTest2;
    private String elementTest3;
    private String elementTest4;
    private String elementTest5;
    private String elementTest6;
    private String elementTest7;

    @Before
    public void setUp() {
        hashTest = new MyHashTableImpl<>(5);
        elementTest1 = "elementTest1";
        elementTest2 = "elementTest2";
        elementTest3 = "elementTest3";
        elementTest4 = "elementTest4";
        elementTest5 = "elementTest5";
        elementTest6 = "elementTest6";
        elementTest7 = "elementTest7";
    }

    @Test
    public void testPutAndContainsFunction() {
        try {
            hashTest.put(elementTest1, elementTest1);
            Assert.assertEquals(-1,hashTest.contains(elementTest2));
            Assert.assertEquals(-1,hashTest.contains(elementTest3));
            int value = hashTest.contains(elementTest1);
            Assert.assertTrue(value > 0);
        } catch (ElementAlreadyInHash ignore) {
            Assert.fail("No se esperaban excepciones");
        }
    }

    @Test
    public void testPutFunctioElementAlreadyInHash() {
        Assert.assertThrows(ElementAlreadyInHash.class, () -> {
            hashTest.put(elementTest1, elementTest1);
            hashTest.put(elementTest1, elementTest1);
        });
    }

    @Test
    public void testRemoveFunction() {
        try {
            hashTest.put(elementTest1, elementTest1);
            hashTest.put(elementTest2, elementTest2);
            hashTest.put(elementTest3, elementTest3);
            hashTest.put(elementTest4, elementTest4);
            hashTest.put(elementTest5, elementTest5);
            hashTest.put(elementTest6, elementTest6);
            Assert.assertTrue(hashTest.contains(elementTest1) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest2) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest3) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest4) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest5) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest6) >= 0);
            Assert.assertEquals(-1, hashTest.contains(elementTest7));
            Assert.assertEquals(10, hashTest.getSizeArray());
            hashTest.put(elementTest7, elementTest7);
            Assert.assertTrue(hashTest.contains(elementTest1) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest2) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest3) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest4) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest5) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest6) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest7) >= 0);
            hashTest.remove(elementTest1);
            Assert.assertEquals(-1, hashTest.contains(elementTest1));
            Assert.assertTrue(hashTest.contains(elementTest2) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest3) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest4) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest5) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest6) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest7) >= 0);
            hashTest.remove(elementTest2);
            hashTest.remove(elementTest3);
            Assert.assertEquals(-1, hashTest.contains(elementTest1));
            Assert.assertEquals(-1, hashTest.contains(elementTest2));
            Assert.assertEquals(-1, hashTest.contains(elementTest3));
            Assert.assertTrue(hashTest.contains(elementTest4) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest5) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest6) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest7) >= 0);
            hashTest.remove(elementTest7);
            hashTest.remove(elementTest6);
            Assert.assertEquals(-1, hashTest.contains(elementTest1));
            Assert.assertEquals(-1, hashTest.contains(elementTest2));
            Assert.assertEquals(-1, hashTest.contains(elementTest3));
            Assert.assertTrue(hashTest.contains(elementTest4) >= 0);
            Assert.assertTrue(hashTest.contains(elementTest5) >= 0);
            Assert.assertEquals(-1, hashTest.contains(elementTest6));
            Assert.assertEquals(-1, hashTest.contains(elementTest7));
        } catch (ElementAlreadyInHash | ElementNotFound ignore) {
            Assert.fail("No se esperaban excepciones");
        }
    }

    @Test
    public void testRemoveFunctionElementNotFound() {
        Assert.assertThrows(ElementNotFound.class, () -> {
            hashTest.put(elementTest1, elementTest1);
            hashTest.remove(elementTest6);
        });
    }

    @Test
    public void testGetFunction() {
        try {
            hashTest.put(elementTest1, elementTest1);
            hashTest.put(elementTest2, elementTest2);
            hashTest.put(elementTest3, elementTest3);
            Assert.assertEquals(elementTest1, hashTest.get(elementTest1));
            Assert.assertEquals(elementTest2, hashTest.get(elementTest2));
            Assert.assertEquals(elementTest3, hashTest.get(elementTest3));
        } catch (ElementAlreadyInHash | ElementNotFound ignore) {
            Assert.fail("No se esperaban excepciones");
        }
    }

    @Test
    public void testGetFunctionElementNotFound() {
        try {
            hashTest.put(elementTest1, elementTest1);
            hashTest.put(elementTest2, elementTest2);
            hashTest.put(elementTest3, elementTest3);
            Assert.assertEquals(elementTest1, hashTest.get(elementTest1));
            Assert.assertEquals(elementTest2, hashTest.get(elementTest2));
            hashTest.remove(elementTest1);
            Assert.assertThrows(ElementNotFound.class, () -> {
                hashTest.get(elementTest1);
            });
        } catch (ElementAlreadyInHash | ElementNotFound ignore) {
            Assert.fail("No se esperaban excepciones");
        }
    }





}

