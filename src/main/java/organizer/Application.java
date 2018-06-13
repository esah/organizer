package organizer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		final RestTemplate result = restTemplateBuilder.build();
		result.setRequestFactory(new HttpComponentsClientHttpRequestFactory()); //workaround for patch HTTP method
		return result;
	}

	@Bean
	public CommandLineRunner runDemo(RestTemplate restTemplate) {
		return args -> Demo.run(restTemplate);
	}

}
