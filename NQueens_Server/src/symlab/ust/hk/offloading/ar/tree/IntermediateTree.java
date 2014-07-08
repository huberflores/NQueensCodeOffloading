
package symlab.ust.hk.offloading.ar.tree;

import java.util.*;

public class IntermediateTree<T> {

    private IntermediateTreeNode<T> root;

    public IntermediateTree() {
        super();
    }

    public IntermediateTreeNode<T> getRoot() {
        return this.root;
    }

    public void setRoot(IntermediateTreeNode<T> root) {
        this.root = root;
    }

    public int getNumberOfNodes() {
        int numberOfNodes = 0;

        if(root != null) {
            numberOfNodes = auxiliaryGetNumberOfNodes(root) + 1; 
        }

        return numberOfNodes;
    }

    private int auxiliaryGetNumberOfNodes(IntermediateTreeNode<T> node) {
        int numberOfNodes = node.getNumberOfChildren();

        for(IntermediateTreeNode<T> child : node.getChildren()) {
            numberOfNodes += auxiliaryGetNumberOfNodes(child);
        }

        return numberOfNodes;
    }

    public boolean exists(T dataToFind) {
        return (find(dataToFind) != null);
    }

    public IntermediateTreeNode<T> find(T dataToFind) {
        IntermediateTreeNode<T> returnNode = null;

        if(root != null) {
            returnNode = auxiliaryFind(root, dataToFind);
        }

        return returnNode;
    }

    private IntermediateTreeNode<T> auxiliaryFind(IntermediateTreeNode<T> currentNode, T dataToFind) {
        IntermediateTreeNode<T> returnNode = null;
        int i = 0;

        if (currentNode.getKey().equals(dataToFind)) {
            returnNode = currentNode;
        }

        else if(currentNode.hasChildren()) {
            i = 0;
            while(returnNode == null && i < currentNode.getNumberOfChildren()) {
                returnNode = auxiliaryFind(currentNode.getChildAt(i), dataToFind);
                i++;
            }
        }

        return returnNode;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public List<IntermediateTreeNode<T>> build(IntermediateTreeTraversalOrderEnum traversalOrder) {
        List<IntermediateTreeNode<T>> returnList = null;

        if(root != null) {
            returnList = build(root, traversalOrder);
        }

        return returnList;
    }

    public List<IntermediateTreeNode<T>> build(IntermediateTreeNode<T> node, IntermediateTreeTraversalOrderEnum traversalOrder) {
        List<IntermediateTreeNode<T>> traversalResult = new ArrayList<IntermediateTreeNode<T>>();

        if(traversalOrder == IntermediateTreeTraversalOrderEnum.PRE_ORDER) {
            buildPreOrder(node, traversalResult);
        }

        else if(traversalOrder == IntermediateTreeTraversalOrderEnum.POST_ORDER) {
            buildPostOrder(node, traversalResult);
        }

        return traversalResult;
    }

    private void buildPreOrder(IntermediateTreeNode<T> node, List<IntermediateTreeNode<T>> traversalResult) {
        traversalResult.add(node);

        for(IntermediateTreeNode<T> child : node.getChildren()) {
            buildPreOrder(child, traversalResult);
        }
    }

    private void buildPostOrder(IntermediateTreeNode<T> node, List<IntermediateTreeNode<T>> traversalResult) {
        for(IntermediateTreeNode<T> child : node.getChildren()) {
            buildPostOrder(child, traversalResult);
        }

        traversalResult.add(node);
    }

    public Map<IntermediateTreeNode<T>, Integer> buildWithDepth(IntermediateTreeTraversalOrderEnum traversalOrder) {
        Map<IntermediateTreeNode<T>, Integer> returnMap = null;

        if(root != null) {
            returnMap = buildWithDepth(root, traversalOrder);
        }

        return returnMap;
    }

    public Map<IntermediateTreeNode<T>, Integer> buildWithDepth(IntermediateTreeNode<T> node, IntermediateTreeTraversalOrderEnum traversalOrder) {
        Map<IntermediateTreeNode<T>, Integer> traversalResult = new LinkedHashMap<IntermediateTreeNode<T>, Integer>();

        if(traversalOrder == IntermediateTreeTraversalOrderEnum.PRE_ORDER) {
            buildPreOrderWithDepth(node, traversalResult, 0);
        }

        else if(traversalOrder == IntermediateTreeTraversalOrderEnum.POST_ORDER) {
            buildPostOrderWithDepth(node, traversalResult, 0);
        }

        return traversalResult;
    }

    private void buildPreOrderWithDepth(IntermediateTreeNode<T> node, Map<IntermediateTreeNode<T>, Integer> traversalResult, int depth) {
        traversalResult.put(node, depth);

        for(IntermediateTreeNode<T> child : node.getChildren()) {
            buildPreOrderWithDepth(child, traversalResult, depth + 1);
        }
    }

    private void buildPostOrderWithDepth(IntermediateTreeNode<T> node, Map<IntermediateTreeNode<T>, Integer> traversalResult, int depth) {
        for(IntermediateTreeNode<T> child : node.getChildren()) {
            buildPostOrderWithDepth(child, traversalResult, depth + 1);
        }

        traversalResult.put(node, depth);
    }

    public String toString() {
   
        String stringRepresentation = "";

        if(root != null) {
            stringRepresentation = build(IntermediateTreeTraversalOrderEnum.PRE_ORDER).toString();

        }

        return stringRepresentation;
    }

    public String toStringWithDepth() {
      

        String stringRepresentation = "";

        if(root != null) {
            stringRepresentation = buildWithDepth(IntermediateTreeTraversalOrderEnum.PRE_ORDER).toString();
        }

        return stringRepresentation;
    }
}
