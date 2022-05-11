package com.zombietank.time;

import com.zombietank.time.config.CustomJsonConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

@Import(CustomJsonConfig.class)
@WebFluxTest(TimeController.class)
class TimeControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @TestConfiguration
    public static class ClockConfiguration {
        @Bean
        Clock clock() {
            return Clock.fixed(Instant.EPOCH, ZoneOffset.UTC);
        }
    }

    @Test
    void currentTimeIsProduced(@Autowired Clock clock) {
        var result = webTestClient.get().uri("/time/current")
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeResponse.class)
                .returnResult();

        assertThat(result.getResponseBody())
                .isNotNull()
                .extracting(TimeResponse::time)
                .isEqualTo(OffsetDateTime.now(clock));
    }
}
