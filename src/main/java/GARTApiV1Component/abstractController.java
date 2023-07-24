package GARTApiV1Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import BaSyxGateComponent.BaSyxAasServerGate;
import BaSyxGateComponent.BaSyxRegistryGate;
import GitHubI40RepositoriesComponent.AasTypesRepoConnector;
import SubmodelGeneratorComponent.SubmodelFactory;

public abstract class abstractController {

	protected BaSyxRegistryGate registryGate = new BaSyxRegistryGate(); 
	protected BaSyxAasServerGate aasServerGate = new BaSyxAasServerGate(); 
	protected Gson gson = new Gson();
	protected AasTypesRepoConnector reqHandlerRepo = new AasTypesRepoConnector();
	protected JsonObject repository = reqHandlerRepo.getAasRepository(); 
	protected SubmodelFactory smFactory = new SubmodelFactory();

	protected List<String> convertJsonArrayToList(JsonArray jsonArray) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			String element = jsonArray.get(i).getAsString();
			list.add(element);
		}
		return list;
	}

	protected Map<?, ?> convertJsonObjectStringToMap(String jsonObjectString) {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, String> map = gson.fromJson(jsonObjectString, type);
		return map;
	}

}