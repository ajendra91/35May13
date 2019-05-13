package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringBootFormHandingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFormHandingApplication.class, args);
	}

	@RequestMapping("/auth/facebook")
	public String hello(@RequestParam String code){
		return code;
	}

	@Bean
	public RestTemplate hel(){
		return new RestTemplate();
	}

}