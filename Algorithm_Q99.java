public class Algorithm_Q99 {
    /*
     * 이 문제는 간단한 배열을 사용한 문제이다.
     * 다만 빠르게 구현하려다 보면 실수가 날 수 있으므로, 생각을 하고 풀어야 한다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/shuffle-the-array/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int[] shuffle(int[] nums, int n) {
        // 셔플의 결과물인 answer를 선언
        int[] answer = new int[2 * n];
        // x좌표, y좌표, 현재 채워야 하는 index 위치를 셋업
        int x = 0;
        int y = n;
        int index = 0;

        // n번 loop를 돌아야 함
        for(int i = 0; i < n; i++) {
            // 현재 index에는 x좌표의 값을 바로 1 더해지는 위치에는 y좌표의 값을 셋업한다.
            answer[index] = nums[x];
            answer[index + 1] = nums[y];
            // 이후 index는 2를 더하고 나머지 좌표를 1씩 더해서 이동한다.
            // index는 2칸씩 채워지기 때문에 2를 더한다.
            x += 1;
            y += 1;
            index += 2;
        }

        // 결과를 리턴한다.
        return answer;
    }
}
