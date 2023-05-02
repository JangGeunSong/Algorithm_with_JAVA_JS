public class Algorithm_Q156 {
    /*
     * 이 문제는 곱셈을 모두 마친 후 부호에 따른 결과를 결정하는 문제다.
     * https://leetcode.com/problems/sign-of-the-product-of-an-array/
     * 풀이를 확인해보자.
     */

    // 곱셈의 결과를 리턴할 때 숫자의 갯수가 많으면 int 형으로는 표현이 안되므로 반드시 더 큰 형태의 숫자를 사용한다.
    public int arraySign(int[] nums) {
        // 곱셈의 결과를 부호로 체크해서 숫자로 리턴하는 문제이다.
        double ans = 1;
        
        for(int num : nums) {
            ans *= num;
        }

        // 곱셈의 결과가 어떻냐에 따라 조건에 맞춰 리턴한다.
        if(ans > 0) {
            return 1;
        } else if(ans < 0) {
            return -1;
        } else {
            return 0;
        }

    }
    
}
