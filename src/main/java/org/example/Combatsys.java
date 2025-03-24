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

    public Combatsys(Char attacker, Char target) {
        this.attacker = attacker;
        this.target = target;
    }

    int finalDamage;
    static boolean isCritical;
    public static boolean running = true;

    // Kampflogik
    public void fight() throws InterruptedException {
        running = true;
        while (attacker.getHp() > 1 && target.getHp() > 1 && running == true) {

            // Angriff Spieler ⇒ Gegner
            if (attacker.isBlock() == false) {
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
            if (target.getHp() < 1) {
                System.out.printf("%n%s wurde besiegt!%n%n", target.getName());
                output.playSound(SoundFiles.ENEMYDEADSHORT.getFileName());
                Thread.sleep(200);
                output.playSoundAsync(SoundFiles.GETCOIN.getFileName());
                System.out.println("Du hast 10 Gold und 100 Punkte erhalten!");
                Game.player.setGold(Game.player.getGold() + 10);
                Game.player.setPunkte(Game.player.getPunkte() + 100);
            }
            swapRoles();
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
                swapRoles();
            }
            nextRoundOption();
        }
    }

    // Schadensberechnung
    public int calcDamage() throws InterruptedException {
        if (attacker.isEscape() == true) {
            attacker.escapeFight();
            return 0;
        }
        int baseDamage = Math.max(0, attacker.getStr() - target.getDef());
        attacker.setMiss() = (rng.nextInt(100) < 10);
        isCritical = (rng.nextInt(100) < 15);
        finalDamage = isCritical ? baseDamage * 2 : baseDamage;

        if (target.isBlock() == true) {
            finalDamage /= 2;
            target.setBlock(false);
        }
        return finalDamage;
    }

    // Schaden ausführen
    public void attack() throws InterruptedException {
        if (running == false || attacker.isEscape() == true) return;
        finalDamage = calcDamage();
        target.setHp(target.getHp() - finalDamage);
    }

    // Auswahl nächste Runde
    public void nextRoundOption() throws InterruptedException {

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
                case "3" -> {
                    attacker.escapeFight();
                    if (running == false) {
                    }
                }
            }
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