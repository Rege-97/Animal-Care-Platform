![logo3](https://github.com/user-attachments/assets/645542d9-b9f1-4a11-b780-a408254bf475)
> 전국 유기동물 보호소와 입양자, 후원자를 연결하는 통합 관리 플랫폼


## 📌 프로젝트 개요

**Animal-Care-Platform**은 유기동물 보호소, 입양자, 후원자 등 다양한 사용자 주체가 참여하는  
**유기동물 통합 관리 플랫폼**입니다.

입양 신청, 봉사 활동, 후원 참여 등 실제 보호소 운영 프로세스를 기반으로 기능을 구현하였으며,  
보호소 관리자와 운영자(관리자 계정)의 기능을 분리해 실무에 가까운 역할 체계를 구성했습니다.

해당 프로젝트는 **4인 팀 프로젝트**로 진행되었으며, 저는 다음과 같은 핵심 영역을 담당했습니다.

### ✅ 주요 기여 내역
- **[사용자]** 유기동물 필터 조회 및 입양 신청 기능 구현
- **[보호소]** 유기동물 등록·수정·삭제, 월별 통계 API 및 Chart.js 시각화 처리
- **[관리자]** 보호소 가입 승인/거절 및 메일 발송, 공지사항 트리형 구조 설계 및 CRUD 구현
- **공통 응답 구조**: `OkResponseDTO`, `ErrorResponseDTO`, `PageInformationDTO` 직접 설계 및 적용
- **파일 업로드 유틸**: `FileManager` 유틸 설계 및 모든 파일 기능 통합 적용
- **MyBatis**: `useGeneratedKeys` 활용해 파일-데이터 연결 시 동시성 문제 해결
- **RESTful API 구조** 및 응답 일관화 설계
- **AWS 배포**: EC2 + RDS 수동 배포, 보안 그룹 설정, WAR 운영 환경 구성
- **GitHub 협업 전략**: Git Flow 제안 및 브랜치/PR 기반 협업 구조 정립

RESTful 방식으로 사용자, 보호소, 관리자 기능을 구현했으며,  
**[👉 API 명세서 보기](https://www.notion.so/API-2287defdd3188071a5b7e4e7b0b020ae)**

## 📌 미리보기

| 메인페이지 | 입양 동물 조회 |
|--------|----------------|
| ![메인페이지](https://github.com/user-attachments/assets/8b90ba12-7f4a-4b81-aeb0-799c1f9ed635) | ![입양페이지](https://github.com/user-attachments/assets/6434513a-5c76-45ee-b202-36305471b4b9) |

| 보호시설 조회 | 보호시설 통계 |
|-------------|--------------|
| ![보호시설 페이지](https://github.com/user-attachments/assets/4e451a17-ee6b-41ed-898a-e4fb9952ca32) | ![통계조회](https://github.com/user-attachments/assets/08f4d76b-1399-4622-ab0e-d17d9e89f540) |

## 🛠 사용 기술 스택

| 분야 | 기술 |
|------|------|
| **언어** | ![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black) ![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white) |
| **백엔드** | ![Spring](https://img.shields.io/badge/Spring_Framework-6DB33F?style=flat-square&logo=spring&logoColor=white) ![MyBatis](https://img.shields.io/badge/MyBatis-0052CC?style=flat-square) ![JSP](https://img.shields.io/badge/JSP-007396?style=flat-square) ![Servlet](https://img.shields.io/badge/Servlet-6A5ACD?style=flat-square) |
| **프론트엔드** | ![JSTL](https://img.shields.io/badge/JSTL-116149?style=flat-square) ![EL](https://img.shields.io/badge/Expression_Language-505050?style=flat-square) ![Chart.js](https://img.shields.io/badge/Chart.js-FF6384?style=flat-square&logo=chartdotjs&logoColor=white) |
| **데이터베이스** | ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) |
| **서버 / 배포** | ![Apache Tomcat](https://img.shields.io/badge/Tomcat-003B6F?style=flat-square&logo=apachetomcat&logoColor=white) ![AWS EC2](https://img.shields.io/badge/AWS_EC2-FF9900?style=flat-square&logo=amazonec2&logoColor=white) ![AWS RDS](https://img.shields.io/badge/AWS_RDS-527FFF?style=flat-square&logo=amazonrds&logoColor=white) |
| **협업 도구** | ![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white) ![Notion](https://img.shields.io/badge/Notion-000000?style=flat-square&logo=notion&logoColor=white) ![Figma](https://img.shields.io/badge/Figma-F24E1E?style=flat-square&logo=figma&logoColor=white) ![ERD Cloud](https://img.shields.io/badge/ERD_Cloud-00B2E1?style=flat-square) |
| **기타 도구** | ![DBeaver](https://img.shields.io/badge/DBeaver-372923?style=flat-square) ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=postman&logoColor=white) ![draw.io](https://img.shields.io/badge/draw.io-F08705?style=flat-square) |

### 🧱 기술 스택 선택 배경
Spring Boot가 아닌 Spring Framework 기반의 레거시 환경을 직접 구성한 이유는 다음과 같습니다.
- 프레임워크의 내부 구조를 깊이 이해하고자 함
- DI, 트랜잭션, ViewResolver 등 핵심 설정을 직접 경험
- 일부 기업의 레거시 시스템 운영 환경에 대한 실무 대응력 확보
- Boot 기반 프로젝트에서도 문제를 구조적으로 해결할 수 있는 기반 마련
  
## 📐 ERD & 데이터베이스 설계

Animal-Care-Platform은 입양, 봉사, 후원, 게시판 등 다양한 주체와 기능 흐름을 고려하여  
총 **28개 테이블**을 팀원들과 설계하고 정규화했습니다.

### ✅ 설계 방식
- **개념 모델**: `Peter Chen 모델`을 활용해 초기 엔티티 간 관계 정의
- **논리/물리 모델**: `IE 방식`으로 정규화, 키 설정, 제약 조건 반영
- **기능 중심** 데이터 흐름 반영  
  └ 회원-입양, 보호소-유기동물, 후원-댓글 등 실사용 흐름을 기반으로 설계

### ✅ 설계 원칙
- 테이블은 기능 단위로 명확히 분리하여 책임을 분산
- 모든 연관 관계는 외래키로 연결 → 참조 무결성 보장
- 불필요한 중복 제거 및 정규화로 유지보수성 향상

### 🗂 ERD 자세히 보기

<a href="https://github.com/Rege-97/Animal-Care-Platform/wiki/DB-ER-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8" target="_blank">📄 ERD 다이어그램 보기</a>

## 🧩 공통 유틸 구조 & 예외 처리

Animal-Care-Platform은 기능별 복잡도가 높은 구조 속에서도  
**일관된 응답 포맷과 재사용 가능한 파일 처리 로직**을 구현하기 위해  
공통 유틸 구조를 설계하고 적용했습니다.


### ✅ 공통 응답 DTO 설계

- 모든 API 응답을 일관된 구조로 관리하기 위해 다음 DTO를 직접 설계 및 적용함:
  - `OkResponseDTO`: 단일 데이터 응답 (`code`, `message`, `data`)
  - `OkPageResponseDTO`: 목록 + 페이징 응답 (`code`, `data`, `PageInformationDTO`)
  - `ErrorResponseDTO`: 예외 응답 (`code`, `message`)

- **프론트에서는 `code`와 `message`만으로도 성공/실패 판단 가능**하도록 설계됨  
- 페이지네이션 정보도 포함되어 별도 API 호출 없이 UI 구현 가능

### ✅ 예외 처리 구조

- 모든 예외는 전역적으로 `ErrorResponseDTO` 형태로 래핑되어 응답됨
- 프론트엔드는 성공/실패 여부를 `code` 값으로 일관되게 분기 가능
- **컨트롤러/서비스/DAO** 전 구간에서 발생 가능한 오류에 대해 공통 메시지 정의

### ✅ 파일 업로드 유틸 (`FileManager`)

- 파일 업로드 및 삭제 로직을 재사용 가능한 유틸 클래스로 설계
- `multipart/form-data` 기반 업로드 시:
  - 경로가 존재하지 않으면 자동 생성
  - 저장 후 저장 경로를 반환하여 DB 저장 또는 프론트 렌더링 가능
- 삭제 시 파일 존재 여부 검증 후 예외 처리 포함
- **모든 도메인에서 재사용 가능하도록 설계** → 코드 중복 제거 + 유지보수 용이

이 구조를 통해 API 응답 처리, 파일 I/O, 예외 대응 등에서  
**중복 없이 안정적이고 확장 가능한 시스템 기반을 확보**했습니다.

## 🔧 내가 구현한 기능

Animal-Care-Platform은 사용자, 보호시설, 관리자 3가지 역할 기반 페이지로 구성되며,  
다음 영역의 핵심 기능들을 설계 및 구현했습니다.

### 🧑 사용자 페이지

#### ▫ 유기동물 조회 & 입양 신청
- 품종, 지역, 성별, 입양 상태 등 다중 조건 필터링 검색 API
- 페이지네이션 포함 응답 → 프론트 단 렌더링 최적화
- 상세 조회 시 보호소 정보 동시 반환
- 입양 신청 시 로그인 여부 및 관련 데이터 유효성 검증

#### ▫ 보호소 정보 조회
- 보호소명 / 지역명 기준 필터링 및 페이징 처리
- 상세 응답 시 후기, 봉사 안내 등 콘텐츠 포함
- 보호소 ID와 연관 콘텐츠 간 소속 관계 검증

### 🏠 보호시설 관리 페이지

#### ▫ 유기동물 등록 / 수정 / 삭제
- 보호소 계정 전용 REST API 구성
- `multipart/form-data` 방식 파일 업로드 처리
- 저장 경로 생성 및 반환, 삭제 처리 공통화
- 처리 상태 및 권한 유효성 검사 구조 설계

#### ▫ 보호소 통계 API
- 최근 6개월 기준 입양/봉사/게시글 월별 집계 API
- 누락된 월 보정을 위한 데이터 기본값 포함
- Chart.js 적용을 위한 응답 포맷 고정화

### 🛠 사이트 관리 페이지 (관리자)

#### ▫ 보호소 가입 승인 / 거절
- 가입 요청 목록 페이징 조회
- 상태 기반 승인/거절 처리 및 메일 발송
- 거절 사유 입력 → 사용자 알림 메일 자동 전송

#### ▫ 공지사항 게시판
- `ref`, `lev`, `turn` 기반 트리형 게시판 구조
- 등록/수정/삭제 API 구현
- 구조적 확장성 고려한 설계 (자유게시판, 답글 구조 지원 가능)

### 🧩 공통 처리 기반

- 응답 구조 표준화 DTO (`OkResponseDTO`, `ErrorResponseDTO`, `OkPageResponseDTO`) 설계 및 적용
- 파일 업로드 및 삭제는 공통 유틸 `FileManager`로 통합 처리
- 모든 기능 API는 RESTful 구조 준수

## 🧠 문제 해결 경험

Animal-Care-Platform 구현 중 실무에서 마주칠 수 있는  
데이터 무결성, 동시성, 예외 처리 등 다양한 문제들을 경험하고 해결했습니다.

### 📌 유기동물 등록 시 파일-데이터 연결 오류

- 초기에는 `MAX(IDX)` 방식으로 유기동물 등록 후 파일 경로를 결정했으나,
- → 동시에 등록 요청이 발생할 경우 다른 동물의 파일과 연결되는 문제 발생
- ✅ 해결: `useGeneratedKeys`를 통해 DB가 생성한 IDX를 즉시 받아와 정확한 파일 경로 설정

### 📌 보호소 가입 승인 중 중복 승인 처리

- 관리자가 이미 승인한 보호소 계정이 다시 승인되는 문제 발생
- ✅ 해결: `checkJoinStatus()` 메서드를 통해 현재 상태를 선검증하고  
  승인 가능한 상태에서만 처리가 되도록 로직 보완

### 📌 공지사항 ref 번호 중복 문제

- 공지사항 등록 시 `MAX(ref)`를 조회 후 INSERT하는 구조
- → 동시 요청 시 동일한 ref 값이 발생해 게시판 트리 구조 깨짐
- ✅ 해결: `INSERT ... SELECT` 트랜잭션 처리로  
  ref 값 생성과 INSERT를 원자적으로 처리하여 충돌 방지

### 📌 보호소 통계 데이터 누락 문제

- 월별 통계 API에서 값이 없는 월은 응답 자체에서 빠지는 문제 발생
- ✅ 해결: 누락된 월은 서버에서 직접 확인 후 기본값 0을 채워  
  응답에 포함 → Chart.js에서 안정적으로 시각화 가능

### 📌 보호소 상세 조회 시 무효한 콘텐츠 요청

- 후기나 봉사 안내 요청 시 보호소 ID가 잘못된 경우에도 응답 시도됨
- ✅ 해결: 보호소와 연관 콘텐츠 간 소속 관계를 서버에서 검증  
  → 무효 요청은 `ErrorResponseDTO`를 통해 일관된 예외 처리

### 📌 입양 신청 시 유효성 검증 누락

- 로그인하지 않은 사용자가 신청하거나, 존재하지 않는 동물 ID 입력 시 서버 오류 발생
- ✅ 해결: 서버 단에서 로그인 여부, 동물 ID, 보호소 ID까지 모두 사전 검증  
  → 500 오류 발생 방지 및 사용자 피드백 명확화

## ☁️ AWS 배포 & 운영 환경 구성

이번 프로젝트는 실제 사용자에게 서비스를 제공할 수 있는 수준의 완성도를 목표로  
**AWS EC2 + RDS 환경에 직접 배포 및 운영을 구성**하며 실전 인프라 경험을 쌓았습니다.


### 📦 EC2 (Ubuntu 22.04) 환경 설정

- EC2 인스턴스 생성 후, Apache Tomcat 9.0 수동 설치
- WAR 파일을 `/webapps`에 직접 배포하여 수동 운영 구조 구성
- 보안 그룹 설정: `22`, `80`, `9090` 포트 개방


### 🛠 서버 환경 설정

- 리눅스 기반으로 Java 11, Maven 환경 설치
- Tomcat 환경 변수 설정 (`JAVA_HOME`, `CATALINA_HOME`)

### 🛢 RDS(MySQL 8.0) 구성

- RDS에서 MySQL 인스턴스 생성, 파라미터 및 보안 그룹 설정

### 🔐 운영 안정성 확보

- 서버 과부하 예방을 위한 리소스 모니터링 (`htop`, `top`, `df -h` 등)
- **프리티어 EC2의 메모리 한계를 극복하기 위해 1GB Swap Memory 수동 설정**

### 📎 기타 운영 사항

- 배포 환경에 따라 `localhost` 기반 API URL을 실제 EC2 Public IP로 변경
- `mapKey.properties` 파일을 통한 Kakao 지도 API Key 설정 분리

## 📈 결과 & 회고


### 🚀 성과 요약

- 실제 배포 환경에서 사용자 서비스 가능한 수준으로 프로젝트 완성
- 보호소/유기동물/입양/후원 등 실제 흐름에 맞춘 기능 구현
- 프리티어 EC2 환경에서 발생하는 문제를 **스왑 메모리, 리소스 분리 등으로 직접 해결**
- 사용자/보호소/관리자 간 권한 구조를 직접 설계하고 정상적으로 분기 처리
- **ERD/Figma 기반의 협업 구조 수립**, 팀원 간 기능 흐름 공통 이해 공유
- Git Flow 도입 및 PR 병합 전략 가이드 → **팀원 간 협업 품질 향상**
- **RESTful API URI/응답 구조 일관화** 및 DTO 기반 설계 경험
  - 에러 응답 포맷 통일 (`status`, `message` 포함)
  - 권한 분기, 입력 검증, 예외 대응을 Controller 단에서 구조화

### 🧠 기술적 성장

- **JSP/Servlet + MyBatis** 기반 구조를 실전 수준까지 구성하며 백엔드 기반 다짐
- 파일 업로드, 이미지 경로 처리, 다중 테이블 조회 등 실제 웹 기능 흐름에 대한 깊이 있는 이해
- DB 중심의 비즈니스 로직 설계 능력 강화 (JOIN/조건 분기/예외 대응 등)
- **RESTful 개발 방식 적용**  
  → URL 명세 설계, 응답 포맷 일관화, 예외 처리 통합 등을 직접 구조화
- 프리티어 서버 구성, 로그 분석, 인프라 운용 등 **운영단 레벨까지 경험**

### 🤝 협업과 태도 변화

- 팀원 기술 역량과 일정 현실성을 함께 고려하여 구조적 방향 제시
- 기능 설계 시마다 화면 흐름, ERD, 권한 구조를 먼저 시각화하며 소통
- 혼자 구현하는 것을 넘어서 **같이 완성해가는 협업 흐름의 중요성 체감**
- 

> 단순히 구현만 하는 프로젝트가 아니라,  
> **"기획 → 설계 → 구현 → 운영" 전체 사이클을 실제처럼 겪은 경험**이었습니다.




