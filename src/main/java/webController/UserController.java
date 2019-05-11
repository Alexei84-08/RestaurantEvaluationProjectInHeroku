package webController;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import service.VoteService;
import to.VoteTO;
import util.AuthorizedUser;
import util.exception.NotFoundException;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final VoteService voteService;

    @Autowired
    public UserController(VoteService voteService) {
        this.voteService = voteService;
    }

//    @GetMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<VoteTO>> getUserVoteHistory() {
//        LOG.info("getUserVoteHistory user id = {}", AuthorizedUser.id());
//
//        return ResponseEntity.ok(voteService.createVoteTOList(AuthorizedUser.get().getUser()));
//    }

    @GetMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VoteTO>> getUserVoteHistory() {
        LOG.info("getUserVoteHistory user id = {}", AuthorizedUser.id());

        return ResponseEntity.ok(voteService.createVoteTOList(AuthorizedUser.get().getUser()));
    }

    @PostMapping(value = "/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addVote(@NotNull @RequestBody Map<String, Integer> map) {
        LOG.info("addVote user id = {} restaurantId = {}", AuthorizedUser.id(), map.get("restaurantId"));
        voteService.save(map.get("restaurantId"), AuthorizedUser.get().getUser());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
