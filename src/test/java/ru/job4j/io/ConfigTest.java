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
    void whenIOException() throws IllegalArgumentException {
        String path = "app.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenPairWithNullFormat() throws IllegalArgumentException {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("surname")).isEqualTo(null);
    }
}