package main.java.com.websystique.springmvc.springREST;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/* test it using URL
 * http://localhost:8080/SpringExampleProj/greeting?name=Ashish
 * http://localhost:8080/SpringExampleProj/greeting
 * 
 * return JSON object will be,
 * {"id":2,"content":"Hello, Ashish!"}
 */

/*
 * A key difference between a traditional MVC controller and the RESTful web service controller above 
 * is the way that the HTTP response body is created. Rather than relying on a view technology 
 * to perform server-side rendering of the greeting data to HTML, 
 * this RESTful web service controller simply populates and returns a Greeting object. 
 * The object data will be written directly to the HTTP response as JSON.

This code uses Spring 4’s new @RestController annotation, which marks the class as a controller where 
every method returns a domain object instead of a view. 
It’s shorthand for @Controller and @ResponseBody rolled together.

The Greeting object must be converted to JSON. 
Thanks to Spring’s HTTP message converter support, you don’t need to do this conversion manually. 
Because Jackson 2 is on the classpath, Spring’s MappingJackson2HttpMessageConverter is automatically chosen 
to convert the Greeting instance to JSON.
 */
@RestController //It’s shorthand for @Controller and @ResponseBody rolled together.
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}

/*
 * @SpringBootApplication is a convenience annotation that adds all of the following:

@Configuration tags the class as a source of bean definitions for the application context.
@EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
@ComponentScan tells Spring to look for other components, configurations, and services in the hello package, allowing it to find the GreetingController.
The main() method uses Spring Boot’s SpringApplication.run() method to launch an application. Did you notice that there wasn’t a single line of XML? No web.xml file either. This web application is 100% pure Java and you didn’t have to deal with configuring any plumbing or infrastructure.
*/