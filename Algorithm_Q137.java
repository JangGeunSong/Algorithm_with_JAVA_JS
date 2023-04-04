import java.util.*;

public class Algorithm_Q137 {
    /*
     * 이 문제는 중복되는 문자가 나타날때 잘라서 부분 문자열을 만들 때 그 최소 값을 묻는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/optimal-partition-of-string/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int partitionString(String s) {
        // 부분 문자열에 내용 체크를 하기 위해서 hashset을 사용해 중복되는 문자가 나타나는지 감시
        Set<Character> set = new HashSet<>();
        int answer = 0;
        
        // 모든 문자를 주어진 문자열에서 순회
        for(char c : s.toCharArray()) {
            // set 안에 현재 문자가 있다면, 중복 문자에 의해 여기서 부분 문자열로 하나 자르고 set을 비운 후
            // 현재 문자를 set에 삽입
            if(set.contains(c)) {
                answer += 1;
                set.clear();
                set.add(c);
            } else {
                // set 안에 현재 문자가 없다면 set에 문자 삽입
                set.add(c);
            }
        }

        // 모든 순회가 끝난 후 set에 하나 이상의 데이터가 남아 있다면 이들끼리 하나의 부분 문자열이 더 생성되어야 함
        // 따라서 answer에 1을 더하고 아니면 무시하고 넘어간다.
        if(set.size() > 0) {
            answer += 1;
        }

        return answer;
    }
}
