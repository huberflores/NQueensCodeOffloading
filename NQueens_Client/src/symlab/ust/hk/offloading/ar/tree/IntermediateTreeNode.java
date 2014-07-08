package symlab.ust.hk.offloading.ar.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ut.mobile.network.Pack;
import edu.ut.mobile.network.ResultPack;

public class IntermediateTreeNode<T> {

	private T iKey;
	private Pack iObject;
	private ResultPack iResult;

    
    private List<IntermediateTreeNode<T>> children;
    private IntermediateTreeNode<T> parent;

    public IntermediateTreeNode() {
        super();
        children = new ArrayList<IntermediateTreeNode<T>>();
    }

    public IntermediateTreeNode(T iKey, /*Pack iObj, */ResultPack iRes) {
        this();
        setKey(iKey);
        //setObject(iObj);
        setResult(iRes);
    }

    public IntermediateTreeNode<T> getParent() {
        return this.parent;
    }

    public List<IntermediateTreeNode<T>> getChildren() {
        return this.children;
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }

    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }

    public void setChildren(List<IntermediateTreeNode<T>> children) {
        for(IntermediateTreeNode<T> child : children) {
           child.parent = this;
        }

        this.children = children;
    }

    public void addChild(IntermediateTreeNode<T> child) {
        child.parent = this;
        children.add(child);
    }

    public void addChildAt(int index, IntermediateTreeNode<T> child) throws IndexOutOfBoundsException {
        child.parent = this;
        children.add(index, child);
    }

    public void removeChildren() {
        this.children = new ArrayList<IntermediateTreeNode<T>>();
    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public IntermediateTreeNode<T> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    
    public T getKey(){
    	return this.iKey;
    }
    
    
    public Pack getObject(){
    	return this.iObject;
    }
    
    public ResultPack getResult() {
        return this.iResult;
    }
    
   
    public void setKey(T iKey){
    	this.iKey = iKey;
    }
    
    public void setObject(Pack iObject){
    	this.iObject = iObject;
    }

    public void setResult(ResultPack iResult) {
        this.iResult = iResult;
    }
   

    public String toString() {
        return getKey().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
           return true;
        }
        if (obj == null) {
           return false;
        }
        if (getClass() != obj.getClass()) {
           return false;
        }
        IntermediateTreeNode<?> other = (IntermediateTreeNode<?>) obj;
        if (iKey == null) {
           if (other.iKey != null) {
              return false;
           }
        } else if (!iKey.equals(other.iKey)) {
           return false;
        }
        return true;
    }

   
    @Override
    public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + ((iKey == null) ? 0 : iKey.hashCode());
       return result;
    }

    public String toStringVerbose() {
        String stringRepresentation = getKey().toString() + ":[";

        for (IntermediateTreeNode<T> node : getChildren()) {
            stringRepresentation += node.getKey().toString() + ", ";
        }

       
        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);

        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";

        return stringRepresentation;
    }
}

