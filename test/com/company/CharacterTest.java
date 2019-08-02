package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    @Test
    public void Given_BadCharacteristics_When_NewCharacterCreated_Then_ThrowException() {
        assertThrows(BadCharacteristicsSetted.class, () -> new Character("Test",10, 10, 1, 0) {
            @Override
            public void basicAttack(Character enemy) {

            }

            @Override
            public void specialAttack(Character enemy) {

            }
        });
    }

    @Test
    public void Given_OneCharacter_When_CharacterSuddenDamages_Then_CharacterLoseVitality() {
        Character warrior = new Character("Test", 10, 10, 0, 0) {
            @Override
            public void basicAttack(Character enemy) {

            }

            @Override
            public void specialAttack(Character enemy) {

            }
        };
        warrior.inflictDamages(10);
        assertEquals(50 - 10,  warrior.getVitality());
    }
}