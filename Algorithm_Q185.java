public class Algorithm_Q185 {
    /*
     * 이 문제는 간단한 현재 고도 계산으로 지금부터 길을 떠났을 때 가장 높은 고도는 언제였는지 찾는 문제이다.
     * https://leetcode.com/problems/find-the-highest-altitude/
     * 풀이를 확인해보자.
     */

    public int largestAltitude(int[] gain) {
        // 현재 고도 + 1이 현재 마주친 고도 + 지금 고도 임을 생각하며 풀이
        int n = gain.length;
        int answer = Integer.MIN_VALUE;
        int[] road = new int[n + 1];

        // 초기 값은 0 으로 세팅
        road[0] = 0;

        // 모든 길을 타고 다니면서 데이터를 쌓고 이때 현재 시점이 최대값인지 판별해 answer에 담는다.
        // 이때 반드시 이전 경로의 데이터를 갖고 있어야 한다.
        for(int i = 0; i < n; i++) {
            road[i + 1] = road[i] + gain[i];
            answer = Math.max(Math.max(road[i + 1], road[i]), answer);
        }

        // 결과를 리턴한다.
        return answer;
    }
}
