public class Algorithm_Q178 {
    /*
     * 이 문제는 수학적인 지식을 활용해 푸는 문제이다.
     * 나는 기울기를 계산해서 풀려고 했으나, 몇가지 제약 사항들이 걸리면서 계속 틀렸다. 이런 방법 보다는 변화율에 대한 식을 세우고 푸는게 더 간결하다.
     * https://leetcode.com/problems/check-if-it-is-a-straight-line/
     * 풀이를 확인해보자.
     */

    public boolean checkStraightLine(int[][] coordinates) {
        // 직선 여부를 판별하기 위해서는 2개의 점을 선택한 후 이를 통해 변화율을 체크하면 된다.
        // 무조건 (0,0)은 존재하기 때문에 이를 x1, y1으로 설정
        int deltaX = coordinates[1][0] - coordinates[0][0];
        int deltaY = coordinates[1][1] - coordinates[0][1];

        // 만약 점이 2개 뿐이라면 무조건 직선일 수 밖에 없으므로, true 리턴한다.
        if(coordinates.length == 2) {
            return true;
        }

        // 이제 세 번째 점부터 시작해서 만약 새롭게 얻은 변화율을 가지고 생각해 볼때 이 변화율이 아래 식
        // deltaX / newDeltaX = deltaY / newDeltaY 가 만족해야 한다.
        // 즉, 교차해서 보면 deltaX * newDeltaY == deltaY * newDeltaX 가 만족해야 하는데, 이게 안된다면
        // 이 점들은 직선이 아니다.
        for(int i = 2; i < coordinates.length; i++) {
            int newDeltaX = coordinates[i][0] - coordinates[0][0];
            int newDeltaY = coordinates[i][1] - coordinates[0][1];

            if(deltaY * newDeltaX != deltaX * newDeltaY) {
                return false;
            }
        }

        // 모든 순회를 다 돌았는데, 제외 사항에 걸리지 않는다면, 이 점들은 하나의 직선상에 있다고 리턴한다.
        return true;
    }
}
