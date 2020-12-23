public class EnemyAiMovementDown extends EnemyAi
{

    Drone flight;

    public EnemyAiMovementDown(Drone flight)
    {
        this.flight = flight;
    }

    @Override
    public void AiMovement() {
        System.out.println("EnemyAiMovement ");
        flight.setMoveY(1);
        flight.setMoveX(0);
    }
}
