package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    public void whenPairWitComment() {
        String path = "./data/pair_with_comment";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username")).isEqualTo("postgres");
    }

    @Test
    public void whenPairWithNullFormat() throws IllegalArgumentException {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("surname")).isEqualTo(null);
    }


    @Test
    void whenException() throws IllegalArgumentException {
        String path = "app.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNotValues() throws IllegalArgumentException {
        String path = "app.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNotKey() throws IllegalArgumentException {
        String path = "app.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNotAllNull() throws IllegalArgumentException {
        String path = "app.properties";
        Config config = new Config(path);
        assertThat(config.value(null)).isEqualTo(null);
        assertThatThrownBy(config::load)
        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNullFormatValue() throws IllegalArgumentException {
        String path = "app.properties";
        Config config = new Config(path);
        assertThat(config.value("Anton")).isEqualTo(null);
        assertThat(config.value("username")).isEqualTo(null);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }
}