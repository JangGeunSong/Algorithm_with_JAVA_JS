public class Algorithm_Q199 {
    /*
     * 데이터 타입의 이해와 재귀를 통한 지수 계산을 더 빠르게 하는 방법을 알려주는 문제이다.
     * 이 문제는 빠르게 문제를 푸는데 유용하게 쓸 수 있으므로, 잘 살펴보도록 하자.
     * https://leetcode.com/problems/powx-n/
     * 풀이를 확인해보자.
     */

    public double myPow(double x, int n) {
        // ***** 최 중요 정보 ******
        // int 형은 자기 타입의 최대 값에서 -1을 계속 곱하면 오버플로우가 날 수 밖에 없으므로, 반드시 long 등의
        // int보다 표현 범위가 넓은 데이터 타입을 사용해야 한다.
        return binaryExp(x, (long)n);
    }

    public double binaryExp(double x, long n) {
    // 지수가 0 이라면 어떤 숫자가 와도 1로 리턴
        if(n == 0) {
            return 1.0;
        }
        
        // n 이 음수라면, 이 값은 분수로서 데이터를 출력한다.
        // 즉 1 / x^n 으로 계산해야 함
        if(n < 0) {
            return 1.0 / binaryExp(x, -1 * n);
        }

        // 나머지의 경우 지수를 짝수로 만들어 나가면 배수로 곱하도록 계산할 수 있다.
        // 즉 이런 것이다. 2 ^ 100 = (2 x 2)^50 = 4^50 = (4 x 4)^25  이렇게 하면 지수를 작게 하면서
        // 계산도 밑수를 좀 더 키워서 계산이 가능해 지고, 이렇게 풀면 더 적은 횟수의 계산으로 답을 구할 수 있다.
        // 단 지수가 홀수라면, 이런 연산의 앞에 x 를 한번 더 곱해서 (홀수 - 1 = 짝수) 를 만들어서 풀이한다.
        if(n % 2 == 0) {
            return binaryExp(x * x, n / 2);
        } else {
            return x * binaryExp(x * x, (n - 1) / 2);
        }
    }
}
