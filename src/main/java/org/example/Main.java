package org.example;

import org.example.audio.SoundFiles;
import org.example.audio.SoundEffects;
import org.example.utils.SpeedController;
import org.example.utils.Utils;

public class Main {

    static String input;
    static SoundEffects output = new SoundEffects();

    public static void main(String[] args) throws Exception {
        new SpeedController().start(); // Chatgpt - siehe Klasse SpeedController
        Game game = new Game();

        Utils.skipDelays = true; //true ignoriert alle sleeps, false führt sie aus
        boolean running = true;

        game.introduce();

        while (running) {
            System.out.printf("%n? für Menüanzeige%n");
            Utils.sleep(200);
            System.out.printf("Erwarte Eingabe:%n");
            input = Utils.getSoundInput();


            // Attribute abfragen
            if (input.equals("a")) {
                game.printAttributes();
            }

            // Essen
            else if (input.equals("e")) {
                game.eat();
            }

            // trainieren
            else if (input.equals("t")) {
                game.training();
            }

            // kämpfen
            else if (input.equals("k")) {
                game.fight();
            }

            // heilen
            else if (input.equals("h")) {
                game.heal();
            }

            // Punktestand abfragen
            else if (input.equals("p")) {
                game.showPunkte();
            }

            // Neustart
            else if (input.equals("n")) {
                game.reset();
                continue;
            }

            // Programm beenden
            else if (input.equals("q")) {
                game.quit();
            }

            // Debugmenü
            else if (input.equals("debug")) {
                System.out.printf("%nLade Debugmenü, bitte warten");
                Utils.dotText();
                game.debugMenu();
            }

            // Auflisten
            else if (input.equals("?")) {
                game.mainMenu();
            }

            // falsche Taste
            else {
                Utils.sleep(500);
                System.out.printf("%nungültige Taste%n");
                output.playSoundAsync(SoundFiles.INPUTFAIL.getFileName());
                Utils.sleep(300);
            }
        }
    }
}