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

# API 타입별 스펙

## 사용자 생성 (create)

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

## 사용자 조회 (read)

### Request Body
id 값이 공백이면 전체 사용자를 조회한다.
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

