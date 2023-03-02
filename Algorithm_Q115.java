public class Algorithm_Q115 {
    /*
     * 이 문제는 two pointer 문제다.
     * 입력 배열을 실제 문자들을 압축하면서 수정까지 거쳐 가며 이렇게 수정해서 바꿔준 배열의 길이만큼을 반환해야 한다.
     * 따라서, 단순히 몇개의 자리수를 바꿔야 하는지 계산만 하고 넘어가면 이는 틀린 답으로 떨어지게 된다는 점을 기억하자
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/string-compression/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int compress(char[] chars) {
        // 이 문제는 문자열을 압축해서 원본 배열에 채워두고, 이때 채운 배열의 길이만큼을 숫자로 반환하는
        // 문제이다. 따라서 숫자만 계산해서 리턴하면 무조건 틀리는 문제이다.
        int left = 0;
        int right = 0;
        int cnt = 0;

        // 배열의 길이가 1이라면 그냥 수정 없이 바로 길이인 1만 리턴한다.
        if(chars.length == 1) {
            return 1;
        }

        // right 가 실제 이동 거리, left는 교체하면서 이동하는 인덱스 인자로 생각
        while(right < chars.length) {
            // 갯수는 1로 초기화
            cnt = 1;
            // 배열을 돌면서 같은 문자일때는 right 1 증가, 갯수 1 증가시킨다.
            while(right < chars.length - 1 && chars[right] == chars[right + 1]) {
                right += 1;
                cnt += 1;
            }

            // 현재 left에 right 위치의 문자를 삽입 (어떤 문자가 있는지 처리)
            chars[left] = chars[right];
            // 이후 left, right 1씩 증가해서 right는 다른 문자로, left는 갯수를 적는 위치로 이동
            left += 1;
            right += 1;

            // 만약 cnt, 즉 갯수가 1보다 크다면 몇개인지 세어서 적어야 하므로 if문을 진입
            if(cnt > 1) {
                // 숫자를 문자열로 변환
                String countStr = String.valueOf(cnt);
                for(int i = 0; i < countStr.length(); i++) {
                    // 문자열의 길이만큼(100개라면 3자리 숫자이므로 3칸 적어야 함)
                    // 순회하며 각 자리수의 문자를 입력하고 left를 한칸씩 이동
                    chars[left] = countStr.charAt(i);
                    left += 1;
                }
            }
        }

        // 최종적으로 교체하면서 작성하는데 쓰인 거리를 left로 기록해 냈으므로, 이 길이를 반환한다.
        return left;
    }
}
