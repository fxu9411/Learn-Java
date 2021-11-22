# Building a RESTful Web Service
## Create a Resource Representation Class
---
### Step 1. Think about the service interaction
* The service will handle ```GET``` request from ```/greeting```, optionally with a name parameter in the query string.
* The ```GET``` request should return a ```200 OK``` response with JSON in the body
* To model the greeting representation, create a resource representation class. To do so, provide a POJO with fields
* Fields are ```id``` and ```content```

### Step 2. Create a Resource Controller
* In Spring's approach to building  RESTful Web services, HTTP Request are handlede by a controller, identified by ```@RestController``` annotation
* GreetingController will handle GET Request for ```/greeting``` by returning a new instance of the ```Greeting``` class

    ```java
    @RestController
    public class GreetingController {

        private static final String template = "Hello, %s!";
        private final AtomicLong counter = new AtomicLong();

        @GetMapping("/greeting")
        public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
            return new Greeting(counter.incrementAndGet(), String.format(template, name));
        }
    }
    ```
* ```@GetMapping``` annotation ensures that HTTP GET Requests to ```/greeting``` are mapped to the ```greeting()``` function
* There are companion annotations for other HTTP verbs (e.g. ```@PostMapping``` for ```POST```). There is also a ```@RequestMapping``` annotation that they all derive from, and can serve as a synonym (e.g. ```@RequestMapping(method=GET)```)
* ```@RequestParam``` binds the value of the query string parameter ```name``` in the ```name``` parameter of the ```greeting()``` method
---
### Difference between MVC and RESTful
Key difference between traiditional MVC controller and the RESTful web service controller shown earlier is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service controller populates and returns a Greeting object. The object data will be written directly to the HTTP response as JSON.

---
## Main File
* ```@SpringBootApplication``` will add the following annotations as well
  * ```@Configuration```: Tags the class as a source of bean definitions for the application context
  * ```@EnableAutoConfiguration```: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
  * ```@ComponentScan```: Tells Spring to look for other components, configurations, and services in the ```com/example``` package, letting it find the controllers.
* The ```main()``` method uses Sprint Boot's ```SpringApplication.run()``` method to launch an application. 