package BaSyxGateComponent;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BaSyxRegistryGate extends BaSyxGate{

	private static Gson gson = new Gson();

	public BaSyxRegistryGate() {

	}

	public ArrayList<String> getAllAasIdentifier(String registryAddress) {
		ArrayList<String> list = new ArrayList<String>();
		JsonArray jsonArray = gson.fromJson(httpGetCommand(registryAddress), JsonArray.class);
		for (JsonElement ele : jsonArray) {
			JsonObject obj = ele.getAsJsonObject();
			String identifier = obj.get(IDENTIFICATION).getAsJsonObject().get(ID).getAsString();
			list.add(identifier); 
		}
		return list; 
	}
	
	public ArrayList<String> getSubmodelEnpointListBySemanticId(String registryAddress, String semanticIdTarget){
		ArrayList<String> list = new ArrayList<String>(); 
		JsonArray aasEntries = gson.fromJson(httpGetCommand(registryAddress), JsonArray.class);
		for (JsonElement ele : aasEntries) {
			JsonObject aas = ele.getAsJsonObject();
			JsonArray smEntries = aas.get(SUBMODELS).getAsJsonArray();
			for(JsonElement ele2:smEntries) {
				JsonObject smEntry = ele2.getAsJsonObject();
				JsonArray semanticIdEntries = smEntry.get(SEMANTICID).getAsJsonObject().get(KEYS).getAsJsonArray();
				for(JsonElement ele3:semanticIdEntries) {
					JsonObject semanticIdEntry = ele3.getAsJsonObject();
					String semanticId = semanticIdEntry.getAsJsonObject().get(VALUE).getAsString();
					if (semanticId.contains(semanticIdTarget)) {
						String smEndpoint = smEntry.get(ENDPOINTS).getAsJsonArray().get(0).getAsJsonObject().get(ADDRESS).getAsString();
						list.add(smEndpoint);
					}
				}
			}
		}
		return list; 
	}	
}