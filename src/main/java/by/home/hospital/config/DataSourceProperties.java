package by.home.hospital.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "datasource")
@Data
public class DataSourceProperties {

    private String driverClassName;
    private String password;
    private String user;
    private String url;

}