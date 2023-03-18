import java.util.*;

public class Algorithm_Q126 {
    /*
     * 이 문제는 데이터가 stream으로 들어올 때 어떤 방식으로 처리할지를 생각해 풀이하는 문제이다.
     * 조건에 맞는 상황들에 대해 체크하며 풀이하면 해결 방법이 보인다.
     * 문제 링크는 아래와 같다.
     * https://leetcode.com/problems/design-browser-history/
     * 풀이를 확인해보자.
     */

    // https://leetcode.com/problems/design-browser-history/solutions/3309557/image-explanation-both-array-stack-approaches-c-java-python/?orderBy=hot
    // 위 경로에는 아래의 내 풀이 방식 이 외에 stack으로 풀이하는 방식이 있다. 다음에 이 문제를 맞이 한다면, stack으로 풀어 보라는 제약을 걸어 진행해보자.

    // 나의 풀이 -> List를 사용하여 데이터를 관리하는 방식의 풀이
    class BrowserHistory_List_Model {

        // Page History를 기록하기 위한 List 선언
        private List<String> pageHistory;
        // 현재 페이지 위치를 기록하기 위한 필드 선언
        private int currPage;
    
        // 홈페이지 접속 기록 초기화
        public BrowserHistory_List_Model(String homepage) {
            this.pageHistory = new ArrayList<>();
            // 파라미터로 들어온 페이지 정보는 첫 페이지 이므로, list에 삽입하고 현재 페이지를 0으로 받는다
            this.pageHistory.add(homepage);
            this.currPage = pageHistory.size() - 1;
        }
        
        // 페이지 접속
        public void visit(String url) {
            // 만약 현재 페이지가 페이지 접속 기록의 크기보다 작다면, 페이지 접속 기록 리스트를 subList 함수를
            // 사용해서 0 ~ 현재 페이지 까지의 서브 리스트로 만든 후 입력된 URL을 list에 기록한다.
            if(this.currPage < this.pageHistory.size() - 1) {
                this.pageHistory = this.pageHistory.subList(0, this.currPage + 1);
            }
            this.pageHistory.add(url);
            this.currPage = this.pageHistory.size() - 1;
        }
        
        // 뒤로 가기
        public String back(int steps) {
            String page = "";
            // 받은 step을 현재 페이지 index에서 빼고 저장
            this.currPage = this.currPage - steps;
            // 만약 위 값이 0 보다 작다면, 아예 처음 페이지인 index 0 의 위치로 처리
            if(this.currPage < 0) {
                this.currPage = 0;
            }
            // 해당 페이지 URL을 반환하여 리턴
            page = this.pageHistory.get(this.currPage);
            return page;
        }
        
        // 앞으로 가기
        public String forward(int steps) {
            String page = "";
            // 받은 step을 현재 페이지 index에서 더하고 저장
            this.currPage = this.currPage + steps;
            // 만약 위 값이 페이지 접속기록 리스트 크기보다 크다면 해당 리스트의 가장 마지막 index로 처리
            if(this.currPage > this.pageHistory.size() - 1) {
               this.currPage = this.pageHistory.size() - 1;
            }
            // 해당 페이지 URL을 반환하여 리턴
            page = this.pageHistory.get(this.currPage);
            return page;
        }
    }
    
    /**
     * Your BrowserHistory object will be instantiated and called as such:
     * BrowserHistory obj = new BrowserHistory(homepage);
     * obj.visit(url);
     * String param_2 = obj.back(steps);
     * String param_3 = obj.forward(steps);
     */
}
