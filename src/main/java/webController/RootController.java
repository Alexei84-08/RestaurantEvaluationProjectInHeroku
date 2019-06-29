package webController;

import org.apache.commons.dbcp.managed.ManagedConnection;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.*;
import to.DayEstimate;
import to.UserTo;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class RootController {
    private final Logger LOG = LoggerFactory.getLogger(RootController.class);

    private final DayEstimateCreator estimateCreator;

    private final RestaurantService restaurantService;

    private final UserService userService;

    @Autowired
    public RootController(DayEstimateCreator estimateCreator, RestaurantService restaurantService, UserService userService) {
        this.estimateCreator = estimateCreator;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @GetMapping()
    public String root(@NotNull Model model){
        LOG.info("getByDateWithMenuHistory date = {}", LocalDate.now());
        model.addAttribute("restaurants", restaurantService.getByDateWithMenuHistory(LocalDate.now()));
        return "index";
    }

    @GetMapping(value = "/estimate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dayEstimate() {
        LOG.info("dayEstimate date = {}", LocalDate.now());
        DayEstimate dayEstimate = estimateCreator.createDayEstimate(LocalDate.now());
        return ResponseEntity.ok(dayEstimate);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registeration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registerationUser(@Valid @ModelAttribute UserTo userTo){
        LOG.info("registerationUser userTo ={}", userTo);
        userService.save(userTo);
        return "redirect:/";
    }

    @GetMapping("/dbupdate")
    public void runSqlFile() throws SQLException {
        private final JdbcTemplate jdbcTemplate;
        Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-79-125-4-72.eu-west-1.compute.amazonaws.com:5432/dd72b35hi5r0ri?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "dxmfjzxurhfgsf", "3eca5fbc00b068b33e59617e6b1c28747182471ed9576601b1a1266091ae0b53");

//        Connection dataSource = DriverManager.getConnection("dbH2/heroku.properties");


        Resource resource = new ClassPathResource("dbH2/populateDB.sql")
        EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
        ScriptUtils.executeSqlScript(connection, encodedResource);
    }}
