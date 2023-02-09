import java.util.*;

public class Algorithm_Q101 {
    /*
     * 이 문제는 해시 테이블을 활용하고 이를 통해 만들 수 있는 이름의 경우의 수를 계산해 내는 문제이다.
     * 난이도가 hard 인 이유는 이 문제 자체가 수학 계산을 해야 하는 문제이면서, 동시에 hash에 대한 이해가 있어야 하기 때문이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/naming-a-company/
     * 풀이를 확인해보자.
     */

    public long distinctNames(String[] ideas) {
        HashMap<Character, HashSet<String>> stringGroup = new HashMap<>();
        HashSet<String> set = null;
        HashSet<String> set2 = null;
        int overlapCount = 0;
        long answer = 0L;

        // 알파벳 순서대로 hash set을 만들어서 map으로 보관한다.
        for(int i = 0; i < 26; i++) {
            stringGroup.put((char)('a' + i), new HashSet<>());
        }

        // 첫 알파벳을 key로 해서 hash set에 담아서 map에 보관한다.
        for(String idea : ideas) {
            set = stringGroup.get(idea.charAt(0));
            set.add(idea.substring(1, idea.length()));

            stringGroup.put(idea.charAt(0), set);
        }

        // 모든 해시맵을 순회
        for(int i = 0; i < 25; i++) {
            // 만약 사이즈가 0 이라면 해시셋 안에 아무것도 없는 것이기 때문에 continue
            set = stringGroup.get((char)('a' + i));
            if(set.size() == 0) {
                continue;
            }
            for(int j = i + 1; j < 26; j++) {
                set2 = stringGroup.get((char)('a' + j));
                // 겹치는 문자의 갯수를 세기위한 count 초기화
                overlapCount = 0;
                if(set2.size() == 0) {
                    continue;
                }
                // 겹치는 문자의 수를 계산
                for(String str : set) {
                    if(set2.contains(str)) {
                        overlapCount += 1;
                    }
                }
                // 이름을 정하는 경우의 수는 각 set 버켓당 
                // A 그룹의 안겹치는 문자의 수(안 겹치는 문자들 중 1개 선택)
                // * B 그룹의 안 겹치는 문자의 수(안 겹치는 문자들 중 1개 선택)
                // * 2 (A 그룹과 B 그룹에서 고른 단어를 순서를 바꾸면 이름이 달라지기 때문에 2를 곱한다)
                // 위 계산을 해서 얻은 숫자를 answer에 더한다.
                answer = answer + (2 * (set.size() - overlapCount) * (set2.size() - overlapCount));
            }
        }

        // 결과를 리턴한다.
        return answer;
    }
}
