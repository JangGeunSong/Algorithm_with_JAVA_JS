public class Algorithm_Q152 {
    /*
     * 이 문제는 간단한 반복만으로 풀이가능한 쉬운 문제다.
     * 하지만 수학적으로 알아두면 편리한 공식을 소개하는 문제로 해당 풀이를 확인해보자.
     * https://leetcode.com/problems/add-digits/
     * 풀이를 확인해보자.
     */

    // 반복을 활용한 풀이
    public int addDigits(int num) {
        // 반복을 통한 값 구하기
        while(num >= 10) {
            // 만약 10보다 같거나 큰 경우 아래 로직을 진행
            // char 배열을 해당 num을 이용해 생성한다.
            char[] arr = String.valueOf(num).toCharArray();
            // num은 이렇게 만든 digit을 모두 더해서 새로 갱신할 것이기 때문에 0으로 초기화
            num = 0;
            for(char c : arr) {
                // num에 digit을 모두 더한다
                num += Integer.valueOf(String.valueOf(c));
            }
        }

        // 모든 로직이 종료된 후 데이터를 받아온다.
        return num;
    }

    // 수학적 디지털 근의 공식을 활용한 풀이
    // https://leetcode.com/problems/add-digits/editorial/
    public int addDigits_using_math(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }
}
