package cs.ut.ee.characterization;

import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * @author Huber Flores
 */

public abstract class Characterization  {

	
	@Expose
	private String mobileApplication;
	@Expose
	private String deviceID;
	@Expose
	private String description;
	@Expose
	private List<String> candidateComponents; 
	@Expose
	private List<Set> variables; 
	@Expose
	private List<Rule> rules;

	//@Expose
	//private List<ExecutionPlan> plans;
	
	public List<String> getMobileComponents() {
	    return candidateComponents;
	}
	
	public String getMobileApplication(){
		return mobileApplication;
	}
	
	public List<Set> getVariables(){
		return variables;
	}
	
	public List<Rule> getRules(){
		return rules;
	}

	
}




