package GARTApiV1SubmodelComponent;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(path = "GART/api/v1/Submodel")
public class SubmodelRestApi implements ISubmodelRestApi{

	private static final String getSubmodelElementByPath_POST = "/getSubmodelElementByPath";
	private static final String getSubmodelElementValue_POST = "/getSubmodelElementValue";
	private static final String getSubmodel_GET = "/getSubmodel";
	private static final String downloadSubmodelElementValue_POST = "/getSubmodelElementValue/download";
	private static final String editSubmodelElementValue_POST = "/editSubmodelElementValue";
	private static final String addSubmodelElements_POST = "/addSubmodelElements";
	private static final String addSubmodelToAas_POST = "/addSubmodelToAas";
	private static final String deleteSubmodel_Post = "/deleteSubmodel";
	private static final String editSubmodelElementValues_Post = "/editSubmodelElementValues";
	
	private static final String AASSERVERADDRESS = "aasServerAddress";
	private static final String AASIDENTIFIER = "aasIdentifier";
	private static final String SUBMODELIDSHORT = "submodelIdShort";
	private static final String SUBMODELELEMENTIDSHORT = "submodelElementIdShort"; 
	private static final String VALUE = "value";
	private static final String SEMANTICIDSUBMODEL = "semanticIdSubmodel"; 
	private static final String SUBMODELElEMENTVALUES = "submodelElementsValue"; 
	private static final String SUBMODELELEMENTSTOEDIT = "submodelElementsToEdir"; 

	private SubmodelController controller = new SubmodelController(); 

	public SubmodelRestApi() {

	}

	@PostMapping(path = getSubmodelElementByPath_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<?, ?> getSubmodelElementByPath(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String aasIdentifier = body.get(AASIDENTIFIER).getAsString();
		String submodelIdShort = body.get(SUBMODELIDSHORT).getAsString();
		JsonArray submodelElementIdShorts = body.get(SUBMODELELEMENTIDSHORT).getAsJsonArray();
		return controller.getSubmodelElementByPath(aasServerAddress,aasIdentifier, submodelIdShort, submodelElementIdShorts );
	}

	@PostMapping(path = getSubmodelElementValue_POST)
	public Map<?, ?> getSubmodelElementValue(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String aasIdentifier = body.get(AASIDENTIFIER).getAsString();
		String submodelIdShort = body.get(SUBMODELIDSHORT).getAsString();
		JsonArray submodelElementIdShorts = body.get(SUBMODELELEMENTIDSHORT).getAsJsonArray();
		return controller.getSubmodelElementValue(aasServerAddress,aasIdentifier, submodelIdShort, submodelElementIdShorts);
	}

	@PostMapping(path = getSubmodel_GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<?, ?> getSubmodel(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String aasId = body.get(AASIDENTIFIER).getAsString();
		String smIdShort = body.get(SUBMODELIDSHORT).getAsString();
		return controller.getSubmodel(aasServerAddress, aasId, smIdShort); 
	}
	
	@PostMapping(path = downloadSubmodelElementValue_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<byte[]> downloadSubmodelElementValue(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String aasIdentifier = body.get(AASIDENTIFIER).getAsString();
		String submodelIdShort = body.get(SUBMODELIDSHORT).getAsString();
		JsonArray submodelElementIdShorts = body.get(SUBMODELELEMENTIDSHORT).getAsJsonArray();
		return controller.downloadSubmodelElementValue(aasServerAddress,aasIdentifier, submodelIdShort, submodelElementIdShorts );
	}
	
	@PostMapping(editSubmodelElementValue_POST)
	public void editSubmodelElementValue(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String aasId = body.get(AASIDENTIFIER).getAsString();
		String smIdShort = body.get(SUBMODELIDSHORT).getAsString();
		JsonArray smElementIdShorts = body.get(SUBMODELELEMENTIDSHORT).getAsJsonArray();
		String value = body.get(VALUE).getAsString();
		controller.editSubmodelElementValue(aasServerAddress, aasId, smIdShort, smElementIdShorts, value); 
	}

	@PostMapping(addSubmodelElements_POST)
	public void addSubmodelElements(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String aasId = body.get(AASIDENTIFIER).getAsString();
		String smIdShort = body.get(SUBMODELIDSHORT).getAsString();
		String smSemanticId = body.get(SEMANTICIDSUBMODEL).getAsString();
		JsonObject smElements = body.get(SUBMODELElEMENTVALUES).getAsJsonObject();
		controller.addSubmodelElements(aasServerAddress, aasId, smIdShort,smSemanticId, smElements); 
	}
	
	@PostMapping(addSubmodelToAas_POST)
	public void addSubmodelToAas(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString();
		String aasId = body.get(AASIDENTIFIER).getAsString();
		String smSemanticId = body.get(SEMANTICIDSUBMODEL).getAsString();
		controller.addSubmodelToAas(aasServerAddress, aasId, smSemanticId); 
	}

	@PostMapping(deleteSubmodel_Post)
	public void deleteSubmodel(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString();
		String aasId = body.get(AASIDENTIFIER).getAsString();
		String smIdShort = body.get(SUBMODELIDSHORT).getAsString();
		controller.deleteSubmodel(aasServerAddress, aasId, smIdShort); 
	}
	
	@PostMapping(editSubmodelElementValues_Post)
	public void editSubmodelElementValues(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString();
		String aasId = body.get(AASIDENTIFIER).getAsString();
		String smIdShort = body.get(SUBMODELIDSHORT).getAsString();
		JsonArray submodelElementsToEdit = body.get(SUBMODELELEMENTSTOEDIT).getAsJsonArray();
		controller.editSubmodelElementValues(aasServerAddress,aasId,smIdShort,submodelElementsToEdit); 
	}
}