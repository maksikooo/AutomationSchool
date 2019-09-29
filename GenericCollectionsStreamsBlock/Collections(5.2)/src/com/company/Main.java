package com.company;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


    public class Main {

        public static void main(String[] args) {
            System.out.println("Введите номер задания");
            String i ;
            Scanner read = new Scanner(System.in);
            i = read.next();
            switch (i) {
                case "1": {
                    Set<String> set = new HashSet<String>();
                    set = Stream.of("арбуз","банан","вишня","груша","дыня","ежевика","жень-шень","земляника","ирис","картофель").collect(Collectors.toSet());
//Альтернатива :
//                    set.add("арбуз");
//                    set.add("банан");
//                    set.add("вишня");
//                    set.add("груша");
//                    set.add("дыня");
//                    set.add("ежевика");
//                    set.add("жень-шень");
//                    set.add("земляника");
//                    set.add("ирис");
//                    set.add("картофель");
                    for (String x : set) {
                        System.out.println(x);
                    }
                }
                break;
                case "2": {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("арбуз", "ягода");
                    map.put("банан", "трава");
                    map.put("вишня", "ягода");
                    map.put("груша", "фрукт");
                    map.put("дыня", "овощ");
                    map.put("ежевика", "ягода");
                    map.put("жень-шень", "корень");
                    map.put("земляника", "ягода");
                    map.put("ирис", "цветок");
                    map.put("картофель", "клубень");
                    for (HashMap.Entry<String, String> x : map.entrySet()) {
                        System.out.println(x.getKey() + " - " + x.getValue());
                    }
                }
                break;
                case "3": {
                    HashMap<String, cat> cats = new HashMap<String, cat>();
                    cat blea1 = new cat();
                    blea1.setName("dolgo1");
                    cats.put(blea1.getName(), blea1);
                    cat blea2 = new cat();
                    blea2.setName("dolgo2");
                    cats.put(blea2.getName(), blea2);
                    cat blea3 = new cat();
                    blea3.setName("dolgo3");
                    cats.put(blea3.getName(), blea3);
                    cat blea4 = new cat();
                    blea4.setName("dolgo4");
                    cats.put(blea4.getName(), blea4);
                    cat blea5 = new cat();
                    blea5.setName("dolgo5");
                    cats.put(blea5.getName(), blea5);
                    cat blea6 = new cat();
                    blea6.setName("dolgo6");
                    cats.put(blea6.getName(), blea6);
                    cat blea7 = new cat();
                    blea7.setName("dolgo7");
                    cats.put(blea7.getName(), blea7);
                    cat blea8 = new cat();
                    blea8.setName("dolgo8");
                    cats.put(blea8.getName(), blea8);
                    cat blea9 = new cat();
                    blea9.setName("dolgo9");
                    cats.put(blea9.getName(), blea9);
                    cat blea10 = new cat();
                    blea10.setName("dolgo10");
                    cats.put(blea10.getName(), blea10);
                    for (HashMap.Entry<String, cat> x : cats.entrySet()) {
                        System.out.println(x.getKey() + " - " + x.getValue());
                    }

                }
                break;
                case "4": {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("арбуз2", "ягода");
                    map.put("банан2", "трава");
                    map.put("вишня2", "ягода");
                    map.put("груша2", "фрукт");
                    map.put("дыня2", "овощ");
                    map.put("ежевика2", "ягода");
                    map.put("жень-шень2", "корень");
                    map.put("земляника2", "ягода");
                    map.put("ирис2", "цветок");
                    map.put("картофель2", "клубень");
                    for (HashMap.Entry<String, String> x : map.entrySet()) {
                        System.out.println(x.getKey());
                    }
                }
                break;
                case "5": {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("арбуз1", "ягода");
                    map.put("банан1", "трава");
                    map.put("вишня1", "ягода");
                    map.put("груша1", "фрукт");
                    map.put("дыня1", "овощ");
                    map.put("ежевика1", "ягода");
                    map.put("жень-шень1", "корень");
                    map.put("земляника1", "ягода");
                    map.put("ирис1", "цветок");
                    map.put("картофель1", "клубень");
                    for (HashMap.Entry<String, String> x : map.entrySet()) {
                        System.out.println(x.getValue());
                    }
                }
                break;
                case "6": {
                    HashMap<String, Object> objects = new HashMap<String, Object>();
                    Object bleat221 = new Object();
                    objects.put("s",bleat221);
                    Object bleat1 = new Object();
                    objects.put("dolgo1", bleat1);
                    Object bleat2 = new Object();
                    objects.put("dolgo2", bleat2);
                    Object bleat3 = new Object();
                    objects.put("dolgo3", bleat3);
                    Object bleat4 = new Object();
                    objects.put("dolgo4", bleat4);
                    Object bleat5 = new Object();
                    objects.put("dolgo5", bleat5);
                    for (HashMap.Entry<String, Object> x : objects.entrySet()) {
                        System.out.println(x.getKey() + " - " + x.getValue());
                    }

                }
                break;
                case "7": {
                    ArrayList<String> list = new ArrayList<String>();
                    list.add("Ku");
                    list.add("Kuku");
                    list.add("Kue");
                    list.add("Kuss");
                    list.add("Kus");
                    System.out.println("Размер массива: "+list.size());
                    for (String l : list) {
                        System.out.println(l);
                    }
                }
                break;
                case "8": {
                    String max = "";
                    ArrayList<String> list = new ArrayList<String>();
                    System.out.println("Введите строку");
                    list.add(read.next());
                    System.out.println("Введите строку");
                    list.add(read.next());
                    System.out.println("Введите строку");
                    list.add(read.next());
                    System.out.println("Введите строку");
                    list.add(read.next());
                    System.out.println("Введите строку");
                    list.add(read.next());
                    for (String tmp : list) {
                        if (max.length() < tmp.length()) {
                            max = tmp;
                        }
                    }
                    for (String tmp : list) {
                        if (max.length() == tmp.length()) {
                            System.out.println(tmp);
                        }
                    }
                }
                break;
                case "9": {
                    String min;
                    ArrayList<String> listmin = new ArrayList<String>();
                    System.out.println("Введите строку.");
                    listmin.add(read.next());
                    System.out.println("Введите строку.");
                    listmin.add(read.next());
                    System.out.println("Введите строку.");
                    listmin.add(read.next());
                    System.out.println("Введите строку.");
                    listmin.add(read.next());
                    System.out.println("Введите строку.");
                    listmin.add(read.next());
                    min = listmin.get(0);
                    for (String tmp : listmin) {
                        if (min.length() > tmp.length()) {
                            min = tmp;
                        }
                    }
                    for (String tmp : listmin) {
                        if (min.length() == tmp.length()) {
                            System.out.println(tmp);
                        }
                    }
                }
                break;
                case "10": {
                    ArrayList<String> list = new ArrayList<String>();
                    System.out.println("Введите строку");
                    list.add(0, read.next());
                    System.out.println("Введите строку");
                    list.add(0, read.next());
                    System.out.println("Введите строку");
                    list.add(0, read.next());
                    System.out.println("Введите строку");
                    list.add(0, read.next());
                    System.out.println("Введите строку");
                    list.add(0, read.next());
                    for (String str : list) {
                        System.out.println(str);
                    }
                }
                break;
                case "11": {
                    ArrayList<String> list = new ArrayList<String>();
                    System.out.println("Введите  строку");
                    list.add(read.next());
                    System.out.println("Введите  строку");
                    list.add(read.next());
                    System.out.println("Введите  строку");
                    list.add(read.next());
                    System.out.println("Введите  строку");
                    list.add(read.next());
                    System.out.println("Введите  строку");
                    list.add(read.next());
                    String tmp;
                    for (int q=0;q < 13;q++ ) {
                        list.add(0,list.get(list.size()-1));
                        list.remove(list.size()-1);
                    }
                    for (String str : list) {
                        System.out.println(str);
                    }
                }
                break;
                case "12": {
                    Set<String> set = new HashSet<String>();
                    randomStr r = new randomStr();
                    for (int j = 0; j < 20; j++)
                        set.add(r.generateRandomWord(5));
                }
                break;
                case "13": {
                    Set<Integer> set = new HashSet<Integer>();
                    Random r = new Random();
                    for (int j = 0; j < 20; j++) {
                        set.add(r.nextInt(50));
                    }
                    Iterator<Integer> it = set.iterator();
                    while (it.hasNext()) {
                        if (it.next() > 10) {
                            it.remove();
                        }
                    }
                    Iterator<Integer> iter = set.iterator(); // вывод результата
                    while (iter.hasNext())
                        System.out.println(iter.next());
                }
                break;
                case "14": {
                    String mapValue = "Кло";
                    int qty = 0;
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("Бло", "Кgло");
                    map.put("Как", "же");
                    map.put("Много", "Значений");
                    map.put("Одинаковых", "Нужно");
                    map.put("Пихать", "в");
                    map.put("Массивы", "ы");
                    map.put("Бyло", "Кло");
                    map.put("Бuло", "Кло");
                    map.put("ЫЫк", "Кло");
                    map.put("ыы", "s");
                    for (Map.Entry<String, String> tmp : map.entrySet()) {
                        //вывод значений map  System.out.println(tmp.getKey()+" : "+tmp.getValue());
                        if (tmp.getValue() == mapValue) {
                            qty++;
                        }
                    }
                    System.out.println(qty);
                }
                break;
                case "15": {
                    Map<String, LocalDate> map = new HashMap<String, LocalDate>();
                    map.put("Бла", LocalDate.of(2015, 12, 30));
                    map.put("S", LocalDate.of(2015, 10, 30));
                    map.put("Er", LocalDate.of(2015, 11, 30));
                    map.put("QE", LocalDate.of(2015, 1, 30));
                    map.put("Rt", LocalDate.of(2015, 8, 30));
                    map.put("Fs", LocalDate.of(2015, 6, 3));
                    map.put("FG", LocalDate.of(2015, 7, 30));
                    map.put("qee", LocalDate.of(2015, 5, 30));
                    map.put("tt", LocalDate.of(2015, 12, 30));
                    System.out.println("Не отфильтрованный map - " + map);
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, LocalDate> tmp = (Map.Entry<String, LocalDate>) it.next();
                        if ((tmp.getValue().getMonthValue() > 5) & (tmp.getValue().getMonthValue() < 9)) {
                            it.remove();
                        }
                    }
                    for (Map.Entry<String, LocalDate> tmp1 : map.entrySet()) {
                        System.out.println(tmp1.getKey() + ":" + tmp1.getValue());
                    }
                }
                break;
                case "16": {
                    Map<String, String> map = new HashMap<String, String>();
                    Map<String, String> mapUnique = new HashMap<>();
                    map.put("Бло2", "Кgло");
                    map.put("Как2", "же");
                    map.put("Много2", "Значений");
                    map.put("Одинаковых2", "Нужно");
                    map.put("Пихать2", "в");
                    map.put("Массивы2", "ы");
                    map.put("Бyло2", "Кло");
                    map.put("Бuло2", "Кло");
                    map.put("ЫЫк", "Кло");
                    map.put("ыы", "s");
                    System.out.println(map);
                    Iterator iterator = map.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> str = (Map.Entry<String, String>) iterator.next();
                        if (!mapUnique.containsValue(str.getValue())) {           // Возможно это не правильная реализация,если нужно было удалить не дубликаты
                            mapUnique.put(str.getKey(), str.getValue());          // а всех у кого есть дубликат,если так то переделаю
                        }
                    }
                    System.out.println(mapUnique);
                }break;
                case "17":{
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("Бло1", "Кgло");
                    map.put("Как1", "же");
                    map.put("Много1", "Значений");
                    map.put("Пихать1", "Нужно");
                    map.put("Пихать1", "в");
                    map.put("Пихать1", "ы");
                    map.put("Бyло1", "Кло");
                    map.put("Бuло1", "Кло");
                    map.put("ЫЫк1", "Кло");
                    map.put("ыы1", "s");
                    System.out.println(map);
                }break;
                default:{
                    System.out.println("Goodbye");
                }
            }
        }
    }