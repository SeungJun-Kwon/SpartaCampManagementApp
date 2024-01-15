import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentData {

  private static Map<String, Student> studentStore = new HashMap<>();

  private static int studentIndex;
  private static final String INDEX_TYPE_STUDENT = "ST";

  public static Map<String, Student> getStudentStore() {
    return studentStore;
  }

  public static List<Student> getSortedStudentStore() {
    List<Student> result = new ArrayList<>(studentStore.values());
    result.sort(Comparator.comparing(Student::getStudentName));

    return result;
  }

  public static int getStudentIndex() {
    return studentIndex++;
  }

  public static String getNewUID() {
    return INDEX_TYPE_STUDENT + getStudentIndex();
  }

  public static boolean addStudent(Student student) {
    if (studentStore.containsKey(student.getStudentId())) {
      // 이미 있다
      return false;
    }

    if (student.getMandatorySubjectCount() < 3) {
      // 필수 과목 3개 미만
      return false;
    }

    if (student.getChoiceSubjectCount() < 2) {
      // 선택 과목 2개 미만
      return false;
    }

    studentStore.put(student.getStudentId(), student);
    return true;
  }

  public static List<Student> findStudentByName(String name) {
    return studentStore.values().stream().filter(s -> s.getStudentName().equals(name)).toList();
  }

  public static List<Student> findStudentBySubjectName(String name) { // 과목 id
    return studentStore.values()
            .stream()
            .filter(s -> (s.getMandatorySubjectList().contains(name) || s.getChoiceSubjectList().contains(name)))
            .toList();
  }

  public static List<Student> findStudentByState(String state) {
    return studentStore.values().stream().filter(s -> s.getStudentState().equals(state)).toList();
  }
}