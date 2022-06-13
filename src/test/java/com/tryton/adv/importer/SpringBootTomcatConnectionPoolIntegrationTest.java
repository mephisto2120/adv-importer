package com.tryton.adv.importer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class SpringBootTomcatConnectionPoolIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void givenTomcatConnectionPoolInstanceWhenCheckedPoolClassName() {
        assertThat(dataSource.getClass().getName())
                .isEqualTo("org.apache.tomcat.jdbc.pool.DataSource");
    }
}
