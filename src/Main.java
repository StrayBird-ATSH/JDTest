import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input;
        input = new Scanner(System.in);
        int m = input.nextInt();
        if (m == 1) {
            System.out.println(1);
            return;
        }
        input.nextLine();
        Tree tree = new Tree(m);
        ArrayList<Tree.Node> list = tree.tree;
        for (int i = 0; i < m - 1; i++) {
            int a = input.nextInt() - 1;
            int b = input.nextInt() - 1;
            int parentNode = Math.min(a, b);
            int childNode = Math.max(a, b);
            list.get(parentNode).childrenNode.add(list.get(childNode));
            list.get(childNode).parent = list.get(parentNode);
        }
        ArrayList<Integer> size = new ArrayList<>();
        for (Tree.Node directSubNode : list.get(0).childrenNode)
            size.add(directSubNode.childNodeNumber());
        size.sort(null);
        System.out.println(size.get(size.size() - 1));
    }
}

class Tree {
    ArrayList<Node> tree;

    public Tree(int m) {
        tree = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            tree.add(new Node());
        }
    }

    class Node {
        Node parent;
        ArrayList<Node> childrenNode;

        public Node() {
            childrenNode = new ArrayList<>();
        }

        int childNodeNumber() {
            if (childrenNode.size() == 0) return 1;
            int sum = 0;
            for (Node node : childrenNode)
                sum += node.childNodeNumber();
            sum += 1;
            return sum;
        }
    }
}
