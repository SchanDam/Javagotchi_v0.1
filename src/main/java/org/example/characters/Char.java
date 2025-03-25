package org.example.characters;

import org.example.audio.SoundFiles;
import org.example.audio.SoundEffects;

public abstract class Char {

    protected int str;
    protected int def;
    protected int hp;
    protected int maxHp;
    protected String name;
    protected boolean miss;
    protected boolean alive = true;

    protected SoundEffects output = new SoundEffects();

    public Char(String name, int getStr, int getDef, int getHp) {
        this.str = getStr;
        this.def = getDef;
        this.hp = getHp;
        this.maxHp = hp;
        this.name = name;
    }

    public void setStr(int str) {
        this.str = Math.min(255, str);
    }
    public int getStr() {
        return str;
    }

    public void setDef(int def) {
        this.def = Math.min(255, def);
    }
    public int getDef() {
        return def;
    }

    public void setHp(int hp) {
        this.hp = Math.min(9999, hp);
    }
    public int getHp() {
        return Math.max(0, hp);
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public boolean isMiss() {
        return miss;
    }
    public void setMiss(boolean miss) {
        this.miss = miss;
    }

    public boolean isBlock() {
        return false;
    }
    public void setBlock(boolean value) {
    }

    public void setEscape(boolean escape) {
    }
    public boolean isEscape() {
        return false;
    }

    public boolean isAlive() {
        return getHp() > 0;
    }

    public void getHitSound() {
        output.playSoundAsync(SoundFiles.NORMALHIT.getFileName());
    }
    public String getCritSound() {
        output.playSoundAsync(SoundFiles.NORMALCRIT.getFileName());
        return "*kritischer Treffer!*";
    }
    public void getMissSound() {
        output.playSoundAsync(SoundFiles.ATTACKMISS.getFileName());
    }

    public void escapeFight() throws InterruptedException {
    }
}