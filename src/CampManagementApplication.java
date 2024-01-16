import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Notification Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다. main 메서드를 실행하면 프로그램이 실행됩니다. model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요! 프로젝트 구조를 변경하거나 기능을
 * 추가해도 괜찮습니다! 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SubjectData.Init();
        StudentData.Init();
        ScoreData.Init();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    /*
    메인 화면
     */
    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("0. 프로그램 종료");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 과목 관리");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> flag = false; // 프로그램 종료
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> displaySubjectView();
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    /*
    학생 관리 화면
     */
    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("0. 메인 화면 이동");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 이름으로 조회");
            System.out.println("4. 수강생 상태로 조회");
            System.out.println("5. 수강생 정보 수정");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> flag = false; // 메인 화면 이동
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> inquireStudentByName(); // 수강생 이름으로 조회
                case 4 -> inquireStudentByState(); // 수강생 상태로 조회
                case 5 -> displayModifyStudent();
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }

            if (input != 0) {
                System.out.println("돌아가시려면 아무 키나 누르고 엔터를 입력해주세요.");
                sc.next();
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();

        // 기능 구현 (필수 과목, 선택 과목)
        // 필수 과목 선택
        int index = 1;
        int input = -1;
        List<String> selectedMandatorySubject = new ArrayList<>();
        List<Subject> mandatorySubjects = SubjectData.getSubjectStore().stream().
                filter((subject -> subject.getSubjectType().equals("MANDATORY"))).toList();

        while (true) {
            sc.reset();
            index = 1;

            if (selectedMandatorySubject.size() == mandatorySubjects.size()) {
                break;
            }

            System.out.println("\n필수 과목을 선택해주세요...(선택 완료는 0)");
            for (Subject mandatory : mandatorySubjects) {
                System.out.print(index++ + ". [" + mandatory.getSubjectName() + "]");
                System.out.println(selectedMandatorySubject.contains(mandatory.getSubjectId()) ? " - 선택 완료" : "");
            }

            try {
                input = sc.nextInt();

                if (input == 0) {
                    break;
                }

                if (selectedMandatorySubject.contains(mandatorySubjects.get(input - 1).getSubjectId())) {
                    System.out.println("\n** 이미 선택한 과목입니다! 다시 입력해주세요. **\n");
                    continue;
                }

                selectedMandatorySubject.add(mandatorySubjects.get(input - 1).getSubjectId());
            } catch (Exception e) {
                System.out.println("\n** 잘못된 입력입니다! 다시 입력해주세요. **\n");
            }
        }

        // 선택 과목 선택
        List<String> selectedChoiceSubject = new ArrayList<>();
        List<Subject> choiceSubjects = SubjectData.getSubjectStore().stream().
                filter((subject -> subject.getSubjectType().equals("CHOICE"))).toList();

        while (true) {
            sc.reset();
            index = 1;

            if (selectedChoiceSubject.size() == choiceSubjects.size()) {
                break;
            }

            System.out.println("\n선택 과목을 선택해주세요...(선택 완료는 0)");
            for (Subject choice : choiceSubjects) {
                System.out.print(index++ + ". [" + choice.getSubjectName() + "]");
                System.out.println(selectedChoiceSubject.contains(choice.getSubjectId()) ? " - 선택 완료" : "");
            }

            try {
                input = sc.nextInt();

                if (input == 0) {
                    break;
                }

                if (selectedChoiceSubject.contains(choiceSubjects.get(input - 1).getSubjectId())) {
                    System.out.println("\n** 이미 선택한 과목입니다! 다시 입력해주세요. **\n");
                    continue;
                }

                selectedChoiceSubject.add(choiceSubjects.get(input - 1).getSubjectId());
            } catch (Exception e) {
                System.out.println("\n** 잘못된 입력입니다! 다시 입력해주세요. **\n");
            }
        }

        // 수강생 인스턴스 생성
        Student student = new Student(StudentData.getNewUID(), studentName, selectedMandatorySubject, selectedChoiceSubject);

        // 수강생 추가
        if (StudentData.addStudent(student)) {
            System.out.println("수강생 등록 성공!\n");
        } else {
            System.out.println("수강생 등록 실패...\n");
        }
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...\n");
        // 기능 구현
        List<Student> students = StudentData.getSortedStudentStore();
        for (Student s : students) {
            System.out.println(s.toString());
        }

        System.out.println("\n수강생 목록 조회 성공!\n");
    }

    private static void inquireStudentByName() {
        printStudentList(getStudentListByName());

        System.out.println();
    }

    private static void inquireStudentByState() {
        System.out.println("\n상태로 수강생 목록을 조회합니다...");

        while (true) {
            System.out.print("조회할 수강생의 상태를 입력해주세요.(Green, Yellow, Red) ");
            try {
                String state = sc.next();

                List<Student> result = StudentData.findStudentByState(state);
                if (result == null || result.isEmpty()) {
                    System.out.println(state + " 상태를 가진 수강생이 존재하지 않습니다.\n");
                    return;
                }

                System.out.println();
                for (Student s : result) {
                    System.out.println(s.toString() + "\n");
                }

                System.out.println("수강생 조회 완료!\n");
                break;
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private static void displayModifyStudent() {
        System.out.println("\n수강생 수정...\n");

        Student student = selectStudent(getStudentListByName());

        if(student == null)
            return;

        while (true) {
            try {
                System.out.println("\n[현재 수강생 정보]");
                System.out.println(student + "\n");
                System.out.print("수정할 이름을 입력해주세요 : ");

                String name = sc.next();

                if (name.trim().isEmpty()) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    continue;
                }

                System.out.print("수정할 상태를 입력해주세요(Green, Yellow, Red) : ");
                String state = sc.next();

                // Green, Yellow, Red 상태 입력 외 다른 문자열이 들어왔을 경우
                if (!state.equals("Green") && !state.equals("Yellow") && !state.equals("Red")) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    continue;
                }

                student.setStudentName(name);
                student.setStudentState(state);
                break;
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

        System.out.println("수강생 수정을 종료합니다.\n");
    }


    /*
    점수 관리 화면
     */

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("0. 메인 화면 이동");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급을 조회");
            System.out.println("5. 특정 상태 수강생들의 필수 과목 평균 등급을 조회");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> flag = false; // 메인 화면 이동
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAverageScoreBySubjectForStudent(); // 수강생의 과목별 평균 등급을 조회
                case 5 -> inquireAverageMandatoryScoreBySubjectForStudentWithState(); // 특정 상태 수강생들의 필수 과목 평균 등급을 조회
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 이름을 입력받아 수강생의 리스트를 가져오는 메소드
    private static List<Student> getStudentListByName() {
        while (true) {
            System.out.print("\n관리할 수강생의 이름을 입력하시오...(종료는 0)\n입력 : ");
            String name = sc.next();

            if (name.equals("0")) {
                return null;
            }

            List<Student> result = StudentData.findStudentByName(name);

            if (result == null || result.isEmpty()) {
                System.out.println(name + " 이름을 가진 수강생이 존재하지 않습니다. 다시 입력해주세요.");
                continue;
            }

            return result;
        }
    }

    // 수강생의 리스트를 화면에 출력해주는 메소드
    private static void printStudentList(List<Student> studentList) {
        int index = 1;

        if(studentList == null || studentList.isEmpty())
            return;

        System.out.println();

        for (Student s : studentList) {
            System.out.println(index++ + ". " + s.toString() + "\n");
        }
    }

    // 수강생의 리스트들을 출력하고 그 중에서 수강생을 선택해 반환하는 메소드
    private static Student selectStudent(List<Student> studentList) {
        printStudentList(studentList);

        if(studentList == null || studentList.isEmpty())
            return null;

        while (true) {
            try {
                System.out.print("관리할 수강생의 번호를 선택하시오...(종료는 0)\n입력 : ");
                int index = sc.nextInt();

                if (index == 0) {
                    return null;
                }

                return studentList.get(index - 1);
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
            }
        }
    }

    // 수강생의 과목별 시험 회차 및 점수 등록

    private static void createScore() {
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        // 특정 학생의 특정 과목에 대한 점수(회차, 점수값)을 추가하는 메소드
        // 1. 추가할 학생의 이름을 입력받아 해당 이름 학생들의 리스트를 받고 학생을 선택
        Student student = selectStudent(getStudentListByName());

        if (student == null) {
            return;
        }

        // 2. 해당 학생의 과목 리스트(필수, 선택)들을 받고 추가할 과목을 선택
        String selectedSubjectId;
        List<String> subjectIdList = new ArrayList<>();
        subjectIdList.addAll(student.getMandatorySubjectList());
        subjectIdList.addAll(student.getChoiceSubjectList());

        // 한 학생에 대한 점수 추가 작업을 반복해서 할 수 있도록 while 문 안에서 처리
        while (true) {
            System.out.println();

            int index = 1;
            for (String s : subjectIdList) {
                System.out.println(index++ + ". " + SubjectData.findSubjectById(s).getSubjectName());
            }

            System.out.println();

            while (true) {
                try {
                    System.out.print("수정할 과목의 번호를 입력하시오...(종료는 0)\n입력 : ");
                    index = sc.nextInt();

                    if (index == 0) {
                        return;
                    }

                    if (index > subjectIdList.size()) {
                        throw new Exception();
                    }

                    selectedSubjectId = subjectIdList.get(index - 1);

                    break;
                } catch (Exception e) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
                }
            }

            // 3. 점수의 회차와 점수값을 입력
            int scoreIndex, scoreValue;
            while (true) {
                System.out.print("[ " + SubjectData.findSubjectById(selectedSubjectId).getSubjectName() + " ]");
                System.out.println(" 과목의 회차와 점수를 입력하시오...(종료는 0)");
                try {
                    System.out.print("회차 : ");
                    scoreIndex = sc.nextInt();

                    if (scoreIndex == 0) {
                        return;
                    }

                    System.out.print("점수 : ");
                    scoreValue = sc.nextInt();

                    if (scoreValue == 0) {
                        return;
                    }

                    break;
                } catch (Exception e) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
                }
            }

            // 4. 각 클래스에서 구현한 메소드들(get 메소드, add 메소드 등)을 통해 추가 시도
            Score score = new Score(ScoreData.getNewUID(), scoreIndex, scoreValue);
            if (student.addScoreBySubject(selectedSubjectId, score) && ScoreData.addScore(score)) {
                System.out.println("\n점수 등록 성공!");
            } else {
                System.out.println("\n점수 등록 실패(이미 존재하는 회차이거나 학생이 수강하지 않는 과목)");
                student.removeScoreBySubject(selectedSubjectId, score.getScoreIndex());
                ScoreData.removeScore(score.getScoreId());
            }
        }
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        // 특정 학생의 특정 과목에 대한 점수들 중에서 특정 회차의 점수값을 수정하는 메소드
        // 1. 수정할 학생의 이름을 입력받아 해당 이름 학생들의 리스트를 받고 학생을 선택
        List<Student> students = StudentData.findStudentByName(sc.nextLine().strip());
        if (students.isEmpty()) {
            System.out.println("학생이 존재하지 않습니다.");
            return;
        }
        // 정확한 학생 선택
        Student student;
        if (students.size() > 1) {
            System.out.println("학생을 선택 해주세요");
            IntStream.range(1, students.size() + 1)
                    .forEach(i -> System.out.format("%d. %s%n", i, students.get(i).getStudentName()));
            System.out.format("%n학생 선택...");
            student = students.get(Integer.parseInt(sc.next().strip()) - 1);
        } else {
            student = students.getFirst();
        }


        // 2. 해당 학생의 과목 리스트(필수, 선택)들을 받고 수정할 과목을 선택
        List<String> subjectIds = new ArrayList<>();
        subjectIds.addAll(student.getMandatorySubjectList());
        subjectIds.addAll(student.getChoiceSubjectList());
        List<Subject> subjects = subjectIds.stream().map(SubjectData::getSubjectById).toList();

        System.out.println("\n" + student.getStudentName() + "의 시험 점수를 수정합니다...");
        System.out.println("과목을 선택 해주세요");
        IntStream.range(1, subjects.size() + 1).forEach(i -> {
            System.out.format("%d. %s%n", i, subjects.get(i).getSubjectName());
        });
        System.out.format("%n과목 선택...");

        Subject subject = subjects.get(Integer.parseInt(sc.next().strip()) - 1);

        // 3. 해당 과목의 점수 정보(회차, 점수값)을 받고 수정할 회차를 선택
        List<String> scoreIds = student.getScoreIdListBySubject(subject.getSubjectId());
        List<Score> scores = scoreIds.stream().map(ScoreData::getScoreByID).toList();
        if (scores.isEmpty()) {
            System.out.println("점수 정보가 없습니다.");
            return;
        }

        System.out.println("\n" + subject.getSubjectName() + "의 시험 점수를 수정합니다...");
        System.out.println("회차를 선택 해주세요");
        IntStream.range(1, scores.size() + 1)
                .forEach(i -> System.out.format("%d회차: %s점%n", i, scores.get(i).getScoreValue()));
        System.out.format("%n회차 선택...");
        String scoreIndex = sc.next().strip();
        Score score = scores.get(Integer.parseInt(scoreIndex) - 1);

        // 4. 수정할 점수값을 입력 후 수정 시도
        System.out.println("\n" + scoreIndex + "회차의 시험 점수를 수정합니다...");
        score.setScoreValue(Integer.parseInt(sc.next().strip()));
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        // 특정 학생의 특정 과목에 대한 회차별 점수값과 등급을 조회하는 메소드
        // 1. 조회할 학생의 이름을 입력받아 해당 이름 학생들의 리스트를 받고 학생을 선택

        // 2. 해당 학생의 과목 리스트(필수, 선택)들을 받고 조회할 과목을 선택

        // 3. 해당 과목의 점수값들을 출력
        // 가능하면 맨 밑에 평균 점수도 출력

        System.out.println("\n등급 조회 성공!");
    }

    private static void inquireAverageScoreBySubjectForStudent() {
        try {
            // * 학생 이름 입력
            List<Student> students = StudentData.findStudentByName(sc.nextLine().strip());
            if (students.isEmpty()) {
                System.out.println("** 해당 학생이 존재하지 않습니다. **");
            }

            // * 수강중인 과목의 목록
            for (Student s : students) {
                System.out.format("%s님의 평균 점수입니다.%n", s.getStudentName());
                System.out.println("\n [ 필수 과목 ] ");
                s.getMandatorySubjectList().forEach(sbId -> {
                    List<String> scoreIdList = s.getScoreIdListBySubject(sbId);
                    double averageScore = ScoreData.getAverageScoreByScoreIds(scoreIdList);
                    System.out.format("[ %s ]: %.2g -- %d회차%n",
                            SubjectData.getSubjectById(sbId).getSubjectName(),
                            averageScore,
                            scoreIdList.size());
                });

                System.out.println("\n [ 선택 과목 ] ");
                s.getChoiceSubjectList().forEach(sbId -> {
                    List<String> scoreIdList = s.getScoreIdListBySubject(sbId);
                    double averageScore = ScoreData.getAverageScoreByScoreIds(scoreIdList);
                    System.out.format("[ %s ]: %.2g -- 총 %d회%n",
                            SubjectData.getSubjectById(sbId).getSubjectName(),
                            averageScore,
                            scoreIdList.size());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void inquireAverageMandatoryScoreBySubjectForStudentWithState() {
        try {
            String studentState = sc.next().strip();
            List<Student> students = StudentData.findStudentByState(studentState);
            if (students.isEmpty()) {
                System.out.format("** %s인 학생이 존재하지 않습니다. **", studentState);
                return;
            }

            System.out.format("%n%s인 학생들의 과목별 평균 점수%n", students);
            System.out.println("[ 필수 과목 ]");
            SubjectData.getMandatorySubjects().forEach(sb -> {
                ArrayList<String> allSubjectScoreIds = new ArrayList<>();
                students.stream()
                        .map(st -> st.getScoreIdListBySubject(sb.getSubjectId()))
                        .forEach(allSubjectScoreIds::addAll);
                System.out.format("%s: %.2f%n", sb.getSubjectName(), ScoreData.getAverageScoreByScoreIds(allSubjectScoreIds));
            });

            System.out.println("[ 선택 과목 ]");
            SubjectData.getChoiceSubjects().forEach(sb -> {
                ArrayList<String> allSubjectScoreIds = new ArrayList<>();
                students.stream()
                        .map(st -> st.getScoreIdListBySubject(sb.getSubjectId()))
                        .forEach(allSubjectScoreIds::addAll);
                System.out.format("%s: %.2f%n", sb.getSubjectName(), ScoreData.getAverageScoreByScoreIds(allSubjectScoreIds));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    과목 관리 화면
     */
    private static void displaySubjectView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("과목 관리 실행 중...");
            System.out.println("0. 메인 화면 이동");
            System.out.println("1. 전체 과목 리스트 조회");
            System.out.println("2. 과목 추가");
            System.out.println("3. 과목 수정");
            System.out.println("4. 과목 삭제");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> flag = false; // 메인 화면 이동
                case 1 -> viewAllSubject(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> createSubject(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 3 -> updateSubjectById(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 4 -> deleteSubjectById();
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static void viewAllSubject() {
        for (Subject s : SubjectData.getSubjectStore()) {
            System.out.format("[%s] | %s - %s%n", s.getSubjectId(), s.getSubjectName(), s.getSubjectType());
        }
    }

    private static void createSubject() {
        while (true) {
            try {
                // 과목 이름 입력
                System.out.println("\n과목을 등록합니다...");
                System.out.print("과목 이름 입력: ");
                String subjectName = sc.next().strip();
                // 과목 타입 입력
                System.out.println("\n과목 타입을 선택 해주세요");
                System.out.println("1. 필수 과목\t2. 선택 과목");
                System.out.print("과목 타입 선택: ");
                int subjectTypeInput = Integer.parseInt(sc.next().strip());

                String subjectType;
                switch (subjectTypeInput) {
                    case 1 -> subjectType = "MANDATORY";
                    case 2 -> subjectType = "CHOICE";
                    default -> throw new IllegalStateException();
                }

                // 과목 저장소에 추가
                Subject subject = new Subject(SubjectData.getNewId(), subjectName, subjectType);

                // 결과
                if (SubjectData.addSubject(subject)) {
                    System.out.println("과목 등록 성공!\n");
                } else {
                    System.out.println("과목 등록 실패!\n");
                }
                break;
            } catch (Exception e) {
                System.out.println("\n** 잘못된 입력입니다! 다시 입력해주세요. **\n");
            }
        }
    }

    private static void updateSubjectById() {
        // 수정할 과목 코드
        System.out.println("\n과목을 수정합니다...");
        System.out.print("과목 코드 입력: ");
        String inputSubjectId = sc.next().strip();
        Subject subject = SubjectData.findSubjectById(inputSubjectId);

        if (subject == null) {
            System.out.println("과목이 존재하지 않습니다.");
        } else {
            // 수강 중인 학생 확인
            List<Student> enrolledStudents = StudentData.findStudentBySubjectName(subject.getSubjectName());
            boolean hasEnrolledStudent = !enrolledStudents.isEmpty();
            if (hasEnrolledStudent) {
                System.out.println("해당 과목을 수강중인 학생이 존재합니다.");
                enrolledStudents.forEach(s -> System.out.print(s.getStudentName() + ' '));
                System.out.println("과목의 이름만 수정 가능합니다...");
            }

            // 과목 이름 입력
            System.out.format("%n[%s] | %s - %s%n", subject.getSubjectId(), subject.getSubjectName(), subject.getSubjectType());
            System.out.println("\n과목 이름을 수정합니다...");
            System.out.print("과목 이름 입력: ");
            String subjectNameInput = sc.next().strip();
            // 과목 타입 입력
            String subjectType = null;
            if (!hasEnrolledStudent) {
                System.out.format("%n[%s] | %s - %s%n", subject.getSubjectId(), subjectNameInput, subject.getSubjectType());
                System.out.println("\n과목 타입을 수정합니다...");
                System.out.println("1. 필수 과목\t2. 선택 과목");
                System.out.print("과목 타입 선택: ");
                int subjectTypeInput = sc.nextInt();

                switch (subjectTypeInput) {
                    case 1 -> subjectType = "MANDATORY";
                    case 2 -> subjectType = "CHOICE";
                    default -> throw new IllegalStateException();
                }
            }

            // 과목 수정
            subject.setSubjectName(subjectNameInput);
            if (!hasEnrolledStudent) {
                subject.setSubjectType(subjectType);
            }

            // 결과
            System.out.println("과목 수정 성공!\n");
            System.out.format("%n[%s] | %s - %s%n", subject.getSubjectId(), subject.getSubjectName(), subject.getSubjectType());
        }
    }

    private static void deleteSubjectById() {
        // 삭제할 과목 코드
        while (true) {
            try {
                System.out.println("\n과목을 삭제합니다...");
                System.out.print("과목 코드 입력: ");
                String inputSubjectId = sc.next().strip();
                Subject subject = SubjectData.findSubjectById(inputSubjectId);

                // 과목 존재하는지 확인
                if (subject == null) {
                    System.out.println("과목이 존재하지 않습니다.");
                    break;
                }
                // 수강 중인 학생이 없는지 확인
                List<Student> enrolledStudents = StudentData.findStudentBySubjectName(subject.getSubjectName());
                if (!enrolledStudents.isEmpty()) {
                    System.out.println("해당 과목을 수강중인 학생이 존재합니다.");
                    enrolledStudents.forEach(s -> System.out.print(s.getStudentName() + ' '));
                    break;
                }
                // 삭제
                SubjectData.deleteSubject(subject);
                System.out.println("과목 삭제 성공.");
                break;


            } catch (Exception e) {
                System.out.println("과목 삭제 실패.");
            }
        }
    }
}
