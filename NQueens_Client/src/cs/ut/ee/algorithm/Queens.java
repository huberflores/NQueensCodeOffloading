package cs.ut.ee.algorithm;

import java.lang.reflect.Method;
import java.util.Vector;

import symlab.ust.hk.offloading.ar.tree.IntermediateTreeNode;
import symlab.ust.hk.offloading.ar.tree.TreeManager;

import edu.ut.mobile.network.CloudRemotable;
import edu.ut.mobile.network.ResultPack;

public class Queens extends CloudRemotable  { 
	
	//Activates dynamic JSON mechanism
	//ProfilerManager manager = ProfilerManager.getInstance();  
    
    int x;

    public Queens(int N) {         
    	x = N; 
    } 

    public boolean isConsistent(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n])             return false; 
            if ((q[i] - q[n]) == (n - i)) return false; 
            if ((q[n] - q[i]) == (n - i)) return false; 
        }
        return true;
    }


    public void localenumerateQueens() {
        int[] a = new int[x];
        enumerate(a, 0);
    }

    public void enumerate(int[] q, int n) {
        int N = q.length;
        if (n == N) ;
        else {
            for (int i = 0; i < N; i++) {
                q[n] = i;
                if (isConsistent(q, n)) enumerate(q, n+1);
            }
        }
    }
    
    
    public void enumerateQueens() { 
    	//if (manager.processMethod("callplaceNqueens")){    
    	
    
    	Method toExecute;   
    	Class<?>[] paramTypes = null;  
    	Object[] paramValues = null; 
    	
    	try{ 
    		/*Activates tree-based mechanism*/
    		/*IntermediateTreeNode node;*/
        	
        	/*if ((node = TreeManager.getInstance().find("keyTree1"))!=null){
        		
        		copyState(node.getResult().getstate()); 
    		
        	}else{*/
        		
        		toExecute = this.getClass().getDeclaredMethod("localenumerateQueens", paramTypes);
        		Vector results = getCloudController().execute(toExecute,paramValues,this,this.getClass());
        		if(results != null){
        			copyState(results.get(1));  
        			
        			/*if (TreeManager.getInstance().isEmpty()){
    					TreeManager.getInstance().setRoot(new IntermediateTreeNode("keyTree1", 
    							new ResultPack(results.get(0), results.get(1)))
    							);
    				}else{
    					TreeManager.getInstance().getRoot().addChild(new IntermediateTreeNode("keyTree2", 
    							new ResultPack(results.get(0), results.get(1)))
    							);
    				}*/
        			
        		}else{ 
        			localenumerateQueens(); 
        		}
        		
        		
        		
        	/*}*/
    	
    	
    	}  catch (SecurityException se){
    		} catch (NoSuchMethodException ns){
    		}catch (Throwable th){}
    	
    	//
    	//}else{
    		//localenumerateQueens();
    	//}	
    }

    void copyState(Object state){
    	Queens localstate = (Queens) state;
    	this.x = localstate.x;
    }  

}



