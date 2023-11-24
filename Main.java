public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> myTree = new BinarySearchTree<>();
        int[] array = {1,3,2,5,4,7,6};
        for(int s : array){
                myTree.add(s);
            }
            
        // BinarySearchTree<String> myTree = new BinarySearchTree<>();
        // String[] array2 = {"Apples", "Bananas", "Sex"};
        // for(String s : array2){
        //     myTree.add(s);
        // }
        // myTree.add(10);
        // myTree.add(5);
        // myTree.add(13);
        // myTree.add(3);
        // myTree.add(7);
        // myTree.add(11);
        // myTree.add(15);
        // myTree.add(4);

        //System.out.println(myTree.inorder());
        // System.out.println(myTree.preorder()); 
        // System.out.println(myTree.postorder());
        System.out.println(myTree.toString());
        System.out.println(myTree.height());
        myTree.balance();
        System.out.println(myTree.toString());
        System.out.println(myTree.height());


    }    
}
