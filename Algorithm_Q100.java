public class Algorithm_Q100 {
    /*
     * 이 문제는 해시 테이블을 활용하고 sliding windows를 사용해야 하는 문제이다.
     * 나의 경우 hash table은 2개의 bucket만 고려하므로 배열을 통해 문제를 풀이 했다.
     * 이 문제를 푸는 힌트를 공식 풀이 글에서 체크했고, 소스코드는 보지 않고 풀이를 진행했다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/fruit-into-baskets/
     * 풀이를 확인해보자.
     */

    // 나의 풀이
    public int totalFruit(int[] fruits) {
        // 리턴할 정답
        int answer = Integer.MIN_VALUE;
        // 과일의 종류를 저장할 버켓을 선언
        int[] set = new int[2];
        // 해당 종류의 과일이 처음 나온 시점을 저장할 위치 정보 선언
        int[] elemStartPos = new int[2];
        // 지나온 나무의 수
        int step = 0;
        // 인덱스
        int index = 0;

        // 버켓과 위치 정보를 모두 -1로 초기화
        set[0] = -1;
        set[1] = -1;
        elemStartPos[0] = -1;
        elemStartPos[1] = -1;

        // 나무 그룹 전체를 지나가는 과정을 밟을 때
        while(index < fruits.length) {
            // 만약 첫번째 버켓이 -1 이라면
            if(set[0] == -1) {
                // 첫번째 버켓의 정보를 저장하고 최대 거리를 계산
                set[0] = fruits[index];
                elemStartPos[0] = index;
                step += 1;
                index += 1;
                answer = Math.max(answer, step);
            } else if(set[1] == -1) {
                // 두번째 버켓이 -1 이라면
                if(set[0] == fruits[index]) {
                    // 현재 과일의 종류가 첫번째 버켓과 같으면 그냥 한 step을 더 가고 넘어간다.
                    step += 1;
                    answer = Math.max(answer, step);
                    index += 1;
                } else {
                    // 첫번째 버켓과 다르면 두번째 버켓이 정보를 담고 내용 처리
                    set[1] = fruits[index];
                    elemStartPos[1] = index;
                    step += 1;
                    index += 1;
                    answer = Math.max(answer, step);
                }
            } else {
                // 만약 버켓에 두 종류의 과일이 모두 채워졌다면
                if(set[0] == fruits[index]) {
                    // 현재 과일이 첫번째 버켓과 같은 종류의 과일 일때
                    // 위치 정보 저장 후 거리 정보 처리
                    step += 1;
                    index += 1;
                    answer = Math.max(answer, step);
                } else if(set[1] == fruits[index]) {
                    // 현재 과일이 두번째 버켓과 같은 종류의 과일 일때
                    // 위치 정보 저장 후 거리 정보 처리
                    step += 1;
                    index += 1;
                    answer = Math.max(answer, step);
                } else {
                    // 만약 두가지 종류의 과일과 다른게 나올 때
                    // 현재 저장된 가장 나중에 나오는 과일의 위치 정보를 비교해서 가장 뒤에 있는 과일의 위치를 확인
                    // 이후 해당 가장 뒤에 위치에서 start 해서 다시 index를 추가하며 순회를 진행한다.
                    if(elemStartPos[0] > elemStartPos[1]) {
                        index = elemStartPos[0];
                        set[1] = -1;
                        step = 0;
                    } else {
                        index = elemStartPos[1];
                        set[0] = set[1];
                        elemStartPos[0] = elemStartPos[1];
                        set[1] = -1;
                        step = 0;
                    }
                }
            }
        }

        // 결과를 리턴한다.
        return answer;
    }
}
