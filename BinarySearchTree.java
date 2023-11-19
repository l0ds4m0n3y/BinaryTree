import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * A class to represent a binary search tree.
 * 
 * @author Koffman and Wolfgang
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E>
		implements SearchTree<E> {
	
	// Data Fields
	/** Return value from the public add method. */
	protected boolean addReturn;

	/** Return value from the public delete method. */
	protected E deleteReturn;

	// Methods
	/**
	 * Starter method find. pre: The target object must implement the Comparable
	 * interface.
	 * 
	 * @param target
	 *            The Comparable object being sought
	 * @return The object, if found, otherwise null
	 */
	public E find(E target) {
		return find(root, target);
	}

	/**
	 * Recursive find method.
	 * 
	 * @param localRoot
	 *            The local subtreeï¿½s root
	 * @param target
	 *            The object being sought
	 * @return The object, if found, otherwise null
	 */
	private E find(Node<E> localRoot, E target) {
		if (localRoot == null)
			return null;

		// Compare the target with the data field at the root.
		int compResult = target.compareTo(localRoot.data);
		if (compResult == 0)
			return localRoot.data;
		else if (compResult < 0)
			return find(localRoot.left, target);
		else
			return find(localRoot.right, target);
	}

	/**
	 * Starter method add. pre: The object to insert must implement the
	 * Comparable interface.
	 * 
	 * @param item
	 *            The object being inserted
	 * @return true if the object is inserted, false if the object already
	 *         exists in the tree
	 */
	public boolean add(E item) {
		root = add(root, item);
		return addReturn;
	}

	/**
	 * Recursive add method. post: The data field addReturn is set true if the
	 * item is added to the tree, false if the item is already in the tree.
	 * 
	 * @param localRoot
	 *            The local root of the subtree
	 * @param item
	 *            The object to be inserted
	 * @return The new local root that now contains the inserted item
	 */
	private Node<E> add(Node<E> localRoot, E item) {
		if (localRoot == null) {
			// item is not in the tree, insert it.
			addReturn = true;
			return new Node<E>(item);
		} else if (item.compareTo(localRoot.data) == 0) {
			// item is equal to localRoot.data
			addReturn = false;
			return localRoot;
		} else if (item.compareTo(localRoot.data) < 0) {
			// item is less than localRoot.data
			localRoot.left = add(localRoot.left, item);
			return localRoot;
		} else {
			// item is greater than localRoot.data
			localRoot.right = add(localRoot.right, item);
			return localRoot;
		}
	}

	/**
	 * Starter method delete. post: The object is not in the tree.
	 * 
	 * @param target
	 *            The object to be deleted
	 * @return The object deleted from the tree or null if the object was not in
	 *         the tree
	 * @throws ClassCastException
	 *             if target does not implement Comparable
	 */
	public E delete(E target) {
		root = delete(root, target);
		return deleteReturn;
	}

	/**
	 * Recursive delete method. post: The item is not in the tree; deleteReturn
	 * is equal to the deleted item as it was stored in the tree or null if the
	 * item was not found.
	 * 
	 * @param localRoot
	 *            The root of the current subtree
	 * @param item
	 *            The item to be deleted
	 * @return The modified local root that does not contain the item
	 */
	private Node<E> delete(Node<E> localRoot, E item) {
		if (localRoot == null) {
			// item is not in the tree.
			deleteReturn = null;
			return localRoot;
		}

		// Search for item to delete.
		int compResult = item.compareTo(localRoot.data);
		if (compResult < 0) {
			// item is smaller than localRoot.data.
			localRoot.left = delete(localRoot.left, item);
			return localRoot;
		} else if (compResult > 0) {
			// item is larger than localRoot.data.
			localRoot.right = delete(localRoot.right, item);
			return localRoot;
		} else {
			// item is at local root.
			deleteReturn = localRoot.data;
			if (localRoot.left == null) {
				// If there is no left child, return right child
				// which can also be null.
				return localRoot.right;
			} else if (localRoot.right == null) {
				// If there is no right child, return left child.
				return localRoot.left;
			} else {
				// Node being deleted has 2 children, replace the data
				// with inorder predecessor.
				if (localRoot.left.right == null) {
					// The left child has no right child.
					// Replace the data with the data in the
					// left child.
					localRoot.data = localRoot.left.data;
					// Replace the left child with its left child.
					localRoot.left = localRoot.left.left;
					return localRoot;
				} else {
					// Search for the inorder predecessor (ip) and
					// replace deleted node's data with ip.
					localRoot.data = findLargestChild(localRoot.left);
					return localRoot;
				}
			}
		}
	}

	/**
	 * Removes target from tree.
	 * 
	 * @param target
	 *            Item to be removed
	 * @return true if the object was in the tree, false otherwise
	 * @post target is not in the tree
	 * @throws ClassCastException
	 *             if target is not Comparable
	 */
	public boolean remove(E target) {
		return delete(target) != null;
	}

	/**
	 * Determine if an item is in the tree
	 * 
	 * @param target
	 *            Item being sought in tree
	 * @return true If the item is in the tree, false otherwise
	 * @throws ClassCastException
	 *             if target is not Comparable
	 */
	public boolean contains(E target) {
		return find(target) != null;
	}

	/**
	 * Find the node that is the inorder predecessor and replace it with its
	 * left child (if any). post: The inorder predecessor is removed from the
	 * tree.
	 * 
	 * @param parent
	 *            The parent of possible inorder predecessor (ip)
	 * @return The data in the ip
	 */
	private E findLargestChild(Node<E> parent) {
		// If the right child has no right child, it is
		// the inorder predecessor.
		if (parent.right.right == null) {
			E returnValue = parent.right.data;
			parent.right = parent.right.left;
			return returnValue;
		} else {
			return findLargestChild(parent.right);
		}
	}

	/**
	 * Clears the contents of this BinarySearchTree, leaving the 
	 * tree empty.
	 */	
	public void clear() {
		root = null;
	}
	
	// ************************ Unimplemented methods*************************/
	/**
	 * Returns the number of nodes in this BinarySearchTree object.
	 * 
	 * @return the size of this BinarySearchTree object.
	 * 
	 */
	public int size() {
		return size(root);
	}

	private int size(Node<E> root){
		if(root == null){
			return 0;
		} else{
			return 1 + size(root.left) + size(root.right);
		}
	}


	/**
	 * Returns the number of interior nodes in the tree.
	 * An interior node is a node that is not a leaf.
	 * 
	 * @return The number of interior nodes in the tree
	 */
	public int interiorNodes() {
		return interiorNodes(root);
	}

	private int interiorNodes(Node<E> root){
		if(root == null || (root.left == null && root.right == null)){
			return 0;
		}
		else{
			return 1 + interiorNodes(root.left) + interiorNodes(root.right);
		}
	}


	/**
	 * Returns the number of leaves in the tree
	 * 
	 * @return the number of leaves in the tree
	 */
	public int leaves() {
		return leaves(root);
	}

	private int leaves(Node<E> root){
		if(root == null){
			return 0;
		}
		else if(root.left == null && root.right == null){
			return 1;
		}else{
			return leaves(root.left) + leaves(root.right);
		}
	}

	/**
	 * Returns the height of the tree.  A tree with one node has
	 * a height of one.  An empty tree has a height of zero.
	 * 
	 * @return returns the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(Node<E> root){
		if(root == null){
			return 0;
		}else{
			return Math.max(height(root.left), height(root.right)) + 1;
		}
	}

	/**
	 * Returns a preorder String of tree elements.
	 * 
	 * @return a string with an preorder traversal of the tree
	 */
	public String preorder() {
		return preorder(root);
	}

	private String preorder(Node<E> root){
		if(root == null){
			return "";
		}
		else{
			String left = preorder(root.left);
			String current = root.toString();
			String right = preorder(root.right);

			return current + " " + left + right;
		}
	}
	
	/**
	 * Returns an inorder String of tree elements.
	 * 
	 * @return a string with an inorder traversal of the tree
	 */
	public String inorder() {
		return inorder(root);
	}

	public String inorder(Node<E> root){
		if(root == null){
			return "";
		} else{
			String left = inorder(root.left);
			String current = root.toString();
			String right = inorder(root.right);

			return left + current + " " + right;
		}
	}

	/**
	 * Returns a postorder String of tree elements.
	 * 
	 * @return a String with a postorder traversal of the tree
	 */
	public String postorder() {
		return postorder(root);
	}

	private String postorder(Node<E> root){
		if(root == null){
			return "";
		} else{
			String left = postorder(root.left);
			String current = root.toString();
			String right = postorder(root.right);

			return left + right + current + " ";
		}
	}
	
	/**
	 * Returns a breadth-first order String of tree elements.
	 * 
	 * @return a String with a breadth-first order traversal of the tree
	 */
	public String breadthFirstOrder() {
		Queue<Node<E>> myQueue = new LinkedList<>();
		String output = "";
		myQueue.offer(root);
		while(!myQueue.isEmpty()){
			Node<E> current = myQueue.poll();
			output += current.toString() + " ";
			if(current.left != null)
				myQueue.offer(current.left);
			if(current.right != null)
				myQueue.offer(current.right);
		}
		return output;
	}

	private String breadthFirstOrder(Node<E> root){
		int height = height();
		return "";
	}
	
	/**
	 * Find the smallest element in the tree.
	 * 
	 * @return smallest element or null if the tree is empty.
	 */
	public E findMin() {
		return findMin(root);
	}

	private E findMin(Node<E> root){
		if(root.left == null){
			return root.data;
		}else{
			return findMin(root.left);
		}
	}

	/**
	 * Find the largest element in the tree.
	 * 
	 * @return the largest item or null if the tree is empty.
	 */
	public E findMax() {
		return findMax(root);
	}

	private E findMax(Node<E> root){
		if(root == null){
			return null;
		}
		else if(root.right == null){
			return root.data;
		}else{
			return findMax(root.right);
		}
	}
	
	/**
	 * Returns a parenthesized String representing the structure of the Binary
	 * Search Tree.  The String produced will have the following structure:
	 * "((L)N(R))" where L is the left subtree, R is the right subtree and N
	 * is the root of the subtree.  For example, if L has one child on the left
	 * and R has two children, then the string would be "(((LL)L)N((RL)R(RR)))",
	 * where LL is L's left child and RL and RR are the left and right children
	 * of R.
	 * 
	 * @return a parenthesized string representing structure of BST tree
	 */
	public String toString( ) { 
		return inorder();
	}

	/**
	 * Remove the minimum element from the tree
	 * 
	 * @return true if the minimum element is removed; false otherwise
	 * @throws NoSuchElementException
	 *             if the tree is empty
	 */
	public boolean removeMin() {
		return removeMin(root);
	}

	private boolean removeMin(Node<E> root){
		if(root == null){
			throw new NoSuchElementException();
		}
		
		//base case for removing the main root making its right branch the new root
		if (root.left == null){
			this.root = root.right;
			return true;
		}
		else if(root.left.left == null){
			root.left = root.left.right;
			return true;
		}
		else{
			removeMin(root.left);
		}
		return false;
	}

	/**
	 * Remove the largest element from the tree
	 * 
	 * @return true if the largest element is removed; false otherwise
	 * @throws NoSuchElementException
	 *             if the tree is empty
	 */
	public boolean removeMax() {
		return removeMax(root);
	}
	
	private boolean removeMax(Node<E> root){
		if(root == null){
			throw new NoSuchElementException();
		}

		//base case for removing the main root making its left branch the new root
		if(root.right == null){
			this.root = root.left;
			return true;
		}
		else if(root.right.right == null){
			root.right = root.right.left;
			return true;
		}
		else{
			removeMax(root.right);
		}
		return false;
	}


	// **************  End of unimplemented methods  *********************/

}
