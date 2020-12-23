public class EnemyAiMovementRightDown extends EnemyAi {

    Drone flight;

    public EnemyAiMovementRightDown(Drone flight)
    {
        this.flight = flight;
    }

    @Override
    public void AiMovement() {
        System.out.println("EnemyAiMovement ");
        flight.setMoveY(1);
        flight.setMoveX(1);
    }
}
