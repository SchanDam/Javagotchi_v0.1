//public void setBlock() {
//    this.setBlock = true;
//}
//public void resetBlock() {
//    this.setBlock = false;
//}

//        if (escape == true) {
//escapeFight();
//            return 0;
//                    } else if (enemy.isBlock()) {
//int baseDamage = Math.max(0, this.str - enemy.getDef());
//isCritical = (rng.nextInt(100) < 15);
//        return isCritical ? baseDamage * 2 / 2 : baseDamage / 2;
//        } else {
//int baseDamage = Math.max(0, this.str - enemy.getDef());
//isCritical = (rng.nextInt(100) < 15);
//        return isCritical ? baseDamage * 2 : baseDamage;

//\u001B[30m → Schwarz
//\u001B[31m → Rot
//\u001B[32m → Grün
//\u001B[33m → Gelb
//\u001B[34m → Blau
//\u001B[35m → Magenta
//\u001B[36m → Cyan
//\u001B[37m → Weiß
//\u001B[0m → Setzt die Farbe zurück

//        System.out.println("\u001B[31mDas ist roter Text\u001B[0m");
//        System.out.println("\u001B[32mDas ist grüner Text\u001B[0m");
//        System.out.println("\u001B[34mDas ist blauer Text\u001B[0m");

// Kampf startet
//
//enemy.setHp(100);
//System.out.printf("%n%s ausgewählt, starte Kampf", enemy.getName());
//        Utils.dotText();
//            System.out.printf("%nKampf beginnt gegen %s\n", enemy.getName());
//        output.playSound(SoundFiles.STARTFIGHT.getFileName());
//        Thread.sleep(500);
//
//
//            while (running == true) {
//
//        // Angriff Spieler ⇒ Gegner
//        if (player.isBlock() == false) {
//        if (comSysInst.isMiss() == true) {
//        System.out.printf("%nDer Angriff ging daneben.%n");
//                        output.playSound(SoundFiles.ATTACKMISS.getFileName());
//        } else {
//        comSysInst.attack(enemy);
//                        System.out.printf("%n%s greift %s an und verursacht %s Schaden. ", player.getName(), enemy.getName(), comSysInst.getFinalDamage(enemy));
//        Thread.sleep(100);
//                        System.out.printf("%s%n", showCritAndHitSound(player));
//        Thread.sleep(500);
//                        System.out.printf("Verbleibende Lebenspunkte von %s: %s%n", enemy.getName(), enemy.getHp());
//        Thread.sleep(1500);
//                    }
//                            }
//
//                            // Angriff blocken
//                            else if (player.isBlock() == true) {
//        System.out.println("der nächste Angriff wird geblockt");
//                    Thread.sleep(1500);
//                }
//
//                        // Gegner besiegt
//                        if (enemy.getHp() < 1) {
//        System.out.printf("%n%s wurde besiegt!%n%n", enemy.getName());
//        output.playSound(SoundFiles.ENEMYDEADSHORT.getFileName());
//        Thread.sleep(200);
//                    System.out.println("Du hast 10 Gold und 100 Punkte erhalten!");
//                    output.playSound(SoundFiles.GETCOIN.getFileName());
//        player.setGold(player.getGold() + 10);
//        player.setPunkte(player.getPunkte() + 100);
//        return;
//        }
//
//        // Angriff Gegner ⇒ Spieler
//        else {
//        if (enemy.isMiss() == true) {
//        System.out.printf("%nDer Angriff ging daneben.%n");
//                        output.playSound(SoundFiles.ATTACKMISS.getFileName());
//        } else {
//        enemy.attack(player);
//                        System.out.printf("%n%s greift %s an und verursacht %s Schaden. ", enemy.getName(), player.getName(), enemy.getFinalDamage(player));
//        Thread.sleep(100);
//                        System.out.printf("%s%n", player.showCritAndHitSound(enemy));
//        Thread.sleep(500);
//                        System.out.printf("%s's Lebenspunkte: %s%n", player.getName(), player.getHp());
//        Thread.sleep(1500);
//                    }
//                            }
//
//                            // Auswahl nächste Runde
//                            if (player.getHp() > 1) {
//        System.out.printf("%n**Nächste Runde**%n%n");
//                    Thread.sleep(100);
//                    output.playSound(SoundFiles.NEXTROUND.getFileName());
//        System.out.println("\"1\" für angreifen");
//                    System.out.println("\"2\" für blocken");
//                    System.out.println("\"3\" für flüchten");
//input = Utils.getSoundInput();
//                    System.out.println();
//                    Thread.sleep(500);
//
//                    switch (input) {
//        case "1" -> {
//        }
//        case "2" -> player.setBlock(true);
//                        case "3" -> {
//                                comSysInst.escapeFight();
//                            if (running == false) {
//        return;
//        }
//        }
//        }
//        }
//
//        // Spieler besiegt
//        if (player.getHp() <= 0) {
//        System.out.println("%nDu wurdest besiegt!%n");
//                    Thread.sleep(500);
//                    System.out.println("Dein Sättigungslevel ist um 3 gesunken.");
//                    player.setHunger(player.getHunger() - 3);
//        return;
//        }
//        }
//
//        case "q" -> {
//        System.out.printf("%nIns Hauptmenü zurückkehren, bitte warten");
//            Utils.dotText();
//            System.out.println();
//        }
//                }