public class EnemyAiMovementRightUp extends EnemyAi {

    Drone flight;

    public EnemyAiMovementRightUp(Drone flight)
    {
        this.flight = flight;
    }

    @Override
    public void AiMovement() {
        System.out.println("EnemyAiMovement ");
        flight.setMoveY(-1);
        flight.setMoveX(1);
    }
}
