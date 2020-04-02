# utility.library Library (version 1.0.0)

## Coverage report:

|               Element              | Coverage | Covered Instructions | Missed Instructions | Total Instructions |
|:----------------------------------:|:--------:|:--------------------:|:-------------------:|:------------------:|
|         com.hermestrade.utility.library         |  100,0 % |          35          |          0          |         35         |
| com.hermestrade.utility.library.controller.util |  100,0 % |          234         |          0          |         234        |
|       com.hermestrade.utility.library.dto       |  100,0 % |          67          |          0          |         67         |
|      com.hermestrade.utility.library.entity     |  100,0 % |          38          |          0          |         38         |
|    com.hermestrade.utility.library.exception    |  100,0 % |          168         |          0          |         168        |
|       com.hermestrade.utility.library.util      |  100,0 % |          57          |          0          |         57         |


## How to use it

Import this dependency in your POM

```
<dependency>
	<groupId>com.hermestrade</groupId>
	<artifactId>utility.library</artifactId>
	<version>1.0.0</version>
</dependency>
```

## Feature

- Automatic logging on controllers, all input input data (both query parameters and payloads) are printed
```
# Put this lines in your application.properties to enable/disable logging
utility.library.controllers.endpoint.log=true
utility.library.controllers.body.log=false
```

- Automatic audit on Entity (createdBy, createdDate, lastModifiedBy, lastModifiedDate, version)
[Not working with sprign security. I use only 'keycloak-spring-boot-starter' dependency.]
```
// Easy as extending a class!

@Entity
@Table(name = "stt_a")
public class AEntity extends BaseEntity {
	...
}
```

- BaseDTO corresponding to BaseEntity above, useful when mapping entity -> dto. Extending that class is enough.
```
// Easy as extending a class!

public class MyDTO extends BaseDTO {
	...
}
```

- Automatically generating 400 BadRequest collecting all the errors when using @Valid + @RequestBody with a DTO with some constraints (@NotNull, ...)


- Built in custom RuntimeExceptions to easily return a specific status code (Example: BadRequestException, NotFoundException).
[If called from @Transactional method, they rollback transaction!]
```
# MyService.java

@Autowired
MyRepository myRepository;

public getById(Integer id) {
	myRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find on database the entity with id=" + id.toString()));
}
```

- Need another smart exception? Implement the HandledException interface and extend RuntimeException. It will be automatically handled!
```
public class BadRequestException extends RuntimeException implements HandledException {

	private final String message;

	public BadRequestException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public ResponseEntity<Object> getResponseEntity() {
		return new ResponseEntity<>((Object) new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(), this.message), HttpStatus.BAD_REQUEST);
	}
}
```

- Bean definition for KeycloakSecurityContext & AccessToken when using 'keycloak-spring-boot-starter'. Just autowire (@Autowired) them and use!
