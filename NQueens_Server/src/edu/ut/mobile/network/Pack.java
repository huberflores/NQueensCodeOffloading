package edu.ut.mobile.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pack implements Serializable{
    private static final long serialVersionUID = 1;
    String functionName = null;
    Class stateType = null;
    Object state = null;
    Object[] paramValues = null;
    Class[] paramTypes = null;
    
    //Timestamps
    List<String> timestamps = new ArrayList<String>();
    

    public Pack(String functionName, Class stateType, Object state, Object[] paramValues, Class[] FuncDTypes) {
        this.functionName = functionName;
        this.stateType = stateType;
        this.state = state;
        this.paramValues = paramValues;
        this.paramTypes = FuncDTypes;
        timestamps.add(System.currentTimeMillis()+",client1");
        
    }

    public String getfunctionName(){
        return functionName;
    }
    
    public Class getstateType(){
        return stateType;
    }

    public Object[] getparamValues(){
        return paramValues;
    }

    public Class[] getparamTypes(){
        return paramTypes;
    }

    public Object getstate(){
        return state;
    }
    
    public List<String> getTimeStamps(){
    	return timestamps;
    }
    
}
