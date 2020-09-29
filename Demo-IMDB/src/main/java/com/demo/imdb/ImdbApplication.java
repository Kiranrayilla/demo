package com.demo.imdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.demo.imdb.model.IS;
import com.demo.imdb.model.ISDetail;
import com.demo.imdb.service.ImdbService;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class ImdbApplication {

	private static final Logger log = LoggerFactory.getLogger(ImdbApplication.class);

	@Value("${is.port}")
	private String isPort;
	@Value("${is.host}")
	private String isHost;
	@Value("${is.resource}")
	private String isResource;

	public static void main(String[] args) {
		SpringApplication.run(ImdbApplication.class, args);
	}

	@Autowired
	private ImdbService isService;

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Demo-Imdb Details API").version(appVersion)
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			ISDetail[] quote = restTemplate.getForObject("http://"+isHost+":"+isPort+isResource, ISDetail[].class);
			//"http://localhost:8081/api/v1/dbenrich", ISDetail[].class);
			log.info(quote.toString());
			log.info("Info value is : "+quote);
			IS is = new IS();
			for(ISDetail isd: quote) {
				is = new IS();
				is.setLs(isd.getLs());
				is.setMsisdn(isd.getMsisdn());
				is.setCreatedAt(isd.getCreatedAt());
				is.setUpdatedAt(isd.getUpdatedAt());
				isService.createImdb(is);	
			}

		};
	}

}
