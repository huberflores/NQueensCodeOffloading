package cs.ut.ee.characterization;

import java.util.List;

/*
 * author Huber Flores
 */

public class ProfilerManager extends Characterization {

	public static ProfilerManager instance;
	
	private static DataLoader loader;
	

	private ProfilerManager(){ //constructor should be private

	}

	public static synchronized ProfilerManager getInstance(){
		if (instance==null){
			loader = new DataLoader();
			
			instance = (ProfilerManager) loader.getDataCharacterization();
			
			return instance;
		}

		return instance;
	}
	
	public boolean processMethod(String methodName){
		boolean process = false;
		
		List<String> candidateComponents = getMobileComponents();
		
		if (candidateComponents.contains(methodName)){
			process = true;
		}
		
		return process;
	}
	

	
	

	
	
}
