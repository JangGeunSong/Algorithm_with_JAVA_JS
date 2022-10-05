public class Algorithm_Q3 {
    /*
     * 해당 문제는 문제를 풀이하기 위해 생각해야할 부분이 좀 있는 문제이다. 첫번째 ***_myanswer 함수에서 간단히 풀이법에 대해 정리하였으므로, 이에 따른 내용을 잘 확인하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
     * 첫번째 함수는 해법에 대한 설명을 본 후 구현을 한 파트이기 때문에 주석이 상세하게 걸려있다.
     * 두번째 함수는 실제 해법을 적용해서 간결하게 구성한 코드이다.
     * 이진 탐색의 기법을 활용하고, 중간값(median)의 정의를 이해한 후 이를 활용해서 풀이법을 찾는것이 핵심인 문제이다.
     */ 
    public double findMedianSortedArrays_myanswer(int[] nums1, int[] nums2) {
        double answer = 0.0;
        
        /*
            median의 정의를 활용
            A a1 a2 a3 a4 a5 a6
            B b1 b2 b3 b4 b5 b6
            위와 같은 배열일 때
            A a1 | a2 a3 a4 a5 a6
            B b1 b2 b3 b4 b5 | b6

            | 로 분리한다면, 왼쪽 그룹과 오른쪽 그룹 사이에는 다음 관계가 성립한다.
            a1 <= a2
            b5 <= b6
            이때, 만약 정확하게 두 배열이 크기순으로 절반씩 정확하게 분리되었다면 다음과 같은 관계도 성립한다.
            b5 <= a2
            a1 <= b6
            따라서, 위 관계가 성립한다면, max(a1, b5), min(a2, b6) 의 2개 값이 median이 된다.

            해당 공식을 고려하여 코드를 구현한다.
        */

        int m = nums1.length;
        int n = nums2.length;

        // 항상 m <= n 이 성립할 수 있도록 함수를 처리한다.
        if(m > n) {
            /* 
             * 진짜 최 중요 파트인데, 우리가 해당 문제를 풀때는 항상 m <= n 일때만 성립하도록 로직을 구성하였기 때문에, 
             * 반드시 현재 if 로직이 작동한다면, return 해서 아래 내용이 진행되지 않도록 해야한다. 아니라면, 꼭 else 로 감싸서 아래 로직이 실행되지 않도록 한다.
             * 
            */
            return findMedianSortedArrays_myanswer(nums2, nums1);
        }

        int half = (m + n + 1) / 2;
        boolean isEven = (m + n) % 2 == 0;

        int left = 0;
        int right = m;

        while(left <= right) {
            int i = (left + right) / 2;
            int j = half - i;

            int max_left_1 = 0;
            int min_right_1 = 0;

            int max_left_2 = 0;
            int min_right_2 = 0;

            if(i == 0) {
                max_left_1 = Integer.MIN_VALUE;
            } else {
                max_left_1 = nums1[i - 1];
            }

            if(i == m) {
                min_right_1 = Integer.MAX_VALUE;
            } else {
                min_right_1 = nums1[i];
            }

            if(j == 0) {
                max_left_2 = Integer.MIN_VALUE;
            } else {
                max_left_2 = nums2[j - 1];
            }

            if(j == n) {
                min_right_2 = Integer.MAX_VALUE;
            } else {
                min_right_2 = nums2[j];
            }

            if(max_left_1 <= min_right_2 && max_left_2 <= min_right_1) {
                if(isEven) {
                    answer = (Math.max(max_left_1, max_left_2) + Math.min(min_right_1,min_right_2)) / 2.0;
                    return answer;
                } else {
                    answer = Math.max(max_left_1, max_left_2) / 1.0;
                    return answer;
                }
            } else if(min_right_1 >= max_left_2) {
                // 만약 작은 크기의 배열의 왼쪽 그룹의 최대값이 큰 크기의 배열의 오른쪽 그룹의 가장 작은값보다 크다면,
                // 작은 배열의 왼쪽 그룹의 제일 큰 값이 더 작은 인덱스 위치에 있어야 하기 때문에, 오른쪽 끝 값인 right를 
                // 작은 크기의 배열의 절반으로 줄여 준다.
                right = i - 1;
            } else {
                // 이 조건에 들어오는 경우는 위 2개의 조건에 맞지 않다는 이야기 이므로,
                // 작은 크기의 배열이 큰 쪽으로 절반보다 앞에 위치에서 시작하도록 조정하여 값을 조정한다.
                // 즉, 오른쪽 그룹에 중앙값이 존재한다는 의미로, 해당 scope를 줄여서 검증을 진행한다.
                left = i + 1;
            }
        }

        return answer;
    }


    public double findMedianSortedArrays_checkAnswer(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays_checkAnswer(nums2, nums1);
        }
        
        int x = nums1.length, y = nums2.length;
        int start = 0;
        int end = x;
        while (start<=end) {
            int positionX = (start + end) / 2;
            int positionY = (x + y + 1) / 2 - positionX;
            
            int maxX = positionX == x ? Integer.MAX_VALUE : nums1[positionX];
            int minX = positionX <= 0 ? Integer.MIN_VALUE : nums1[positionX - 1];
            int maxY = positionY == y ? Integer.MAX_VALUE : nums2[positionY];
            int minY = positionY <= 0 ? Integer.MIN_VALUE : nums2[positionY - 1];
            
            if (minY <= maxX && minX <= maxY) {
                if ((x + y) % 2 == 0) {
                    return ((double)Math.max(minX,minY) + Math.min(maxX,maxY))/2;
                } else {
                   return Math.max(minX,minY);
                }
            } else if (minX >= maxY) {
                end = positionX - 1;
            } else {
                start = positionX + 1;
            }
        }
        return 0.0;
    }
}
