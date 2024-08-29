package com.example.montyhall;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontyHallSimulation {

    private static final int TOTAL_TRIALS = 1000;

    public static void main(String[] args) {
        Map<Integer, Boolean> results = new HashMap<>();
        Random random = new Random();

        int winCountWhenSwitch = 0;
        int loseCountWhenSwitch = 0;

        for (int i = 1; i <= TOTAL_TRIALS; i++) {
            boolean win = simulateMontyHall(random, true);
            results.put(i, win);
            if (win) {
                winCountWhenSwitch++;
            } else {
                loseCountWhenSwitch++;
            }
        }

        System.out.println("Total wins when switching: " + winCountWhenSwitch);
        System.out.println("Total losses when switching: " + loseCountWhenSwitch);

        // Выводим результаты
        System.out.println("Results: " + results);
    }

    private static boolean simulateMontyHall(Random random, boolean switchDoor) {
        // Инициализируем двери: 0, 1, 2, где 0 - машина, 1 и 2 - козлы
        int carPosition = random.nextInt(3); // позиция машины
        int userChoice = random.nextInt(3); // выбор игрока

        // Ведущий открывает одну из оставшихся дверей с козлом
        int openDoor;
        do {
            openDoor = random.nextInt(3);
        } while (openDoor == carPosition || openDoor == userChoice);

        // Игрок делает свой выбор: поменять дверь или остаться при своем выборе
        if (switchDoor) {
            userChoice = 3 - userChoice - openDoor;
        }

        // Возвращаем результат игры: true если выиграл, иначе false
        return userChoice == carPosition;
    }
}