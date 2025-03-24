package org.example.audio;
import javax.sound.sampled.*;
import java.io.InputStream;

//Ganze Klasse von CHATGPT

public class Sounds {

    private float volume = -10.0f; // Standard-Lautstärke in dB

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void playSound(String fileName) {
        try {
            InputStream audioSrc = Sounds.class.getResourceAsStream("/" + fileName);
            if (audioSrc == null) {
                System.err.println("⚠️ Datei nicht gefunden: /" + fileName);
                return; // NICHT mehr Exception werfen – nur testen
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            setClipVolume(clip, volume);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void playSoundAsync(String fileName) {
        new Thread(() -> {
            try {
                String fullPath = "/" + fileName;
//                System.out.println("Versuche Sound zu laden: " + fullPath);

                InputStream audioSrc = Sounds.class.getResourceAsStream(fullPath);
                if (audioSrc == null) {
                    System.err.println("⚠️ ResourceAsStream gibt null zurück!");
                    return;
                }

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                setClipVolume(clip, volume);
                clip.start();
            } catch (Exception e) {
                System.err.println("❌ Fehler beim Abspielen von '" + fileName + "': " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }


    private void setClipVolume(Clip clip, float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }
}
