import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private final String studentId;
    private String studentName;
    private String studentState;

    private List<String> mandatorySubjectList;
    private List<String> choiceSubjectList;

    // 과목 id를 key, 10회차에 대한 Score id의 List를 Value
    private Map<String, List<String>> scoreBySubject;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        this.studentState = "Green";

        mandatorySubjectList = new ArrayList<>();
        choiceSubjectList = new ArrayList<>();
        scoreBySubject = new HashMap<>();
    }

    public Student(String studentId, String studentName, List<String> mandatorySubjectList, List<String> choiceSubjectList) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentState = "Green";

        scoreBySubject = new HashMap<>();

        this.mandatorySubjectList = mandatorySubjectList;
        for(String s : mandatorySubjectList) {
            scoreBySubject.put(s, List.of("0", "0", "0", "0", "0", "0", "0", "0", "0", "0"));
        }

        this.choiceSubjectList = choiceSubjectList;
        for(String s : choiceSubjectList) {
            scoreBySubject.put(s, List.of("0", "0", "0", "0", "0", "0", "0", "0", "0", "0"));
        }
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentState() {
        return studentState;
    }

    public void setStudentState(String studentState) {
        this.studentState = studentState;
    }

    public List<String> getMandatorySubjectList() {
        return mandatorySubjectList;
    }

    public List<String> getChoiceSubjectList() {
        return choiceSubjectList;
    }

    public void addMandatorySubject(String subject) {
        if(mandatorySubjectList.contains(subject)) {
            // 메세지
            return;
        }

        mandatorySubjectList.add(subject);
    }

    public void addChoiceSubject(String subject) {
        if(choiceSubjectList.contains(subject)) {
            // 메세지
            return;
        }

        choiceSubjectList.add(subject);
    }

    public Integer getMandatorySubjectCount() {
        return mandatorySubjectList.size();
    }

    public Integer getChoiceSubjectCount() {
        return choiceSubjectList.size();
    }

    public List<String> getScoreIdListBySubject(String subjectId) {
        return scoreBySubject.get(subjectId);
    }

    // 해당 과목을 수강하고 있고 해당 과목의 회차에 대한 점수가 아직 등록되지 않았을 때
    public boolean addScoreBySubject(String subjectId, Score score) {
        if(scoreBySubject.containsKey(subjectId)) {
            if(!scoreBySubject.get(subjectId).get(score.getScoreIndex() - 1).equals("0")) {
                scoreBySubject.get(subjectId).set(score.getScoreIndex() - 1, score.getScoreId());

                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return studentId + " : " + studentName + ",\n" +
                "필수 과목 " + mandatorySubjectList +
                "\n선택 과목 " + choiceSubjectList +
                "\n현재 상태 " + studentState;
    }
}