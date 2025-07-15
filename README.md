# Virtual vs Platform Threads in Spring Boot

This project benchmarks the performance difference between **platform threads** and **virtual threads** in Java 21 using Spring Boot.

## Scenarios
- 500 customer records in an H2 database
- `/platform-generate-report` uses a fixed thread pool
- `/virtual-generate-report` uses virtual threads (per-task)
- CSV is written on each request

## Load test with Apache Benchmark
Example: ab -n 300 -c 100 http://localhost:8080/virtual-generate-report

## Goal
Trying to understand why virtual threads sometimes perform worse, especially with I/O operations (DB + File).

Feel free to check the code and give feedback!


C:\windows\system32>ab -n 300 -c 100 http://localhost:8080/platform-generate-report
This is ApacheBench, Version 2.3 <$Revision: 1923142 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Finished 300 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /platform-generate-report
Document Length:        0 bytes

Concurrency Level:      100
Time taken for tests:   0.149 seconds
Complete requests:      300
Failed requests:        0
Total transferred:      27600 bytes
HTML transferred:       0 bytes
Requests per second:    2019.21 [#/sec] (mean)
Time per request:       49.524 [ms] (mean)
Time per request:       0.495 [ms] (mean, across all concurrent requests)
Transfer rate:          181.41 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.5      0       2
Processing:     5   42  13.5     46      59
Waiting:        1   24  13.2     24      55
Total:          6   42  13.5     47      60

Percentage of the requests served within a certain time (ms)
  50%     47
  66%     50
  75%     52
  80%     54
  90%     56
  95%     57
  98%     59
  99%     59
 100%     60 (longest request)

C:\windows\system32>ab -n 300 -c 100 http://localhost:8080/virtual-generate-report
This is ApacheBench, Version 2.3 <$Revision: 1923142 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Finished 300 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /virtual-generate-report
Document Length:        0 bytes

Concurrency Level:      100
Time taken for tests:   0.147 seconds
Complete requests:      300
Failed requests:        0
Total transferred:      27600 bytes
HTML transferred:       0 bytes
Requests per second:    2037.31 [#/sec] (mean)
Time per request:       49.084 [ms] (mean)
Time per request:       0.491 [ms] (mean, across all concurrent requests)
Transfer rate:          183.04 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.5      0       1
Processing:    12   40   8.1     43      51
Waiting:        1   24  12.7     22      48
Total:         12   41   8.1     43      51

Percentage of the requests served within a certain time (ms)
  50%     43
  66%     46
  75%     46
  80%     47
  90%     47
  95%     48
  98%     49
  99%     50
 100%     51 (longest request)

C:\windows\system32>ab -n 300 -c 100 http://localhost:8080/platform-generate-report
This is ApacheBench, Version 2.3 <$Revision: 1923142 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Finished 300 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /platform-generate-report
Document Length:        0 bytes

Concurrency Level:      100
Time taken for tests:   0.112 seconds
Complete requests:      300
Failed requests:        0
Total transferred:      27600 bytes
HTML transferred:       0 bytes
Requests per second:    2681.06 [#/sec] (mean)
Time per request:       37.299 [ms] (mean)
Time per request:       0.373 [ms] (mean, across all concurrent requests)
Transfer rate:          240.88 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.4      0       1
Processing:     6   30   8.3     34      37
Waiting:        1   18   9.6     17      37
Total:          7   31   8.3     34      38

Percentage of the requests served within a certain time (ms)
  50%     34
  66%     36
  75%     36
  80%     36
  90%     37
  95%     37
  98%     37
  99%     37
 100%     38 (longest request)

C:\windows\system32>ab -n 300 -c 100 http://localhost:8080/virtual-generate-report
This is ApacheBench, Version 2.3 <$Revision: 1923142 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Finished 300 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /virtual-generate-report
Document Length:        0 bytes

Concurrency Level:      100
Time taken for tests:   0.138 seconds
Complete requests:      300
Failed requests:        0
Total transferred:      27600 bytes
HTML transferred:       0 bytes
Requests per second:    2175.10 [#/sec] (mean)
Time per request:       45.975 [ms] (mean)
Time per request:       0.460 [ms] (mean, across all concurrent requests)
Transfer rate:          195.42 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.5      0       3
Processing:     9   37  10.2     40      50
Waiting:        0   21  11.7     21      47
Total:         10   38  10.2     41      50

Percentage of the requests served within a certain time (ms)
  50%     41
  66%     44
  75%     45
  80%     45
  90%     48
  95%     49
  98%     50
  99%     50
 100%     50 (longest request)
