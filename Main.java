public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> myTree = new BinarySearchTree<>();
        int[] array = {25, 15, 50, 10, 22, 4, 12, 18, 24, 35, 31, 44, 70, 66, 90};
        for(int s : array){
            myTree.add(s);
        }
        //TODO preorder and post order needs work 
        System.out.println(myTree.postorder());
    }    
}
