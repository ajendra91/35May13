package hello;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EmployeeController {

	@Autowired
	public RestTemplate restTemplate;

	public String token;
	public String url = "http://localhost:9090/app/user/getEmployeesList";
	public String url2 = "http://localhost:9090/app/demo";
	public String access_token_url = "http://localhost:9090/app/oauth/token";

	ResponseEntity<String> response = null;
	ResponseEntity<String> em = null;

	@RequestMapping(value = "/showEmployees", method = RequestMethod.GET)
	public String showEmployees(@RequestParam("code") String code) throws JsonProcessingException, IOException {


		System.out.println("Authorization Ccode------" + code);



		String credentials = "ci:sc";
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> request = new HttpEntity<String>(headers);


		access_token_url += "?code=" + code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=http://localhost:9091/showEmployees";

		response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

		System.out.println("Access Token Response ---------" + response.getBody());

		// Get the Access Token From the recieved JSON response
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody());
		token = node.path("access_token").asText();

		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		HttpEntity entity = new HttpEntity(headers1);

		System.out.println(restTemplate.exchange(url2, HttpMethod.GET, entity, String.class).getBody());

		HttpHeaders headers2 =new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity entity2 =new HttpEntity(headers2);
		return restTemplate.exchange(url, HttpMethod.GET,entity2,String.class).getBody();
	}


}