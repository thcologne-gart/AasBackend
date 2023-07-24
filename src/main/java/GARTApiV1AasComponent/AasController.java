package GARTApiV1AasComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import AasGeneratorComponent.AasGenerator;
import GARTApiV1Component.abstractController;

public class AasController extends abstractController{
	
	private static final String Slash = "/";
	private static final String EncodedSlash = "%2F";
	private static final String DERIVEDFROM = "derivedFrom";
	private static final String KEYS = "keys";
	private static final String VALUE = "value";
	
	public AasController() {
	
	}
	
	public List<String> getAllAasIdentifier(String registryAddress) {
		return registryGate.getAllAasIdentifier(registryAddress);
	}
	
	public Map<?, ?> getAasByIdentifier(String aasServerAddress, String identifier) { 
		identifier = identifier.replaceAll(Slash, EncodedSlash);
		String aasStr = aasServerGate.getAasByIdentifier(aasServerAddress, identifier);
        return convertJsonObjectStringToMap(aasStr);
	}
	
	public ArrayList<String> getallAasIdentifierByAasType(String aasServerAddress,String registryAddress, String semanticIdAasType) {
		ArrayList<String> aasIdList = registryGate.getAllAasIdentifier(registryAddress);
		ArrayList<String> returnList = new ArrayList<>();
		for (String aasId : aasIdList) {
			JsonObject obj = gson.fromJson(aasServerGate.getAasByIdentifier(aasServerAddress,aasId), JsonObject.class);
			String tempSemanticIdAasType = null; 
			if (obj.has(DERIVEDFROM)){
				tempSemanticIdAasType = obj.get(DERIVEDFROM).getAsJsonObject().get(KEYS).getAsJsonArray().get(0).getAsJsonObject().get(VALUE).getAsString();
			}
			if (tempSemanticIdAasType != null && tempSemanticIdAasType.contains(semanticIdAasType)) {
				returnList.add(aasId);
			}
		}
		return  returnList;
	}
	
	public void deleteAasByIdentifier(String aasServerAddress, String aasIdentifier) { 
		aasServerGate.deleteAasByIdentifier(aasServerAddress,aasIdentifier); 
	}
	
	public String createAasByAasType(String aasServerAddress, String idShort, String semanticIdAasType) {
		AasGenerator aas = new AasGenerator(idShort,semanticIdAasType);
		aasServerGate.writeAASToAASServer(aasServerAddress,aas.getAasInstance());
		JsonArray submodelSemanticIds = repository.get(semanticIdAasType).getAsJsonArray();
		for (JsonElement jsonElement : submodelSemanticIds) {
			String submodelSemanticId = jsonElement.getAsString();
			aasServerGate.writeSubmodelToAASServer(aasServerAddress, smFactory.loadInitialSubmodel(submodelSemanticId), aas.getAasIdentifier());
		}
		return aas.getAasIdentifier(); 
	}

	public List<?> getAasIdShortByIdentifier(String aasServerAddress, String identifier) { 
		ArrayList<String> returnList = new ArrayList<String>(); 
		returnList.add((String) getAasByIdentifier(aasServerAddress, identifier).get("idShort")); 
        return returnList;
	}
}