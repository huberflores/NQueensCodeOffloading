package cs.ut.ee.characterization;

import com.google.gson.Gson;

import cs.ut.ee.algorithm.Parameters;

public class DataLoader {
	
	Characterization data = null;
	
	public void loadJSONCharacterization(){
		Gson gson = new Gson();
		Class taskClass;
		
		try {
			taskClass = Class.forName(Parameters.CLASS_PATH_ROUTINES + "ProfilerManager");
			
			String characterizationDescriptor = Parameters.JSON_PATTERN_SAMPLE;
		
			data = (Characterization) gson.fromJson(characterizationDescriptor, taskClass);
		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	public Characterization getDataCharacterization(){
		loadJSONCharacterization();
		
		return data;
	}
	
	

}
