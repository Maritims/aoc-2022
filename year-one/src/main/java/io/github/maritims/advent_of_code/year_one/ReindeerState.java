package io.github.maritims.advent_of_code.year_one;

public class ReindeerState {
    private final Reindeer reindeer;
    private       int      remainingStamina;
    private       int      remainingRestTime;
    private       int      travelledDistance;
    private       int      points;

    public ReindeerState(Reindeer reindeer) {
        this.reindeer = reindeer;
        this.remainingStamina = reindeer.staminaInSeconds();
    }

    public int getTravelledDistance() {
        return travelledDistance;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        points++;
    }

    public void tick() {
        if (remainingStamina > 0) {
            travelledDistance += reindeer.velocityInKilometersPerSecond();
            remainingStamina--;
        } else {
            if (remainingRestTime == 0) {
                remainingRestTime = reindeer.restTimeInSeconds();
            }

            remainingRestTime--;

            if (remainingRestTime == 0) {
                remainingStamina = reindeer.staminaInSeconds();
            }
        }
    }
}
