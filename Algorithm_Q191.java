public class Algorithm_Q191 {
    /*
     * 이 문제는 언뜻 보기에는 쉽지만, 사실 생각해봐야할 부분이 많은 문제이다.
     * https://leetcode.com/problems/buddy-strings/
     * 풀이를 확인해보자.
     */
    
    public boolean buddyStrings(String s, String goal) {
        // edge case 두 문자열이 길이가 서로 다를 때 절대 swap으로 같은 문자열이 나올 수 없으므로, false
        if(s.length() != goal.length()) {
            return false;
        }
        // 만약 2 문자열이 같다면
        if(s.equals(goal)) {
            int[] freq = new int[26];
            // 하나의 문자가 2개가 나오면 그걸 바꾸면 같은 문자열이므로 true
            for(char c : s.toCharArray()) {
                freq[c - 'a'] += 1;
                if(freq[c - 'a'] == 2) {
                    return true;
                }
            }
            // 위 조건에 맞는게 없으면 false 처리한다.
            return false;
        }
        
        int firstIndex = -1;
        int secondIndex = -1;
        
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != goal.charAt(i)) {
                if(firstIndex == -1) {
                    // 첫번째 다른 문자 체크
                    firstIndex = i;
                } else if(secondIndex == -1) {
                    // 두번째 다른 문자 체크
                    secondIndex = i;
                } else {
                    // 만약 서로 다른 문자가 3개 이상이 된다면 바꿔도 같은 문자열이 될 수 없으므로 false 처리
                    return false;
                }
            }
        }

        if(secondIndex == -1) {
            // 서로 다른 문자가 1개 위치 뿐일 때
            return false;
        }

        // 딱 2개의 위치가 체크될 때 각 문자의 first to second가 서로 같으면 true (바꾸면 같은 문자열이 됨)
        if(s.charAt(firstIndex) == goal.charAt(secondIndex) && s.charAt(secondIndex) == goal.charAt(firstIndex)) {
            return true;
        } else {
            // 아니라면 false 처리 (바꿔도 같은 문자열이 안됨)
            return false;
        }
    }
}
