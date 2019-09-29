package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        randomStr r = new randomStr();
            List<String > list = new ArrayList<>();

            for (int j = 0 ; j<50 ; j++){
                list.add(r.generateRandomWord(7));
            }
            list.add("");
            list.add("");
            list.add("тест д");
            list.add("А dолжна быть в начале");
            list.add("Я dолжна быть в конце");
            System.out.println(list);
//            List<String> result1 = list.stream()
//                    .filter(line -> line.length()>0)
//                    .filter(line ->!line.contains("д"))
//                    .sorted()
//                    .collect(Collectors.toList());
//            result1.forEach(System.out::println);  //по началу сделал так ,после перепрочтения показалось что требуется сделать как ниже
        System.out.print("Длинна элементов: ");
        list.stream().map(x-> x.length()).map(x->x+",").forEach(System.out::print); // добавил запятые что б можно было вывести в 1 строку
        System.out.println("");
        System.out.print("Не пустые элементы не содержащие d: ");
        list.stream().filter(x-> x.length()>0).filter(x->!x.contains("d")).map(x->x+",").forEach(System.out::print);
        System.out.println("");
        System.out.print("Элементы отсортированны в алфавитном порядке: ");
        list.stream().map(x->x+",").sorted().forEach(System.out::print);



    }
}
