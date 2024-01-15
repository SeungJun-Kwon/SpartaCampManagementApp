import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScoreData {
    private static final String INDEX_TYPE_SCORE = "SC";
    private static int scoreIndex;

    private static Map<String, Score> scoreStore = new HashMap<>();

    public static String getNewUID() {
        return INDEX_TYPE_SCORE + scoreIndex++;
    }

    // Score들의 정보가 담긴 Map에서 Values(Score들의 정보)를 가져오는 메소드
    public static List<Score> getScoreList() {
        return scoreStore.values().stream().toList();
    }

    // Score들의 정보가 담긴 Map에서 Key(Score의 고유 ID)로 Value를 가져오는 메소드
    public static Score getScoreByID(String id) {
        return scoreStore.get(id);
    }

    // Score들의 회차 정보를 바탕으로 Values에서 뽑아내는 메소드
    public static List<Score> getScoreByIndex(Integer index) {
        return scoreStore.values().stream().filter(s -> s.getScoreIndex() == index).toList();
    }

    // 요구사항 1. 과목을 입력받아 회차와 점수를 추가하는 메서드
    public static boolean addScore(Score score) {
        int index = score.getScoreIndex();

        // 같은 회차가 존재하는지 검사 -> 입력 취소
        boolean hasSameIndex = false;
        for(Score s : scoreStore.values()) {
            if (s.getScoreIndex() == index) {
                hasSameIndex = true;
                break;
            }
        }

        if((hasSameIndex)) {
            return false;
        }

        // 회차 1~9
        if(!(score.getScoreIndex() >= 1 && score.getScoreIndex() <= 9)){
            return false;
        }

        // 점수 0~100
        if(!(score.getScoreValue() >= 0 && score.getScoreValue() <= 100)){
            return false;
        }

        scoreStore.put(score.getScoreId(), score);
        return true;
    }

    // 요구사항 2. 과목별 회차 점수를 수정.
    public static boolean updateScore(int index, int newScore){
        Score target = null;

        for (Score s: scoreStore.values()){
            if(s.getScoreIndex() == index){
                target = s;
                break;
            }
        }
        if(target == null){
            return false;
        }else{
            target.setScoreValue(newScore);
        }
        return true;
    }

    // 요구사항 3. 수강생의 특정 과목 회차별 등급을 조회
    public static String gradeCheck(int scoreIndex){

        for(Score s : scoreStore.values()){
            if(s.getScoreIndex() == scoreIndex){
               return essentialGrade(s.getScoreValue());
            }
        }
        return "등록되지 않았습니다.";
    }

    public static String essentialGrade(int score) { // 필수과목 등급
        String s = "";
        if ((score >= 95) && (score <= 100)) {
            s = "A";
        } else if ((score >= 90) && (score <= 94)) {
            s = "B";
        } else if ((score >= 80) && (score <= 89)) {
            s = "C";
        } else if ((score >= 70) && (score <= 79)) {
            s = "D";
        } else if ((score >= 60) && (score <= 69)) {
            s = "F";
        } else if (score < 60) {
            s = "N";
        }
        return s;
    }
    public static String choicelGrade(int score) { // 선택과목 등급
        String s = "";
        if ((score >= 90) && (score <= 100)){
            s = "A";
        } else if ((score >= 80) && (score <= 89)) {
            s = "B";
        } else if ((score >= 70) && (score <= 79)) {
            s = "C";
        } else if ((score >= 60) && (score <= 69)) {
            s = "D";
        } else if ((score >= 50) && (score <= 59)) {
            s = "F";
        } else if (score < 50) {
            s = "N";
        }
        return s;
    }
}
