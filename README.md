# 프로젝트 jar 파일

1. dist/oam-api_jennifer-1.0.0.jar : 제니퍼 뷰서버에 실험실로 추가할 수 있는 플러그인
2. dist/oam-api_local-1.0.0.jar : 제니퍼 뷰서버 없이 독립적으로 실행할 수 있는 테스트 애플리케이션

# OAM API 플러그인 설정

1. 관리 > 어댑터 및 실험실 > 실험실 탭으로 이동한다.
2. 경로는 dist/oam-api_jennifer-1.0.0.jar 파일을 선택한다.
3. 종류는 API, ID는 oamapi로 설정한다.
4. 제니퍼 뷰서버를 재시작한다.

# 테스트 애플리케이션 실행

1. java -jar dist/oam-api_local-1.0.0.jar 명령어를 입력한다.
2. http://localhost:8080/plugin/oamapi/user/타입 경로로 API를 호출한다.
3. Request Body를 설정하지 않으면 서버 에러가 발생한다.
```
curl -X GET \
-H "Content-Type: application/json" \
-d '{ "eACommHeaderVO": [], "id": "" }' \
http://localhost:8080/plugin/oamapi/user/read
```
참고로 eACommHeaderVO가 빈 배열이여도 커맨드는 동작한다.
다만 설정 값이 다수가 존재할 경우에는 가이드 문서에 명시된 기본 헤더 4개만 Response Body에 추가된다.

# API 타입별 스펙

## 사용자 생성 (Create)
Request Method는 POST와 GET 방식만 지원한다.

### Request Body
```
POST http://localhost:8080/plugin/oamapi/user/create
Content-Type: application/json

{
  "eACommHeaderVO": [],
  "id": "tester",
  "password": "1234",
  "name": "Tester",
  "department": "R&D",
  "group": "guest"
}
```

### Response Body
```
{
  "eACommHeaderVO": [],
  "message": "SUCCESS"
}
```

## 사용자 조회 (Read)

### Request Body
id 값이 공백이면 전체 사용자를 조회하며, 사용자 비밀번호는 보안상의 이유로 공백으로 표시된다.
```
GET http://localhost:8080/plugin/oamapi/user/read
Content-Type: application/json

{
  "eACommHeaderVO": [],
  "id": "guest"
}
```

### Response Body
```
{
  "eACommHeaderVO": [],
  "message": "SUCCESS",
  "users": [
    {
      "id": "tester",
      "password": "",
      "name": "Tester",
      "group": "guest",
      "department": "R&D"
    }
  ]
}
```

## 사용자 수정 (Update)

### Request Body
```
POST http://localhost:8080/plugin/oamapi/user/update
Content-Type: application/json

{
  "eACommHeaderVO": [],
  "id": "tester",
  "password": "1234",
  "name": "Tester2",
  "department": "R&D",
  "group": "guest"
}
```

### Response Body
```
{
  "eACommHeaderVO": [],
  "message": "SUCCESS"
}
```

## 사용자 삭제 (Delete)

### Request Body
```
POST http://localhost:8080/plugin/oamapi/user/delete
Content-Type: application/json

{
  "eACommHeaderVO": [],
  "id": "tester"
}
```

### Response Body
```
{
  "eACommHeaderVO": [],
  "message": "SUCCESS"
}
```

## 응답 메시지 정리
모든 요청에 대한 응답에는 message가 포함되는데, 종류는 다음과 같다.
 - SUCCESS : 요청 성공
 - USER_EXIST : 사용자가 존재함
 - USER_NOT_EXIST : 사용자가 존재하지 않음
 - GROUP_NOT_EXIST : 그룹이 존재하지 않음
 - REQUIRED_PARAMETERS : 필수 요청 값이 누락됨
 - 서버 예외 메시지 : 예외 종류에 따라 메시지가 다르게 표시됨

