# SpringParentPom

1. `git clone` + `mvn install`

2. Add this in child project POM
```
	<parent>
		<groupId>com.hermestrade</groupId>
		<artifactId>spring-parent-pom</artifactId>
		<version>1.0.0</version>
	</parent>
```