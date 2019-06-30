package webController;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.*;
import to.DayEstimate;
import to.UserTo;

import javax.validation.Valid;
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
}
