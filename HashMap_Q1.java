import java.util.*;

class HashMap_Q1 {

    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        int val =0;

        Map <String, Integer> hm = new HashMap<>();

        for(String part : participant) {
            if(hm.get(part) == null)
                hm.put(part,1);
            else{
                val = hm.get(part) + 1;
                hm.put(part,val);
            }
        }

        for(String comp : completion) {
            val = hm.get(comp) - 1;
            hm.put(comp,val);
        }

        for(String key : hm.keySet()) {
            if(hm.get(key) == 1) answer = key;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] part = {"leo", "kiki", "eden"};
        String[] comp = {"eden", "kiki"};
        System.out.println(solution(part, comp));
    }

}