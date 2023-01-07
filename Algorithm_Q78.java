public class Algorithm_Q78 {
    /*
     * 또 다른 그리디 문제이다. 이 문제는 이동 비용에 대해서 가능한지 여부 체크와 함께 가능한 경로인지 여부 체크가 필요한 문제다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/gas-station/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int rest_gas = 0;
        int answer = 0;
        int n = gas.length;
        int total_cost = 0;
        int total_gas = 0;
        
        // 전체 사용 가능 비용과 충전 가능 가스 량을 구한다.
        for(int i = 0; i < n; i++) {
            total_gas += gas[i];
            total_cost += cost[i];
        }

        // 만약 어떤 경로를 통해 가더라도 충전 가스 총량이 이동 총량보다 커야 한바퀴 순회가 가능하므로,
        // 이 조건을 만족 못한다면 -1로 리턴한다.
        if(total_cost > total_gas) {
            return -1;
        }

        // 0번 부터 시작했을 때, 만약 다음으로 진행하려고 할 때의 가스 잔량이 음수라면 해당 시작점은
        // 답이 아니다. 따라서 남은 가스 잔량을 0으로 리셋하고 현재 인덱스의 다음 인덱스로 시작점을 바꿔서 다시 진행한다.
        // 왜냐하면 현재 인덱스에서 가스잔량 계산을 할 때 가야할 비용을 빼면 음수라서 if문을 가는 것이기 떄문이다.
        // 현재 인덱스에서 가스가 얼마가 남았든 이동 비용이 너무 커서 음수인 것이기 때문에
        // 현재 인덱스가 아니라 다음 인덱스에서 계산을 해 봐야 하는 것이다.
        for(int i = 0; i < n; i++) {
            rest_gas += (gas[i] - cost[i]);
            if(rest_gas < 0) {
                answer = i + 1;
                rest_gas = 0;
            }
        }

        return answer;
    }
}
