public class Algorithm_Q153 {
    /*
     * 이 문제는 DFS를 활용한 문제다.
     * 구현을 위해서 연관되는 문자열들이 존재할때 계속해서 DFS를 이어갈 수 있도록 처리하는게 핵심이다.
     * https://leetcode.com/problems/similar-string-groups/
     * 풀이를 확인해보자.
     */

    public int numSimilarGroups(String[] strs) {
        // 이미 그룹에 속해 있는지 여부를 체크하기 위한 visited 선언
        // true : 그룹에 속해 있음, false : 그룹에 속해 있지 않음
        boolean[] visited = new boolean[strs.length];
        // 그룹의 수 answer
        int answer = 0;

        // 모든 string을 순회할때
        for(int i = 0; i < strs.length; i++) {
            // 만약 현재 그룹에 속해있지 않은 문자열이라면
            if(!visited[i]) {
                // 그룹의 수를 1 추가 시키고 dfs를 순회하여 그룹에 속하는 문자열들을 솎아낸다.
                answer += 1;
                dfs(strs, visited, i);
            }
        }

        return answer;
    }

    public void dfs(String[] strs, boolean[] visited, int index) {
        // 현재 문자열의 인덱스는 그룹에 속하게 되었으므로, true로 처리
        visited[index] = true;
        // 지금 문자열을 받고
        String curr = strs[index];

        // 다시 문자열을 순회할때
        for(int i = 0; i < strs.length; i++) {
            // 그룹에 속해있지 않은 문자열이면서 지금 문자열과 similar 조건이 맞는 문자열이라면
            if(!visited[i] && isSimilar(curr, strs[i])) {
                // dfs를 다시 해당 인덱스인 i 부터 돌아본다/
                dfs(strs, visited, i);
            }
        }
    }

    // similar 여부를 가리는 함수
    public boolean isSimilar(String a, String b) {
        int differentChars = 0;

        // 문제 조건에 두 문자열의 길이는 같다고 했으므로 2개의 문자열을 문자 배열로 만든 후 순회
        char[] aCharArr = a.toCharArray();
        char[] bCharArr = b.toCharArray();

        for(int i = 0; i < aCharArr.length; i++) {
            // 만약 각 포지션을 순회하면서 문자가 서로 다른게 있다면 이 숫자를 센다.
            if(aCharArr[i] != bCharArr[i]) {
                differentChars += 1;
            }
        }

        // 서로다른 문자가 2개거나 아무것도 없을 때 true를 주고 이외에는 false 처리한다.
        return (differentChars == 2 || differentChars == 0);
    }
}
