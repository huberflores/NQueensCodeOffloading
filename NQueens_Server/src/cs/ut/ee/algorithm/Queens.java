package cs.ut.ee.algorithm;

import java.lang.reflect.Method;
import java.util.Vector;

import edu.ut.mobile.network.CloudRemotable;

public class Queens extends CloudRemotable  {
    
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
    	Method toExecute; 
    	Class<?>[] paramTypes = null; 
    	Object[] paramValues = null; 
    	
    	try{
    		toExecute = this.getClass().getDeclaredMethod("localenumerateQueens", paramTypes);
    		Vector results = getCloudController().execute(toExecute,paramValues,this,this.getClass());
    		if(results != null){
    			copyState(results.get(1));
    		}else{
    			localenumerateQueens();
    		}
    		}  catch (SecurityException se){
    		} catch (NoSuchMethodException ns){
    		}catch (Throwable th){
    	}

    	
    	
    	
    }

    void copyState(Object state){
    	Queens localstate = (Queens) state;
    	this.x = localstate.x;
    }  

}



