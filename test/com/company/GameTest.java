package com.company;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void Given_InformationsToCreateWarrior_When_CreateCharacter_Then_WarriorIsCorrectlyCreated() {
        System.setIn(new ByteArrayInputStream(String.format("1%n10%n5%n3%n2%n").getBytes()));
        Game game = new Game();
        Character character = game.createCharacter("Test");
        assertEquals("Test", character.name);
        assertEquals(10, character.level);
        assertEquals(5, character.strength);
        assertEquals(3, character.agility);
        assertEquals(2, character.intelligence);
        assertTrue(character instanceof Warrior);
    }

    @Test
    public void Given_GoodIntValue_When_AskForIntValue_Then_GetSameIntValue() {
        System.setIn(new ByteArrayInputStream(String.format("42%n").getBytes()));
        Game game = new Game();
        int value = game.askForIntValue("Test ?", 35, 50);
        assertEquals(42, value);
    }

    @Test
    public void Given_BadIntValuesAndGoodIntValue_When_AskForIntValue_Then_GetGoodIntValue() {
        System.setIn(new ByteArrayInputStream(String.format("84%n21%n42%n").getBytes()));
        Game game = new Game();
        int value = game.askForIntValue("Test ?", 35, 50);
        assertEquals(42, value);
    }

    @Test
    public void Given_TwoCharacterFighting_When_IsPlayerTurn_Then_PlayerTargetedLosesVitality() {
        System.setIn(new ByteArrayInputStream(String.format("1%n").getBytes()));
        Game game = new Game();
        Character character = new Warrior("Test", 10, 10, 0, 0);
        Character target = new Warrior("Target", 10, 10, 0, 0);
        game.playerTurn(character, target);
        assertEquals(50 - 10, target.getVitality());
    }

    @Test
    public void Given_FullGamePlayed_When_GameIsRun_Then_Player2IsDead() {
        // Player 1 : Warrior level 10 with 10 strength, 0 agility and 0 intelligence
        // Player 2 : Rogue level 3 with 0 strength, 3 agility and 0 intelligence
        // Player 1 basic attack
        // Player 2 basic attack
        // Player 1 basic attack and kill Player 2
        System.setIn(new ByteArrayInputStream(String.format("1%n10%n10%n0%n0%n2%n3%n0%n3%n0%n1%n1%n1%n").getBytes()));
        Game game = new Game();
        game.run();
        assertEquals(50 - 3, game.player1.getVitality());
        assertEquals(15 - 10 - 10, game.player2.getVitality());
    }
}