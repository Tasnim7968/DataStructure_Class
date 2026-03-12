import java.util.*;

public class IteratorLambdaDemo {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        List<String> words = new ArrayList<>(Arrays.asList("Apple","Banana","Cherry","Date","Mango"));

        method1_printNumbers(numbers);
        method2_printSquares(numbers);
        method3_printEvenNumbers(numbers);
        method4_printOddNumbers(numbers);
        method5_printGreaterThanFive(numbers);
        method6_printWordLengths(words);
        method7_printUpperCase(words);
        method8_removeOddNumbers(numbers);
        method9_multiplyByTwo(numbers);
        method10_printReverse(numbers);
    }

    // Method 1
    public static void method1_printNumbers(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        System.out.println("\nMethod 1: Printing all numbers using Iterator + Lambda");
        list.forEach(n -> System.out.println("Iterator + Lambda -> Number: " + n));
    }

    // Method 2
    public static void method2_printSquares(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        System.out.println("\nMethod 2: Printing squares using Iterator + Lambda");
        list.forEach(n -> System.out.println("Iterator + Lambda -> Square of " + n + " = " + (n*n)));
    }

    // Method 3
    public static void method3_printEvenNumbers(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        System.out.println("\nMethod 3: Printing even numbers using Iterator + Lambda");

        list.forEach(n -> {
            if(n % 2 == 0)
                System.out.println("Iterator + Lambda -> Even number: " + n);
        });
    }

    // Method 4
    public static void method4_printOddNumbers(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        System.out.println("\nMethod 4: Printing odd numbers using Iterator + Lambda");

        list.forEach(n -> {
            if(n % 2 != 0)
                System.out.println("Iterator + Lambda -> Odd number: " + n);
        });
    }

    // Method 5
    public static void method5_printGreaterThanFive(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        System.out.println("\nMethod 5: Printing numbers greater than 5 using Iterator + Lambda");

        list.forEach(n -> {
            if(n > 5)
                System.out.println("Iterator + Lambda -> Number greater than 5: " + n);
        });
    }

    // Method 6
    public static void method6_printWordLengths(List<String> list) {
        Iterator<String> it = list.iterator();
        System.out.println("\nMethod 6: Printing word lengths using Iterator + Lambda");

        list.forEach(word ->
                System.out.println("Iterator + Lambda -> Word: " + word + " | Length: " + word.length()));
    }

    // Method 7
    public static void method7_printUpperCase(List<String> list) {
        Iterator<String> it = list.iterator();
        System.out.println("\nMethod 7: Converting words to uppercase using Iterator + Lambda");

        list.forEach(word ->
                System.out.println("Iterator + Lambda -> Uppercase: " + word.toUpperCase()));
    }

    // Method 8
    public static void method8_removeOddNumbers(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        System.out.println("\nMethod 8: Removing odd numbers using Iterator, then printing using Lambda");

        while(it.hasNext()) {
            if(it.next() % 2 != 0) {
                it.remove();
            }
        }

        list.forEach(n ->
                System.out.println("Iterator + Lambda -> Remaining even number: " + n));
    }

    // Method 9
    public static void method9_multiplyByTwo(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        System.out.println("\nMethod 9: Multiplying numbers by 2 using Iterator + Lambda");

        list.forEach(n ->
                System.out.println("Iterator + Lambda -> " + n + " * 2 = " + (n*2)));
    }

    // Method 10
    public static void method10_printReverse(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        System.out.println("\nMethod 10: Printing numbers in reverse order using Iterator + Lambda");

        Collections.reverse(list);

        list.forEach(n ->
                System.out.println("Iterator + Lambda -> Reverse number: " + n));
    }
}
