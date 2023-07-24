package GitHubI40RepositoriesComponent;

import com.google.gson.JsonObject;

public class AasTypesRepoConnector extends GitHubConnector {

	private static final String repositoryName = "GART-I4.0Repositorium"; 
	private static final String permanentGitHubId = "d29277c4091259277b203a7eb814c18a7707010e";
	private static final String filename = "AasTypeRepository.json"; 
	
	
	public AasTypesRepoConnector() {
		// TODO Auto-generated constructor stub
	}

	public JsonObject getAasRepository() {
		return getJsonObjectFromGitHubRepo(repositoryName,permanentGitHubId,filename);
	}
}
