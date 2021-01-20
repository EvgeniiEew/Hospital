package by.home.hospital.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "email.server")
public class EmailProperties {
    String host;
    String port;
    String domainHost;
    String username;
    String password;
}
