package org.example.audio;

public enum SoundFiles {

    FANFARE("music/fanfare.wav", Type.MUSIC),
    FIGHTBOSS("music/fightBoss.wav", Type.MUSIC),
    FIGHTNORMAL("music/fightNormal.wav", Type.MUSIC),
    FIGHTSEPH("music/fightSeph.wav", Type.MUSIC),
    FIGHTSUPERBOSS("music/fightSuperBoss.wav", Type.MUSIC),
    INTRO("music/intro.wav", Type.MUSIC),
    LOSE("music/lose.wav", Type.MUSIC),
    MENU("music/menu.wav", Type.MUSIC),
    MID("music/mid.wav", Type.MUSIC),
    ATTACKMISS("sounds/attackMiss.wav", Type.EFFECT),
    BIRDS("sounds/birds.wav", Type.EFFECT),
    CASTINGLIMIT("sounds/castingLimit.wav", Type.EFFECT),
    CASTINGSPELL("sounds/castingSpell.wav", Type.EFFECT),
    CHOCOBO("sounds/chocobo.wav", Type.EFFECT),
    CLOUDCRIT("sounds/cloudCrit.wav", Type.EFFECT),
    CLOUDHIT("sounds/cloudHit.wav", Type.EFFECT),
    ENEMYDEADSHORT("sounds/enemyDeadShort.wav", Type.EFFECT),
    ENEMYDEADLONG("sounds/enemyDeadLong.wav", Type.EFFECT),
    ESCAPE("sounds/escape.wav", Type.EFFECT),
    GETCOIN("sounds/getCoin.wav", Type.EFFECT),
    HEAL("sounds/heal.wav", Type.EFFECT),
    HEARTBUMP("sounds/heartbump.wav", Type.EFFECT),
    INPUT("sounds/input.wav", Type.EFFECT),
    INPUTBIGHIT("sounds/inputBig.wav", Type.EFFECT),
    INPUTFAIL("sounds/inputFail.wav", Type.EFFECT),
    LAUGH("sounds/laugh.wav", Type.EFFECT),
    NEXTROUND("sounds/nextRound.wav", Type.EFFECT),
    NORMALHIT("sounds/normalHit.wav", Type.EFFECT),
    NORMALCRIT("sounds/normalCrit.wav", Type.EFFECT),
    SEPH("sounds/seph.wav", Type.EFFECT),
    STARTFIGHT("sounds/startFight.wav", Type.EFFECT); // <-- Semikolon verschieben, wenn unten was dazu kommt!!!!!

    private final String fileName;
    private final Type type;

    SoundFiles(String fileName, Type type) {
        this.fileName = fileName;
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        EFFECT,
        MUSIC
    }
}
