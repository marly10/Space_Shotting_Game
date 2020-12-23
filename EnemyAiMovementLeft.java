public class EnemyAiMovementLeft extends EnemyAi {

    Drone flight;

public EnemyAiMovementLeft(Drone flight)
{
    this.flight = flight;
}

    @Override
    public void AiMovement() {
        System.out.println("EnemyAiMovement ");

        flight.setMoveY(0);
        flight.setMoveX(-1);
    }
}
