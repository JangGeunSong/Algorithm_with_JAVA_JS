public class Algorithm_Q200 {
    /*
     * 데이터를 다루는 데 특정 확률에 대한 계산을 어떻게 최대 확률까지만 가고 계산을 덜 할지, 그리고 이미 한 계산을 어떻게 또 안할지를 생각해야 하는 문제다.
     * 이를 위해 memozation DP 를 썼고, 이외에 계산에 대한 부분이 존재한다.
     * https://leetcode.com/problems/soup-servings/
     * 풀이를 확인해보자.
     */

    public double soupServings(int n) {
        // 종료 조건 : 만약 스프의 양이 4800을 넘어가면 이때는 뭘 어떻게 해도 항상 1의 값이 나오게 된다.
        if(n > 4800) {
            return 1;
        }

        // 이 외의 경우 게산을 진행
        double answer = 0.0;
        // 이미 특정 용량에서 확률을 계산해 냈다면, 특정 operation 계산중 마주 쳤을 때 또 계산 안해도 되도록 값을
        // memozation -> 그래서 DP 문제임
        // 각 지수에 대해서 n을 25로 나누면 단순화 할 수 있음.
        n = (int)Math.ceil(n * (1.0 / 25));
        double[][] dp = new double[n + 1][n + 1];
        // 가장 처음 step은 아무 것도 안하고 먼저 2개의 값을 볼 때이다.
        answer = getPossibility(n, n, dp);

        return answer;
    }

    public double getPossibility(int A, int B, double[][] dp) {
        // 종료 조건 1 : A와 B가 모두 비어있거나 혹은 둘 다 음수일 때
        if(A <= 0 && B <= 0) {
            // 해당 조건의 결과를 step에 맞춰 계산 했을 떄 위 결과는 확률의 절반을 곱하라고 했으므로, 0.5
            return  0.5;
        }

        // 종료 조건 2 : A 만 다 사용했을 때
        if(A <= 0) {
            return 1;
        }

        // 종료 조건 3 : B 만 다 사용했을 때
        if(B <= 0) {
            // 그냥 0을 준다.
            return 0;
        }

        // 종료 조건 4 : dp에 저장한 값이 이미 존재한다면, 그 값을 리턴한다.
        if(dp[A][B] > 0) {
            return dp[A][B];
        }

        // 현재 스프의 남은 양 A, B 에 대해서 계산 확률은 각 계산을 DFS로 돌았을 떄 얻는 확률에 각 시행이 0.25 확률 이므로 이를 곱한 값에 합산과 같다. (각각의 시행은 독립이니까 독립된 경우 시행 * 일어날 확률 의 합산인데 일어날 확률이 같아서 묶어서 계산함)
        dp[A][B] = 0.25 * (
            getPossibility(A - 4, B, dp)
            + getPossibility(A - 3, B - 1, dp)
            + getPossibility(A - 2, B - 2, dp)
            + getPossibility(A - 1, B - 3, dp)
        );

        return dp[A][B];
    }
}
