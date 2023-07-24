package GitHubI40RepositoriesComponent;

import com.google.gson.JsonObject;

public class SubmodelTemplatesRepoConnector extends GitHubConnector{

	private static final String repositoryName = "GART-I4.0Repositorium"; 
	private static final String permanentGitHubIdThKoeln = "d29277c4091259277b203a7eb814c18a7707010e";
	private static final String permanentGitHubIdIDTA = "ceb71fa32175084f3ada0eb10c982359d71df9e6";
	private static final String urlPart1 = "SubmodelTemplates";

	private static final String thKoelnSnippet = "https://th-koeln.de/gart";
	private static final String idtaSnippet = "https://admin-shell.io/idta"; 

	public SubmodelTemplatesRepoConnector() {
		// TODO Auto-generated constructor stub
	}

	public JsonObject getSubmodelTemplate(String submodelSemanticId) {
		if (submodelSemanticId.contains(thKoelnSnippet)) {
			return getSubmodelTemplateFromTHKoelnSubmodels(submodelSemanticId);
		}
		else if (submodelSemanticId.contains(idtaSnippet)) {
			return getSubmodelTemplateFromIDTASubmodels(submodelSemanticId);
		}
		else {
			System.out.println(this.getClass() + " - Implementierung fehlt für: " + submodelSemanticId);
			return null;
		}
	}

	private JsonObject getSubmodelTemplateFromIDTASubmodels(String submodelSemanticId) {
		String urlPart2 = "idta";
		String[] splittedString = submodelSemanticId.split(idtaSnippet);
		String[] splittedString2 = splittedString[1].split("/"); 
		String smIdShort = splittedString2[1];
		String verNumber = splittedString2[2];
		String revNumber = splittedString2[3];
		String filename = splittedString2[1] + ".json";
		String urlPart = urlPart1 + SLASH + urlPart2 + SLASH + smIdShort + SLASH + verNumber + SLASH + revNumber + SLASH + filename;
		return getJsonObjectFromGitHubRepo(repositoryName,permanentGitHubIdIDTA,urlPart);
	}

	private JsonObject getSubmodelTemplateFromTHKoelnSubmodels(String submodelSemanticId) {
		String urlPart2 = "th-koeln";
		String[] splittedString = submodelSemanticId.split(thKoelnSnippet);
		String[] splittedString2 = splittedString[1].split("/"); 
		String smIdShort = splittedString2[2];
		String verNumber = splittedString2[3];
		String revNumber = splittedString2[4];
		String filename = splittedString2[2] + ".json";
		String urlPart = urlPart1 + SLASH + urlPart2 + SLASH + smIdShort + SLASH + verNumber + SLASH + revNumber + SLASH + filename;
		return getJsonObjectFromGitHubRepo(repositoryName,permanentGitHubIdThKoeln,urlPart);
	}
}
