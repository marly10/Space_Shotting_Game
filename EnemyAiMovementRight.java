public class EnemyAiMovementRight extends EnemyAi {

    Drone flight;

    public EnemyAiMovementRight(Drone flight)
    {
        this.flight = flight;
    }

    @Override
    public void AiMovement() {
        System.out.println("EnemyAiMovement");
        flight.setMoveY(0);
        flight.setMoveX(1);
    }
}
