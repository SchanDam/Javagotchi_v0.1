package org.example.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;

public class MusicManager {
    private Clip musicClip;
    private float volume = -10.0f;

    public void play(String fileName) {
        stop();
        new Thread(() -> {
            try {
                BufferedInputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream("/" + fileName));
                if (bufferedIn == null) return;

                AudioInputStream stream = AudioSystem.getAudioInputStream(bufferedIn);
                musicClip = AudioSystem.getClip();
                musicClip.open(stream);
                FloatControl volumeControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(volume);
                musicClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void stop() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            musicClip.close();
        }
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
