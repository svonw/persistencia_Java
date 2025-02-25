package locator;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.stream.Stream;

import com.google.gson.Gson;

import model.Pais;

public class StreamLocator {
	
	public static Stream<Pais> streamPaises(){
		Gson gson=new Gson();
		try{
			String url="https://restcountries.com/v2/all";
			HttpRequest request=HttpRequest.newBuilder()
					.uri(URI.create(url))
					.GET()
					.build();
			HttpClient client=HttpClient.newBuilder()
					.version(Version.HTTP_1_1)
					.build();
			HttpResponse<String> response=client.send(request, BodyHandlers.ofString());
			return Arrays.stream(gson.fromJson(response.body(), Pais[].class));
		}catch(Exception ex) {
			ex.printStackTrace();
			return Stream.empty();
		}
	}
}
