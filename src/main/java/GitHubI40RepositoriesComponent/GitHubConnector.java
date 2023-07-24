package GitHubI40RepositoriesComponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public abstract class GitHubConnector {

	private static final String HTTPGETPUTCOMMAND = "GET";
	private static final int ConnectTimeout = 60000;
	private static final int ReadTimeout = 60000;

	private static final String urlPart1 = "https://raw.githubusercontent.com/thcologne-gart";

	protected static final char SLASH = 47;

	public GitHubConnector() {
		// TODO Auto-generated constructor stub
	}

	protected JsonObject getJsonObjectFromGitHubRepo(String repositoryName,String permanentGitHubId,String filename) {
		ArrayList<String> urlPartsList = new ArrayList<String>(); 
		urlPartsList.add(repositoryName);
		urlPartsList.add(permanentGitHubId);
		urlPartsList.add(filename);
		String url = buildUrl(urlPartsList);
		String jsonStr = readObjectFromGitHub(url).toString();
		return convertStringToJson(jsonStr);
	}

	private String buildUrl(ArrayList<String> urlPartsList) {
		String url = urlPart1; 
		for (String string : urlPartsList) {
			url = url + SLASH + string; 
		}
		return url; 
	}

	private StringBuffer readObjectFromGitHub(String urlStr) {
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(HTTPGETPUTCOMMAND);
			connection.setDoOutput(true);
			connection.setConnectTimeout(ConnectTimeout);
			connection.setReadTimeout(ReadTimeout);
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			} else {
				System.out.println("GET request did not work.");
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private JsonObject convertStringToJson(String jsonString) {
		JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
		return jsonObject;
	}
}