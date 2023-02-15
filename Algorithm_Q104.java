import java.util.*;

public class Algorithm_Q104 {
    /*
     * 이 문제는 쉬워 보이지만 입력값이 매우 크기 때문에 하나의 자료형에 데이터를 담을 수 없다는 것을 이해해야 하는 문제이다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/add-to-array-form-of-integer/
     * 풀이를 확인해보자.
     */

    // 많은 값이 들어가는 극한의 상황까지 모두 고려할 수 있는 문제 풀이 방식을 체크하자.
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> answer = new ArrayList<>();
        int carry = k;

        // carry 를 사용해서 현재 자리의 숫자 + carry 를 해서 10을 나눈 나머지를 자리수로 answer에 추가
        // 이때 현재 배열은 역순으로 add 되고 있으므로, 나중에 뒤집을 것을 생각한다.
        for(int i = num.length - 1; i > -1; i--) {
            carry += num[i];
            answer.add(carry % 10);
            // 1의 자리는 썼으니까 10으로 나눈 몫을 carry에 가지고 간다.
            carry = carry / 10;
        }

        // 결과 carry가 0 이 아니면 아직 자리수가 남은 것이므로 추가
        if(carry != 0) {
            while(carry > 0) {
                answer.add(carry % 10);
                carry = carry / 10;
            }
        }

        // 이 배열을 뒤집는다.
        Collections.reverse(answer);

        return answer;
    }
}
