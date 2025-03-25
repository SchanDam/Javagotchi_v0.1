package org.example.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.InputStream;

public class SoundEffects {
    private float volume = -10.0f;

    public void playSoundAsync(String fileName) {
        new Thread(() -> {
            try {
                InputStream audioSrc = getClass().getResourceAsStream("/" + fileName);
                if (audioSrc == null) return;

                AudioInputStream stream = AudioSystem.getAudioInputStream(audioSrc);
                Clip clip = AudioSystem.getClip();
                clip.open(stream);
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(volume);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
