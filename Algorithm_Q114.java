public class Algorithm_Q114 {
    /*
     * 이 문제는 병합정렬(Merge Sort) 문제다.
     * 구현하는 방법에 대해 다시금 확인해보고 명확히 이해를 하도록 하자.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/find-duplicate-subtrees/
     * 풀이를 확인해보자.
     */

    // 풀이 코드
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);

        return nums;
    }

    // 분리하는 파트 (Divide)
    public void mergeSort(int[] nums, int start, int end) {
        // 병합 정렬 -> 배열을 반으로 나눈 후 최소 단위로 쪼개 졌을 때 병합을 진행한다.
        // 종료 조건 : 최소 단위보다 더 작게 쪼개질때 함수 종료
        if(start < end) {
            // 반으로 배열 분리
            int mid = (start + end) / 2;
            // 절반씩 나뉜 구간에 따라 병합 정렬을 재 호출
            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            // 최소 단위까지 쪼개서 나온 결과들이 위 과정을 통해 얻어지면, 아래 merge를 통해 결과로 도출 진행
            merge(nums, start, mid, end);
        }
        return;
    }

    // 결과를 도출하는 merge 파트 (conquer)
    public void merge(int[] nums, int start, int mid, int end) {
        // 구간 start to end 에서의 병합을 진행한다.
        // 정렬을 start to mid and mid + 1 to end 까지 2개로 분리해 진행했으므로,
        // 이에 대해서 구간별 배열을 잡아 원본 배열에서 처리한다.
        int n1 = mid + 1 - start;
        int n2 = end - mid;
        // 구간별 배열을 새롭게 만들어 저장
        int[] left = new int[n1];
        for (int i = 0; i < n1; i++) {
            left[i] = nums[start + i];
        }
        int[] right = new int[n2];
        for (int i = 0; i < n2; i++) {
            right[i] = nums[mid + 1 + i];
        }
        // 배열들을 최종적으로 크기순으로 맞춰 병합
        int i = 0, j = 0, k = start;
        while (i < n1 || j < n2) {
            // 만약 j -> 구간 2의 배열이 전부 순회를 돌았거나, i -> 구간 1이 아직 종료하지 않았는데 해당 구간에 값이
            // 구간 2의 값 보다 작을 때에는 배열에 k 번 index(start to end를 순회하는 인덱스)에 
            // 구간 1의 배열 원소를 넣는다.
            if (j == n2 || i < n1 && left[i] < right[j]) {
                nums[k++] = left[i++];
            } else {
                // 이외의 케이스라면 구간 2의 배열 원소를 넣는다.
                nums[k++] = right[j++];
            }
        }
        return;
    }
}
