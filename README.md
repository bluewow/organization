## 기술과제

### 서버 구동 방법

1. PostgreSQL 설정  
    - organization_demo 데이터베이스 생성
    - src/main/resources/application.properties 의 datasource 의 username, password 를 변경한다
2. 서버 구동
    - ./gradlew bootRun
3. 테스트  
    - http://localhost:9000/api/organizations

### 기타
- server port = 9000 
- 한글깨짐 발생시 data.sql 파일 내용을 직접 수행
