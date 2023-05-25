import java.util.*;

public class Algorithm_Q171 {
    /*
     * 이 문제는 DP와 sliding window를 통해 풀이해야 하는 문제이다.
     * 문제 자체로 조건이 복잡하기 때문에 이해하기 위해서는 반드시케이스를 그려보고 파악해야 한다.
     * 수학 문제이므로, 경우의 수를 구하는 논리를 잘 파악하도록 해보자.
     * https://leetcode.com/problems/new-21-game/
     * 풀이를 확인해보자.
     */

    public double new21Game(int n, int k, int maxPts) {
        // ### 문제가 대단히 복잡하기 때문에 아래 링크의 풀이를 꼭 참고할것 ###
        // https://www.youtube.com/watch?v=zKi4LzjK27k&ab_channel=NeetCodeIO

        // 종료 조건 : 만약 K 가 0 이라면 어떤 값을 뽑더라도 stop이 되며
        // (문제에서 K보다 커지면 더 이상 뽑기를 하지 않는다고 했다.), 이때 문제안에서 n이 0부터 시작하기
        // 때문에, n 이하 즉, n 포함해서 그보다 작은 값이 될 수 있는 경우의 수가 100% 이므로, 1.0을 리턴한다.
        if(k == 0) {
            return 1.0;
        }

        // sliding window로 풀기 위해 현재 window 안에서 문제의 조건 (윈도우 범위는 1 ~ maxPts 까지)
        // 즉, 1 ~ maxPts 사이에서 K 보다 작게 나오게 만드는 숫자는 1로 아닌 숫자는 0으로 두고
        // 이들을 일렬로 늘여 놓을 때의 해당 window의 합이다.
        double windowSum = 0.0;
        // DP -> hashmap으로 해당 key값이 시작 점 일때, K가 되면 멈추고, 이때 n 보다 작을 확률이 value값이다.
        Map<Integer, Double> dp = new HashMap<>();

        // windowSum 을 구한다.
        for(int i = k; i < k + maxPts; i++) {
            // 해당 창의 합산값은 K부터 시작 즉, 뽑기를 멈춰야 하는 시점 부터 maxPts 값 사이에 있는 모든 숫자들을 순회
            // 이때 K ~ K + maxPts - 1 사이에 값들 중 n 보다 작거나 같으면 이는 문제에서 허용하는 경우의 수 이므로
            // windowSum에서 이 지점은 1.0을 더해 가능 함을 체크한다. 그게 아니라면 아무것도 안한다.
            if(i <= n) {
                windowSum += 1.0;
            }
        }

        // 이제 K 부터 0까지 역으로 이동하면서 진행
        for(int i = k - 1; i >= 0; i--) {
            // DP에서 지금 시점의 문제 조건을 만족할 확률은
            // windowSum을 구한 곳에서 maxPts를 나누었을 때 값이 된다.
            // 즉 전체 숫자 구간에서 1.0이 되는 곳만 OK 인 것이므로, 
            // 이 확률이 (조건에 맞는 곳 / 전체 경우의 수) 인 것이다.
            dp.put(i, windowSum / (double) maxPts);
            // 이제 1 왼쪽 으로 즉 -1 이동하게 되면 이제 window가 한칸 왼쪽 이동 했으니 가장 오른쪽에 있던 값을
            // 뺴야 한다.
            // 이는 현재 구간 + maxPts값이 n 이하가 될 때 dp에 확률값이 있다면, 그 확률을 뺴면 되고, 아니라면 1.0을 뺀다
            // 이때 i + maxPts가 n보다 크다면 어차피 거기는 처음부터 0 이었을 것이므로, 그냥 0.0으로 그대로 둔다.
            double remove = 0.0;
            if(i + maxPts <= n) {
                remove = dp.getOrDefault(i + maxPts, 1.0);
            }
            // 이제 해당 window에 총 합산값은 지금 시점에서 얻은 확률을 더하고, 윈도우에서 가장 우측에 있던 값을 뺀다.
            windowSum += dp.get(i) - remove;
        }

        // 이렇게 쯕 왼쪽으로 이동하다 보면 for문의 조건때문에 0까지 이동할 수 밖에 없는데, 이떄 0은 문제에서 요구하는
        // 시작점이고, 이 시작점에서 얻을 수 있는 문제 조건에 맞는 확률을 구하는 것이기 때문에 dp HashMap에서 해당 값을 
        // 리턴한다.
        return dp.get(0);
    }
}
