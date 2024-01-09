# Implementing CORS in My Spring Boot Projects

In my Spring Boot applications, handling Cross-Origin Resource Sharing (CORS) is essential, especially when front-end and back-end are hosted on different domains. Let's delve into how I've implemented CORS in my projects, with specific code snippets.

## Using `@CrossOrigin` Annotation

**Overview:**  
In my controllers, I utilize the `@CrossOrigin` annotation to enable CORS. This approach is straightforward and applied directly at the controller level.

```java
@CrossOrigin
@RestController 
@RequestMapping("/api/sort")
public class SortController {
    // Controller methods
}
```

**Explanation:**
The `@CrossOrigin` annotation at the top of the SortController class indicates that CORS is enabled for all request mappings in this controller. It allows requests from any origin, which is useful when the front-end making requests to this API is hosted on a different domain.

## Configuring CORS in Security COnfiguration

**Overview:**
In my security configuration, I've also defined CORS settings, which are particularly useful when working with Spring Security.

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.cors(Customizer.withDefaults()).cors(Customizer.withDefaults()).headers(headers -> headers
    // additional configurations
}
```

**Explanation:**
This snippet from my `SecurityConfig` class enables CORS with default settings within the Spring Security context. It ensures that CORS configurations are respected in conjunction with the security settings of my application.

## Global CORS Configuration

**Overview:**
For a more global approach, affecting the entire application, I've used an MVC configuration class to define CORS settings.

```java
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*") 
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");
    }
}
```

**Explanation:**
In this `MvcConfig` class, I'm configuring CORS settings for all endpoints (`/**`) in the application. I've allowed requests from any origin (`allowedOrigins("*")`) and permitted all standard HTTP methods and headers. This global setup is effective when you have multiple controllers and want a consistent CORS policy across the board.

## Conclusion

Implementing CORS in my Spring Boot applications has been vital for ensuring smooth communication between different parts of my web applications, hosted on various domains. Whether using annotations for specific controllers or global configurations, understanding and correctly implementing CORS has been essential in developing secure and functional applications.