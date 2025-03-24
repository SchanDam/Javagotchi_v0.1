package org.example;

import org.example.audio.SoundFiles;
import org.example.audio.Sounds;
import org.example.characters.*;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static boolean running;
    static Scanner sc = new Scanner(System.in);
    static Random rng = new Random();
    static Sounds output = new Sounds();
    static String input;

    static Player player = new Player();
    static Enemies enemy;

    // Einleitung
    public static void introduce() throws InterruptedException {

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
                player.setHunger(255);
                //mainMenu();
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
                System.out.println("Eine gute Wahl! Das Bustersword wird jeden Gegner zerschmettern!");
                Utils.sleep(1000);
                ASCII.cloud();
                Utils.sleep(1500);
                System.out.println("Cloud hat gesteigerte Attribute:");
                player.setStr(20);
                player.setDef(16);
                player.setHp(314);
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
    public static void mainMenu() throws InterruptedException {
        System.out.printf("\n Was möchtest du tun?%n%n");
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
    public static void printAttributes() throws InterruptedException {
        System.out.printf("%nMeine Attribute sind:%n");
        Utils.sleep(500);
        System.out.printf("Stärke: %d%n", player.getStr());
        Utils.sleep(200);
        System.out.printf("Verteidigung: %d%n", player.getDef());
        Utils.sleep(200);
        System.out.printf("Lebenspunkte: %d%n", player.getHp());
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
    public static void eat() throws InterruptedException {
        if (Game.player.getHunger() < 10) {
            Game.player.setHunger(Game.player.getHunger() + 1);
            System.out.printf("%nDanke für das Essen!%n");
            Utils.sleep(200);
            System.out.printf("Meine Sättigung ist: %d%n", Game.player.getHunger());
        } else {
            System.out.printf("Ich bin satt%n%n");
        }
    }

    // Training
    public static void training() throws InterruptedException {
        System.out.printf("%nIch muss meine Fähigkeiten mit Schwert und Schild trainieren!%n");
        Utils.sleep(500);
        System.out.print("Ein Jahr vergeht");
        Utils.dotText();
        player.setAge(player.getAge() + 1);

        int strIncrease = rng.nextInt(1, 5);         // 1 bis 4
        int defIncrease = rng.nextInt(1, 4);         // 1 bis 3
        int hpIncrease = rng.nextInt(15, 26);        // 15 bis 25
        int hungerDecrease = rng.nextInt(-4, 0);     // -1 bis -4

        player.setStr(player.getStr() + strIncrease);
        player.setDef(player.getDef() + defIncrease);
        player.setHp(player.getHp() + hpIncrease);
        player.setHunger(player.getHunger() + hungerDecrease);

        player.setPunkte(player.getPunkte() + strIncrease + defIncrease + hpIncrease);

        Utils.sleep(1000);
        System.out.printf("%nIch bin um ein Jahr gealtert. Ich bin nun %d Jahre alt.%n", player.getAge());
        Utils.sleep(500);
        System.out.printf("%nIch bin stärker geworden!%n");
        Utils.sleep(200);
        System.out.printf("Stärke: + %d = %d%n", strIncrease, player.getStr());
        System.out.printf("Verteidigung: + %d = %d%n", defIncrease, player.getDef());
        System.out.printf("Lebenspunkte: + %d = %d%n", hpIncrease, player.getHp());
        System.out.printf("Ich bin hungrig! Neuer Sättigungswert: %d%n", player.getHunger());
    }

    // fight
    public static void fight() throws InterruptedException {
        System.out.printf("%nGegen welchen Gegner soll %s kämpfen?%n", player.getName());
        //Utils.sleep(800);
        System.out.println("\"1\" für Skelett");
        //Utils.sleep(300);
        System.out.println("\"2\" für Oger");
        //Utils.sleep(300);
        System.out.println("\"3\" für Drache");
        //Utils.sleep(300);
        System.out.printf("\"q\" zurück in Hauptmenü%n");
        input = Utils.getSoundInput();

        switch (input) {
            case "1" -> {
                running = true;
                player.setEscape(false);
                enemy = new Skelett();
                enemy.setHp(enemy.getMaxHp());

                System.out.printf("%n%s ausgewählt, starte Kampf", enemy.getName());
                Utils.dotText();
                System.out.printf("%nKampf beginnt gegen %s\n", enemy.getName());
                output.playSound(SoundFiles.STARTFIGHT.getFileName());
                Utils.sleep(500);
                Combatsys comsys = new Combatsys(player, enemy);
                comsys.fight();

            }
            case "2" -> enemy = new Oger();
            case "3" -> enemy = new Drache();
            case "Sephiroth" -> enemy = new Sephiroth();
            default -> {
                Utils.sleep(500);
                System.out.printf("%nungültige Taste%n");
                output.playSound(SoundFiles.INPUTFAIL.getFileName());
                Utils.sleep(300);
            }
        }
    }

    // heilen
    public static void heal() throws InterruptedException {
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
                    output.playSound(SoundFiles.HEAL.getFileName());
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
                output.playSound(SoundFiles.INPUTFAIL.getFileName());
                Utils.sleep(300);
            }
        }
    }

    // Programmneustart
    public static void reset() throws InterruptedException {
        System.out.printf("%nDas Spiel wird neu gestartet, resette Attribute");
        Utils.dotText();
        Utils.reset();
        System.out.println();
    }

    // Debugmenü
    public static void debugMenu() throws InterruptedException {

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
            System.out.println("\"alle\" für alle Werte (außer Alter)");
            Utils.sleep(200);
            System.out.printf("\"q\" für Hauptmenü%n%n");

            input = Utils.getSoundInput();

            switch (input) {
                case "a":
                    System.out.printf("\nAuf welchen Wert soll das Alter geändert werden?%n");
                    int newAge = sc.nextInt();
                    player.setAge(newAge);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nAlter wurde auf %d gesetzt%n", player.getAge());
                    Utils.sleep(500);
                    break;
                case "h":
                    System.out.printf("%nAuf welchen Wert soll das Sättigungslevel geändert werden?%n");
                    int newHunger = sc.nextInt();
                    player.setHunger(newHunger);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nHunger wurde auf %d gesetzt%n", player.getHunger());
                    Utils.sleep(500);
                    break;
                case "s":
                    System.out.printf("%nAuf welchen Wert soll die Stärke geändert werden?%n");
                    int newStr = sc.nextInt();
                    player.setStr(newStr);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nStärke wurde auf %d gesetzt%n", player.getStr());
                    Utils.sleep(500);
                    break;
                case "v":
                    System.out.printf("%nAuf welchen Wert soll die Verteidigung geändert werden?%n");
                    int newDef = sc.nextInt();
                    player.setDef(newDef);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nVerteidigung wurde auf %d gesetzt%n", player.getDef());
                    Utils.sleep(500);
                    break;
                case "l":
                    System.out.printf("%nAuf welchen Wert soll die Lebenspunkte geändert werden?%n");
                    int newHp = sc.nextInt();
                    player.setHp(newHp);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nLebenspunkte wurden auf %d gesetzt%n", player.getHp());
                    Utils.sleep(500);
                    break;
                case "g":
                    System.out.printf("%nWie viele Goldmünzen möchtest du erhalten?%n");
                    int newGold = sc.nextInt();
                    player.setGold(newGold);
                    sc.nextLine();
                    Utils.sleep(200);
                    System.out.printf("%nDu hast %d Goldmünzen erhalten%n", player.getGold());
                    Utils.sleep(500);
                    break;
                case "alle":
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
                    break;
                case "q":
                    System.out.printf("%nIns Hauptmenü zurückkehren, bitte warten");
                    Utils.dotText();
                    System.out.println();
                    Utils.sleep(500);
                    return;
                default:
                    System.out.println("ungültige Taste");
                    Utils.sleep(500);
                    break;
            }
        }
    }

}