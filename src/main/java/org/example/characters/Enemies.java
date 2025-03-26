package org.example.characters;

import org.example.Game;
import org.example.Utils;
import org.example.audio.SoundFiles;

public class Enemies extends Char {

    private int rewardGold;
    private int rewardPoints;
    final String ITALIC = "\033[3m";
    final String STOP = "\033[0m";

    public Enemies(String name, int getStr, int getDef, int getHp) {
        super(name, getStr, getDef, getHp);
    }

    public void getReward(Game game) throws InterruptedException {
        game.getMusic().stop();
        if (this.name.equals("Sephiroth")) {
            System.out.printf("%n%s“Ich werde nie eine Erinnerung sein", ITALIC);
            Utils.sleep(500);
            Utils.dotText();
            System.out.printf("”%s", STOP);
        } else {
            System.out.printf("%n%s wurde besiegt!%n%n", this.getName());
        }
        output.playSoundAsync(SoundFiles.ENEMYDEADSHORT.getFileName());
        Utils.sleep(800);
        game.getMusic().play(SoundFiles.FANFARE.getFileName());
        Utils.sleep(7000);
        output.playSoundAsync(SoundFiles.GETCOIN.getFileName());
        System.out.printf("%nDu hast %d Gold und %d Punkte erhalten!%n", this.rewardGold, this.rewardPoints);
        Utils.sleep(2000);
        game.getPlayer().setGold(game.getPlayer().getGold() + this.rewardGold);
        game.getPlayer().setPunkte(game.getPlayer().getPunkte() + this.rewardPoints);
        game.getMusic().stop();
    }
}