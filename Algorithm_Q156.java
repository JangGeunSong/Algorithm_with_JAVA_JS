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

    public int arraySign_use_boolean(int[] nums) {
        // 숫자가 무한대로 커지게 될 때
        boolean ans = true;

        for(int num : nums) {
            if(num == 0) {
                return 0;
            }

            // 만약 음수가 한번 나오면 false로 처리하기 위해 ans의 ! 를 처리한다.
            // 또 음수가 나오면 다시 ! 처리하면서 양수로 인식되는 true가 된다.
            if(num < 0) {
                ans = !ans;
            }
        }

        return ans ? 1 : -1;
    }
    
}
