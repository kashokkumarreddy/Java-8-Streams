package com.java8_streams.java_streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Java8CodingQuestions {

    public static void java8CodingQuestions(){

        System.out.println("Java 8 Coding Interview Questions");

        // count the occurrence of each character in string
        String name = "ashokkumarreddy";
        // a -2, d-2,e-1,h-1,k-2,m-1,o-1,r-2,s-1,u-1,y-1

        Map<String, Long> result = Arrays.stream(name.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//            System.out.println(result);
        // Find all duplicate elements from a given string



        // Reverse each word of a string
        String name1 = "Ashok Kumar Reddy";
        List<String> reverseString = Arrays.stream(name1.split(" "))
                .map(e -> new StringBuilder(e).reverse().toString())
                .collect(Collectors.toList());
        System.out.println(reverseString);

        // Second-highest element in a list of integers
        List<Integer> numbers = asList(3, 1, 4, 2, 5);
        Optional<Integer> secondHighest = numbers.stream()
                .distinct()
//                .sorted(Collections.reverseOrder())
//                .sorted(Comparator.reverseOrder())
                .sorted((a,b) -> b-a)
                .skip(1)
                .findFirst();

        System.out.println("secondHighest "+secondHighest);


    }
}
