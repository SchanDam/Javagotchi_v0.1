package org.example;
import org.example.audio.SoundFiles;
import org.example.audio.Sounds;
import org.example.characters.*;

import java.util.Random;

public class Combatsys {
    static Sounds output = new Sounds();
    static Random rng = new Random();

    private Char attacker;
    private Char target;

    public Combatsys(Char attacker, Char target) {
        this.attacker = attacker;
        this.target = target;
    }

    private int finalDamage;
    private static boolean isCritical;
    private boolean running = true;

    public int getFinalDamage() {
        return this.finalDamage;
    }

    public static void startFight() {

    }

    public int calcDamage() throws InterruptedException {
        if (attacker.isEscape() == true) {
            attacker.escapeFight();
            return 0;
        }
        int baseDamage = Math.max(0, attacker.getStr() - target.getDef());
//      miss = (rng.nextInt(100) < 10);
        isCritical = (rng.nextInt(100) < 15);
        finalDamage = isCritical ? baseDamage * 2 : baseDamage;

        if (attacker.isBlock() == true) {
            finalDamage /= 2;
            attacker.setBlock(false);
        }
        return finalDamage;
    }

    public void attack() throws InterruptedException {
        if (running == false || attacker.isEscape() == true) return;
        finalDamage = calcDamage();
        target.setHp(target.getHp() - finalDamage);
    }

    public static String showCritAndHitSound(Char attacker) {
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