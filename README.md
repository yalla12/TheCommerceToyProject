## 프로젝트 설명

- 회원 CRUD 구현

## 개발 환경
- 언어 : java (1.8)
- database : h2
- framework : spring boot (2.6.2)

## 요구 사항
- 회원가입
- 회원 목록 조회
- 회원 정보 수정

# 소스 설명

프로젝트 전체적인 구조는 MVC 패턴으로 구성되어 있습니다.

- Controller
  - Controller는 Data를 반환하기 때문에 RestContrller 어노테이션을 사용하였습니다. 그리고 생성자 주입을 자동으로 설정해 주는 requiredargsconstructor 어노테이션을 사용했습니다. 
  - CRUD 기능에 따라 @GetMapping, @PostMapping, @PutMapping 을 사용했으며 @PathVariable과 @RequestBody를 통해 요청 데이터를 전달 받았습니다. 그리고 상세 조회 기능에서는 페이징 기능이 필요하기 때문에 params을 통해 데이터를 전달 받았습니다. 

- Service 
  - Service에서는 Controller로 부터 전달 받는 파라미터를 통해 Respository에서 원하는 데이터를 가져오는 로직을 수행하는 곳입니다.
  - 모든 메소드에는 트랜잭션 처리를 하였으며 조회 메소드는 @Transactional(readOnly = true) 통해  DB에서 데이터를 읽기만 한다는 것을 명확하게 확인할 수 있어 가독성을 향상시켰습니다. 

- Repository
  - Java 진영에서 ORM(Object-Relational Mapping) 기술 표준으로 사용하는 인터페이스를 만들었습니다.
 
  -Test
  - Spring boot 내장되어 있는 Junit5를 사용했습니다.
 
## DB

h2 database 선택이유
- 데이터가 서버가 동작하는 중에만 저장되면 되었고, 데이터 용량이 작아 가벼운 프로그램인 h2 db를 선택
- h2 db는 하드 디스크가 아니라 메모리에서 Data CRUD 연상을 진행함으로 다른 디비보다 상대적으로 효율이 좋음 (현재 요구사항에 CRUD 기능뿐이였음으로 적합하다고 판단)

h2 확인 주소
- URL: http://localhost:8080/h2-console/
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:test
- User Name: sa
- Password: x(공백)

## API
Swagger를 통한 API 문서화
- API 확인 URL : http://localhost:8080/swagger-ui/
