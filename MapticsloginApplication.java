package kr.co.maptics.mapticslogin;

import kr.co.maptics.mapticslogin.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
//@EnableConfigurationProperties
@SpringBootApplication
public class MapticsloginApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapticsloginApplication.class, args);
	}

	@Bean
	public EncryptUtil platformEncryptUtil(@Value("${good-soft.platform-secure-key}") String key) {
		return new EncryptUtil(key);
	}


	@Bean
	public EncryptUtil campaignEncryptUtil(@Value("${good-soft.campaign-secure-key}") String key) {
		return new EncryptUtil(key);
	}
}
