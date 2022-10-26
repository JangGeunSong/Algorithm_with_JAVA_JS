import java.util.*;

public class Algorithm_Q21 {
    /*
     * 해당 문제는 HashMap과 String 함수들을 활용하여 풀 수 있는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/group-anagrams/
     * 이 문제는 내가 푼 버전은 hashmap을 사용하지 않는 버전이 있고, hashmap을 사용하는 버전이 있다.
     * 당연히 hashmap을 사용한게 더 성능이 좋기 때문에, 두 풀이 모두 기억해서 풀이하면 좋다.
     * 풀이를 확인해보자.
     */ 

    /*
     * 나의 풀이로 hashmap을 사용하지 않고 문자열이 해당 그룹에 참여 했는지 여부를 확인할 수 있는 boolean 배열과 2개의 char배열을 통해 서로 비교하여 맴버에 들어가도 되는지 
     * 여부를 체크하여 맞다면 list에 삽입처리해 정리 하는 방식을 사용하였다.
     * 해당 풀이는 문자열을 여러번 반복문을 돌아야 하기 때문에 문제의 조건에서 벗어나 문자열 길이가 더 길어지면 너무 많은 처리 시간을 소요한다.
     * 이런 풀이법도 가능하다 정도로만 기억하고, 실제 풀이는 아래 best practice를 보는것이 더 좋다.
     */
    public List<List<String>> groupAnagrams_mysolution(String[] strs) {
        List<List<String>> answer = new ArrayList<>();
        boolean[] isEnterdGroup = new boolean[strs.length];

        char[] leaderArr = null;
        char[] memberArr = null;

        boolean isMember = false;

        Arrays.fill(isEnterdGroup, false);

        for(int i = 0; i < strs.length; i++) {
            if(isEnterdGroup[i]) {
                continue;
            }
            List<String> list = new ArrayList<>();
            list.add(strs[i]);
            isEnterdGroup[i] = true;
            leaderArr = strs[i].toCharArray();
            Arrays.sort(leaderArr);
            // 다른 문자열을 순회
            for(int j = 0; j < strs.length; j++) {
                // 만약 길이가 다른 문자열이거나, 이미 그룹에 포함되어 있는 문자열이라면, 아래 로직을 진행하지 않도록 처리
                if(isEnterdGroup[j] || strs[i].length() != strs[j].length()) {
                    continue;
                }
                isMember = true;
                memberArr = strs[j].toCharArray();
                Arrays.sort(memberArr);
                for(int idx = 0; idx < leaderArr.length; idx++) {
                    if(leaderArr[idx] != memberArr[idx]) {
                        isMember = false;
                        break;
                    }
                }
                if(isMember) {
                    list.add(strs[j]);
                    isEnterdGroup[j] = true;
                }
            }
            answer.add(list);
        }

        return answer;
    }

    /*
     * Best practice
     * 문자열 배열을 한번만 진행한다.
     * hashmap을 key : 오름차순 or 내림차순으로 정렬한 신규 문자열 
     *           value : 해당 문자열을 사용한 애너그램 그룹 list
     * 만약 문자열을 위의 key 처럼 정렬해서 새로 뽑을때, hashmap에 이 문자열이 존재한다면,
     * 이 문자열을 key값으로 들고 있는 list를 불러서 그 list에 현재 strs[index] 를 add 한다.
     * 이후 이 hashmap을 순회하면서 list들을 모두 answer 안에 add 시켜주기만 하면 된다.
     */
    public List<List<String>> groupAnagrams_best_practice(String[] strs) {
        List<List<String>> answer = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> mapList = null;
        
        String str = "";
        
        for(int i = 0; i < strs.length; i++) {
            str = sortStringChars(strs[i]);
            if(map.get(str) == null) {
                List<String> list = new ArrayList<String>();
                list.add(strs[i]);
                map.put(str, list);
            } else {
                mapList = map.get(str);
                mapList.add(strs[i]);
                map.put(str, mapList);
            }
        }
        
        Iterator<String> itr = map.keySet().iterator();
        
        while(itr.hasNext()) {
            answer.add(map.get(itr.next()));
        }
        
        return answer;
    }
    
    public String sortStringChars(String str) {
        
        char[] arr = str.toCharArray();
        
        Arrays.sort(arr);
        
        return String.valueOf(arr);
        
    }
}
