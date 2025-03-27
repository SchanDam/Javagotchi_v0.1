package org.example;

import org.example.audio.SoundFiles;
import org.example.audio.SoundEffects;
import org.example.audio.MusicManager;
import org.example.characters.*;
import org.example.utils.Utils;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static boolean running;
    Scanner sc = new Scanner(System.in);
    Random rng = new Random();
    SoundEffects output = new SoundEffects();
    MusicManager music = new MusicManager();

    String input;
    int hungerDecrease;
    final String ITALIC = "\033[3m";
    final String STOP = "\033[0m";

    private final Player player = new Player();
    private Enemies enemy;

    // Einleitung
    public void introduce() throws InterruptedException {

        System.out.println("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");

        music.play(SoundFiles.INTRO.getFileName());
        System.out.printf("%nDas Ziel des Spiels ist es, den Drachen zu besiegen.%n");
        Utils.sleep(1000);
        System.out.println("Wie ist mein Name?");
        player.setName(Utils.getSoundInput());
        Utils.sleep(1000);

        // Godmode
        switch (player.getName()) {
            case "Godmode" -> {
                Utils.sleep(500);
                System.out.printf("%nWoher wusstest du, dass es einen Godmode gibt?%n");
                Utils.sleep(1000);
                System.out.print("Godmode wird aktiviert, bitte warten");
                Utils.dotText();
                System.out.printf("%nStärke, Verteidigung und Hunger auf 255 gesetzt, Lebenspunkte auf 9999 gesetzt.%n%n");
                Utils.sleep(1000);
                ASCII.javaRitter();
                Utils.sleep(1500);
                System.out.printf("%nHallo! Ich bin ein Javagotchiritter und Ich heiße %s. Du musst mich nicht mehr trainieren, ich bin bereits ein GOTT!%n%n%n", player.getName());

                player.setStr(255);
                player.setDef(255);
                player.setHp(9999);
                player.setMaxHp(9999);
                player.setHunger(255);
                music.stop();
                mainMenu();
            }

            // Twigolu
            case "Twigolu" -> {
                System.out.printf("%nOh, ich heiße wohl wie deine Katzen?!%n%n");
                Utils.sleep(1000);
                ASCII.gatos();
                Utils.sleep(1500);
                System.out.printf("%nHallo! Ich bin ein Javagotchiritter und Ich heiße %s. Trainiere mich und koche für mich, damit ich stärker werde!%n%n%n", player.getName());
                mainMenu();
            }

            // Cyberpunk
            case "V" -> {
                System.out.printf("Cyberpunk?%n%n");
                Utils.sleep(1000);
                ASCII.cyberpunk();
                Utils.sleep(1500);
                System.out.printf("%nHallo! Ich bin ein Javagotchiritter und Ich heiße %s. Trainiere mich und koche für mich, damit ich stärker werde!%n%n%n", player.getName());
                mainMenu();
            }

            // Cloud
            case "Cloud" -> {
                System.out.printf("%nEine gute Wahl! Das Bustersword wird jeden Gegner zerschmettern!");
                Utils.sleep(2000);
                ASCII.cloud();
                Utils.sleep(1500);
                System.out.println("Cloud hat gesteigerte Attribute");
                player.setStr(20);
                player.setDef(16);
                player.setHp(314);
                player.setMaxHp(314);
                mainMenu();
            }
            default -> {
                System.out.printf("%nHallo! Ich bin ein Javagotchiritter und Ich heiße %s. Trainiere mich und koche für mich, damit ich stärker werde!%n%n%n", player.getName());
                Utils.sleep(1000);
                ASCII.javaRitter();
                Utils.sleep(1500);
                mainMenu();
            }
        }
    }

    // Hauptmenü
    public void mainMenu() {
        music.stop();
        music.play(SoundFiles.MENU.getFileName());
        System.out.printf("%n Was möchtest du tun?%n%n");
        Utils.sleep(1000);
        System.out.println("\"a\" für Attribute prüfen");
        Utils.sleep(200);
        System.out.println("\"e\" für Essen kochen");
        Utils.sleep(200);
        System.out.println("\"t\" für trainieren");
        Utils.sleep(200);
        System.out.println("\"k\" für kämpfen");
        Utils.sleep(200);
        System.out.println("\"h\" für Lebenspunkte heilen");
        Utils.sleep(200);
        System.out.println("\"p\" für Punktestand abfragen");
        Utils.sleep(200);
        System.out.println("\"n\" für Programmneustart");
        Utils.sleep(200);
        System.out.printf("\"q\" für Programm beenden%n");
    }

    // Attribute abfragen
    public void printAttributes() {
        System.out.printf("%nMeine Attribute sind:%n");
        Utils.sleep(500);
        System.out.printf("Stärke: %d%n", player.getStr());
        Utils.sleep(200);
        System.out.printf("Verteidigung: %d%n", player.getDef());
        Utils.sleep(200);
        System.out.printf("Lebenspunkte aktuell: %d%n", player.getHp());
        Utils.sleep(200);
        System.out.printf("Lebenspunkte maximal: %d%n", player.getMaxHp());
        Utils.sleep(200);
        System.out.printf("Sättigungslevel: %d%n", player.getHunger());
        Utils.sleep(200);
        System.out.printf("Alter: %d%n", player.getAge());
        Utils.sleep(200);
        System.out.printf("Mein Name ist: %s%n", player.getName());
        Utils.sleep(200);
        System.out.printf("Ich habe %d Gold!%n", player.getGold());
        Utils.sleep(200);
    }

    // essen
    public void eat() {
        if (player.getHunger() < 10) {
            player.setHunger(player.getHunger() + 1);
            System.out.printf("%nDanke für das Essen!%n");
            Utils.sleep(200);
            System.out.printf("Meine Sättigung ist: %d%n", player.getHunger());
        } else {
            System.out.printf("Ich bin satt%n%n");
        }
    }

    // Training
    public void training() throws InterruptedException {
        System.out.printf("%nIch muss meine Fähigkeiten mit Schwert und Schild trainieren!%n");
        Utils.sleep(500);
        System.out.print("Ein Jahr vergeht");
        Utils.dotText();
        player.setAge(player.getAge() + 1);

        int strIncrease = rng.nextInt(1, 5);         // 1 bis 4
        int defIncrease = rng.nextInt(1, 4);         // 1 bis 3
        int hpIncrease = rng.nextInt(15, 26);        // 15 bis 25
        hungerDecrease = rng.nextInt(-4, 0);     // -1 bis -4

        player.setStr(player.getStr() + strIncrease);
        player.setDef(player.getDef() + defIncrease);
        player.setHp(player.getHp() + hpIncrease);
        player.setMaxHp(player.getMaxHp() + hpIncrease);
        player.setHunger(player.getHunger() + hungerDecrease);

        player.setPunkte(player.getPunkte() + strIncrease + defIncrease + hpIncrease);

        trackAge();
        Utils.sleep(1000);
        System.out.printf("%nIch bin um ein Jahr gealtert. Ich bin nun %d Jahre alt.%n", player.getAge());
        Utils.sleep(500);
        System.out.printf("%nIch bin stärker geworden!%n");
        Utils.sleep(200);
        System.out.printf("Stärke: + %d = %d%n", strIncrease, player.getStr());
        System.out.printf("Verteidigung: + %d = %d%n", defIncrease, player.getDef());
        System.out.printf("Lebenspunkte: + %d = %d%n", hpIncrease, player.getHp());
        System.out.printf("Ich bin hungrig! Neuer Sättigungswert: %d%n", player.getHunger());
        trackHunger();
    }

    // fight
    public void fight() throws InterruptedException {
        while (true) {

            System.out.printf("%nGegen welchen Gegner soll %s kämpfen?%n%n", player.getName());
            Utils.sleep(800);
            System.out.println("\"1\" für Skelett");
            Utils.sleep(300);
            System.out.println("\"2\" für Oger");
            Utils.sleep(300);
            System.out.println("\"3\" für Drache");
            Utils.sleep(300);
            System.out.printf("\"q\" zurück in Hauptmenü%n");
            input = Utils.getSoundInput();

                switch (input) {
                    case "1" -> {
                        enemy = new Skelett();
                        Combatsys comSys = getComSys();
                        music.play(SoundFiles.FIGHTNORMAL.getFileName());
                        Utils.sleep(2000);
                        comSys.fight();
                        return;
                    }
                    case "2" -> {
                        enemy = new Oger();
                        Combatsys comSys = getComSys();
                        music.play(SoundFiles.FIGHTBOSS.getFileName());
                        Utils.sleep(2000);
                        comSys.fight();
                        return;
                    }
                    case "3" -> {
                        enemy = new Drache();
                        Combatsys comSys = getComSys();
                        music.play(SoundFiles.FIGHTSUPERBOSS.getFileName());
                        Utils.sleep(2000);
                        comSys.fight();
                        return;
                    }
                    case "Sephiroth" -> {
                        if (player.getName().equals("Cloud")) {
                            System.out.printf("%n%s“Leih mir deine Kraft, Cloud! Lehn´ dich mit mir gegen das Schicksal auf.”%s%n", ITALIC, STOP);
                            Utils.sleep(500);
                        } else {
                            System.out.printf("%n%s“Ich bin der wahre Erbe dieses Planeten.”%s%n", ITALIC, STOP);
                            Utils.sleep(500);
                        }
                        enemy = new Sephiroth();
                        Combatsys comSys = getComSys();
                        music.play(SoundFiles.FIGHTSEPH.getFileName());
                        Utils.sleep(2000);
                        comSys.fight();
                        return;
                    }
                    case "q" -> {
                        System.out.printf("%nIns Hauptmenü zurückkehren, bitte warten");
                        Utils.dotText();
                        System.out.println();
                        Utils.sleep(500);
                        return;
                    }
                    default -> {
                        Utils.sleep(500);
                        System.out.printf("%nungültige Taste%n");
                        output.playSoundAsync(SoundFiles.INPUTFAIL.getFileName());
                        Utils.sleep(300);
                    }
                }

        }
    }
    private Combatsys getComSys() throws InterruptedException {
        running = true;
        player.setEscape(false);
        System.out.printf("%n%s ausgewählt, starte Kampf", enemy.getName());
        Utils.dotText();
        System.out.printf("%nKampf beginnt gegen %s%n", enemy.getName());
        music.stop();
        output.playSoundAsync(SoundFiles.STARTFIGHT.getFileName());
        Combatsys comSys = new Combatsys(player, enemy, player, this);
        return comSys;
    }
    public void playerDefeat() throws InterruptedException {
        music.stop();
        output.playSoundAsync(SoundFiles.LOSE.getFileName());
        System.out.printf("%n%s wurde besiegt!%n", player.getName());
        Utils.sleep(7000);
        hungerDecrease = rng.nextInt(-5, -1);
        System.out.printf("%nDein Sättigungslevel ist um %d gesunken.%n", hungerDecrease);
        player.setHunger(player.getHunger() + hungerDecrease);
        Utils.sleep(2000);
        music.stop();
        trackHunger();
    }
    public void enemyDefeat() throws InterruptedException {
        enemy.getReward(this);
    }

    // heilen
    public void heal() throws InterruptedException {
        while (true) {

            Utils.sleep(200);
            System.out.printf("%nEin Heiltrank kostet 5 Goldstücke, soll %s einen Trank trinken? (\"ja\" oder \"nein\")%n", player.getName());
            input = Utils.getSoundInput();

            if (input.equals("ja")) {
                if (player.getHp() == player.getMaxHp() && player.getGold() > 4) {
                    Utils.sleep(200);
                    System.out.printf("%nDu hast bereits volle Lebenspunkte.%n%n");
                    Utils.laugh();
                    return;
                } else if (player.getGold() > 4) {
                    player.setHp(player.getMaxHp());
                    player.setGold(player.getGold() - 5);
                    Utils.sleep(200);
                    System.out.printf("%nDu hast dich vollständig geheilt!%n");
                    output.playSoundAsync(SoundFiles.HEAL.getFileName());
                    Utils.sleep(500);
                    return;
                } else {
                    Utils.sleep(200);
                    System.out.printf("%nDu hast nicht genug Gold zum heilen!%n");
                    Utils.sleep(500);
                    Utils.laugh();
                    return;
                }
            } else if (input.equals("nein")) {
                System.out.printf("%nWer nicht will, der hat schon.%n%n");
                Utils.laugh();
                return;
            } else {
                Utils.sleep(500);
                System.out.printf("%nungültige Taste%n");
                output.playSoundAsync(SoundFiles.INPUTFAIL.getFileName());
                Utils.sleep(300);
            }
        }
    }

    // Programmneustart
    public void reset() throws InterruptedException {
        System.out.printf("%nDas Spiel wird neu gestartet, resette Attribute");
        Utils.dotText();
        getPlayer().setStr(1);
        getPlayer().setDef(1);
        getPlayer().setHp(100);
        getPlayer().setGold(0);
        getPlayer().setAge(1);
        getPlayer().setPunkte(0);
        getPlayer().setHunger(5);
        System.out.println();
    }

    // Debugmenü
    public void debugMenu() throws InterruptedException {

        while (true) {
            System.out.printf("%nWelchen Wert möchtest du bearbeiten?%n%n");
            Utils.sleep(500);
            System.out.println("\"a\" für Alter");
            Utils.sleep(200);
            System.out.println("\"h\" für Hunger");
            Utils.sleep(200);
            System.out.println("\"s\" für Stärke");
            Utils.sleep(200);
            System.out.println("\"v\" für Verteidigung");
            Utils.sleep(200);
            System.out.println("\"l\" für Lebenspunkte");
            Utils.sleep(200);
            System.out.println("\"g\" für Goldmünzen");
            Utils.sleep(200);
            System.out.println("\"n\" für Name");
            Utils.sleep(200);
            System.out.println("\"alle\" für alle Werte (außer Alter)");
            Utils.sleep(200);
            System.out.printf("\"q\" für Hauptmenü%n%n");

            input = Utils.getSoundInput();

            switch (input) {
                case "a" -> {
                    System.out.printf("\nAuf welchen Wert soll das Alter geändert werden?%n");
                    int newAge = sc.nextInt();
                    player.setAge(newAge);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nAlter wurde auf %d gesetzt%n", player.getAge());
                    Utils.sleep(500);
                }
                case "h" -> {
                    System.out.printf("%nAuf welchen Wert soll das Sättigungslevel geändert werden?%n");
                    int newHunger = sc.nextInt();
                    player.setHunger(newHunger);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nHunger wurde auf %d gesetzt%n", player.getHunger());
                    Utils.sleep(500);
                }
                case "s" -> {
                    System.out.printf("%nAuf welchen Wert soll die Stärke geändert werden?%n");
                    int newStr = sc.nextInt();
                    player.setStr(newStr);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nStärke wurde auf %d gesetzt%n", player.getStr());
                    Utils.sleep(500);
                }
                case "v" -> {
                    System.out.printf("%nAuf welchen Wert soll die Verteidigung geändert werden?%n");
                    int newDef = sc.nextInt();
                    player.setDef(newDef);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nVerteidigung wurde auf %d gesetzt%n", player.getDef());
                    Utils.sleep(500);
                }
                case "l" -> {
                    System.out.printf("%nAuf welchen Wert soll die Lebenspunkte geändert werden?%n");
                    int newHp = sc.nextInt();
                    player.setHp(newHp);
                    player.setMaxHp(newHp);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nLebenspunkte wurden auf %d gesetzt%n", player.getHp());
                    Utils.sleep(500);
                }
                case "g" -> {
                    System.out.printf("%nWie viele Goldmünzen möchtest du erhalten?%n");
                    int newGold = sc.nextInt();
                    player.setGold(newGold);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nDu hast %d Goldmünzen erhalten%n", player.getGold());
                    Utils.sleep(500);
                }
                case "n" -> {
                    System.out.printf("%nAuf welchen Name soll der Name gesetzt werden?%n");
                    String newName = sc.nextLine();
                    player.setName(newName);
                    System.out.printf("%nName wurde auf %s gesetzt%n", player.getName());
                }
                case "alle" -> {
                    System.out.printf("%nAuf welchen Wert sollen alle Attribute geändert werden?%n");
                    int newVariable = sc.nextInt();
                    sc.nextLine();
                    player.setStr(newVariable);
                    player.setDef(newVariable);
                    player.setHp(newVariable);
                    player.setGold(newVariable);
                    player.setHunger(newVariable);
                    Utils.sleep(200);
                    System.out.printf("%nAlle Werte wurden auf %d gesetzt%n", newVariable);
                    Utils.sleep(500);
                }
                case "q" -> {
                    System.out.printf("%nIns Hauptmenü zurückkehren, bitte warten");
                    Utils.dotText();
                    System.out.println();
                    Utils.sleep(500);
                    return;
                }
                default -> {
                    Utils.sleep(500);
                    System.out.printf("%nungültige Taste%n");
                    output.playSoundAsync(SoundFiles.INPUTFAIL.getFileName());
                    Utils.sleep(300);
                }
            }
        }
    }

    // Programm beenden
    public void quit() {
        System.out.printf("%nTschüss, bis bald!%n");
        Utils.sleep(200);
        System.out.printf("Du hast %s Punkte erreicht!%n", player.getPunkte());
        Utils.sleep(500);
    }

    // Track Hunger
    public void trackHunger() throws InterruptedException {
        if (player.getHunger() < 1) {
            Utils.sleep(1000);
            System.out.print("Ich bin verhungert");
            Utils.dotText();
            System.out.printf("%nDas Spiel ist vorbei.%n");
            Utils.sleep(500);
            ASCII.ritterTot();
            Utils.sleep(1000);
            System.out.printf("Du hast %s Punkte erreicht!", player.getPunkte());
            running = false;
        }
    }

    // Track Alter
    public void trackAge() {
        if (player.getAge() > 19) {
            Utils.sleep(1000);
            System.out.printf("%nIch bin zu alt um zu kämpfen, ich werde mich nun niederlassen.%n%n");
            Utils.sleep(1000);
            System.out.println("Du hast das Spiel beendet!");
            Utils.sleep(200);
            System.out.printf("Du hast %s Punkte erreicht!", player.getPunkte());
        }
    }

    // Zeige Punkte
    public void showPunkte() {
        System.out.printf("%nDein aktueller Punktestand ist %s.%n", player.getPunkte());
    }

    // Getter und Setter von Charakteren
    public Player getPlayer() {
        return player;
    }
    public Enemies getEnemies() {
        return enemy;
    }
    public MusicManager getMusic() {
        return music;
    }
    public void setEnemy(Enemies enemy) {
        this.enemy = enemy;
    }
}