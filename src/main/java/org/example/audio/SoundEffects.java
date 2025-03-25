package org.example.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class SoundEffects {

    // Synchrone Wiedergabe (optional)
    public void playSound(String resourcePath) {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (stream == null) {
                System.err.println("Datei nicht gefunden: " + resourcePath);
                return;
            }
            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(stream)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

                while (clip.isRunning()) {
                    Thread.sleep(10);
                }
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Asynchrone Wiedergabe (nicht blockierend)
    public void playSoundAsync(String resourcePath) {
        new Thread(() -> {
            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
                if (stream == null) {
                    System.err.println("Datei nicht gefunden: " + resourcePath);
                    return;
                }
                try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(stream)) {
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();

                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    });

                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
