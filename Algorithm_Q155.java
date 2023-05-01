import java.util.*;

public class Algorithm_Q155 {
    /*
     * 이 문제는 크기 정렬 후 평균값을 구하는 문제다.
     * https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
     * 풀이를 확인해보자.
     */
    
    public double average(int[] salary) {
        // 배열을 오름차순으로 정렬
        Arrays.sort(salary);

        // 정답을 double로 선언
        double answer = 0.0;

        // 최소값과 최대값을 제외하고 나머지들을 모두 더한다.
        for(int i = 1; i < salary.length - 1; i++) {
            answer += salary[i];
        }

        // 해당 총 합에서 급여의 수 - 2 를 나눈다
        answer = answer / (salary.length - 2);

        // 정답을 리턴한다.
        return answer;
    }
}
