package configuration.javabased.quickstart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигуратор бинов.
 * Альтернатива использования xml.
 */
@Configuration
public class WelcomeConfig {
    @Bean
    public Welcome welcome() {
        return new Welcome();
    }
}
