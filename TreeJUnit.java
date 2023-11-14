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
}
