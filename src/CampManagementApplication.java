import java.util.Scanner;

/**
 * Notification Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다. main 메서드를 실행하면 프로그램이 실행됩니다. model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요! 프로젝트 구조를 변경하거나 기능을
 * 추가해도 괜찮습니다! 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
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
      System.out.println("1. 수강생 관리");
      System.out.println("2. 점수 관리");
      System.out.println("3. 과목 관리");
      System.out.println("4. 프로그램 종료");
      System.out.print("관리 항목을 선택하세요...");
      int input = sc.nextInt();

      switch (input) {
        case 1 -> displayStudentView(); // 수강생 관리
        case 2 -> displayScoreView(); // 점수 관리
        case 3 -> displaySubjectView();
        case 4 -> flag = false; // 프로그램 종료
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
      System.out.println("1. 수강생 등록");
      System.out.println("2. 수강생 목록 조회");
      System.out.println("3. 메인 화면 이동");
      System.out.print("관리 항목을 선택하세요...");
      int input = sc.nextInt();

      switch (input) {
        case 1 -> createStudent(); // 수강생 등록
        case 2 -> inquireStudent(); // 수강생 목록 조회
        case 3 -> flag = false; // 메인 화면 이동
        default -> {
          System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
          flag = false;
        }
      }
    }
  }

  // 수강생 등록
  private static void createStudent() {
    System.out.println("\n수강생을 등록합니다...");
    System.out.print("수강생 이름 입력: ");
    String studentName = sc.next();
    // 기능 구현 (필수 과목, 선택 과목)

    Student student = new Student(StudentData.getUID(), studentName); // 수강생 인스턴스 생성 예시 코드
    // 기능 구현
    System.out.println("수강생 등록 성공!\n");
  }

  // 수강생 목록 조회
  private static void inquireStudent() {
    System.out.println("\n수강생 목록을 조회합니다...");
    // 기능 구현
    System.out.println("\n수강생 목록 조회 성공!");
  }

  private static void displayScoreView() {
    boolean flag = true;
    while (flag) {
      System.out.println("==================================");
      System.out.println("점수 관리 실행 중...");
      System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
      System.out.println("2. 수강생의 과목별 회차 점수 수정");
      System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
      System.out.println("4. 메인 화면 이동");
      System.out.print("관리 항목을 선택하세요...");
      int input = sc.nextInt();

      switch (input) {
        case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
        case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
        case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
        case 4 -> flag = false; // 메인 화면 이동
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
      System.out.println("2. 과목 등록");
      System.out.println("4. 메인 화면 이동");
      System.out.print("관리 항목을 선택하세요...");
      int input = sc.nextInt();

      switch (input) {
        case 1 -> viewAllSubject(); // 수강생의 과목별 시험 회차 및 점수 등록
        case 2 -> createSubject();  // 신규 과목 등록
        // TODO
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
      System.out.println("[ " + s.getSubjectId() + " ]\n" + s.getSubjectName() + "\n" + s.getSubjectType() + "\n");
    }
  }

  private static void createSubject() {
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
      default -> throw new IllegalStateException("Unexpected value: " + subjectTypeInput);
    }

    // 과목 저장소에 추가
    Subject subject = new Subject(SubjectData.getNewUID(), subjectName, subjectType);

    if (SubjectData.addSubject(subject)){
      System.out.println("과목 등록 성공!\n");
    }else {
      System.out.println("과목 등록 실패!\n");
    }

    displaySubjectView();
  }
    private static void viewAllSubject() { // 과목 조회.
        for(Subject s : SubjectData.getSubjectStore()) {
            System.out.println("[ " + s.getSubjectId() + " ]\n" + s.getSubjectName() + "\n" + s.getSubjectType() + "\n");
        }
    }
}
