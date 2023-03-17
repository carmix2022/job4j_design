package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }

    @Test
    void test2() {
        String path = "data/app1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect=");
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver=1");
        assertThatThrownBy(() -> config.value("#hibernate.connection.username"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void whenStringIsOnlySignEquals() {
        String path = "data/app2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenStringWithoutKey() {
        String path = "data/app3.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenStringWithoutValue() {
        String path = "data/app4.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenStringWithoutSighEquals() {
        String path = "data/app5.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }
}