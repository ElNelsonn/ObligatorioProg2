package ADTs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ADTs.src.uy.edu.um.prog2.adt.Tree.MyTree;
import ADTs.src.uy.edu.um.prog2.adt.Tree.MyTreeImpl;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.*;

public class MyTreeTest {
    private MyTreeImpl<Integer, Integer> treeTest;
    private Integer elementTest1;
    private Integer elementTest2;
    private Integer elementTest3;
    private Integer elementTest4;
    private Integer elementTest5;
    private Integer elementTest6;
    private Integer elementTest7;

    @Before
    public void setUp() {
        treeTest = new MyTreeImpl<Integer, Integer>();
        elementTest1 = 1;
        elementTest2 = 2;
        elementTest3 = 3;
        elementTest4 = 4;
        elementTest5 = 5;
        elementTest6 = 6;
        elementTest7 = 7;
        ;
    }

    //    4
    //   / \
    //  2   6
    // / \ / \
    //1  3 5  7
    @Test
    public void testInsertFunction() {
        treeTest.insert(elementTest4,elementTest4);
        treeTest.insert(elementTest2,elementTest2);
        treeTest.insert(elementTest6,elementTest6);

        Assert.assertEquals(elementTest4, treeTest.getRoot().getValue());
        Assert.assertEquals(elementTest2, treeTest.getRoot().getLeft().getValue());
        Assert.assertEquals(elementTest6, treeTest.getRoot().getRight().getValue());

        treeTest.insert(elementTest7,elementTest7);
        Assert.assertEquals(elementTest7, treeTest.getRoot().getRight().getRight().getValue());
        treeTest.insert(elementTest1,elementTest1);
        Assert.assertEquals(elementTest1, treeTest.getRoot().getLeft().getLeft().getValue());
        treeTest.insert(elementTest3,elementTest3);
        treeTest.insert(elementTest5,elementTest5);
        Assert.assertEquals(elementTest3, treeTest.getRoot().getLeft().getRight().getValue());
        Assert.assertEquals(elementTest5, treeTest.getRoot().getRight().getLeft().getValue());

    }

    @Test
    public void testFindSort(){
        treeTest.insert(elementTest4,elementTest4);
        treeTest.insert(elementTest2,elementTest2);
        treeTest.insert(elementTest6,elementTest6);

        Assert.assertEquals(elementTest4, treeTest.findSort(elementTest4));
        Assert.assertEquals(null, treeTest.findSort(9));
        Assert.assertEquals(null, treeTest.findSort(5));
        Assert.assertEquals(elementTest2, treeTest.findSort(elementTest2));

        Assert.assertEquals(elementTest2, treeTest.findSort(elementTest2));

        treeTest.insert(elementTest7,elementTest7);
        treeTest.insert(elementTest1,elementTest1);
        treeTest.insert(elementTest5,elementTest5);
        Assert.assertEquals(elementTest5, treeTest.findSort(elementTest5));
        Assert.assertEquals(elementTest7, treeTest.findSort(elementTest7));
        Assert.assertEquals(null, treeTest.findSort(10));
        Assert.assertEquals(elementTest1, treeTest.findSort(elementTest1));
        Assert.assertEquals(null, treeTest.findSort(0));
    }

