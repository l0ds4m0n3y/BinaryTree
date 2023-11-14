import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.*;

public class TreeJUnit {
    static BinarySearchTree<Integer> myTree;
    
    @BeforeAll
    public static void setUp(){
        myTree = new BinarySearchTree<>();
    }
    
    @Test
    void testEmptySize(){
        assertEquals(0, myTree.size());
    }

    @Test
    void testSize(){
        myTree.add(1);
        myTree.add(7);
        myTree.add(8);
        myTree.add(0);
        myTree.add(9999);
        assertEquals(5, myTree.size());
    }

    @Test
    void testNumInterior(){
        myTree.add(10);
        myTree.add(5);
        myTree.add(13);
        myTree.add(3);
        myTree.add(7);
        myTree.add(11);
        myTree.add(15);
        assertEquals(4, myTree.leaves());

        myTree.add(4);
        assertEquals(4, myTree.leaves());

        myTree.add(2);
        assertEquals(5, myTree.leaves());
    }

    @Test
    void testInterior(){
        myTree.add(10);
        myTree.add(5);
        myTree.add(13);
        myTree.add(3);
        myTree.add(7);
        myTree.add(11);
        myTree.add(15);
        assertEquals(3, myTree.interiorNodes());

        myTree.add(4);
        // assertEquals(4, myTree.interiorNodes());

        myTree.add(2);
        assertEquals(4, myTree.interiorNodes());
    }
}
