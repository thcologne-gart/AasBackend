package GARTApiV1SubmodelComponent;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import GARTApiV1Component.abstractController;

public class SubmodelController extends abstractController{

	public SubmodelController() {
		
	}
	
	public Map<?,?> getSubmodelElementByPath(String aasServerAddress, String aasIdentifier,String submodelIdShort, JsonArray submodelElementIdShorts) {
		ArrayList<String> arrayList = new ArrayList<>();
		for (JsonElement submodelElementId : submodelElementIdShorts) {
            String stringValue = gson.fromJson(submodelElementId, String.class);
            arrayList.add(stringValue);
        }
		String strSmElement =aasServerGate.getSubmodelElement(aasServerAddress,aasIdentifier, submodelIdShort, arrayList); 
		return convertJsonObjectStringToMap(strSmElement); 
	}
	
	public Map<?, ?> getSubmodelElementValue(String aasServerAddress, String aasIdentifier,String submodelIdShort, JsonArray submodelElementIdShorts) {
		ArrayList<String> arrayList = new ArrayList<>();
		for (JsonElement submodelElementId : submodelElementIdShorts) {
            String stringValue = gson.fromJson(submodelElementId, String.class);
            arrayList.add(stringValue);
        }
		String value = aasServerGate.getSubmodelElementValue(aasServerAddress,aasIdentifier, submodelIdShort, arrayList);
		JsonObject valueObject = new JsonObject();
		if (value != null && value.length() >= 2 && value.charAt(0) == '\"' && value.charAt(value.length() - 1) == '\"') {
			value = value.substring(1, value.length() - 1);
			valueObject.addProperty("value", value);
		}
		if (value != null && value.length() >= 2  && value.charAt(0) == '[' && value.charAt(value.length() - 1) == ']') {
			JsonArray testArray = gson.fromJson(value, JsonArray.class);
			valueObject.add("value", testArray);
		}
		return convertJsonObjectStringToMap(valueObject.toString());
	}
	
	public  Map<?,?> getSubmodel(String aasServerAddress, String aasId,String smIdShort) {
		String strSm = aasServerGate.getSubmodel(aasServerAddress, aasId, smIdShort).toString();
		return convertJsonObjectStringToMap(strSm); 
	}
	
	public ResponseEntity<byte[]> downloadSubmodelElementValue(String aasServerAddress, String aasIdentifier,String submodelIdShort, JsonArray submodelElementIdShorts) {
		HttpHeaders headers = new HttpHeaders();
		String filename = submodelIdShort;
		for (int i = 0; i < submodelElementIdShorts.size(); i++) {
			filename = filename + "_" + submodelElementIdShorts.get(i).getAsString();
		}
		filename = filename + ".txt";
		System.out.println(filename);
		headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());
		Object smeValue = getSubmodelElementValue(aasServerAddress, aasIdentifier, submodelIdShort, submodelElementIdShorts).get("value");
		byte[] decodedBytes = smeValue.toString().getBytes();
		return ResponseEntity.ok().headers(headers).body(decodedBytes); 
	}

	public void editSubmodelElementValue(String aasServerAddress, String aasId,String smIdShort, JsonArray smEleIdShorts, String value) {
		ArrayList<String> arrayList = new ArrayList<>();
		for (JsonElement submodelElementId : smEleIdShorts) {
            String stringValue = gson.fromJson(submodelElementId, String.class);
            arrayList.add(stringValue);
        }
		aasServerGate.editSubmodelElementValue(aasServerAddress, aasId, smIdShort, arrayList, value);
	}
	
	public void addSubmodelElements(String aasServerAddress, String aasId,String smIdShort,String smSemanticId, JsonObject smElements) {
		ArrayList<JsonObject> smEleList = smFactory.createSubmodelElementsByValues(smSemanticId, smElements);
		for(JsonObject obj :smEleList) {
			aasServerGate.putSubmodelElementInSubmodel(aasServerAddress, aasId, smIdShort, obj);
		}
	}
	
	public void addSubmodelToAas(String aasServerAddress, String aasId, String smSemanticId) {
		JsonObject initialSm = smFactory.loadInitialSubmodel(smSemanticId);
		aasServerGate.writeSubmodelToAASServer(aasServerAddress,initialSm, aasId);
	}

	public void deleteSubmodel(String aasServerAddress, String aasId,String smIdShort) {
		aasServerGate.deleteSubmodel(aasServerAddress,aasId,smIdShort); 
	}
	
	public void editSubmodelElementValues(String aasServerAddress, String aasId, String smIdShort, JsonArray submodelElementsToEdit) {
		for (JsonElement jsonElement : submodelElementsToEdit) {
			JsonObject obj = jsonElement.getAsJsonObject(); 
			JsonArray smEleIdShorts = obj.get("submodelElementShortIdPath").getAsJsonArray(); 
			String value = obj.get("value").getAsString(); 
			System.out.println(smEleIdShorts);
			System.out.println(value);
			editSubmodelElementValue(aasServerAddress, aasId, smIdShort,smEleIdShorts,value );
		}
	}	
}