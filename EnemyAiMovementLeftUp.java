public class EnemyAiMovementLeftUp extends EnemyAi {

    Drone flight;

    public EnemyAiMovementLeftUp(Drone flight)
    {
        this.flight = flight;
    }

    @Override
    public void AiMovement() {
        System.out.println("EnemyAiMovement ");
        flight.setMoveY(-1);
        flight.setMoveX(-1);
    }
}
