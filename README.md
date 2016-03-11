# congenial-chainsaw
yet another Jersey rest tutorial.

My goals were to get simple HTTP GETs to return an XML instance created by JAXB.

As such `Customer.java` is the JAXB object that will create a simple XML document with root element customer, child element name which has attribute pin as an integer.

`XMLService.java` exposes "/customer" which should return a simple string, my thought during troublshooting was `HTTP GET /customer` and at least return a simple String. That doesn't work. We also expose "/customer/{pin}" if `HTTP GET /customer/1234` we should receieve XML document of
```<customer pin="1234">
  <name>mykong</name>
  </customer>
  ```
This doesn't work either. I did enable trace on the Jersey servlet and I get these trace headers back

```
--REQUEST--
GET /mavenproject1/rest/customer/1234
Host: localhost:8888
User-Agent: Mozilla/5.0 (X11; Fedora; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Referer: http://localhost:8888/mavenproject1/rest/customer/1234
Cookie: JSESSIONID=32903AC549B6319DC66D81534B41FEF1
Connection: keep-alive
--RESPONSE--
Content-Language: en
Content-Length: 1010
Content-Type: text/html;charset=utf-8
Date: Fri, 11 Mar 2016 19:00:25 GMT
Server: Apache-Coyote/1.1
X-Jersey-Tracing-000: START       [ ---- /  ---- ms |  ---- %] baseUri=[http://localhost:8888/mavenproject1/rest/] requestUri=[http://localhost:8888/mavenproject1/rest/customer/1234] method=[GET] authScheme=[n/a] accept=[text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8] accept-encoding=[gzip, deflate] accept-charset=n/a accept-language=[en-US,en;q=0.5] content-type=n/a content-length=n/a
X-Jersey-Tracing-001: PRE-MATCH   [ 0.02 /  0.58 ms |  0.54 %] PreMatchRequest summary: 0 filters
X-Jersey-Tracing-002: MATCH       [ ---- /  0.66 ms |  ---- %] Matching path [/customer/1234]
X-Jersey-Tracing-003: MATCH       [ 0.07 /  0.71 ms |  2.12 %] RequestMatching summary
X-Jersey-Tracing-004: RESP-FILTER [ 2.47 /  3.42 ms | 70.71 %] Response summary: 0 filters
X-Jersey-Tracing-005: FINISHED    [ ---- /  3.49 ms |  ---- %] Response status: 404/CLIENT_ERROR|Not Found
```
It appears that Jersey is not finding a suitable servlet to handle my request.

mavenproject1 is my WAR and exploded directory in tomcat8 webapps folder, "/rest" is my url-mapping value in the web.xml, rest of URI is matching decorators in XMLService.java.
