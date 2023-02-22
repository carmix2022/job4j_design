package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .containsIgnoringCase("ph")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(9, 10);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .isEqualTo("Unknown object")
                .doesNotContain("Sphere");
    }

    @Test
    void whenSphereThenVertices0() {
        Box box = new Box(0, 10);
        int count = box.getNumberOfVertices();
        assertThat(count).isGreaterThan(-1)
                .isLessThan(3)
                .isZero();
    }

    @Test
    void whenUnknownThenVerticesMinusOne() {
        Box box = new Box(9, 10);
        int count = box.getNumberOfVertices();
        assertThat(count).isNotZero()
                .isNegative()
                .isEqualTo(-1);
    }

    @Test
    void whenUnknownThenNotExist() {
        Box box = new Box(9, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse()
                .isEqualTo(false);
    }

    @Test
    void whenCubeThenExists() {
        Box box = new Box(8, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue()
                .isEqualTo(true);
    }

    @Test
    void whenUnknownThenArea0() {
        Box box = new Box(9, 10);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(0)
                .isZero();
    }

    @Test
    void whenSphereThenAreaIsCloseTo12dot56() {
        Box box = new Box(0, 1);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(12.57d, withPrecision(0.004d))
                .isCloseTo(12.56d, withPrecision(0.01d))
                .isGreaterThan(12.56d)
                .isLessThan(12.57d);
    }
}