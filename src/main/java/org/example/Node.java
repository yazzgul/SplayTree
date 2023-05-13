package org.example;
class Node {
    int key;
    Node left, right, parent;

    public Node(int key) {
        this.key = key;
        left = right = parent = null;
    }
}

// Класс Splay Tree

class SplayTree {
    int countOp = 0;
    private Node root;

    // Конструктор
    public SplayTree() {
        root = null;
    }

    // Вспомогательный метод для поворота влево
    private void rotateLeft(Node x) {
        countOp++;
        Node y = x.right;
        countOp++;
        x.right = y.left;
        if (y.left != null) {
            countOp++;
            y.left.parent = x;
        }
        countOp++;
        y.parent = x.parent;
        if (x.parent == null) {
            countOp++;
            root = y;
        }
        else if (x == x.parent.left) {
            countOp++;
            x.parent.left = y;
        }
        else {
            countOp++;
            x.parent.right = y;
        }
        countOp++;
        y.left = x;
        countOp++;
        x.parent = y;
    }

    // Вспомогательный метод для поворота вправо
    private void rotateRight(Node x) {
        countOp++;
        Node y = x.left;
        x.left = y.right;
        countOp++;
        if (y.right != null) {
            countOp++;
            y.right.parent = x;
        }
        countOp++;
        y.parent = x.parent;
        if (x.parent == null) {
            countOp++;
            root = y;
        }
        else if (x == x.parent.right) {
            countOp++;
            x.parent.right = y;
        }
        else {
            countOp++;
            x.parent.left = y;
        }
        countOp++;
        y.right = x;
        countOp++;
        x.parent = y;
    }

    // Метод Splay - выполняет операцию "splay" для узла x
    private void splay(Node x) {
        while (x.parent != null) {
            if (x.parent.parent == null) {
                if (x == x.parent.left) {
                    rotateRight(x.parent);
                }
                else {
                    rotateLeft(x.parent);
                }
            } else if (x == x.parent.left && x.parent == x.parent.parent.left) {
                rotateRight(x.parent.parent);
                rotateRight(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.right) {
                rotateLeft(x.parent.parent);
                rotateLeft(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.left) {
                rotateLeft(x.parent);
                rotateRight(x.parent);
            } else {
                rotateRight(x.parent);
                rotateLeft(x.parent);
            }
        }
    }

    // Метод вставки ключа в дерево
    public void insert(int key) {
        countOp++;
        Node newNode = new Node(key);
        countOp++;
        Node y = null;
        countOp++;
        Node x = root;

        while (x != null) {
            countOp++;
            y = x;
            if (key < x.key) {
                countOp++;
                x = x.left;
            }
            else if (key > x.key) {
                countOp++;
                x = x.right;
            }
            else {
                return; // Дубликаты не разрешены
            }
        }
        countOp++;
        newNode.parent = y;
        if (y == null) {
            countOp++;
            root = newNode;
        }
        else if (key < y.key) {
            countOp++;
            y.left = newNode;
        }
        else {
            countOp++;
            y.right = newNode;
        }

        splay(newNode); // Выполняем операцию "splay" для вставленного узла
    }
        // Метод поиска ключа в дереве
    public boolean search ( int key){
        countOp++;
        Node node = searchNode(key);
        if (node != null) {
            splay(node); // Выполняем операцию "splay" для найденного узла
            return true;
        }
        return false;
    }

    // Вспомогательный метод для поиска узла с заданным ключом
    private Node searchNode (int key){
        countOp++;
        Node current = root;
        while (current != null) {
            if (key < current.key) {
                countOp++;
                current = current.left;
            }
            else if (key > current.key) {
                countOp++;
                current = current.right;
            }
            else {
                return current;
            }
        }
        return null;
    }

    // Метод удаления ключа из дерева
    public void delete ( int key){
        countOp++;
        Node node = searchNode(key);
        if (node == null)
            return;

        splay(node); // Выполняем операцию "splay" для узла, который будет удален

        // Если у узла нет левого поддерева, просто отсоединяем его
        if (node.left == null) {
            countOp++;
            root = node.right;
            if (node.right != null) {
                countOp++;
                node.right.parent = null;
            }
        }
        // Если у узла нет правого поддерева, просто отсоединяем его
        else if (node.right == null) {
            countOp++;
            root = node.left;
            countOp++;
            node.left.parent = null;
        }
        // Если у узла есть и левое, и правое поддерево
        else {
            countOp++;
            Node leftSubtree = node.left;
            countOp++;
            leftSubtree.parent = null;
            countOp++;
            root = leftSubtree;

            // Находим самый правый узел в левом поддереве
            countOp++;
            Node rightmost = findRightmost(leftSubtree);
            splay(rightmost); // Выполняем операцию "splay" для найденного узла

            // Присоединяем правое поддерево к корню
            countOp++;
            rightmost.right = node.right;
            countOp++;
            node.right.parent = rightmost;
        }
    }

    // Вспомогательный метод для поиска самого правого узла в поддереве
    private Node findRightmost (Node node){
        while (node.right != null) {
            countOp++;
            node = node.right;
        }
        return node;
    }

    int getCountOp() {
        int newC = countOp;
        countOp = 0;
        return newC;
    }
}
