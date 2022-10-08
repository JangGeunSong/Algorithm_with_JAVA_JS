import java.util.*;

public class Algorithm_Q6 {
    /*
     * 해당 문제는 쉬운 문제이지만, 요구 사항에 대해 이해하여야 더 풀기 쉽고 간단하게 구현 가능하므로, 항상 생각해보고 풀이하자
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/majority-element/
     * 해당 문제의 풀이는 내가 풀이한 과거 버전과 현재 버전이 들어 있다.
     * 각각에 대한 내용을 통해 더 나은 풀이에 대해 고민해보자.
     */ 

    public int majorityElement_now(int[] nums) {
        // n / 2 번 등장하는 원소라면 어디에 존재하더라도 아래와 같은 비율을 보인다.
        // ? ? ? k k k k k k ? ? ?
        // => k 가 12개의 원소를 가진 배열중 7개 존재할 때 (more than 이므로)
        // k 가 12개 원소들 중 최소값이라면
        // k k k k k k k ? ? ? ? ?
        // k 가 12개 원소중 가장 큰 값이면
        // ? ? ? ? ? k k k k k k k
        // 즉, 이런 상황이라면, 항상 배열에서 n / 2 번째 원소에서 등장한다.
        Arrays.sort(nums);
        return nums[(nums.length / 2)];
    }

    // 과거의 풀이는 간단한 구현 보다는 단순하게 요구항을 그대로 옮기는 작업을 주로 했었다.
    // 해당 풀이도 역시 문제가 있지는 않지만, 너무 길게 풀이할 필요는 없으므로, 참고로만 활용하자.
    public int majorityElement_past(int[] nums) {
        int answer = 0;
        int countNumber = 0;
        int majorityIndex = nums.length / 2;
        
        if(nums.length == 1) {
            return nums[0];    
        }   
        
        Arrays.sort(nums);
        
        answer = nums[0];
        
        for(int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
            if(answer == nums[i]) {
                countNumber++;
            } else {
                if(countNumber > majorityIndex) {
                    break;
                } else {
                    answer = nums[i];
                    countNumber = 1;
                }
            }
        }
        
        return answer;
    }
}
