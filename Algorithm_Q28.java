import java.util.*;

public class Algorithm_Q28 {
    /*
     * 해당 문제는 DP를 통해 풀이하는 문제이다. 이전의 계산값을 저장하면, 쉽게 원하는 결과를 얻을 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/maximum-product-subarray/
     * 풀이를 확인해보자.
     */  

    /*
     * 나의 풀이 (wrong)
     * 내 풀이는 전체적으로 큰 문제는 없었으나, 반대 방향에서 처리되는 부분에 대한 고려가 없어 틀렸다.
     */
    public int maxProduct_mine(int[] nums) {
        // 0 이 등장하는 순간 곱셉의 결과가 무조건 0으로 떨어지기 때문에
        // 곱셈의 결과를 기록할 때, 이 점에 유의하여 계산을 진행한다.
        int answer = -999;
        int[] productMemo = new int[nums.length];

        Arrays.fill(productMemo, 0);

        // 1개의 단일 숫자 중 가장 큰 값을 answer에 우선 담아 주면서, 연속되는 곱셈의 결과를 미리 메모한다.
        for(int i = 0; i < nums.length; i++) {
            // answer = Math.max(answer, nums[i]);
            if(i == 0) {
                productMemo[i] = nums[i];
            } else if(i > 0 && nums[i] != 0) {
                if(productMemo[i - 1] == 0) {
                    productMemo[i] = nums[i];
                } else {
                    productMemo[i] = productMemo[i - 1] * nums[i];
                }
            }
        }

        for(int i = 0; i < productMemo.length; i++) {
            answer = Math.max(answer, productMemo[i]);
        }

        return answer;
    }

    /*
     * 정답 코드
     * 아래 주석을 참고하자.
     */
    public int maxProduct(int[] nums) {
        // 0 이 등장하는 순간 곱셉의 결과가 무조건 0으로 떨어지기 때문에
        // 곱셈의 결과를 기록할 때, 이 점에 유의하여 계산을 진행한다.
        // 생각해 볼 점은 nums[i] 값이 1 < nums[i] <= 10 이 계속 된다면, 값이 작아질 일이 없다는 점으로,
        // 0이 아닌 숫자가 계속 이어진다면, 음수가 짝수개이기만 하면 무조건 큰 값이 나온다는 점이다.
        // 다만 왼쪽 끝이나 오른쪽 끝에서 시작할 때, 시작값이 다르기 때문에, 최종적으로 맨 오른쪽 끝 혹은 맨 왼쪽 끝 까지
        // 계산 된 값이 음수면, 그 이전에 음수가 제외되어 연산된 결과가 최대일 것이다.
        // 따라서 이 부분은 양 끝단에서 계산이 시작되었을 때, 파악할 수 있으므로, 메모를 2개로 분산해 진행한다.
        int answer = -999;
        int n = nums.length;
        int[] productMemoLeft = new int[nums.length];
        int[] productMemoRight = new int[nums.length];

        Arrays.fill(productMemoLeft, 0);
        Arrays.fill(productMemoRight, 0);

        // 1개의 단일 숫자 중 가장 큰 값을 answer에 우선 담아 주면서, 연속되는 곱셈의 결과를 미리 메모한다.
        for(int i = 0; i < nums.length; i++) {
            if(i == 0) {
                productMemoLeft[i] = nums[i];
                productMemoRight[i] = nums[n - 1 - i];
            } else {
                if(nums[i] != 0) {
                    if(productMemoLeft[i - 1] == 0) {
                        productMemoLeft[i] = nums[i];
                    } else {
                        productMemoLeft[i] = productMemoLeft[i - 1] * nums[i];
                    }
                }
                
                if(nums[n - 1 - i] != 0) {
                    if(productMemoRight[i - 1] == 0) {
                        productMemoRight[i] = nums[n - 1 - i];
                    } else {
                        productMemoRight[i] = productMemoRight[i - 1] * nums[n - 1 - i];
                    }
                }
            }
        }

        for(int i = 0; i < productMemoLeft.length; i++) {
            answer = Math.max(answer, Math.max(productMemoLeft[i], productMemoRight[i]));
        }

        return answer;
    }
}
