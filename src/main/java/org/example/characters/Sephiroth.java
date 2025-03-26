package org.example.characters;

import org.example.audio.SoundFiles;

public class Sephiroth extends Enemies {

    private int playerGetGold = 50;
    private int playerGetPoints = 500;

    public Sephiroth() {
        super("Sephiroth", 177, 167, 1);
    } //3264

    public void getHitSound() {
        output.playSoundAsync(SoundFiles.SEPH.getFileName());
    }
    public String getCritSound() {
        output.playSoundAsync(SoundFiles.SEPH.getFileName());
        return "*kritischer Treffer!*";
    }
}

