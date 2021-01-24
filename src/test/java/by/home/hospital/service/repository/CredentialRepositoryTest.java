package by.home.hospital.service.repository;

import by.home.hospital.domain.Credential;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = {CredentialRepositoryTest.EmbeddedPostgresContextConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CredentialRepositoryTest {
    private static final String EMAIL = "JJJ@mail.ru";


    @Configuration
    @EntityScan(basePackageClasses = Credential.class)
    @EnableJpaRepositories(basePackageClasses = CredentialsJpaRepository.class)
    public static class EmbeddedPostgresContextConfiguration {

        @Bean
        @Primary
        public DataSource embeddedPG() throws IOException {
            return EmbeddedPostgres.start().getPostgresDatabase();
        }

    }

    @Autowired
    private CredentialsJpaRepository credentialsJpaRepository;

    @Test
    public void testRepoPresent() {
       assertNotNull(credentialsJpaRepository);
    }


    @BeforeEach
    public void setUpDB() {
        Credential credential = new Credential();
        credential.setEmail(EMAIL);
        credential.setPassword("password");
        credentialsJpaRepository.save(credential);
    }

    @Test
    public void testCreate_readByUserName() {
        // WHEN
        Optional<Credential> findByEmail = credentialsJpaRepository.findByEmail(EMAIL);
        // THEN
        assertNotNull(findByEmail);
        assertEquals("JJJ@mail.ru", findByEmail.get().getEmail());
    }


}
