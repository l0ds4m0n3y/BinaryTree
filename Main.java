public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> myTree = new BinarySearchTree<>();
        int[] array = {25, 15, 50, 10, 22, 4, 12, 18, 24, 35, 31, 44, 70, 66, 90};
        for(int s : array){
            myTree.add(s);
        }
        // myTree.add(10);
        // myTree.add(5);
        // myTree.add(13);
        // myTree.add(3);
        // myTree.add(7);
        // myTree.add(11);
        // myTree.add(15);
        // myTree.add(4);

        // System.out.println(myTree.inorder());
        // System.out.println(myTree.preorder()); 
        // System.out.println(myTree.postorder());
        System.out.println(myTree.inorder());
    }    
}
