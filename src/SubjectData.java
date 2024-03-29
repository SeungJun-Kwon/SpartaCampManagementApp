import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubjectData {

    private static List<Subject> subjectStore;

    private static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";

    public static void Init() {
        subjectStore = new ArrayList<>(
                List.of(new Subject(getNewId(), "Java", SUBJECT_TYPE_MANDATORY),
                        new Subject(getNewId(), "객체지향", SUBJECT_TYPE_MANDATORY),
                        new Subject(getNewId(), "Spring", SUBJECT_TYPE_MANDATORY),
                        new Subject(getNewId(), "JPA", SUBJECT_TYPE_MANDATORY),
                        new Subject(getNewId(), "MySQL", SUBJECT_TYPE_MANDATORY),
                        new Subject(getNewId(), "디자인 패턴", SUBJECT_TYPE_CHOICE),
                        new Subject(getNewId(), "Spring Security", SUBJECT_TYPE_CHOICE),
                        new Subject(getNewId(), "Redis", SUBJECT_TYPE_CHOICE),
                        new Subject(getNewId(), "MongoDB", SUBJECT_TYPE_CHOICE)));
    }

    public static boolean addSubject(Subject subject) {
        // 이미 있다
        if (subjectStore.contains(subject)) {
            return false;
        }

        subjectStore.add(subject);
        return true;
    }

    public static String getNewId() {
        return INDEX_TYPE_SUBJECT + subjectIndex++;
    }


    public static List<Subject> getSubjectStore() {
        return subjectStore;
    }

    public static List<Subject> getMandatorySubjects() {
        return subjectStore.stream().filter(s -> s.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)).toList();
    }

    public static List<Subject> getChoiceSubjects() {
        return subjectStore.stream().filter(s -> s.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)).toList();
    }

    public static Subject findSubjectById(String id) {
        return subjectStore.stream().filter(subject -> Objects.equals(id, subject.getSubjectId())).findFirst().orElse(null);
    }

    public static void deleteSubject(Subject subject) {
        subjectStore.remove(subject);
    }

    public static Subject getSubjectById(String id) {
        return subjectStore.stream()
                .filter(s -> Objects.equals(s.getSubjectId(), id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("SubjectData Dont Have" + id));
    }
}
