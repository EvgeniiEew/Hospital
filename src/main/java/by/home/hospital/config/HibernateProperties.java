package by.home.hospital.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "jpaprops")
@Data
public class HibernateProperties {

    private Map<String, String> jpaProperties;

}