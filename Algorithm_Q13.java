import java.util.Arrays;

public class Algorithm_Q13 {
    /*
     * 해당 문제는 XOR 연산을 이해하고 있다면, 쉽게 해결되는 문제였다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/single-number/
     * 위 XOR 연산에 대해서 기억하고 있다면 쉽게 풀리지만, 역시 다른 풀이를 통해 해결한 내 풀이도 존재하므로(XOR를 모른다고 할때) 같이 참고 하도록 하자.
     */ 

    public int singleNumber_no_XOR(int[] nums) {
        int answer = 0;
        int count = 0;
        
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {
            if(i == 0) {
                answer = nums[i];
                count++;
            } else {
                if(nums[i] == answer) {
                    count++;
                    continue;
                } else {
                    if(count == 1) {
                        return answer;
                    } else {
                        answer = nums[i];
                        count = 1;
                    }
                }
            }
        }

        return answer;
    }

    public int singleNumber_XOR_version(int[] nums) {
        int answer = 0;
        
        if(nums.length == 1) {
            return nums[0];
        }
        
        //XOR calculation need
        for(int i = 0; i < nums.length; i++) {
            answer = answer ^ nums[i];
        }
        
        return answer;
        
    }
}
