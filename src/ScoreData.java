import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ScoreData {
    private static final String INDEX_TYPE_SCORE = "SC";
    private static int scoreIndex;

    private static Map<String, Score> scoreStore = new HashMap<>();

    public static void Init() {
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 3; j++) {
                String uid = getNewUID();
                scoreStore.put(uid, new Score(uid, j + 1, (int)(Math.random()*100)));
            }
        }

        for(Student student : StudentData.getSortedStudentStore()) {
            for(String subjectId : student.getMandatorySubjectList()) {
                for(int i = 1; i <= 3; i++) {
                    String uid = getNewUID();
                    Score score = new Score(uid, i, (int)(Math.random()*100));
                    scoreStore.put(uid, score);
                    student.addScoreBySubject(subjectId, score);
                }
            }

            for(String subjectId : student.getChoiceSubjectList()) {
                for(int i = 1; i <= 3; i++) {
                    String uid = getNewUID();
                    Score score = new Score(uid, i, (int)(Math.random()*100));
                    scoreStore.put(uid, score);
                    student.addScoreBySubject(subjectId, score);
                }
            }
        }
    }

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
        // 회차 1~9
        if(!(score.getScoreIndex() >= 1 && score.getScoreIndex() <= 9)){
            return false;
        }

        // 점수 0~100
        if (!(score.getScoreValue() >= 0 && score.getScoreValue() <= 100)){
            return false;
        }

        scoreStore.put(score.getScoreId(), score);
        return true;
    }

    public static void removeScore(String scoreId) {
        scoreStore.remove(scoreId);
    }

    // 요구사항 2. 과목별 회차 점수를 수정.
    public static boolean updateScore(int index, int newScore){
        Score target = null;

        for (Score s: scoreStore.values()){
            if (s.getScoreIndex() == index){
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
    public static String mandatoryGradeCheck(int scoreIndex){

        for(Score s : scoreStore.values()){
            if(s.getScoreIndex() == scoreIndex){
               return mandatoryGrade(s.getScoreValue());
            }
        }
        return "등록되지 않았습니다.";
    }
    public static String choiceGradeCheck(int scoreIndex){

        for(Score s : scoreStore.values()){
            if(s.getScoreIndex() == scoreIndex){
                return choiceGrade(s.getScoreValue());
            }
        }
        return "등록되지 않았습니다.";
    }

    public static String mandatoryGrade(int score) { // 필수과목 등급
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
    public static String choiceGrade(int score) { // 선택과목 등급
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

    public static double getAverageScoreByScoreIds(List<String> ids) {
        return ids.stream().map(ScoreData::getScoreByID).filter(Objects::nonNull).mapToInt(Score::getScoreValue).average().orElse(0D);
    }

    public static double calculateAverageScore(List<Score> scores) {
        return scores.stream().mapToInt(Score::getScoreValue).average().orElse(0F);
    }
}
