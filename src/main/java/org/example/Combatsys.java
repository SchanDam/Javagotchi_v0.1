package org.example;

import org.example.audio.SoundFiles;
import org.example.audio.Sounds;
import org.example.characters.*;

import java.util.Random;

public class Combatsys {
    static Sounds output = new Sounds();
    static Random rng = new Random();
    static String input;

    private Char attacker;
    private Char target;
    private final Char truePlayer;

    public Combatsys(Char attacker, Char target, Char truePlayer) {
        this.attacker = attacker;
        this.target = target;
        this.truePlayer = truePlayer;
    }

    int finalDamage;
    boolean isCritical;
    public static boolean running = true;

    // Kampflogik
    public void fight() throws InterruptedException {
        running = true;
        while (attacker.isAlive() == true && target.isAlive() == true && running == true) {

            playerTurn();
            checkDefeat();
            swapRoles();
            enemyTurn();
            checkDefeat();
            swapRoles();
            nextRoundOption();

            // Spieler besiegt
            if (truePlayer.isAlive() == false) {
                Game.playerDefeat();
            }

            // Gegner besiegt
            if (target.isAlive() == false) {
                Game.enemyDefeat();
            }
        }
    }

    // Schadensberechnung
    private int calcDamage() throws InterruptedException {
        if (attacker.isEscape() == true) {
            attacker.escapeFight();
            return 0;
        }
        int baseDamage = Math.max(0, attacker.getStr() - target.getDef());
        attacker.setMiss(rng.nextInt(100) < 10);
        isCritical = (rng.nextInt(100) < 15);
        finalDamage = isCritical ? baseDamage * 2 : baseDamage;

        if (target.isBlock() == true) {
            finalDamage /= 2;
            target.setBlock(false);
        }
        return finalDamage;
    }

    // Schaden ausführen
    private void attack() throws InterruptedException {
        if (running == false || attacker.isEscape() == true) return;
        finalDamage = calcDamage();
        target.setHp(target.getHp() - finalDamage);
    }

    // Angriff Spieler ⇒ Gegner
    private void playerTurn() throws InterruptedException {

        if (attacker.isMiss() == true) {
            System.out.printf("%nDer Angriff ging daneben.%n");
            output.playSound(SoundFiles.ATTACKMISS.getFileName());
        } else {
            attack();
            System.out.printf("%n%s greift %s an und verursacht %s Schaden. ", attacker.getName(), target.getName(), finalDamage);
            Utils.sleep(100);
            System.out.printf("%s%n", showCritAndHitSound(attacker));
            Utils.sleep(500);
            System.out.printf("Verbleibende Lebenspunkte von %s: %s%n", target.getName(), target.getHp());
            Utils.sleep(1500);
        }

    }

    // Angriff Gegner ⇒ Spieler
    private void enemyTurn() throws InterruptedException {
        if (attacker.isMiss() == true) {
            System.out.printf("%nDer Angriff ging daneben.%n");
            output.playSound(SoundFiles.ATTACKMISS.getFileName());
            swapRoles();
        } else {
            attack();
            System.out.printf("%n%s greift %s an und verursacht %s Schaden. ", attacker.getName(), target.getName(), finalDamage);
            Utils.sleep(100);
            System.out.printf("%s%n", showCritAndHitSound(attacker));
            Utils.sleep(500);
            System.out.printf("Verbleibende Lebenspunkte von %s: %s%n", target.getName(), target.getHp());
            Utils.sleep(1500);
        }
    }

    // Auswahl nächste Runde
    private void nextRoundOption() throws InterruptedException {

        if (attacker.getHp() > 1) {
            System.out.printf("%n**Nächste Runde**%n%n");
            Utils.sleep(100);
            output.playSound(SoundFiles.NEXTROUND.getFileName());
            System.out.println("\"1\" für angreifen");
            System.out.println("\"2\" für blocken");
            System.out.println("\"3\" für flüchten");
            input = Utils.getSoundInput();
            System.out.println();
            Utils.sleep(500);

            switch (input) {
                case "1" -> swapRoles();
                case "2" -> attacker.setBlock(true);
                case "3" -> attacker.escapeFight();
            }
        }
    }

    // defeatcheck
    private void checkDefeat() throws InterruptedException {
        if (target == truePlayer && target.isAlive() == false) {
           Game.playerDefeat();
           running = false;
        }
        else {
            Game.enemyDefeat();
            running = false;
        }
    }

    //Methode zum Tausch der Kampfteilnehmer
    private void swapRoles() {
        Char temp = attacker;
        attacker = target;
        target = temp;
    }

    public String showCritAndHitSound(Char attacker) {
        if (attacker == Game.player && isCritical == true) {
            output.playSound(SoundFiles.CLOUDCRIT.getFileName());
            return "*kritischer Treffer!*";
        } else if (attacker == Game.player) {
            output.playSound("sounds/cloudHit.wav");
            return "";
        } else if (attacker == Game.enemy && isCritical == true) {
            output.playSound("sounds/normalHit.wav");
            return "*kritischer Treffer!*";
        } else if (attacker == Game.enemy) {
            output.playSound("sounds/normalHit.wav");
            return "";
        }
        return "";
    }
}