import java.util.ArrayList;
import java.util.List;

public class StudentData {
    private static List<Student> studentStore = new ArrayList<>();

    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";

    public static int getStudentIndex() {
        return studentIndex++;
    }

    public static String getUID() {
        return INDEX_TYPE_STUDENT + getStudentIndex();
    }

    public static List<Student> getStudentStore() {
        return studentStore;
    }
}
