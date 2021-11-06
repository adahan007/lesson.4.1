package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {270, 280, 240, 500, 260, 300, 250};
    public static int[] heroesDamage = {20, 15, 25, 10, 30, 35, 10};
    public static String[] heroesAttackType = {"Physical","Medic",
            "Magical", "Kinetic", "Golem", "Berserk", "Thor"};
    public static int round_number = 0;
    public static Random random = new Random();

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(
                heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round_number++;
        chooseBossDefence();
        if (bossHealth > 0) { // на всякий случай
            bossHits();
        }
        heroesHit();
        medic();
        golem();
        lucky();
        berserk();
        thor();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
       /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(11); //0,1,2,3,4,5,6,7,8,9
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100) {
                heroesHealth[i] += 20;
            }
        }
    }

    public static void golem() {
        if (heroesHealth[3] > 0) {
            bossDamage = bossDamage / 5;
        }
    }


    public static void lucky() {
        int a = random.nextInt(2) + 1;
        switch (a) {
            case 1:
                heroesHealth[4] = heroesHealth[4] + bossDamage;
                System.out.println("lucky is avaded");
                break;
            case 2:
                System.out.println("Lucky isn't avaded");
                break;
        }

    }
    public static void thor() {
        Random random = new Random();
        boolean b = random.nextBoolean();
        if(b){
            bossDamage = 0;
            System.out.println("Boos is stunned");
        }else {
            bossDamage = 50;
        }

    }
    public static void berserk() {
        Random random = new Random();
        int kot = random.nextInt(30)+2;
        if (heroesHealth[5] > 0 && bossHealth > 0){
            heroesHealth[5] += kot;
            bossHealth -= kot;
            System.out.println("berserkHitBoss "+kot );
        }else {
            heroesHealth[5] = 0;
        }
    }


    public static void printStatistics() {
        System.out.println(round_number + " ROUND ______________");
        System.out.println("Boss health: " + bossHealth
                + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + " (" + heroesDamage[i] + ")");
        }
        System.out.println("____________________");
    }
}
