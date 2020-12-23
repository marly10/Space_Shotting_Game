public abstract class EnemyAi
{
    public abstract void AiMovement();

    // instantiating Singleton class with variable x
    static EnemyAiMovement EnemyAiSingleton = EnemyAiMovement.getInstance();
}
