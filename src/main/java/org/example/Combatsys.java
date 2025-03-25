package org.example;

import org.example.audio.SoundEffects;
import org.example.audio.SoundFiles;
import org.example.characters.*;

import java.util.Random;

public class Combatsys {
    SoundEffects output = new SoundEffects();
    Random rng = new Random();

    String input;
    int finalDamage;
    boolean isCritical;
    public static boolean running = true;

    private Char attacker;
    private Char defender;
    private final Char truePlayer;

    public Combatsys(Char attacker, Char defender, Char truePlayer) {
        this.attacker = attacker;
        this.defender = defender;
        this.truePlayer = truePlayer;
    }

    // Kampflogik
    public void fight() throws InterruptedException {
        running = true;
        while (attacker.isAlive() == true && defender.isAlive() == true && running == true) {

            playerTurn();
            checkDefeat();
            if (running == false) break;
            swapRoles();
            enemyTurn();
            checkDefeat();
            if (running == false) break;
            swapRoles();
            nextRoundOption();
        }
    }

    // Schadensberechnung
    private int calcDamage() throws InterruptedException {
        if (attacker.isEscape() == true) {
            attacker.escapeFight();
            return 0;
        }
        int baseDamage = Math.max(0, attacker.getStr() - defender.getDef());
        isCritical = (rng.nextInt(100) < 15);
        finalDamage = isCritical ? baseDamage * 2 : baseDamage;

        if (defender.isBlock() == true) {
            finalDamage /= 2;
        }
        return finalDamage;
    }

    // Schaden ausführen
    private void attack() throws InterruptedException {
        if (running == false || attacker.isEscape() == true) return;
        finalDamage = calcDamage();
        defender.setHp(defender.getHp() - finalDamage);
    }

    // Angriff Spieler ⇒ Gegner
    private void playerTurn() throws InterruptedException {
        attacker.setMiss(rng.nextInt(100) < 10);
        if (attacker.isBlock() == true) {
            System.out.printf("%n%s blockt!%n", attacker.getName());
            Utils.sleep(500);
            return;
        }
        if (attacker.isMiss() == true) {
            isCritical = false;
            System.out.printf("%nDer Angriff von %s ging daneben.%n", attacker.getName());
            attacker.getMissSound();
            Utils.sleep(1000);
            attacker.setMiss(false);
        } else {
            attack();
            System.out.printf("%n%s greift %s an und verursacht %s Schaden. ", attacker.getName(), defender.getName(), finalDamage);
            Utils.sleep(50);
            if (isCritical == true) {
                System.out.print(attacker.getCritSound());
            } else {
                attacker.getHitSound();
            }
            Utils.sleep(500);
            System.out.printf("%nVerbleibende Lebenspunkte von %s: %s%n", defender.getName(), defender.getHp());
            Utils.sleep(1000);
        }
    }

    // Angriff Gegner ⇒ Spieler
    private void enemyTurn() throws InterruptedException {
        attacker.setMiss(rng.nextInt(100) < 10);
        if (attacker.isMiss() == true) {
            isCritical = false;
            System.out.printf("%nDer Angriff von %s ging daneben.%n", attacker.getName());
            attacker.getMissSound();
            Utils.sleep(1000);
            attacker.setMiss(false);
        } else {
            attack();
            if (defender.isBlock() == true) {
                defender.setBlock(false);
            }
            System.out.printf("%n%s greift %s an und verursacht %s Schaden. ", attacker.getName(), defender.getName(), finalDamage);
            Utils.sleep(50);
            if (isCritical == true) {
                System.out.print(attacker.getCritSound());
            } else {
                attacker.getHitSound();
            }
            Utils.sleep(500);
            System.out.printf("%nVerbleibende Lebenspunkte von %s: %s%n", defender.getName(), defender.getHp());
            Utils.sleep(1000);
        }
    }

    // Auswahl nächste Runde
    private void nextRoundOption() throws InterruptedException {

        if (attacker != truePlayer) return;
        if (attacker.getHp() > 1) {
            System.out.printf("%n**Nächste Runde**%n%n");
            Utils.sleep(100);
            output.playSoundAsync(SoundFiles.NEXTROUND.getFileName());
            System.out.println("\"1\" für angreifen");
            System.out.println("\"2\" für blocken");
            System.out.println("\"3\" für flüchten");
            input = Utils.getSoundInput();
            System.out.println();
            Utils.sleep(500);

            switch (input) {
                case "1" -> {
                }
                case "2" -> truePlayer.setBlock(true);
                case "3" -> attacker.escapeFight();
            }
        }
    }

    // defeatcheck
    private void checkDefeat() throws InterruptedException {
        if (defender == truePlayer && defender.isAlive() == false) {
            Game.playerDefeat();
            running = false;
        } else if (defender != truePlayer && defender.isAlive() == false) {
            Game.enemyDefeat();
            running = false;
        }
    }

    //Methode zum Tausch der Kampfteilnehmer (Hinweis von CHATGPT)
    private void swapRoles() {
        Char temp = attacker;
        attacker = defender;
        defender = temp;
    }
}