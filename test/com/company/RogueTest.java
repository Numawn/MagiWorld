package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RogueTest {
    @Test
    public void Given_RogueLevel10_When_UseBasicAttack_Then_InflictGoodDamagesValue() {
        Rogue rogue = new Rogue("Test", 10, 0, 10, 0);
        Rogue enemy = new Rogue("Enemy", 10, 0, 10, 0);
        rogue.basicAttack(enemy);
        assertEquals(50 - 10, enemy.getVitality());
    }

    @Test
    public void Given_RogueLevel10_When_UseSpecialAttack_Then_EarnGoodAgilityValue() {
        Rogue rogue = new Rogue("Test", 10, 0, 10, 0);
        Rogue enemy = new Rogue("Enemy", 10, 0, 10, 0);
        rogue.specialAttack(enemy);
        assertEquals(10 + 5, rogue.agility);
    }
}