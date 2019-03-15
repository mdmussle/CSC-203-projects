/*
 Author: Michael Mussler  
 10/11/2016
 Binary Search Tree Traversals
 */

public class BinarySearchTree {
TreeNode root;

    public static void main(String[] args) {
        BinarySearchTree Tree = new BinarySearchTree();
        Tree.insert(50);
        Tree.insert(25);
        Tree.insert(15);
        Tree.insert(30);
        Tree.insert(75);
        Tree.insert(85);
        System.out.println("\nInorder: ");
        Tree.inorder(Tree.root);
        System.out.println("\nPreOrder: ");
        Tree.preorder(Tree.root);
    }
   
  

    public BinarySearchTree() {
        root = null;
    }

public void insert(int key){
    TreeNode newNode= new TreeNode (key);
    System.out.println(key);
    if(root==null){
        root = newNode;
        return;
    }
    TreeNode current = root;
    while(true){
        if(current.key<key){
            if(current.right==null){
                current.right=newNode;
                return;
            }
            current=current.right;
        }
        else{
            if(current.left==null){
               current.left=newNode;
                return;
            }
            current=current.left;
        }
    }
   
}
void inOrder() {
        inorder(root);
    }
public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.printf("%d ",root.key);
            inorder(root.right);
        }
    }
 public void preoder() {
        preorder(root);
    }
void preorder(TreeNode root) { 
    if(root != null) { 
 System.out.printf("%d ",root.key);
 preorder(root.left);
 preorder(root.right);
    }
}
}

class TreeNode{
	int key;
	TreeNode left;
	TreeNode right;	
	public TreeNode(int key){
		this.key = key;
		left = null;
		right = null;
	}
}