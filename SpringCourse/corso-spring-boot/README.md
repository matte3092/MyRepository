# project-template

Project Template used to generate archetype

Prerequisites:

	1. `git clone parent-pom` + `mvn install`

	2. `git clone utility-library` + `mvn install`

Archetype can be generated with the following maven command:

	mvn archetype:create-from-project

It then generates the directory tree of the archetype in the target/generated-sources/archetype directory