public class Algorithm_Q98 {
    /*
     * 이 문제는 두 문자열을 통해 원하는 조건을 확인하는 문제이다.
     * 시간 초과를 받지 않으면서 어떻게 풀이할지를 고민해야 된다. 단순히 순회만 돌면 O(n^2)가 날 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/permutation-in-string/
     * 풀이를 확인해보자.
     */

    public boolean checkInclusion(String s1, String s2) {
        // s1이 가지고 있는 문자의 갯수들을 세기 위해 아래와 같이 배열을 생성하여 저장한다.
        // 이때, 순열이 될뻔 하다가 못되는 구간이 존재할 수 있기 때문에 사전에 원본 배열을 하나 더 만든다.
        int[] elemArr = new int[26];
        int[] originArr = new int[26];

        // s1에 있는 모든 문자의 갯수를 저장
        for(int i = 0; i < s1.length(); i++) {
            elemArr[s1.charAt(i) - 'a'] += 1;
            originArr[s1.charAt(i) - 'a'] += 1;
        }

        // s1.length() == m, s2.length() == n 일때
        // 아래 loop는 O(m(n - m)) 의 횟수로 순회를 돌게 된다.
        // 만약 m > n 이면 아예 아래 loop는 돌지 않는다.
        // 또한 m이 아무리 크거나 작아도 결국은 아래와 같이 되므로
        // m ~= n -> O(m(n - m)) -> O(m) // m <<< n -> O(m(n - m)) -> O(n) 즉 TLE가 나지 않는다.
        // 문자열을 순회 하면서 판별을 진행한다.
        // 우선 s1의 길이만큼 확인 하면서 모든 문자가 "연속으로"존재하면 순열이 되는 것임을 고려해서
        // 첫 번째 loop는 s2의 길이 - s1의 길이 만큼이 문자열의 첫 문자로 시작해야 함을 고려해 만든다.
        for(int i = 0; i <= s2.length() - s1.length(); i++) {
            // 이후 두 번째 loop는 s1의 길이 만큼만 순회를 돌면서 모든 문자가 존재하는지 elemArr에 1씩 빼며 확인한다.
            for(int j = i; j < i + s1.length(); j++) {
                if(elemArr[s2.charAt(j) - 'a'] > 0) {
                    elemArr[s2.charAt(j) - 'a'] -= 1;
                    // 만약 모든 요소가 0임을 확인하면 순열이 되는 것이기 때문에 true를 리턴하면서 종료한다.
                    if(isAllZero(elemArr)) {
                        return true;
                    }

                }
            }
            // 위 loop를 도는 동안 all Zero가 안되었다면 현재 위치로 오게 되므로 originArr에 있던 정보로 초기화 시킨다.
            for(int k = 0; k < 26; k++) {
                elemArr[k] = originArr[k];
            }
        }

        // 위 loop가 돌았는데도 true가 return 안되었다면 순열 형성이 안되는 것이기 떄문에 false를 리턴한다.
        return false;
    }

    // 모든 배열의 요소가 0인지 판별하는 함수
    public boolean isAllZero(int[] elems) {
        for(int i = 0; i < elems.length; i++) {
            if(elems[i] > 0) {
                return false;
            }
        }

        return true;
    }
}