    @Test
    public void testCountLeaf(){
        Assert.assertEquals(0, treeTest.countLeaf());
        treeTest.insert(elementTest4,elementTest4);
        treeTest.insert(elementTest2,elementTest2);
        Assert.assertEquals(1, treeTest.countLeaf());
        treeTest.insert(elementTest6,elementTest6);
        Assert.assertEquals(2, treeTest.countLeaf());
        treeTest.insert(elementTest1,elementTest1);
        Assert.assertEquals(2, treeTest.countLeaf());
        treeTest.insert(elementTest3,elementTest3);
        Assert.assertEquals(3, treeTest.countLeaf());
        treeTest.insert(elementTest5,elementTest5);
        treeTest.insert(elementTest7,elementTest7);
        Assert.assertEquals(4, treeTest.countLeaf());
        treeTest.insert(8,8);
        Assert.assertEquals(4, treeTest.countLeaf());
    }
    @Test
    public void deleteLeafTest(){
        // Testeamos caso que sea una hoja a borrar
        Assert.assertThrows(EmptyTreeException.class, () -> {
            treeTest.delete(1);
        });
        try{
            treeTest.insert(elementTest5,elementTest5);
            treeTest.delete(5);
            Assert.assertEquals(null, treeTest.getRoot());
            treeTest.insert(elementTest4,elementTest4);
            treeTest.insert(elementTest7,elementTest7);
            treeTest.delete(elementTest7);
            Assert.assertEquals(null, treeTest.findSort(elementTest7));
            Assert.assertEquals(elementTest4, treeTest.getRoot().getValue());
            Assert.assertEquals(null, treeTest.getRoot().getRight());
            Assert.assertEquals(null, treeTest.getRoot().getLeft());

            //    4
            //   / \
            //  2   6
            // / \ / \
            //1  3 5  7
            treeTest.insert(elementTest4,elementTest4);
            treeTest.insert(elementTest2,elementTest2);
            treeTest.insert(elementTest6,elementTest6);
            treeTest.insert(elementTest1,elementTest1);
            treeTest.insert(elementTest3,elementTest3);
            treeTest.insert(elementTest5,elementTest5);
            treeTest.insert(elementTest7,elementTest7);

            treeTest.delete(elementTest7);
            //    4
            //   / \
            //  2   6
            // / \ /
            //1  3 5
            Assert.assertEquals(null, treeTest.findSort(elementTest7));
            Assert.assertEquals(elementTest6, treeTest.getRoot().getRight().getKey());
            Assert.assertEquals(elementTest5, treeTest.getRoot().getRight().getLeft().getKey());
            Assert.assertEquals(null, treeTest.getRoot().getRight().getRight());
            //debo tener 3 hojas
            Assert.assertEquals(3, treeTest.countLeaf());

            treeTest.delete(elementTest3);
            //    4
            //   / \
            //  2   6
            // /   /
            //1    5
            Assert.assertEquals(null, treeTest.findSort(elementTest3));
            Assert.assertEquals(elementTest2, treeTest.getRoot().getLeft().getKey()); // Padre del borrado
            Assert.assertEquals(elementTest1, treeTest.getRoot().getLeft().getLeft().getKey());
            Assert.assertEquals(null, treeTest.getRoot().getLeft().getRight()); //Donde iria el 3 (borrado)
            //debo tener 3 hojas
            Assert.assertEquals(2, treeTest.countLeaf());

            treeTest.delete(elementTest5);
            treeTest.delete(elementTest1);
            treeTest.delete(elementTest2);
            treeTest.delete(elementTest6);
            Assert.assertEquals(null, treeTest.findSort(elementTest5));
            Assert.assertEquals(null, treeTest.findSort(elementTest1));
            Assert.assertEquals(null, treeTest.findSort(elementTest2));
            Assert.assertEquals(null, treeTest.findSort(elementTest6));
            Assert.assertEquals(elementTest4, treeTest.getRoot().getKey());

        } catch (ElementNotFound | EmptyTreeException ignore){
        Assert.fail("No se esperaba ninguna excepcion");
        }
    }

    @Test
    public void deleteWithOneLeafTest(){
        try {
            treeTest.insert(elementTest4, elementTest4);
            treeTest.insert(elementTest2, elementTest2);
            treeTest.delete(elementTest4);
            Assert.assertEquals(null, treeTest.findSort(elementTest4));
            Assert.assertEquals(null, treeTest.getRoot().getLeft());
            Assert.assertEquals(null, treeTest.getRoot().getRight());
            Assert.assertEquals(elementTest2, treeTest.getRoot().getKey());
            //Reinicio el arbol
            treeTest.delete(elementTest2);


            treeTest.insert(elementTest4, elementTest4);
            treeTest.insert(elementTest2, elementTest2);
            treeTest.insert(elementTest6, elementTest6);
            treeTest.insert(elementTest1, elementTest1);
            treeTest.insert(elementTest7, elementTest7);
            //    4
            //   / \
            //  2   6
            // /     \
            //1       7
            treeTest.delete(elementTest6);
            //    4
            //   / \
            //  2   7
            // /
            //1
            Assert.assertEquals(null, treeTest.findSort(elementTest6));
            Assert.assertEquals(null, treeTest.getRoot().getRight().getRight());
            Assert.assertEquals(null, treeTest.getRoot().getRight().getLeft());
            Assert.assertEquals(elementTest7, treeTest.getRoot().getRight().getKey());


            treeTest.insert(elementTest5,elementTest5);
            //    4
            //   / \
            //  2   7
            // /   /
            //1   5
            treeTest.delete(elementTest7);
            Assert.assertEquals(null, treeTest.findSort(elementTest7));
            Assert.assertEquals(null, treeTest.getRoot().getRight().getRight());
            Assert.assertEquals(null, treeTest.getRoot().getRight().getLeft());
            Assert.assertEquals(elementTest5, treeTest.getRoot().getRight().getKey());

            treeTest.delete(elementTest5);
            //    4
            //   /
            //  2
            // /
            //1
            treeTest.delete(elementTest4);
            Assert.assertEquals(null, treeTest.findSort(elementTest4));
            Assert.assertEquals(null, treeTest.getRoot().getRight());
            Assert.assertEquals(elementTest2, treeTest.getRoot().getKey());
            Assert.assertEquals(elementTest1, treeTest.getRoot().getLeft().getKey());

            treeTest.delete(elementTest2);
            treeTest.delete(elementTest1);
            Assert.assertEquals(null, treeTest.getRoot());

        } catch (ElementNotFound | EmptyTreeException ignore){
            Assert.fail("No se esperaba ninguna excepcion");
        }
    }


