package util.exception;

import util.TimeUtil;

import java.time.LocalTime;

public class TooLateToVote extends RuntimeException{
    public TooLateToVote() {
        super("It's " + LocalTime.now() + " Voting is prohibited after: " + TimeUtil.getVoteFinishTime());
    }
}