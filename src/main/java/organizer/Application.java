package organizer;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import organizer.converter.CsvHttpMessageConverter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		final RestTemplate result = restTemplateBuilder.build();
		result.setRequestFactory(new HttpComponentsClientHttpRequestFactory()); //workaround for patch HTTP method
		result.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
		return result;
	}

	@Bean
	public HttpMessageConverters customConverters() {
		return new HttpMessageConverters(true, Arrays.asList(new CsvHttpMessageConverter()));
	}

	@Bean
	public CommandLineRunner runDemo(RestTemplate restTemplate) {
		return args -> Demo.run(restTemplate);
	}

}
