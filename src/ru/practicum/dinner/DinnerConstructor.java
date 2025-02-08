package ru.practicum.dinner;

import java.util.*;

public class DinnerConstructor {
    private Map<String, List<String>> dishesByType;
    private Random random;

    public DinnerConstructor() {
        dishesByType = new HashMap<>();
        random = new Random();
    }

    // Метод для добавления блюда
    public void addDish(String type, String name) {
        dishesByType.computeIfAbsent(type, k -> new ArrayList<>()).add(name);
    }

    // Метод для проверки существования типа блюда
    public boolean checkType(String type) {
        return dishesByType.containsKey(type);
    }

    // Метод для генерации комбинаций блюд
    public List<List<String>> generateCombinations(int numberOfCombinations, List<String> types) {
        List<List<String>> combinations = new ArrayList<>();

        for (int i = 0; i < numberOfCombinations; i++) {
            List<String> combination = new ArrayList<>();
            for (String type : types) {
                List<String> dishes = dishesByType.get(type);
                if (dishes == null || dishes.isEmpty()) {
                    return Collections.emptyList();
                }
                String randomDish = dishes.get(random.nextInt(dishes.size()));
                combination.add(randomDish);
            }
            combinations.add(combination);
        }

        return combinations;
    }
}