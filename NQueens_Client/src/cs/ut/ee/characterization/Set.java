package cs.ut.ee.characterization;

import java.util.List;

public class Set {
	private String linguisticVariable;
	private List<Term> linguisticTerms; //TODO-this should be <String, functionType, functionValues>
	private String setDescription;
	
	public String getLinguisticVariable(){
		return linguisticVariable;
	}
	
	public List<Term> getLinguisticTerms(){
		return linguisticTerms;
	}
	
	public String getSetDescription(){
		return setDescription;
	}
	
}
