# Shopping-appsvc
Shopping Taco Loco services

The project provides:

1. Custom and manage all exceptions basing on error codes and messages as friendly error JSONs. 
```JAVA
POST http://localhost:8081/api/v1/shopping-carts/total-price
{
   "items": [
        {"product":"Apple", "quantity": 100},
        {"product":"Balama","quantity": 10}
    ]
}

HTTP 400 Bad Request
{
    "code": "00001",
    "message": "No such product instance",
    "extraMessage": "product.instance.not.found",
    "fieldErrors": null
}
```
3. Spring Controller advice to translate the server-side exceptions.
```JAVA
@ControllerAdvice
public class ExceptionTranslator {

    private final Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);
    @Autowired
    private MessageByLocaleService messageByLocaleService;

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorVM dto = new ErrorVM("10001", getMessageLocalization("error.form.input.validation"));
        for (FieldError fieldError : fieldErrors) {
            dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }
        return dto;
    }
}
```
5. Spring Boot logback to write out daily log file. All configuration of logging is stored in logback-spring.xml
6. Unit Testing
7. Using org.junit.jupiter.api
8. Web Layer Testing: SpringBootTest and MockMvc
9. Factory Design Partern example.

Required: JDK version 8+, Maven 3.5x, Spring Boot Version 2.3.3

Install and build step by step by Maven
-	Maven install dependencies: mvn clean install
-	Maven package: mvn clean package
-	Run dev and production environments:

java -jar target/TacoLoco-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

java -jar target/TacoLoco-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

Thank you

Khiem Truong
