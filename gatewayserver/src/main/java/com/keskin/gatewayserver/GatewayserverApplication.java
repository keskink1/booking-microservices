package com.keskin.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	/**
	 * Route configuration:
	 * - /api/bookings/** -> BOOKINGSERVICE
	 * - /api/notifications/** -> NOTIFICATIONSERVICE
	 *
	 * Features per route:
	 *  - Circuit breaker with fallback URI
	 *  - Retry only for GET requests (3 retries + backoff)
	 *  - IP-based Redis rate limiter
	 */
	@Bean
	public RouteLocator keskinkRouteConfig(RouteLocatorBuilder builder) {
		return builder.routes()

				.route("booking-route", r -> r
						.path("/api/bookings/**")
						.filters(f -> f
								// optional: rewrite path so backend receives trimmed path
								.rewritePath("/api/bookings/(?<segment>.*)", "/${segment}")
								.circuitBreaker(c -> c
										.setName("bookingCircuitBreaker")
										.setFallbackUri("forward:/bookingServiceFallback"))
								.retry(retryConfig -> retryConfig
										.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(200), Duration.ofMillis(1000), 2, true))
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(ipKeyResolver()))
						)
						.uri("lb://BOOKINGSERVICE"))

				.route("notification-route", r -> r
						.path("/api/notifications/**")
						.filters(f -> f
								.rewritePath("/api/notifications/(?<segment>.*)", "/${segment}")
								.circuitBreaker(c -> c
										.setName("notificationCircuitBreaker")
										.setFallbackUri("forward:/notificationServiceFallback"))
								.retry(retryConfig -> retryConfig
										.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(200), Duration.ofMillis(1000), 2, true))
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(ipKeyResolver()))
						)
						.uri("lb://NOTIFICATIONSERVICE"))

				.build();
	}

	/**
	 * Default Resilience4J configuration used by circuit breakers.
	 * Time limiter set to 4 seconds.
	 */
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom()
						.timeoutDuration(Duration.ofSeconds(4))
						.build())
				.build());
	}

	/**
	 * RedisRateLimiter: replenishRate = 5 tokens/sec, burstCapacity = 10.
	 * (requestedTokens param omitted - default 1)
	 */
	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(5, 10);
	}

	/**
	 * IP-based KeyResolver for rate limiting.
	 * Uses remote address' host string; falls back to "unknown" if absent.
	 */
	@Bean
	public KeyResolver ipKeyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest()
						.getRemoteAddress())
				.map(addr -> addr.getAddress().getHostAddress())
				.defaultIfEmpty("unknown");
	}
}
