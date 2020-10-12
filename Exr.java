import java.util.*;

public class Exr {
    public static void main (String[] args) {
        System.out.println("Bell's number: " + Bell(3));
        System.out.println("Latin word: " + translateWord("oaken"));
        System.out.println("Latin sentence: " + translateSentence("Do you think it is going to rain today?"));
        System.out.println("Validity: " + validColor("rgba(0,0,0,0.123456789)"));
        System.out.println("Stripped url: " + stripUrlParams("https://edabit.com?a=1&b=2"));
        System.out.println("Tags: " + Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
        System.out.println("Ulam: " + ulam(9));
        System.out.println("Longest: " + longestNonrepeatingSubstring("abcda"));
        System.out.println("Roman: " + convertToRoman(16));
        System.out.println("Is right: " + formula("16 * 10 = 160 = 14 + 120"));
        System.out.println("Is palindrome: " + palindromeDescendant(23336014));
    }

    private static int factorial(int n)
    {
        if (n == 0) return 1;
        else
        {
            int result = 1;
            for(int i = 2; i <= n; i++)
            {
                result *= i;
            }
            return result;
        }
    }
    private static int C(int n, int k)
    {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }
    public static int Bell(int n)
    {
        if (n == 0) return 1;
        else
        {
            int sum = 0;
            for (int k = 0; k < n; k++)
            {
                sum += C(n-1, k) * Bell(n - k - 1);
            }
            return sum;
        }
    }
    public static String translateWord(String str) {
        String vowels = "aeiouAEIOU";
        boolean flag = true;
        int count = 0;
        char[] word = str.toCharArray();
        String res = "";
        if (str.equals("")) return "";
        if (vowels.contains(Character.toString(word[0]))) res = str + "yay";
        else {
        while (flag) {
            if (vowels.contains(Character.toString(word[count]))) flag=false;
            else count++;
        }
        res = str.substring(count) + str.substring(0,count) + "ay";
        }
        return res;
    }
    public static String translateSentence(String str) {
        String[] cent = str.split(" ");
        boolean flag = false;
        String punk = ".,?!", lastsym = "", res = "";
        for (String word : cent) {
            lastsym = word.substring(word.length()-1);
            if (punk.contains(lastsym)) {word = word.substring(0, word.length()-1); flag = true;
            }
            word = translateWord(word);
            if (!word.toLowerCase().equals(word)) {
                word = word.toLowerCase();
                word = word.substring(0,1).toUpperCase() + word.substring(1);
            }
            if (flag) word+=lastsym;
            res += word + " ";
        }
        return res;
    }
    public static boolean validColor(String str) {
        String[] wp = str.substring(str.indexOf('(')+1, str.indexOf(')')).split(",");
        if (str.contains("rgba") && wp.length != 4 || (str.contains(",,"))) return false;
        for (int i = 0; i < wp.length ; i++) {
            double num = Double.parseDouble(wp[i]);
            if (i==3) {
                if (!(num >= 0 && num <= 1.0)) return false;
            }
            else if (!(num >= 0 && num <= 255)) return false;
        }
        return true;
    }
    public static String stripUrlParams(String url, String ...param) {
        String res = "", args = "";
        Map<String, String> map=new HashMap<>();
        String[] wp = url.split("\\?");
        res += wp[0];
        if (wp.length!=1) {
            wp = wp[1].split("&");
            for (int i = 0; i<wp.length; i++) {
                String[] pair = wp[i].split("=");
                if (map.containsKey(pair[0])) {
                    map.replace(pair[0], pair[1]);
                } else map.put(pair[0], pair[1]);
            }
            res+="?";
        }
        for (String pam : param) {
            if (map.containsKey(pam)) map.remove(pam);
        }
        for (String key : map.keySet()) {
            res += key + "=" + map.get(key) + "&";
        }
        return res.substring(0,res.length()-1);
    }
    public static String[] getHashTags (String str) {
        String[] wp = str.split(" ");
        String[] res = { "", "", ""};
        String max = "", maxes = "";
        int count = 0;
        while (count < 3) {
        for (String word : wp) {
            if (!Character.isLetter(word.charAt(word.length() - 1)))
                word = word.substring(0, word.length() - 1);
            if (max.length() < word.length() && !maxes.contains(word)) max=word;
        }
        if (max != "")
        res[count] = "#" + max.toLowerCase();
        maxes += max;
        max = "";
        count++;}
        return res;
    }
    public static int ulam(int n) {
        Vector<Integer> seq = new Vector<Integer>();
        seq.add(1); seq.add(2);

        for (int i = 3; i < 2000; i++) {
            int count = 0;
            for (int j = 0; j < seq.size()-1; j++) {
                for (int k = j + 1; k < seq.size(); k++) {
                    if (seq.get(j) + seq.get(k) == i) {
                        count++;
                    }
                    if (count > 1) break;
                }
                if (count > 1) break;
            }
            if (count == 1) seq.add(i);
        }
    return seq.get(n-1);
    }
    public static String longestNonrepeatingSubstring(String str) {
        String res = "", decoy = "", max = "";
        for (int i = 0; i < str.length(); i++) {
            String wc = Character.toString(str.charAt(i));
            if (!decoy.contains(wc)) decoy+=wc;
            else {
                if (max.length()<decoy.length()) max=decoy;
                decoy = wc;
            }
            if (max.length()<decoy.length()) max=decoy;
        }
        return max;
    }
    public static String convertToRoman(int num) {
        String res = "";
        while (num >= 1000) {
            res += "M";
            num -= 1000;        }
        while (num >= 900) {
            res += "CM";
            num -= 900;
        }
        while (num >= 500) {
            res += "D";
            num -= 500;
        }
        while (num >= 400) {
            res += "CD";
            num -= 400;
        }
        while (num >= 100) {
            res += "C";
            num -= 100;
        }
        while (num >= 90) {
            res += "XC";
            num -= 90;
        }
        while (num >= 50) {
            res += "L";
            num -= 50;
        }
        while (num >= 40) {
            res += "XL";
            num -= 40;
        }
        while (num >= 10) {
            res += "X";
            num -= 10;
        }
        while (num >= 9) {
            res += "IX";
            num -= 9;
        }
        while (num >= 5) {
            res += "V";
            num -= 5;
        }
        while (num >= 4) {
            res += "IV";
            num -= 4;
        }
        while (num >= 1) {
            res += "I";
            num -= 1;
        }
        return res;
    }
    public static boolean formula (String str) {
        String[] full = str.split("=");
        int[] reslts = new int[full.length];
        for (int j =0; j < full.length; j++) {
            if (full[j].length()==1) reslts[j] = Integer.parseInt(full[j]);
            else {
            String[] wp = full[j].split(" ");
        for (int i = 0; i < wp.length; i++) {
            switch (wp[i]) {
                case "*":
                    wp[i+1] = String.valueOf(Integer.parseInt(wp[i-1])*Integer.parseInt(wp[i+1]));
                    break;
                case "/":
                    wp[i+1] = String.valueOf(Integer.parseInt(wp[i-1])/Integer.parseInt(wp[i+1]));
                    break;
                case "+":
                    wp[i+1] = String.valueOf(Integer.parseInt(wp[i-1])+Integer.parseInt(wp[i+1]));
                    break;
                case "-":
                    wp[i+1] = String.valueOf(Integer.parseInt(wp[i-1])-Integer.parseInt(wp[i+1]));
                    break;
                default:
                    break;
                }
            }
        reslts[j] = Integer.parseInt(wp[wp.length-1]);
            }
        }
        int check = reslts[0];
        for (int i = 1; i < reslts.length; i++) {
            if (reslts[i]!=check) return false;
        }
        return true;
    }
    public static boolean palindromeDescendant(int num) {
        String wp = String.valueOf(num);
        while (wp.length()%2==0) { //
            String decoy = "";
            if (isPalindrome(wp)) return true;
            else {
                for (int i=0; i < wp.length()-1; i=+2) {
                    decoy += Character.getNumericValue(wp.charAt(i)) + Character.getNumericValue(wp.charAt(i+1));
                }
                wp = decoy;
            }
        }
        return false;
    }
    public static boolean isPalindrome(String str) {
        StringBuilder sb = new StringBuilder(str);
        boolean fact = sb.equals(sb.reverse());
        return fact;
    }
}
