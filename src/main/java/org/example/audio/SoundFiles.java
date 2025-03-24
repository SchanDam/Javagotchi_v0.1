package org.example.audio;

public enum SoundFiles {
    ATTACKMISS("sounds/attackMiss.wav"),
    BIRDS("sounds/birds.wav"),
    CASTINGLIMIT("sounds/castingLimit.wav"),
    CASTINGSPELL("sounds/castingSpell.wav"),
    CHOCOBO("sounds/chocobo.wav"),
    CLOUDCRIT("sounds/cloudCrit.wav"),
    CLOUDHIT("sounds/cloudHit.wav"),
    ENEMYDEADSHORT("sounds/enemyDeadShort.wav"),
    ENEMYDEADLONG("sounds/enemyDeadLong.wav"),
    ESCAPE("sounds/escape.wav"),
    GETCOIN("sounds/getCoin.wav"),
    HEAL("sounds/heal.wav"),
    HEARTBUMP("sounds/heartbump.wav"),
    INPUT("sounds/input.wav"),
    INPUTBIGHIT("sounds/inputBig.wav"),
    INPUTFAIL("sounds/inputFail.wav"),
    LAUGH("sounds/laugh.wav"),
    NEXTROUND("sounds/nextRound.wav"),
    NORMALHIT("sounds/normalHit.wav"),
    NORMALCRIT("sounds/normalCrit.wav"),
    SEPH("sounds/seph.wav"),
    STARTFIGHT("sounds/startFight.wav");
    private final String fileName;

    SoundFiles(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
