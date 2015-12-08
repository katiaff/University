package trees;

public class AVLtree<T extends Comparable<T>> extends BSTree<T> {

	// To be implemented during lesson 2
	//
	@Override
	public boolean add(T x) {
		try {
			root = add((AVLnode<T>) root, x);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// To be implemented during lesson 2. Please see slide 45 in
	// the Hierarchical Data Structures slide deck.
	//
	protected AVLnode<T> add(AVLnode<T> root, T x) {
		if (root == null)
			System.out.println("Adding " + x + " to null");
		else
			System.out.println("Adding " + x + " to " + root.getInfo());

		if (root == null)
			return new AVLnode<T>(x);
		if (x.compareTo(root.getInfo()) == 0)
			throw new RuntimeException("the element already	exist!");
		if (x.compareTo(root.getInfo()) < 0)
			root.setLeft(add(root.getLeft(), x));
		else
			root.setRight(add(root.getRight(), x));

		return (updateBalanceFactor(root));
	}

	// To be implemented during lesson 2. Please see slide 46 in
	// the Hierarchical Data Structures slide deck.
	//
	protected AVLnode<T> updateBalanceFactor(AVLnode<T> root) {
		if (root.getBalanceFactor() == -2) {
			if (root.getLeft().getBalanceFactor() == -1 || root.getLeft().getBalanceFactor() == 0) {
				// In the case there is ambiguity ( we could do a double left or single left rotation ) 
				// we go for single left rotation.
				System.out.println("We'll do a single left rotation");
				root = singleLeftRotation(root);
			} else {
				System.out.println("We'll do a double left rotation");
				root = doubleLeftRotation(root);
			}
		} else if (root.getBalanceFactor() == 2) {
			if (root.getRight().getBalanceFactor() == 1 || root.getRight().getBalanceFactor() == 0) {
				// In the case there is ambiguity ( we could do a double right or single right rotation ) 
				// we go for single right rotation.
				System.out.println("We'll do a single right rotation");
				root = (singleRightRotation((AVLnode<T>) root));
			} else {
				System.out.println("We'll do a double right rotation");
				root = (doubleRightRotation(root));
			}
		}
		return root;
	}

	// Homework due lesson 3. See the tutorial I recommend in slide 9
	//
	protected AVLnode<T> singleLeftRotation(AVLnode<T> root) {
		AVLnode<T> newRoot = root.getLeft();

		root.setLeft(newRoot.getRight());
		newRoot.setRight(root);

		return newRoot;
	}

	// Homework due lesson 3. See the tutorial I recommend in slide 9
	//
	protected AVLnode<T> singleRightRotation(AVLnode<T> root) {
		AVLnode<T> newRoot = root.getRight();

		root.setRight(newRoot.getLeft());
		newRoot.setLeft(root);

		return newRoot;
	}

	// Homework due lesson 3. See the tutorial I recommend in slide 9
	//
	// Remember you must single-right rotate the left child of root
	// (the parameter) and then single-left rotate the root
	//
	protected AVLnode<T> doubleLeftRotation(AVLnode<T> root) {
		root.setLeft(singleRightRotation(root.getLeft()));

		root = singleLeftRotation(root);

		return root;
	}

	// Homework due lesson 3. See the tutorial I recommend in slide 9
	//
	// Remember you must single-left rotate the right child of root
	// (the parameter) and then single-right rotate the root
	//
	protected AVLnode<T> doubleRightRotation(AVLnode<T> root) {
		root.setRight(singleLeftRotation(root.getRight()));

		root = singleRightRotation(root);

		return root;
	}

	@Override
	public boolean remove(T x) {
		root = remove((AVLnode<T>)root, x);
		return true;
	}

	protected AVLnode<T> remove(AVLnode<T> root, T x) {
		if (root == null) {
			throw new RuntimeException("Element does not exist");
		} else {
			T rootInfo = root.getInfo();

			// if the information we are looking for is located
			// on the left of the current root, remove the node
			// from the left and update tree
			if (x.compareTo(rootInfo) < 0) {
				AVLnode<T> removed = remove(root.getLeft(), x);
				root.setLeft(removed);
			}

			// if it is located on the right, perform the same
			// steps on the right branch
			else if (x.compareTo(rootInfo) > 0) {
				AVLnode<T> removed = remove(root.getRight(), x);
				root.setRight(removed);
			}

			// if the current root is the element to remove
			else {

				// if the left subtree is empty,
				// return right subtree
				if (root.getLeft() == null) {
					return root.getRight();
				}
				// if the right subtree is empty,
				// return left subtree
				else if (root.getRight() == null) {
					return root.getLeft();
				}
				// if it has both subtrees, copy the
				// maximum value from the left subtree
				else {
					T max = getMax(root.getLeft());
					root.setInfo(max);
					AVLnode<T> removed = remove(root.getLeft(), max);
					root.setLeft(removed);
				}
			}
		}
		return updateBalanceFactor(root);
	}
	
	public int getHeight() {
		AVLnode<T> myRoot = (AVLnode<T>) root;
		if (myRoot == null) {
			return 0;
		}
		return myRoot.getHeight() + 1;
	}
}
