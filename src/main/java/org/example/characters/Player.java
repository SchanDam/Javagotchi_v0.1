package org.example.characters;
import org.example.Combatsys;
import org.example.Utils;
import org.example.audio.SoundFiles;
import org.example.audio.Sounds;
import java.util.Random;
import org.example.Game;

public class Player extends Char {

    private int age = 1;
    private int hunger = 5;
    private int gold;
    private int punkte = 0;
    private boolean block = false;
    private boolean escape = false;

    Random rng = new Random();
    Sounds output = new Sounds();

    public Player() {
        super("", 1, 1, 100);
    }


    @Override public void getHitSound() {
        output.playSound(SoundFiles.CLOUDHIT.getFileName());
    }
    @Override public void getCritSound() {
        output.playSound(SoundFiles.CLOUDCRIT.getFileName());
        System.out.println("*kritischer Treffer!*");
    }
    @Override public void getMissSound() {
        output.playSound(SoundFiles.ATTACKMISS.getFileName());
    }

    public void setAge(int age) {
        this.age = Math.max(1, age);
    }
    public int getAge() {
        return age;
    }

    public void setHunger(int hunger) {
        this.hunger = Math.min(255, hunger);
    }
    public int getHunger() {
        return hunger;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    public int getGold() {
        return gold;
    }

    public void setPunkte(int punkte) {
        this.punkte = Math.max(0, punkte);
    }
    public int getPunkte() {
        return punkte;
    }

    @Override public boolean isBlock() {
        return block;
    }
    @Override public void setBlock(boolean block) {
        this.block = true;
    }


    @Override public void setEscape(boolean escape) {
        this.escape = escape;
    }
    @Override public boolean isEscape() {
        return escape;
    }

    @Override public void escapeFight() {
        boolean escapeChance = (rng.nextInt(100) < 80);

        if (escapeChance == true) {
            System.out.println("Du bist geflüchtet");
            output.playSound(SoundFiles.ESCAPE.getFileName());
            escape = true;
            Combatsys.running = false;
        } else {
            System.out.println("Du konntest nicht flüchten");
            Utils.sleep(200);
            output.playSound("sounds/inputFail.wav");
            escape = false;
        }
    }
}