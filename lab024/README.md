Brief notes about this project.

1. This is a simple Spring Security Project.
2. The project has a built-in or embedded h2 database.
3. There is a dependency added as part of Spring Security.
4. This dependency will ensure the entire application is secured including the endpoints.
5. Main configuration is loaded in the class by name 'SecurityConfiguration'.
6. This config file implements a spring-framework service viz., UserDetailsService which is autowired to create necessary objects.
7. In this class, we have overridden the interface SecurityFilterChain to customize the security of spring application.
8. As part of the customizeation we have disabled csrf and explicitely asked for filtering or permitting certain urls only.
9. In this particular example, we have explicitely added to 'allow' any frames related display as 'h2db' contains frame post login.
10. Mostly importantly we have overridden a AuthenticationProvider interface to use UserDetailsService, which inturn connects to h2database.
11. Many things are handled inherently by springframework
12. UserService implements UserDetailsService to load user-specific data for authentication.
13. Country model represents a simple POJO for country data with properties like id, name, code, and population.
14. CountryController is a REST controller handling GET and POST requests for country data.
15. AuthUser is an entity model representing user authentication data stored in the database.
16. A data.sql file is included to pre-populate the Auth_User table with sample user credentials.
17. The project uses basic authentication for simplicity and demonstration purposes.
18. NoOpPasswordEncoder is used for password encoding (Note: not recommended for production use).
19. The application demonstrates stateless session management.
20. Lombok annotations are used to reduce boilerplate code in model classes.


Sequence of events.

Client sends a curl request to a secured endpoint
Request reaches the Security Filter Chain
SecurityContextPersistenceFilter checks for existing session1
If no session, proceeds to authentication filters
UsernamePasswordAuthenticationFilter extracts credentials
Creates UsernamePasswordAuthenticationToken
AuthenticationManager receives the authentication request
Delegates to appropriate AuthenticationProvider
AuthenticationProvider (e.g. DaoAuthenticationProvider) processes request
Calls UserDetailsService to load user data
Uses PasswordEncoder to verify credentials
If authentication succeeds:
    SecurityContext is updated with authentication details
    AccessDecisionManager checks authorization
If authorized:
    Request proceeds to the secured resource (e.g. controller method)
    Controller processes the request and prepares response
    Response passes back through Security Filter Chain
    Possibly adding security headers or tokens2
    Client receives the HTTP response