package com.example.Client7Batch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SecurityBatch batch(){
		return new SecurityBatch();
	}

	class SecurityBatch implements CommandLineRunner{


		@Override
		public void run(String... args) throws Exception {

			ResourceOwnerPasswordResourceDetails resource= new ResourceOwnerPasswordResourceDetails();
			resource.setAccessTokenUri("http://localhost:9090/app/oauth/token");
			resource.setClientId("ci");
			resource.setClientSecret("sc");
			resource.setGrantType("password");
			resource.setUsername("admin");
			resource.setPassword("admin");
			resource.setClientAuthenticationScheme(AuthenticationScheme.header);
			OAuth2RestTemplate template =new OAuth2RestTemplate(resource);
			//String s= template.getForObject("http://localhost:9090/app/demo", String.class);
			//String s= template.getForObject("http://localhost:0507/", String.class);
			String s= template.getForObject("http://localhost:9090/app/principal", String.class);
			System.out.println(s);
		}
	}

}
