import java.util.Scanner;
public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i<args.length;i++) {
            String s = args[i];
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a word");
        String s = sc.nextLine();
        System.out.println("It is " + isPalindrome(s) + " that " + s + " is a palindrome");
    }

    public static String reverseString(String s){
        int len = s.length();
        char[] rs = new char[len];
        for (int i = 0;i<len;i++) {
            rs[i] = s.charAt(len - i - 1);
        }
        return String.valueOf(rs);
    }

    public static boolean isPalindrome(String s){
        return s.equals(reverseString(s));
    }
}
