package org.example;
import org.example.audio.SoundEffects;
import java.util.Random;
import java.util.Scanner;

public class Utils {
    private static final Random rng = new Random();
    private static final Scanner sc = new Scanner(System.in);
    private static final SoundEffects output = new SoundEffects();

    public static void sout(String text) {
        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);

                // Zufällige Geschwindigkeit zwischen 30 und 120 ms für Retro-Effekt
                Thread.sleep(rng.nextInt(40, 120));
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Methode für formatierte Texte
    public static void souf(String text, Object... args) {
        sout(String.format(text, args));
    }

    // Input Sound
    public static String getSoundInput() {
        String input = sc.nextLine();
        output.playSoundAsync("sounds/input.wav");
        return input;
    }

    // dots verzögert ausgeben
    public static void dotText() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            Thread.sleep(500);
        }
    }

    // clear screen
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // laugh
    public static void laugh() throws InterruptedException {
        output.playSoundAsync("sounds/laugh.wav");
        for (int i = 0; i < 8; i++) {
            System.out.println("he!");
            Thread.sleep(300);
        }
    }

    // Skip sleep (CHATGPT)
    public static boolean skipDelays = false;
    public static void sleep(long millis) {
        if (!skipDelays) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Hp Balken CHATGPT
    public static void printHpBar(int hp, int maxHp) {
        int barLength = 10;
        int filledLength = (int) ((hp / (double) maxHp) * barLength);

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            bar.append(i < filledLength ? '█' : '░');
        }
        bar.append("] ");
        bar.append(hp).append("/").append(maxHp);

        System.out.println(bar);
    }
}