public class EnemyAiMovementUp extends EnemyAi {

    Drone flight;

    public EnemyAiMovementUp(Drone flight)
    {
        this.flight = flight;
    }

    @Override
    public void AiMovement() {
        System.out.println("EnemyAiMovementUp");
        flight.setMoveY(-1);
        flight.setMoveX(0);
    }
}
