import java.util.*;

public class Algorithm_Q50 {
    /*
     * 해당 문제는 hash table을 사용한 문제이다. 
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/unique-number-of-occurrences/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public boolean uniqueOccurrences_mine(int[] arr) {
        // Hash map 과 Hash set을 사용하면 쉽게 해결할 수 있다.
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        Iterator<Integer> keys = map.keySet().iterator();

        while(keys.hasNext()) {
            Integer i = keys.next();
            if(set.contains(map.get(i))) {
                return false;
            } else {
                set.add(map.get(i));
            }
        }

        return true;
    }

    // 같지만 더 나은 표현
    public boolean uniqueOccurrences_better_solution(int[] arr) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int num : arr)
        {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        Set<Integer> set = new HashSet<Integer>();
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            
            if (!set.add(entry.getValue())) return false;
        }
        
        return true;
    }
}