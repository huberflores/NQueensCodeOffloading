package symlab.ust.hk.offloading.ar.tree;

public class TreeManager {

	private static IntermediateTree instance = null;
	
	protected TreeManager() {
	}
	
	public static IntermediateTree getInstance() {
	    if(instance == null) {
	       instance = new IntermediateTree();
	    }
	    return instance;
	}
	
}
