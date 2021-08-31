package Set_01;

public class Rome {
    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {
        int ans = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            char c = s.charAt(i);
            if (c == 'I')
                ans += (ans >= 5 ? -1 : 1);
            else if (c == 'V')
                ans += 5;
            else if (c == 'X')
                ans += (ans >= 50 ? -10 : 10);
            else if (c == 'L')
                ans += 50;
            else if (c == 'C')
                ans += (ans >= 500 ? -100 : 100);
            else if (c == 'D')
                ans += 500;
            else if (c == 'M')
                ans += 1000;
        }
        return ans;
    }
}
