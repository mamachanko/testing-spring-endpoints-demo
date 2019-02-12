> If you are using JUnit 4, don’t forget to also add @RunWith(SpringRunner.class) to your test, otherwise the annotations will be ignored.

acceptance test
 * full (web) application context
 * exercise endpoints via (Test)RestTemplate
 * actual HTTP requests going over the local network
 * `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`

mockmvc acceptance test
 * full application context with mocked web environment
 * exercise endpoints via MockMvc
 * no network traffic
 * `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)`

mockmvc unit test
 * controller under test in isolation
 * no filters in chain unless configured otherwise
 * exercise endpoints via MockMvc
 * collaborators / dependencies need to be configured and injected or exposed
 * if you are in favour of annotation-driven test configuration use these:
   * `@WebMvcTest`
   * `@WebMvcTest(controllers = MyController.class)`
   * `@Autowired private MockMvc mockMvc;`
   * `@MockBean private MyControllerDependency myControllerDependency;`
 * but you can also manually configure your mvc test like so:
   * `MyControllerDependency myControllerDependency = mock(MyControllerDependency.class);`
   * `MyController myController = new MyController(resourceRepository);`
   * `MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();`
   * no annotations required
