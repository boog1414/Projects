public class BinarySearchTree <E extends Comparable<? super E>>{
    private class TreeNode {
        E value;
        TreeNode left;
        TreeNode right;

        public TreeNode(E value) {
            this.value = value;
        }
    }

    private TreeNode root;

    public void displayInOrder() {
        displayInOrder(root);
    }

    public boolean remove(E value) {
        TreeNode parent = null;
        TreeNode node = root;
        boolean done = false;
        while (!done) {
            if (node.value.compareTo(value) < 0) {
                parent = node;
                node = node.right;
            } else if (node.value.compareTo(value) > 0) {
                parent = node;
                node = node.left;
            } else {
                done = true;
            }
            if (node == null){
                return false;
            }
        }
        // Case for no left child
        if (node.left == null) {
            if (parent == null) {
                root = node.right;
            } else {
                if (parent.value.compareTo(value) < 0) {
                    parent.right = node.right;
                } else {
                    parent.left = node.right;
                }
            }
        } else { // Case for left child
            TreeNode parentOfRight = node;
            TreeNode rightMost = node.left;
            while (rightMost.right != null) {
                parentOfRight = rightMost;
                rightMost = rightMost.right;
            }
            node.value = rightMost.value;
            if (parentOfRight.right == rightMost) {
                parentOfRight.right = rightMost.left;
            } else {
                parentOfRight.left = rightMost.left;
            }
        }
        return true;
    }

    private void displayInOrder(TreeNode node) {
        if (node == null) return;

        displayInOrder(node.left);
        System.out.println(node.value);
        displayInOrder(node.right);
    }

    public boolean insert(E value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            TreeNode parent = null;
            TreeNode node = root;
            while (node != null) {
                parent = node;
                if (node.value.compareTo(value) < 0 ) {
                    node = node.right;
                } else if (node.value.compareTo(value) > 0){
                    node = node.left;
                }else {
                    return false;
                }
            }
            TreeNode newNode = new TreeNode(value);
            if (parent.value.compareTo(value) < 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
        return true;
    }

    public boolean search(E value) {
        boolean found = false;
        TreeNode node = root;

        while (!found && node != null) {
            if (node.value.compareTo(value) == 0) {
                found = true;
            } else if (node.value.compareTo(value) < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return found;
    }
    public void display(String message){
        System.out.println(message);
        displayInOrder();
    }
    public int numberNodes(){
        return recursionNumberNodes(root);
    }
    public int recursionNumberNodes(TreeNode node){
        if (node == null) {
            return 0;
        }
        return 1 + recursionNumberNodes(node.left) + recursionNumberNodes(node.right);
    }
    public int numberLeafNodes(){
        return recursionLeafNodes(root);
    }
    public int recursionLeafNodes(TreeNode node){
        if (node == null){
            return 0;
        }
        if (node.left == null && node.right == null){
            return 1;
        }
        return recursionLeafNodes(node.left) + recursionLeafNodes(node.right);
    }
    public int height(){
        return recursionHeight(root) - 1;
    }
    public int recursionHeight(TreeNode node){
        if (node == null){
            return 0;
        }
        int left = (1 + recursionHeight(node.left));
        int right = (1 + recursionHeight(node.right));
        return Math.max(left, right);
    }


}