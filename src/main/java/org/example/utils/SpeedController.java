// Klasse kommt komplett von CHATGPT
package org.example.utils;

import java.io.IOException;

public class SpeedController extends Thread {
    private static volatile boolean speedMode = false;

    public SpeedController() {
        setDaemon(true); // läuft im Hintergrund, wird mit Hauptprogramm beendet
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (System.in.available() > 0) {
                    int input = System.in.read();

                    // Aktiviert Speedmode bei Enter oder Leertaste
                    if (input == '\n' || input == '\r' || input == ' ') {
                        speedMode = true;

                        // Eingabepuffer vollständig leeren
                        while (System.in.available() > 0) {
                            System.in.read();
                        }
                    }
                }
                Thread.sleep(30); // Schonung CPU
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSpeedMode() {
        return speedMode;
    }

    public static void resetSpeedMode() {
        speedMode = false;
    }
}
