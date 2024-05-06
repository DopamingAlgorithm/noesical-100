package programmers;

import java.util.Arrays;

public class programmers_가장큰수 {
    class Solution {
        public String solution(int[] numbers) {
            String[] numStrs = new String[numbers.length];
            StringBuilder st = new StringBuilder();

            for(int i = 0; i < numbers.length; i++) numStrs[i] = Integer.toString(numbers[i]);

            Arrays.sort(numStrs, (o1, o2) -> {
                String a = o1 + o2;
                String b = o2 + o1;
                return b.compareTo(a);
            });

            if(numStrs[0].equals("0")) return "0";

            for(String str : numStrs) st.append(str);

            return st.toString();
        }
    }
}
