package ru.practicum.dinner;

import java.util.*;

public class DinnerConstructor {
    private final Map<String, List<String>> dishesByType;
    private final Random random;

    public DinnerConstructor() {
        dishesByType = new HashMap<>();
        random = new Random();
    }

    // Метод для добавления блюда
    public void addDish(String type, String name) {
        dishesByType.computeIfAbsent(type, k -> new ArrayList<>());
        if (!dishesByType.get(type).contains(name)) {
            dishesByType.get(type).add(name);
        } else {
            System.out.println("Блюдо '" + name + "' уже существует в типе '" + type + "'.");
        }
    }

    // Метод для проверки существования типа блюда
    public boolean checkType(String type) {
        return dishesByType.containsKey(type);
    }

    // Метод для генерации комбинаций блюд
    public List<List<String>> generateCombinations(int numberOfCombinations, List<String> types) {
        List<List<String>> combinations = new ArrayList<>();

        for (int i = 0; i < numberOfCombinations; i++) {
            List<String> combination = generateSingleCombination(types);
            if (combination.isEmpty()) {
                return Collections.emptyList();
            }
            combinations.add(combination);
        }

        return combinations;
    }
    private List<String> generateSingleCombination(List<String> types) {
        List<String> combination = new ArrayList<>();
        for (String type : types) {
            List<String> dishes = dishesByType.get(type);
            if (dishes == null || dishes.isEmpty()) {
                return Collections.emptyList();
            }
            String randomDish = dishes.get(random.nextInt(dishes.size()));
            combination.add(randomDish);
        }
        return combination;
    }
}