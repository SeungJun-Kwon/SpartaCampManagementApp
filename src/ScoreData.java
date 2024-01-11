import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScoreData {
    private static final String INDEX_TYPE_SCORE = "SC";
    private static Map<String, Map<Integer, Integer>> scoreStore;
    private static Map<Integer, String> grade;
    // private static Map<Integer, Integer> scoreStores;
    private static String scoreId;
    private static int scoreIndex;
    private static int score;

    Score s = new Score(scoreIndex, score);

    public ScoreData() {
        this.scoreId = scoreId;
        this.scoreIndex = scoreIndex;
        this.score = score;
        this.scoreStore = new HashMap<>();
        this.grade = new HashMap<>();
    }

    public static String getNewUID() {
        return INDEX_TYPE_SCORE + getScoreIndex();
    }
    public static int getScoreIndex() { // 시험 회차
        return scoreIndex;
    }
    public static int getScore() { // 시험 점수
        return score;
    }

    // 요구사항 1. 과목을 입력받아 회차와 점수를 추가하는 메서드
    //
    public static void addScore(String subject, int scoreIndex, int scoreValue) {
        if(scoreIndex >= 1 && scoreIndex <= 9){
            if(scoreValue >= 0 && scoreValue <= 100){
                Map<Integer, Integer> scores = scoreStore.get(subject);
                if (!scores.containsKey(scoreIndex)) {  // 이미 입력된 회차가 아니라면
                    scores.put(scoreIndex, score);      // 회차와 점수 추가.
                    String grade = essentialGrade(scoreValue); // 점수별 등급. 필수, 선택 과목을 선택할 로직이 필요...
                    System.out.println("등급: " + grade + "\n");
                } else {
                    System.out.println("이미 점수가 등록된 회차입니다.\n");
                }
            } else {
                System.out.println("점수는 1 이상 100 이하의 값만 입력할 수 있습니다.\n");
            }
        } else {
            System.out.println("과목의 회차는 1 이상 9 이하의 값만 입력할 수 있습니다.\n");
        }
    }
    public static String essentialGrade(int score) { // 필수과목 등급
        String s = "";
        if (score >= 95) {
            s = "A";
        } else if (score >= 94) {
            s = "B";
        } else if (score >= 89) {
            s = "C";
        } else if (score >= 79) {
            s = "D";
        } else if (score >= 69) {
            s = "F";
        } else if (score > 60) {
            s = "N";
        }
        return s;
    }
    public static String choicelGrade(int score) { // 선택과목 등급
        String s = "";
        if (score >= 90) {
            s = "A";
        } else if (score >= 89) {
            s = "B";
        } else if (score >= 79) {
            s = "C";
        } else if (score >= 69) {
            s = "D";
        } else if (score >= 59) {
            s = "F";
        } else if (score > 50) {
            s = "N";
        }
        return s;
    }
    //요구사항 2. 과목별 회차 점수를 수정
    public static void updateScore(String subject, int key, int value){
        if (scoreStore.containsKey(subject)) {
            Map<Integer, Integer> subjectScores = scoreStore.get(subject);
            subjectScores.put(key, value);
        } else{
            System.out.println("등록된 과목이 아닙니다.\n");
        }
    }
    //요구사항 3. 수강생의 특정 과목 회차별 등급을 조회
    public static String gradeCheck(String subject, int key){
        if (scoreStore.containsKey(subject)) {
            Map<Integer, Integer> grade = scoreStore.get(subject); // 등급은 String인데 Map value를 Integer로 하지 않으면 에러나요..
            if (grade.containsKey(key)) {
                return String.valueOf(grade.get(key));
            } else {
                return "등급이 존재하지 않습니다.\n";
            }
        } else {
            return "과목이 존재하지 않습니다.\n";
        }
    }
}
