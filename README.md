# @Controller
웹 요청을 처리하는 컨트롤러 클래스를 정의할 때 사용하는 어노테이션이다.
Spring이 자동으로 빈(Bean)으로 등록한다.

# @GetMapping("/") 
HTTP GET 요청을 특정 메서드와 매핑하는 어노테이션
("/")는 루트 경로(/)에 대한 요청을 처리한다.
사용자가 웹 브라우저에서 http://localhost:8080/로 접속하면 이 메서드가 실행된다.

# @ResponseBody
메서드의 반환값을 HTTP 응답 본문(Body)으로 직접 전송하는 역할을 한다.
만약 return index.html 처럼 html 파일을 전송한다면 **@ResponseBody**은 빼야한다.

# Thymeleaf
템플릿엔진을 쓰면 서버/데이터베이스의 데이터를 HTML에 넣을 수 있다.

# 날짜, 시간 출력
    @GetMapping("/date")
    @ResponseBody
    String datePage(){
        LocalDateTime data = LocalDateTime.now(); // 현재 날짜 시간
        // 형식에 맞게 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return data.format(formatter);
    }

# spring.jpa.properties.hibernate.show_sql=true
JPA 문법쓸 때  SQL 따로 출력해줄지 여부

# spring.jpa.hibernate.ddl-auto=update
테이블 생성 자동으로 할지 여부, update 적으면 변경사함만 반영
, none 쓰면 모든 변경사항 반영 안함

# @Entity
    @Entity
    public class Item {
    String title;
    Integer price;
    }
class에 붙이면 ORM에서는 이런 이름으로 테이블을 생성해준다.
    
# 기본 키 설정 및 자동증가
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
@컬럼명 -> 기본키 설정

# @Column(nullable = false, unique = true)
    @Column(nullable = false, unique = true)
    public String title;
    public Integer price;
이 컬럼이 비어있으면 테이블에 입력을 막아준다.
이 컬럼값이 유니크 하지 않으면 테이블 입력을 막아준다.

# @Column(columnDefinition = "TEXT")
컬럼 타입 강제 지정

# @Column(length = 1000)
컬럼에 글자 제한 1000자

# @RequiredArgsConstructor
    // Lombok가 자동으로 생성하는 생성자:
    // public Posting(Long id, String title) {
    //     this.id = id;
    //     this.title = title;
    // }
    
    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
필드에 대한 생성자를 자동으로 만들어준다.

# var a = new ArrayList<>();
    a.add(30);
    a.add(40);
    -> 출력: a = [30, 40]
    
    2) System.out.println("a = " + a.get(0));
    
    3) ArrayList<Integer> a = new ArrayList<>();
1) 여러 값을 한번에 저장할 수 있다. 
2) 하나씩 가져오는 것도 가능하다
3) 구현 타입을 명시하여 <>안에 저장할 값들의 타입을 적는다.
   ArrayList<Integer>보다 List<Integer>가 더 상위 타입이다.

# // object의 변수들 한번에 출력하는 법
    public String toStrong(){
        return this.title + this.price;
    }
하지만 lombok 라이브러리를 사용하면 @ToString 으로 끝.

# static
static을 붙이면 클래스, 변수 이렇게 직접 사용 가능





