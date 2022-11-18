import java.util.*;

public class Algorithm_Q41 {
    /*
     * 해당 문제는 HashMap과 sliding window를 사용해서 풀이해야 하는 문제이다.
     * sliding window의 경우 two pointer를 사용해 푸는 경우가 많으므로, 이 점을 고려하며 풀이를 확인하면 더 수월하다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/minimum-window-substring/
     * 풀이를 확인해보자.
     */

    /*
     * 처음 나의 생각 (머리속에 있던 생각)
     * ACCCCCAABAACACABBBACACAAAAABBBACCA
     * A 0 6 7
     * B 8
     * C 1 2 3 4 5
     * 만약 같은 알파벳이 여러개라면, 이때는 어떻게 할지 고민이 필요 => map으로 길이를 재면 될듯
     * min = 5 max = 10
     * if create the target substring then all peek value iterating and find min and max value
     * if min is same as that keys peek value then switch min value at that time
     * and new min value is min vlaue of the other keys peek value
     * hashset 내부에서 서브스트링이 만들어 지는 순간의 set에 가장 큰 idx와 가장 작은 idx의 차이를 빼면
     * subString을 만들 수 있는 값이 얻어진다.
     * 만약 중복으로 같은 알파벳이 나왔다면, 이때는 가장 나중에 나온 인덱스로 재 매핑함.
     * 이후 해시맵에서 가장 작은 값이 변화했으므로, 이 값을 찾아주고,
     * 현재 인덱스 - 다시 구한 가장 작은 값 으로 만들어서 길이를 구한 후 해당 길이가 가장 작다면,
     * 가장 작은값 = 서브 스트링의 시작점
     * 현재 인덱스 = 서브 스트링의 끝점
     * 으로 재 정의한다.
     * 이후 반복문이 종료되면, String의 sub string 함수를 사용해 결과값을 얻어준다.
     * 
     * 위 생각에서 간과한 부분은 two pointer를 사용 하는데 어떻게 보아야 할지 몰랐다는 점이다.
     * 그래서 hash set을 통해 문제를 어떻게 풀까만 고민했다. 중복이 없는 문자열이라면 이게 가능한데, 타켓 문자가 가능한 문자열의 경우에는 위 생각이
     * 전개하면 답이 나오지 않았다.
     * 이를 위해 고민했고, 아래 링크를 통해 이 생각을 다시 정리할 수 있었다.
     * https://leetcode.com/problems/minimum-window-substring/solutions/1496754/java-tc-o-s-t-sc-o-t-space-optimized-sliding-window-using-two-pointers/
     *
     * 최종 풀이 코드
     */
    public String minWindow(String s, String t) {
        String answer = "";
        // 2개의 포인터를 활용해 문장을 만들어 내면 되므로 이 두 포인터를 담당할 변수를 선언
        int left = 0;
        int right = 0;
        int t_length = t.length();
        // 최소 길이를 가질 때, 이 부분 문자열의 시작 지점
        int min_left = 0;
        int minLen = Integer.MAX_VALUE;
        // target 문장에서 문자의 개수
        Map<Character, Integer> map = new HashMap<>();

        // 만약 타겟 문장이 탐색해야 할 문장보다 길다면, 부분 문자열은 존재하지 않으므로 
        if(s.length() < t.length()) {
            return answer;
        }

        // 현재 target 문장에서 해당 문자의 개수를 센다.
        for(int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        // 우선 처음부터 끝까지 계속 진행할 인덱스는 right이다.
        // 이 인덱스가 s 문자열의 끝까지 가지 않는다면 반복문을 계속 진행한다.
        while(right < s.length()) {
            // right 인덱스에 있는 문자를 꺼낸다.
            char end_char = s.charAt(right);
            // 만약 이 문자가 map에 key가 있다면, 부분 문자열의 멤버이므로 아래 if를 진행한다.
            if(map.containsKey(end_char)) {
                // 해당 맵에서 문자의 남은 개수를 가져온다.
                int count = map.get(end_char);
                // 만약 이 개수가 0보다 크면 부분 문자열로 가기 위해 필요한 단어수가 부족한 것이므로
                // 현 시점에서 이 문자가 등장 했으니 이제 1을 빼주면 된다.
                if(count > 0) {
                    // 부분 문자열 중 하나의 문자가 얻어 졌기 때문에, 전체 t 길이에서 1을 뺀다.
                    t_length -= 1;
                }
                // map 에서도 해당 문자의 필요 개수가 1 떨어 져야 한다.
                map.put(end_char, count - 1);
            }

            // 오른쪽으로 한칸 인덱스 이동
            right += 1;

            // 만약 부분 문자열이 만들어 지는 조건인 t의 길이가 0에 도달했다면
            while(t_length == 0) {
                // 최소 길이 여부를 파악한다.
                if(minLen > right - left) {
                    // 만약 최소 길이가 갱신 되어야 한다면 진행
                    minLen = right - left;
                    // 이때 이 부분 문자열의 시작점은 left 포인터 이므로
                    // 이 left로 min_left를 갱신
                    min_left = left;
                }
                // 현재 start 인덱스에 문자를 불러온다.
                char start_char = s.charAt(left);
                // 만약 이 문자가 map에 있다면, 부분 문자열을 만드는데 필요한 멤버일 떄 이다.
                if(map.containsKey(start_char)) {
                    // 해당 멤버의 남은 개수를 가져온다.
                    int start_count = map.get(start_char);
                    if(start_count == 0) {
                        // 만약 남은 개수가 0 이라면 이 부분 문자열의 scope에서 
                        // 해당 문자가 사라지는 것이므로, 부분 문자열이 만들어지는 판별 조건인
                        // 잔여 t 길이를 1 증가 시킨다.
                        t_length += 1;
                    }
                    // 해당 멤버의 값을 1 증가 시킨다.
                    map.put(start_char, start_count + 1);
                }
                // 왼쪽 인덱스를 1 증가 시킨다.
                left += 1;
            }
            // 이후 반복문 다시 무한 진행 해서 종료 조건까지 간다.
        }

        // 만약 최소 길이가 정수의 Max value 값이라면, 최소값 갱신이 안되었다는 이야기이고
        // 이는 부분 문자열을 못 만들었다는 이야기이다. 왜냐하면 문제에서 길이 제한이 10^5 였기 때문이다.
        if(minLen == Integer.MAX_VALUE) {
            answer = "";
        } else {
            // 그게 아니라면 부분 문자열이 만들어 졌을 것이므로, 이를 만들기 위해
            // 부분 문자열의 시작점인 min_left와 min_left + minLen 으로 부분 문자열을 만든다.
            answer = s.substring(min_left, min_left + minLen);
        }

        // 얻은 결과를 리턴
        return answer;
    }

}
