public class Algorithm_Q173 {
    /*
     * 이 문제는 OOP 디자인을 통해 원하는 조건에 대한 기능을 구현하는 문제이다.
     * 나의 경우 3가지 타입에 대해 맞춰서 데이터 구조를 각각 필드로 잡았지만, hashmap을 통해 잡아도 되고 아니면 다른 형태의 데이터 구조를 잡아도 아무 문제가 없을것 같다.
     * https://leetcode.com/problems/design-parking-system/
     * 풀이를 확인해보자.
     */

    class ParkingSystem {

        private int big;
        private int currBig;
        private int medium;
        private int currMedium;
        private int small;
        private int currSmall;
    
        public ParkingSystem(int big, int medium, int small) {
            // 현재 주차된 차량은 다 0 이고, 한계 량은 파라미터로 온 데이터를 바탕으로 정의
            this.big = big;
            this.medium = medium;
            this.small = small;
            this.currBig = 0;
            this.currMedium = 0;
            this.currSmall = 0;
        }
        
        public boolean addCar(int carType) {
            // 각 type에 맞춰서 차를 세울 수 있는지 여부를 확인한다.
            // 현재 차의 대수가 최대치보다 작으면 현재 차량의 대수를 1 올리고 OK처리, 아니라면 false로 처리한다.
            if(carType == 1) {
                if(currBig < big) {
                    currBig += 1;
                    return true;
                } else {
                    return false;
                }
            } else if(carType == 2) {
                if(currMedium < medium) {
                    currMedium += 1;
                    return true;
                } else {
                    return false;
                }
            } else if(carType == 3) {
                if(currSmall < small) {
                    currSmall += 1;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
    
    /**
     * Your ParkingSystem object will be instantiated and called as such:
     * ParkingSystem obj = new ParkingSystem(big, medium, small);
     * boolean param_1 = obj.addCar(carType);
     */
}
