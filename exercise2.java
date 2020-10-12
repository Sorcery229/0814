import java.util.Arrays;

public class exercise2 {
    public static void main (String[] args) {
        System.out.println("Repeated: " + repeat("mice", 5));
        System.out.println("Difference: " + differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println("Is average whole: " + isAvgWhole(new int[] {1, 5, 6}));
        System.out.println("Summed array: " + Arrays.toString(cumulativeSum(new int[]{3, 3, -2, 408, 3, 3})));
        System.out.println("Decimal places: " + getDecimalPlaces("43.20"));
        System.out.println("Fibonacci: " + Fibonacci(7));
        System.out.println("Validity: " + isValid("12345"));
        System.out.println("Strange pair: " + isStrangePair("ratio", "orator"));
        System.out.println("Is prefix: " + isPrefix("automation", "auto-"));
        System.out.println("Is suffix: " + isSuffix("arachnophobia", "-phobia"));
        System.out.println("Fields: " + boxSeq(2));
    }
    public static String repeat(String str, int n) {
        String fin = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < n; j++) {
                fin+=str.charAt(i);
            }
        }
        return fin;
    }
    public static int differenceMaxMin(int [] arr) {
        Arrays.sort(arr);
        int min = arr[0];
        int max = arr[arr.length-1];
        return max-min;
    }
    public static boolean isAvgWhole(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum+=num;
        }
        return (sum%arr.length==0);
    }
    public static int[] cumulativeSum(int[] arr) {
        int sum=0;
        int[] fin = new int [arr.length];
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            fin[i]=sum;
        }
        return fin;
    }
    public static int getDecimalPlaces (String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        boolean flag = false;
        for (char ch : chars) {
            if (flag) count++;
            if (ch=='.') flag=true;
        }
        return count;
    }
    public static int Fibonacci (int a) {
        int n = 0, next = 1, prev = 0, decoy = 0;
        while (n<=a) {
            decoy = next;
            next += prev;
            prev = decoy;
            n++;
        }
        return decoy;
    }
    public static boolean isValid (String index) {
        boolean ok = true;
        if (index.length() != 5) ok = false;
        for (char ch : index.toCharArray()) {
            if (!Character.isDigit(ch)) ok = false;
        }
        return ok;
    }
    public static boolean isStrangePair (String str1, String str2) {
        return (str1.charAt(0)==str2.charAt(str2.length()-1) && (str1.charAt(str1.length()-1)==str2.charAt(0)));
    }
    public static boolean isPrefix (String word, String prefix) {
       return prefix.contains(word.substring(0,prefix.length()-1));
    }
    public static boolean isSuffix (String word, String suffix) {
        return suffix.contains(word.substring(word.length()-suffix.length()+1));
    }
    public static int boxSeq(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i%2==0) count+=3;
            else count-=1;
        }
        return count;
    }
}
