package configuration.javabased.dependencies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигуратор.
 * Альтернатива использования xml.
 */
@Configuration
public class GetDateConfig {
    @Bean
    public DateProvider provider() {
        return new DateProvider();
    }
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public DateEditor editor() {
        return new DateEditor(provider());
    }
}
