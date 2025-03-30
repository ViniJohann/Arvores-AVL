public class AVLTree {
    private Node root;

    private int height(Node node){
        return (node != null) ? node.getHeight() : 0;
    }

    private int getBalance(Node node){
        return (node != null) ? height(node.getLeft()) - height(node.getRight()) : 0;
    }

    private Node rightRotate(Node node){
        Node left = node.getLeft();
        Node right = left.getRight();

        left.setRight(node);
        node.setLeft(right);

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        left.setHeight(Math.max(height(left.getLeft()), height(left.getRight())) + 1);

        return left;
    }

    private Node leftRotate(Node node){
        Node right = node.getRight();
        Node left = right.getLeft();

        right.setLeft(node);
        node.setRight(left);

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        right.setHeight(Math.max(height(right.getLeft()), height(right.getRight())) + 1);

        return right;
    }

    public void insert(int key){
        root = insert(root, key);
    }

    private Node insert(Node node, int key){
        if (node == null)
            return new Node(key);

        if (key < node.getKey())
            node.setLeft(insert(node.getLeft(), key));
        else if (key > node.getKey())
            node.setRight(insert(node.getRight(), key));
        else
            return node;

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) +1);
        int balance = getBalance(node);

        if(balance > 1 && key < node.getLeft().getKey())
            return rightRotate(node);

        if (balance < -1 && key > node.getRight().getKey())
            return leftRotate(node);

        if (balance > 1 && key > node.getLeft().getKey()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balance < -1 && key < node.getRight().getKey()){
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    public void delete(int key){
        root = delete(root, key);
    }

    private Node delete(Node node, int key){
        if(node == null)
            return node;

        if (key < node.getKey())
            node.setLeft(delete(node.getLeft(), key));
        else if (key > node.getKey())
            node.setRight(delete(node.getRight(), key));
        else{
            if ((node.getLeft() == null) || (node.getRight() == null)){
                Node temp = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                if(temp == null){
                    temp = node;
                    node = null;
                } else
                    node = temp;
            } else {
                Node temp = minValueNode(node.getRight());
                node.setKey(temp.getKey());
                node.setRight(delete(node.getRight(), temp.getKey()));
            }
        }

        if (node == null)
            return node;

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.getLeft()) >= 0)
            return rightRotate(node);

        if (balance > 1 && getBalance(node.getLeft()) < 0){
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.getRight()) <= 0)
            return leftRotate(node);

        if (balance < -1 && getBalance(node.getRight()) > 0){
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    private Node minValueNode(Node node){
        Node current = node;
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    public boolean searchAndPrintPath(int key) {
        return searchAndPrintPath(root, key);
    }

    private boolean searchAndPrintPath(Node node, int key) {
        if (node == null) {
            return false;
        }
        System.out.print(node.getKey() + " "); // Imprime a chave do nó visitado
        if (node.getKey() == key) {
            return true;
        }
        if (key < node.getKey()) {
            return searchAndPrintPath(node.getLeft(), key);
        } else {
            return searchAndPrintPath(node.getRight(), key);
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.getKey() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void inOrder() {
        if (root == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getKey() + " ");
            inOrder(node.getRight());
        }
    }

    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

}
