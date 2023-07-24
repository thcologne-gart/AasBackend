package GARTApiV1AasComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(path = "GART/api/v1/AAS")
public class AasRestApi implements IAasRestApi{
	
	private static final String getAllAasIdentifier_Post = "/getAllAasIdentifier";
	private static final String getAasByIdentifier_POST = "/getAasByIdentifier";
	private static final String getAasIdShortByIdentifier_POST = "/getAasIdShortByIdentifier";
	private static final String getAllAasIdentifierByAasType_POST = "/getAllAasIdentifierByAasType";
	private static final String createAasByAasType_POST = "/createAasByAasType";
	private static final String deleteAasByIdentifier_POST = "/deleteAasByIdentifier";
	private static final String helloServer_GET = "/helloServer";
	
	private static final String REGISTRYADDRESS = "registryAddress";
	private static final String AASSERVERADDRESS = "aasServerAddress";
	private static final String AASIDENTIFIER = "aasIdentifier";
	private static final String SEMANTICIDAASTYPE = "semanticIdAasType";
	private static final String IDSHORT = "idShort"; 
	
	private AasController controller = new AasController(); 

	public AasRestApi() {
		
	}
	
	@GetMapping(path = helloServer_GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> helloServer(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("helloServer");
		return list;
	}
	
	@PostMapping(path = getAllAasIdentifier_Post, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAllAasIdentifier(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String registryAddress = body.get(REGISTRYADDRESS).getAsString();
		return controller.getAllAasIdentifier(registryAddress);
	}
	
	@PostMapping(path = getAasByIdentifier_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<?, ?> getAasByIdentifier(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String identifier = body.get(AASIDENTIFIER).getAsString(); 
		return controller.getAasByIdentifier(aasServerAddress,identifier); 
	}
	
	@PostMapping(path = getAllAasIdentifierByAasType_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getallAasIdentifierByAasType(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String registryAddress = body.get(REGISTRYADDRESS).getAsString();
		String semanticIdAasType = body.get(SEMANTICIDAASTYPE).getAsString();
		return controller.getallAasIdentifierByAasType(aasServerAddress,registryAddress,semanticIdAasType);
	}
	
	@PostMapping(deleteAasByIdentifier_POST)
	public void deleteAasByIdentifier(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String aasIdentifier = body.get(AASIDENTIFIER).getAsString(); 
		controller.deleteAasByIdentifier(aasServerAddress,aasIdentifier); 
	}
	
	@PostMapping(createAasByAasType_POST)
	public String createAasByAasType(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String semanticIdAasType = body.get(SEMANTICIDAASTYPE).getAsString();
		String idShort = body.get(IDSHORT).getAsString();
		return controller.createAasByAasType(aasServerAddress, idShort, semanticIdAasType);
	}
	
	@PostMapping(path = getAasIdShortByIdentifier_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<?> getAasIdShortByIdentifier(@RequestBody String jsonObjectStr) {
		JsonObject body = new Gson().fromJson(jsonObjectStr, JsonObject.class);
		String aasServerAddress = body.get(AASSERVERADDRESS).getAsString(); 
		String identifier = body.get(AASIDENTIFIER).getAsString(); 
		return controller.getAasIdShortByIdentifier(aasServerAddress,identifier); 
	}
}