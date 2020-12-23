public class EnemyAiMovementLeftDown extends EnemyAi {

    Drone flight;

    public EnemyAiMovementLeftDown(Drone flight)
    {
        this.flight = flight;
    }

    @Override
    public void AiMovement() {
        System.out.println("EnemyAiMovement ");
        flight.setMoveY(1);
        flight.setMoveX(-1);
    }
}
