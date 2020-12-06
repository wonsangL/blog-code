import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 1000; ++i) {
            transactions.add(new Transaction(i, TransactionType.GROCERY, random.nextInt()));
        }

        firstStreamTest(transactions);
        //parallelStreamTest(transactions);
    }

    private static void firstStreamTest(List<Transaction> transactions) {
        List<Transaction> groceryTransactions = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.GROCERY) {
                groceryTransactions.add(t);
            }
        }

        Collections.sort(groceryTransactions, (t1, t2) -> t2.getValue().compareTo(t1.getValue()));

        List<Integer> transactionIds = new ArrayList<>();
        for (Transaction t : groceryTransactions) {
            transactionIds.add(t.getId());
        }

        for (Integer result : transactionIds) {
            System.out.println(result);
        }

        System.out.println("========Stream=========");

        List<Integer> results = transactions.stream()
                .filter(t -> t.getType() == TransactionType.GROCERY)
                .sorted(comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(toList());

        for (Integer result : results) {
            System.out.println(result);
        }
    }

    private static void parallelStreamTest(List<Transaction> transactions) {
        long start = new Date().getTime();
        List<Integer> parallelResult = transactions.parallelStream()
                .filter(t -> t.getType() == TransactionType.GROCERY)
                .sorted(comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(toList());
        long end = new Date().getTime();

        System.out.println("===parallel Stream result===");
        System.out.println("times: " + (end - start));

        start = new Date().getTime();
        List<Integer> results = transactions.stream()
                .filter(t -> t.getType() == TransactionType.GROCERY)
                .sorted(comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(toList());
        end = new Date().getTime();

        System.out.println("=======Stream result========");
        System.out.println("times: " + (end - start));


    }
}
