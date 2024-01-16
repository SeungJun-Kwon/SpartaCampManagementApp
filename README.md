# Sparta Camp Management App - 스파르타 캠프 관리 시스템

## 👋 소개

- **Sparta Camp Management App**은 스파르타 캠프 관리 시스템으로 수강생, 과목, 점수 등 캠프의 전반적인 데이터들을 관리하는 시스템입니다.
- Java로 구현했으며 협업 능력 및 객체 지향의 전반적인 이해와 설계 능력 등을 기르기 위한 팀 프로젝트입니다.

## 👩‍💻 팀원

<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/SeungJun-Kwon"><img src="https://avatars.githubusercontent.com/u/80217301?v=4" width="100px;" alt=""/><br /><sub><b> 팀장 : 권승준 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/ruh0n"><img src="https://avatars.githubusercontent.com/u/48433827?v=4" width="100px;" alt=""/><br /><sub><b> 팀원 : 박태준 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/wooseok50"><img src="https://avatars.githubusercontent.com/u/155416976?v=4" width="100px;" alt=""/><br /><sub><b> 팀원 : 조우석 </b></sub></a><br /></td>
    </tr>
  </tbody>
</table>

## ✅ 기술 스택

<img  src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white">

<img  src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white">

<img  src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">

<img  src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

## ✅ 주요 기능

- **정보 CRUD 기능**: 수강생, 과목, 점수 정보 등을 생성하고 조회하고 수정하고 삭제할 수 있습니다.

## ✅ 클래스 다이어그램

![nbc drawio (1)](https://github.com/SeungJun-Kwon/SpartaCampManagementApp/assets/80217301/2ef1864b-8f1f-482a-b19b-b689a1414598)

## ✅ 구현 화면

#### 메인 화면

```
==================================
내일배움캠프 수강생 관리 프로그램 실행 중...
0. 프로그램 종료
1. 수강생 관리
2. 점수 관리
3. 과목 관리
관리 항목을 선택하세요...
==================================
```

#### 수강생 관리 화면

```
수강생 관리 실행 중...
0. 메인 화면 이동
1. 수강생 등록
2. 수강생 목록 조회
3. 수강생 이름으로 조회
4. 수강생 상태로 조회
5. 수강생 정보 수정
관리 항목을 선택하세요...
```

#### 수강생 등록

```
수강생을 등록합니다...
수강생 이름 입력: 가나다

필수 과목을 선택해주세요...(선택 완료는 0)
1. [Java] - 선택 완료
2. [객체지향] - 선택 완료
3. [Spring] - 선택 완료
4. [JPA]
5. [MySQL]
0

선택 과목을 선택해주세요...(선택 완료는 0)
1. [디자인 패턴] - 선택 완료
2. [Spring Security] - 선택 완료
3. [Redis]
4. [MongoDB]
0
수강생 등록 성공!
```

#### 수강생 이름으로 조회

```
관리할 수강생의 이름을 입력하시오...(종료는 0)
입력 : 권승준

1. ST0 : 권승준
필수 과목 [SU0, SU1, SU2]
선택 과목 [SU5, SU6]
현재 상태 Green
```

#### 점수 관리 화면

```
점수 관리 실행 중...
0. 메인 화면 이동
1. 수강생의 과목별 시험 회차 및 점수 등록
2. 수강생의 과목별 회차 점수 수정
3. 수강생의 특정 과목 회차별 등급 조회
4. 수강생의 과목별 평균 등급을 조회
5. 특정 상태 수강생들의 필수 과목 평균 등급을 조회
관리 항목을 선택하세요...
```

#### 점수 추가 화면

```
시험 점수를 등록합니다...

관리할 수강생의 이름을 입력하시오...(종료는 0)
입력 : 권승준

1. ST0 : 권승준
필수 과목 [SU0, SU1, SU2]
선택 과목 [SU5, SU6]
현재 상태 Green

관리할 수강생의 번호를 선택하시오...(종료는 0)
입력 : 1

1. Java
2. 객체지향
3. Spring
4. 디자인 패턴
5. Spring Security

수정할 과목의 번호를 입력하시오...(종료는 0)
입력 : 1
[ Java ] 과목의 회차와 점수를 입력하시오...(종료는 0)
회차 : 4
점수 : 80

점수 등록 성공!
```

#### 수강생의 회차 별 등급 조회 화면

```
회차별 등급을 조회합니다
조회할 학생의 이름을 입력하세요: 박태준

1. ST1 : 박태준
필수 과목 [SU0, SU2, SU4]
선택 과목 [SU5, SU7]
현재 상태 Green

학생 번호를 선택 하세요
1

[ 필수과목 ] 
1. Java
2. Spring
3. MySQL

[ 선택과목 ] 
4. 디자인 패턴
5. Redis

과목을 입력하세요: 1

1회차 점수 : 36, 등급: C
2회차 점수 : 59, 등급: C
3회차 점수 : 13, 등급: N
```

#### 수강생의 과목 별 평균 등급 화면

```
관리할 수강생의 이름을 입력하시오...(종료는 0)
입력 : 조우석
조우석님의 평균 점수입니다.

[ 필수 과목 ] 
[ 객체지향 ]: 58 -- 총 3회차
[ Spring ]: 43 -- 총 3회차
[ JPA ]: 61 -- 총 3회차

[ 선택 과목 ] 
[ Spring Security ]: 43 -- 총 3회
[ MongoDB ]: 67 -- 총 3회
```

#### 특정 상태 수강생들의 과목 별 평균 점수 화면

```
조회할 학생의 상태를 입력하시오...Green

Green인 학생들의 과목별 평균 점수

[ 필수 과목 ]
Java: 44.86
객체지향: 53.50
Spring: 30.11
JPA: 61.00
MySQL: 30.00

[ 선택 과목 ]
Java: 44.86
객체지향: 53.50
Spring: 30.11
JPA: 61.00
MySQL: 30.00
```

#### 과목 관리 화면

```
과목 관리 실행 중...
0. 메인 화면 이동
1. 전체 과목 리스트 조회
2. 과목 추가
3. 과목 수정
4. 과목 삭제
```

#### 전체 과목 리스트 조회 화면

```
[SU0] | Java - MANDATORY
[SU1] | 객체지향 - MANDATORY
[SU2] | Spring - MANDATORY
[SU3] | JPA - MANDATORY
[SU4] | MySQL - MANDATORY
[SU5] | 디자인 패턴 - CHOICE
[SU6] | Spring Security - CHOICE
[SU7] | Redis - CHOICE
[SU8] | MongoDB - CHOICE
```

#### 과목 추가 화면

```
과목을 등록합니다...
과목 이름 입력: 자료구조

과목 타입을 선택 해주세요
1. 필수 과목	2. 선택 과목
과목 타입 선택: 1
과목 등록 성공!
```

#### 과목 수정 화면

```
과목을 수정합니다...
과목 코드 입력: SU9

[SU9] | 자료구조 - MANDATORY

과목 이름을 수정합니다...
과목 이름 입력: 알고리즘

[SU9] | 알고리즘 - MANDATORY

과목 타입을 수정합니다...
1. 필수 과목	2. 선택 과목
과목 타입 선택: 1
과목 수정 성공!

[SU9] | 알고리즘 - MANDATORY
```

#### 과목 삭제 화면

```
과목을 삭제합니다...
과목 코드 입력: SU9
과목 삭제 성공.
```