    @Test
    public void deleteWithTwoLeafTest(){
        try{
            treeTest.insert(elementTest4,elementTest4);
            treeTest.insert(elementTest2,elementTest2);
            treeTest.insert(elementTest6,elementTest6);
            treeTest.insert(elementTest1,elementTest1);
            treeTest.insert(elementTest3,elementTest3);
            treeTest.insert(elementTest5,elementTest5);
            treeTest.insert(elementTest7,elementTest7);
            //    4
            //   / \
            //  2   6
            // / \ / \
            //1  3 5  7

            treeTest.delete(elementTest6);
            //    4
            //   / \
            //  2   7
            // / \ /
            //1  3 5
            Assert.assertEquals(null, treeTest.findSort(elementTest6));
            Assert.assertEquals(elementTest5, treeTest.findSort(elementTest5));
            Assert.assertEquals(elementTest7, treeTest.findSort(elementTest7));
            Assert.assertEquals(elementTest7, treeTest.getRoot().getRight().getKey());
            Assert.assertEquals(elementTest5, treeTest.getRoot().getRight().getLeft().getKey());

            treeTest.delete(elementTest2);
            //    4
            //   / \
            //  3   7
            // /   /
            //1   5

            //treeTest.printInOrder();
            Assert.assertEquals(null, treeTest.findSort(elementTest2));
            Assert.assertEquals(elementTest3, treeTest.findSort(elementTest3));
            Assert.assertEquals(elementTest1, treeTest.findSort(elementTest1));
            Assert.assertEquals(elementTest3, treeTest.getRoot().getLeft().getKey());
            Assert.assertEquals(elementTest1, treeTest.getRoot().getLeft().getLeft().getKey());

            treeTest.delete(elementTest4);
            //    5
            //   / \
            //  3   7
            // /
            //1
            Assert.assertEquals(null, treeTest.findSort(elementTest4));
            Assert.assertEquals(elementTest5, treeTest.findSort(elementTest5));
            Assert.assertEquals(elementTest3, treeTest.findSort(elementTest3));
            Assert.assertEquals(elementTest7, treeTest.findSort(elementTest7));
            Assert.assertEquals(elementTest1, treeTest.findSort(elementTest1));
            Assert.assertEquals(elementTest5, treeTest.getRoot().getKey());
            Assert.assertEquals(elementTest7, treeTest.getRoot().getRight().getKey());
            Assert.assertEquals(elementTest3, treeTest.getRoot().getLeft().getKey());
            Assert.assertEquals(elementTest1, treeTest.getRoot().getLeft().getLeft().getKey());

        } catch (ElementNotFound | EmptyTreeException ignore){
            Assert.fail("No se esperaba ninguna excepcion");
        }
    }

    @Test
    public void deleteExceptions(){
        Assert.assertThrows(EmptyTreeException.class, () -> {
            treeTest.delete(1);
        });
        Assert.assertThrows(EmptyTreeException.class, () -> {
            treeTest.delete(5);
        });
        treeTest.insert(elementTest4,elementTest4);
        treeTest.insert(elementTest2,elementTest2);
        treeTest.insert(elementTest6,elementTest6);
        treeTest.insert(elementTest1,elementTest1);
        Assert.assertThrows(ElementNotFound.class, () -> {
            treeTest.delete(7);
        });
        Assert.assertThrows(ElementNotFound.class, () -> {
            treeTest.delete(10);
        });
        Assert.assertThrows(ElementNotFound.class, () -> {
            treeTest.delete(13);
        });
        }



    @Test
    public void countCompleteElements(){
        treeTest.insert(elementTest4,elementTest4);
        treeTest.insert(elementTest2,elementTest2);
        treeTest.insert(elementTest6,elementTest6);
        treeTest.insert(elementTest1,elementTest1);
        treeTest.insert(elementTest3,elementTest3);
        treeTest.insert(elementTest5,elementTest5);
        treeTest.insert(elementTest7,elementTest7);
        Assert.assertEquals(3, treeTest.countCompleteElements());
        try{
            treeTest.delete(elementTest7);
            Assert.assertEquals(2, treeTest.countCompleteElements());
            treeTest.delete(elementTest1);
            Assert.assertEquals(1, treeTest.countCompleteElements());
        } catch (ElementNotFound | EmptyTreeException ignore){
        Assert.fail("No se esperaba ninguna excepcion");
        }
    }









}
