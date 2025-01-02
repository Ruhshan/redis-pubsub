package xyz.ruhshan.redis_pubsub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class RedisPubsubApplicationTests {

	@Test
	void contextLoads() {
	}

}
