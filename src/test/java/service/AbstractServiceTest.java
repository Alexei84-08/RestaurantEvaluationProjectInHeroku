package service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = {"/spring/spring-app.xml", "/spring/spring-db.xml",})
@ExtendWith(TimingExtension.class)
@Sql(scripts = "classpath:/dbH2/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract class AbstractServiceTest {
}
