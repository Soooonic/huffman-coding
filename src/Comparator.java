import java.util.Comparator;

class ImplementComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        //comapreto return value>0 if b>a else return value<0
        return b.prob.compareTo(a.prob);
    }
}

