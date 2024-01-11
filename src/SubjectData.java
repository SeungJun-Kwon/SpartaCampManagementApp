import java.util.ArrayList;
import java.util.List;

public class SubjectData {
    private static List<Subject> subjectStore = new ArrayList<>();

    private static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";

    public static void Init() {
        subjectStore = List.of(
                new Subject(
                        INDEX_TYPE_SUBJECT + getSubjectIndex(),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        INDEX_TYPE_SUBJECT + getSubjectIndex(),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        INDEX_TYPE_SUBJECT + getSubjectIndex(),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        INDEX_TYPE_SUBJECT + getSubjectIndex(),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        INDEX_TYPE_SUBJECT + getSubjectIndex(),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        INDEX_TYPE_SUBJECT + getSubjectIndex(),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        INDEX_TYPE_SUBJECT + getSubjectIndex(),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        INDEX_TYPE_SUBJECT + getSubjectIndex(),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        INDEX_TYPE_SUBJECT + getSubjectIndex(),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
    }

    public static int getSubjectIndex() {
        return subjectIndex++;
    }

    public static String getUID() {
        return INDEX_TYPE_SUBJECT + getSubjectIndex();
    }

    public static List<Subject> getSubjectStore() {
        return subjectStore;
    }
}
