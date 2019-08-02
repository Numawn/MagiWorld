package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagusTest {
    @Test
    public void Given_MagusLevel10_When_UseBasicAttack_Then_InflictGoodDamagesValue() {
        Magus magus = new Magus("Test", 10, 0, 0, 10);
        Magus enemy = new Magus("Enemy", 10, 0, 0, 10);
        magus.basicAttack(enemy);
        assertEquals(50 - 10, enemy.getVitality());
    }

    @Test
    public void Given_MagusLevel10With20Vitality_When_UseSpecialAttack_Then_EarnGoodVitalityValue() {
        Magus magus = new Magus("Test", 10, 0, 0, 10);
        Magus enemy = new Magus("Enemy", 10, 0, 0, 10);
        magus.inflictDamages(30);
        magus.specialAttack(enemy);
        assertEquals(50 - 30 + 20, magus.vitality);
    }

    @Test
    public void Given_MagusLevel10With40Vitality_When_UseSpecialAttack_Then_VitalityGoToMax() {
        Magus magus = new Magus("Test", 10, 0, 0, 10);
        Magus enemy = new Magus("Enemy", 10, 0, 0, 10);
        magus.inflictDamages(10);
        magus.specialAttack(enemy);
        assertEquals(50, magus.vitality);
    }
}