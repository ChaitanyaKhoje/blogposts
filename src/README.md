Backend Assessment - Blog posts

My project has Guava Cache, which has a Cache loader that runs in a separate thread, so there is no need to use the Executor Service for multiple requests i.e concurrency is being tested in the cache itself as requests happen in parallel.

src/main - holds all the project files
src/test - holds all the test files

How to run?

1. This is a maven project with spring-boot
2. Command line statements to run:
    - mvn clean
    - mvn install
    - mvn spring-boot:run