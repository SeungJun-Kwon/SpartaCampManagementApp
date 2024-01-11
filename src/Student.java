import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String studentId;
    private String studentName;
    private List<String> mandatorySubjectList;
    private List<String> choiceSubjectList;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        mandatorySubjectList = new ArrayList<>();
        choiceSubjectList = new ArrayList<>();
    }

    public Student(String studentId, String studentName, List<String> mandatorySubjectList, List<String> choiceSubjectList) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.mandatorySubjectList = mandatorySubjectList;
        this.choiceSubjectList = choiceSubjectList;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
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

    @Override
    public String toString() {
        return studentId + " : " + studentName + ",\n" +
                "필수 과목 " + mandatorySubjectList +
                "\n선택 과목 " + choiceSubjectList;
    }
}
