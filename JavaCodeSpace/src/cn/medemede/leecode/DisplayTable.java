package cn.medemede.leecode;

import java.util.*;

public class DisplayTable {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> foodNames = new TreeSet<>();
        TreeMap<String, HashMap<String, Integer>> tableToFoodNums = new TreeMap<>(Comparator.comparingInt(Integer::parseInt));
        for (List<String> order : orders) {
            String tableNo = order.get(1);
            String foodName = order.get(2);
            foodNames.add(foodName);
            HashMap<String, Integer> foodToNums = tableToFoodNums.computeIfAbsent(tableNo, k -> new HashMap<>());
            foodToNums.put(foodName, foodToNums.getOrDefault(foodName, 0) + 1);
            tableToFoodNums.put(tableNo, foodToNums);
        }
        List<String> titles = new ArrayList<>();
        titles.add("Table");
        titles.addAll(foodNames);
        List<List<String>> res = new ArrayList<>();
        res.add(titles);
        for (Map.Entry<String, HashMap<String, Integer>> entry : tableToFoodNums.entrySet()) {
            List<String> tableFoods = new ArrayList<>();
            tableFoods.add(entry.getKey());
            HashMap<String, Integer> foodNums = entry.getValue();
            for (String foodName : foodNames) {
                if (foodNums.containsKey(foodName)) {
                    tableFoods.add(String.valueOf(foodNums.get(foodName)));
                } else {
                    tableFoods.add("0");
                }
            }
            res.add(tableFoods);
        }
        return res;
    }
}
