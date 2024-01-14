import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SubjectData.Init();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

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

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("0. 메인 화면 이동");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 이름으로 조회");
            System.out.println("4. 수강생 정보 수정");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> flag = false; // 메인 화면 이동
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> inquireStudentByName(); // 수강생 이름으로 조회
                case 4 -> displayModifyStudent();
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }

            if(input != 0){
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

        while(true) {
            sc.reset();
            index = 1;

            if(selectedMandatorySubject.size() == mandatorySubjects.size())
                break;

            System.out.println("\n필수 과목을 선택해주세요...(선택 완료는 0)");
            for (Subject mandatory : mandatorySubjects) {
                System.out.print(index++ + ". [" + mandatory.getSubjectName() + "]");
                System.out.println(selectedMandatorySubject.contains(mandatory.getSubjectId()) ? " - 선택 완료" : "");
            }

            try {
                input = sc.nextInt();

                if(input == 0)
                    break;

                if(selectedMandatorySubject.contains(mandatorySubjects.get(input - 1).getSubjectId())) {
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

        while(true) {
            sc.reset();
            index = 1;

            if(selectedChoiceSubject.size() == choiceSubjects.size())
                break;

            System.out.println("\n선택 과목을 선택해주세요...(선택 완료는 0)");
            for (Subject choice : choiceSubjects) {
                System.out.print(index++ + ". [" + choice.getSubjectName() + "]");
                System.out.println(selectedChoiceSubject.contains(choice.getSubjectId()) ? " - 선택 완료" : "");
            }

            try {
                input = sc.nextInt();

                if(input == 0)
                    break;

                if(selectedChoiceSubject.contains(choiceSubjects.get(input - 1).getSubjectId())) {
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
        if(StudentData.addStudent(student)) {
            System.out.println("수강생 등록 성공!\n");
        }
        else {
            System.out.println("수강생 등록 실패...\n");
        }
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...\n");
        // 기능 구현
        List<Student> students = StudentData.getSortedStudentStore();
        for(Student s : students) {
            System.out.println(s.toString());
        }

        System.out.println("\n수강생 목록 조회 성공!\n");
    }

    private static void inquireStudentByName() {
        System.out.println("\n이름으로 수강생 목록을 조회합니다...");

        System.out.print("조회할 수강생의 이름을 입력해주세요. ");
        String name = sc.next();

        List<Student> result = StudentData.findStudentByName(name);
        if(result == null || result.isEmpty()) {
            System.out.println(name + " 이름을 가진 수강생이 존재하지 않습니다.\n");
            return;
        }

        System.out.println();
        for(Student s : result) {
            System.out.println(s.toString() + "\n");
        }
    }

    private static void displayModifyStudent() {
        System.out.println("\n수강생 수정...");

        System.out.print("수정할 수강생의 이름을 입력해주세요. ");
        String name = sc.next();

        List<Student> result = StudentData.findStudentByName(name);
        if(result == null || result.isEmpty()) {
            System.out.println(name + " 이름을 가진 수강생이 존재하지 않습니다.\n");
            return;
        }
        int index = 1;

        System.out.println();
        for(Student s : result) {
            System.out.println(index++ + ". " + s.toString() + "\n");
        }

        System.out.print("수정할 수강생의 번호를 입력해주세요(종료는 0) : ");
        while(true) {
            try {
                index = sc.nextInt();

                if(index == 0)
                    break;

                Student s = result.get(index - 1);

                System.out.println("\n[현재 수강생 정보]");
                System.out.println(s.toString() + "\n");
                System.out.print("수정할 이름을 입력해주세요 : ");

                name = sc.next();

                if(name.trim().isEmpty()) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    continue;
                }

                s.setStudentName(name);
                break;
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

        System.out.println("수강생 수정을 종료합니다.\n");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("0. 메인 화면 이동");
           /* System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");*/
            System.out.println("4. 회차 및 점수 등록");
            System.out.println("5. 모든 점수 조회");
            System.out.println("6. 회차별 점수 조회");
            System.out.println("7. 점수 수정");
            System.out.println("8. 등급 조회");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> flag = false; // 메인 화면 이동
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> addScore(); // 회차 및 점수 등록
                case 5 -> getScore(); // 모든 점수 조회
                case 6 -> getScoreByIndex(); // 회차별 점수 조회
                case 7 -> uppdateScore(); // 점수 수정
                case 8 -> gradeCheckRe(); // 등급 조회
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
    private static void addScore() {
        // 점수 정보를 입력받고 Data에 추가하는 메소드
        // 1. 회차와 점수 값을 입력받는다
        System.out.print("회차 : ");
        int index = sc.nextInt();
        System.out.print("점수 : ");
        int scoreValue = sc.nextInt();

        // 2. 입력받은 값으로 새로운 Score를 만든다
        Score score = new Score(ScoreData.getNewUID(), index, scoreValue);
        System.out.println(score.getScoreIndex() + " 회차 " + score.getScore() + "점");

        // 3. 방금 만든 Score를 ScoreData에 추가 시도한다
        if(ScoreData.addScore(score)) {
            System.out.println("점수 추가가 완료됐습니다!");
        }
        else {
            System.out.println("점수 추가가 실패했습니다");
        }
    }

    private static void getScore() {
        // 모든 점수의 정보를 출력하는 메소드
        List<Score> scoreList = ScoreData.getScoreList();

        for(Score s : scoreList) {
            System.out.println(s.getScoreIndex() + "회차 점수 : " + s.getScore());
        }
        System.out.println("\n점수 조회 완료\n");
    }

    private static void getScoreByIndex() {
        // 회차를 받아서 점수를 보여주기
        // 1. 회차를 입력받기
        System.out.print("회차를 입력하세요: ");
        int a = sc.nextInt();

        // 2. 해당 회차에 대한 정보들을 가져오기
        List<Score> li;
        li = ScoreData.getScoreByIndex(a);

        for(int i = 0; i < li.size(); i++) {
            Score score = li.get(i);
            System.out.println(a + "회차 점수 : " + score.getScore());
        }
    }
    // 점수 수정 메서드
    private static void uppdateScore(){
        System.out.print("수정할 회차를 입력하세요: ");
        int n = sc.nextInt();
        System.out.print("새로운 점수를 입력하세요: ");
        int s = sc.nextInt();
        ScoreData.updateScore(n, s);
    }
    // 등급 조회 메서드
    private static void gradeCheckRe(){
        System.out.print("조회할 회차를 입력하세요: ");
        int n = sc.nextInt();
        System.out.println(n + " 회차의 등급: " + ScoreData.gradeCheck(n));
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

    private static void displaySubjectView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("과목 관리 실행 중...");
            System.out.println("1. 전체 과목 리스트 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> viewAllSubject(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 4 -> flag = false; // 메인 화면 이동
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
        e.printStackTrace();
        System.out.println("\n** 잘못된 입력입니다! 다시 입력해주세요. **\n");
      }
    }
  }

  private static void updateSubjectById() {
    // 수정할 과목 코드
    System.out.println("\n과목을 수정합니다...");
    System.out.print("과목 코드 입력: ");
    Subject subject = SubjectData.findSubjectById(sc.next().strip());

    if (subject == null) {
      System.out.println("과목이 존재하지 않습니다.");
    } else {
      // 과목 이름 입력
      System.out.format("%n[%s] | %s - %s%n", subject.getSubjectId(), subject.getSubjectName(), subject.getSubjectType());
      System.out.println("\n과목 이름을 수정합니다...");
      System.out.print("과목 이름 입력: ");
      String subjectNameInput = sc.next().strip();
      // 과목 타입 입력
      System.out.format("%n[%s] | %s - %s%n", subject.getSubjectId(), subjectNameInput, subject.getSubjectType());
      System.out.println("\n과목 타입을 수정합니다...");
      System.out.println("1. 필수 과목\t2. 선택 과목");
      System.out.print("과목 타입 선택: ");
      int subjectTypeInput = sc.nextInt();

      String subjectType;
      switch (subjectTypeInput) {
        case 1 -> subjectType = "MANDATORY";
        case 2 -> subjectType = "CHOICE";
        default -> throw new IllegalStateException();
      }

      // 과목 수정
      subject.setSubjectName(subjectNameInput);
      subject.setSubjectType(subjectType);

      // 결과
      System.out.println("과목 수정 성공!\n");
      System.out.format("%n[%s] | %s - %s%n", subject.getSubjectId(), subject.getSubjectName(), subject.getSubjectType());
    }


  }
}
