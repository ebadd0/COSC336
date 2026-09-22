import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LongestDecreasingSubsequence {

    static List<Integer> longestDecreasingSubsequence(int[] a) {
        int n = a.length;
        List<Integer> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }

        int[] bsub = new int[n];      //length of best subseq ending at i
        int[] prev = new int[n];  //previous index in that subseq


        for (int i = 0; i < n; i++) {
            bsub[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i] && bsub[j] + 1 > bsub[i]) {
                    bsub[i] = bsub[j] + 1;
                    prev[i] = j;
                }
            }
        }

        // Index where the longest subsequence ends
        int bsubEnd = 0;
        for (int i = 1; i < n; i++) {
            if (bsub[i] > bsub[bsubEnd]) {
                bsubEnd = i;
            }
        }

        // Reconstruct the subsequence
        for (int i = bsubEnd; i != -1; i = prev[i]) {
            result.add(0, a[i]);
        }

        return result;
    }

    // Reads sequence typed by the user, separated by space and/or commas
    static int[] readSequence(Scanner scanner) {
        System.out.print("Input sequence: ");
        List<Integer> nums = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            // A blank line means the user is finished
            if (line.isEmpty()) {
                break;
            }

            //Convert strings to integers
            String[] str = line.split("[\\s,]+");
            for (String numb : str) {
                nums.add(Integer.parseInt(numb));
            }
        }

        int[] a = new int[nums.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = nums.get(i);
        }

        return a;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = readSequence(scanner);

        List<Integer> subseq = longestDecreasingSubsequence(a);


        StringBuilder subseqStr = new StringBuilder();
        for (int i = 0; i < subseq.size(); i++) {
            subseqStr.append(subseq.get(i));
            if (i < subseq.size() - 1) subseqStr.append(", ");
        }

        System.out.println();

        System.out.println("Length of longest decreasing subsequence: " + subseq.size());
        System.out.println("Longest decreasing subsequence: " + subseqStr);
    }
}