public class Algorithm_Q7 {
    /*
     * 해당 문제는 DP와 memozation에 대한 기초 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/climbing-stairs/
     * 해당 문제의 풀이는 내가 풀이한 과거 버전과 현재 버전이 들어 있다.
     * 각각에 대한 내용은 둘다 좋은 풀이법인데, 재귀로 가냐, 아니면 반복으로 가느냐에 따라 갈리기만 한다.
     */ 

    public int climbStairs_no_recursion(int n) {
        /*
            n == 4 일때,
            1 1 1 1
            1 1 2
            1 2 1
            2 2
            2 1 1
            따라서, 아래와 같은 공식이 된다. => 피보나치 수열인듯
            p[4] = p[4 - 1] + p[4 - 2]
            p[1] = 1
            p[2] = 2
            p[3] = p[3 - 1] + p[3 - 2]
            그렇다면, 초기 값 2개만 미리 설정하고, 이후 값들은 전부 기록해서 앞에 2개의 값의 합으로 현재 index에 값으로
            설정해두면 된다.
        */
        int[] dp = new int[46];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    public static int answer;
    
    public int climbStairs_recursion_version(int n) {
        answer = 0;
        
        getTheDistinctWay(n, 1, 2, 3);
        
        return answer;
    }
    
    public static void getTheDistinctWay(int n, int targetPrevOne, int targetPrevTwo, int count) {
        if(n == 1) {
            answer = 1;
            return;
        }
        
        if(n == 2) {
            answer = 2;
            return;
        }
        
        int newTargetValue = targetPrevOne + targetPrevTwo;
        
        if(count == n) {
            answer = newTargetValue;
            return;
        }
        
        count++;
        
        // Do memozation
        targetPrevOne = targetPrevTwo;
        targetPrevTwo = newTargetValue;
        
        getTheDistinctWay(n, targetPrevOne, targetPrevTwo, count);
    }
}
