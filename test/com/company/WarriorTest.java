package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    @Test
    public void Given_WarriorLevel10_When_UseBasicAttack_Then_InflictGoodDamagesValue() {
        Warrior warrior = new Warrior("Test", 10, 10, 0, 0);
        Warrior enemy = new Warrior("Enemy", 10, 10, 0, 0);
        warrior.basicAttack(enemy);
        assertEquals(50 - 10, enemy.getVitality());
    }

    @Test
    public void Given_WarriorLevel10_When_UseSpecialAttack_Then_InflictGoodDamagesValue() {
        Warrior warrior = new Warrior("Test", 10, 10, 0, 0);
        Warrior enemy = new Warrior("Enemy", 10, 10, 0, 0);
        warrior.specialAttack(enemy);
        assertEquals(50 - 20, enemy.getVitality());
    }

    @Test
    public void Given_WarriorLevel10_When_UseSpecialAttack_Then_InflictGoodDamagesValueOnHimself() {
        Warrior warrior = new Warrior("Test", 10, 10, 0, 0);
        Warrior enemy = new Warrior("Enemy", 10, 10, 0, 0);
        warrior.specialAttack(enemy);
        assertEquals(50 - 5, warrior.getVitality());
    }
}