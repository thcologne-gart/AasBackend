package GARTApiV1AasComponent;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IAasRestApi {
	
	static final String getAllAasIdentifier_Post = "/getAllAasIdentifier";
	static final String getAasByIdentifier_POST = "/getAasByIdentifier";
	static final String getAasIdShortByIdentifier_POST = "/getAasIdShortByIdentifier";
	static final String getAllAasIdentifierByAasType_POST = "/getAllAasIdentifierByAasType";
	static final String getAllAasIdentifierByAasTypeNEW_POST = "/getAllAasIdentifierByAasTypeNEW";
	static final String createAasByAasType_POST = "/createAasByAasType";
	static final String deleteAasByIdentifier_POST = "/deleteAasByIdentifier";
	
	@PostMapping(path = getAllAasIdentifier_Post, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAllAasIdentifier(String jsonObjectStr) ;
	
	@PostMapping(path = getAasByIdentifier_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<?, ?> getAasByIdentifier(@RequestBody String jsonObjectStr);
	
	@PostMapping(path = getAllAasIdentifierByAasType_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getallAasIdentifierByAasType(@RequestBody String jsonObjectStr);
	
	@PostMapping(deleteAasByIdentifier_POST)
	public void deleteAasByIdentifier(@RequestBody String jsonObjectStr);

	@PostMapping(createAasByAasType_POST)
	public String createAasByAasType(@RequestBody String jsonObjectStr);
	
	@PostMapping(path = getAasIdShortByIdentifier_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<?> getAasIdShortByIdentifier(@RequestBody String jsonObjectStr);
	
	
}
