import java.util.Scanner;

public class Main {

    public static void sortNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }

    public static void sortCharacters(char[] characters) {
        for (int i = 0; i < characters.length - 1; i++) {
            for (int j = 0; j < characters.length - i - 1; j++) {
                if (characters[j] > characters[j + 1]) {
                    char temp = characters[j];
                    characters[j] = characters[j + 1];
                    characters[j + 1] = temp;
                }
            }
        }
    }

    public static boolean areAnagrams(String first, String second) {
        if (first.length() != second.length()) return false;

        char[] firstArray = first.toCharArray();
        char[] secondArray = second.toCharArray();

        sortCharacters(firstArray);
        sortCharacters(secondArray);

        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i] != secondArray[i]) return false;
        }
        return true;
    }

    public static int findKthSmallest(int[] numbers, int k) {
        sortNumbers(numbers);
        return numbers[k - 1];
    }

    public static int findMedian(int[] numbers) {
        sortNumbers(numbers);
        return numbers[numbers.length / 2];
    }

    public static boolean canShip(int[] weights, int days, int capacity) {
        int currentWeight = 0;
        int usedDays = 1;

        for (int weight : weights) {
            if (currentWeight + weight > capacity) {
                usedDays++;
                currentWeight = 0;
            }
            currentWeight += weight;
        }
        return usedDays <= days;
    }

    public static int findMinimumCapacity(int[] weights, int days) {
        int left = 0, right = 0;

        for (int weight : weights) {
            if (weight > left) left = weight;
            right += weight;
        }

        while (left < right) {
            int mid = (left + right) / 2;

            if (canShip(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int[] readArray(Scanner scanner, int size) {
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }
        return numbers;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Task 1
        String first = scanner.next();
        String second = scanner.next();
        System.out.println(areAnagrams(first, second) ? "YES" : "NO");

        // Task 2
        int size2 = scanner.nextInt();
        int[] numbers2 = readArray(scanner, size2);
        int k = scanner.nextInt();
        System.out.println(findKthSmallest(numbers2, k));

        // Task 3
        int size3 = scanner.nextInt();
        int[] numbers3 = readArray(scanner, size3);
        System.out.println(findMedian(numbers3));

        // Task 4
        int size4 = scanner.nextInt();
        int[] weights = readArray(scanner, size4);
        int days = scanner.nextInt();
        System.out.println(findMinimumCapacity(weights, days));

        scanner.close();
    }
}