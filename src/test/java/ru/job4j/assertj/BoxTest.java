package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknow() {
        Box box = new Box(11, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isThisNegativew() {
        Box box = new Box(2, -1);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
        int name2 = box.getNumberOfVertices();
        assertThat(name2).isNegative();
    }

    @Test
    void isThisCube() {
        Box box = new Box(6, 1);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
        int name2 = box.getNumberOfVertices();
        assertThat(name2).isEqualTo(6);
    }

    @Test
    void isThisExist() {
        Box box = new Box(4, 3);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isThisNotExist() {
        Box box = new Box(0, 0);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void isThisGetAreaTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.whatsThis()).isEqualTo("Tetrahedron");
        assertThat(box.getArea()).isEqualTo(173.2,  withPrecision(0.006d));
    }
}