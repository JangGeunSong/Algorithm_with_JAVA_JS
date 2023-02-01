public class Algorithm_Q95 {
    /*
     * 이 문제는 최대 공약수를 활용한 문제이다.
     * 문자열이 섞여 있어 당황스러운데 최대 공약수를 구하고 그 성질을 활용하면 풀 수 있다.
     * 수학적인 사고력이 있어야 하기 때문에 쉬움 수준은 아닌 것 같다. 처음 보면 당황스러울 수 있다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/greatest-common-divisor-of-strings/
     * 풀이를 확인해보자.
     */
    
    public String gcdOfStrings(String str1, String str2) {
        String answer = "";

        // 공약수가 있다면, 문자열을 앞뒤로 더해도 서로 같아야 한다 3 + 6 = 6 + 3 과 같은 이치
        String a = str1 + str2;
        String b = str2 + str1;

        // 만약 같지 않다면 빈 문자열을 리턴
        if(!a.equals(b)) {
            return "";
        }

        // 두 문자열의 길이를 주고 최대 공약수를 찾는다.
        int gcd = findGcd(str1.length(), str2.length());

        // 0부터 최대 공약수 까지 부분 문자열을 리턴한다.
        answer = str1.substring(0, gcd);

        return answer;
    }

    public int findGcd(int a, int b) {
        // 두 숫자의 최대 공약수 찾기
        int gcd = 1; // 항상 1은 두 수의 약수
        int num = 0;
        // 두 숫자 중 가장 작은 값이 최대 범위
        int smallNum = Math.min(a, b);

        // 2 부터 시작해서 두 숫자 모두 나누어 떨어지면 그 숫자를 일단 최대 공약수로 저장
        for(num = 2; num <= smallNum; num++) {
            if(a % num == 0 && b % num == 0) {
                gcd = num;
            }
        }

        // 얻은 결과를 리턴
        return gcd;
    }
}
