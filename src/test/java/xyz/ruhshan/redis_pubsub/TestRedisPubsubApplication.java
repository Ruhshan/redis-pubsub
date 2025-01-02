package xyz.ruhshan.redis_pubsub;

import org.springframework.boot.SpringApplication;

public class TestRedisPubsubApplication {

	public static void main(String[] args) {
		SpringApplication.from(RedisPubsubApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
