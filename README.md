# Virtual vs Platform Threads in Spring Boot

This project benchmarks the performance difference between **platform threads** and **virtual threads** in Java 21 using Spring Boot.

## Scenarios
- 500 customer records in an H2 database
- `/platform-generate-report` uses a fixed thread pool
- `/virtual-generate-report` uses virtual threads (per-task)
- CSV is written on each request

## Load test with Apache Benchmark
Example: ab -n 5000 -c 500 http://localhost:8080/virtual-generate-report

## Goal
Trying to understand why virtual threads sometimes perform worse, especially with I/O operations (DB + File).

Feel free to check the code and give feedback!
