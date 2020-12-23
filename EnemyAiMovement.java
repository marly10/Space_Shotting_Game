public class EnemyAiMovement//context for movement
{
    private static EnemyAiMovement instance = null;
    private int x, y;

    //singleton implementation
    public static EnemyAiMovement getInstance()
    {
        if (instance == null)
        {
            instance = new EnemyAiMovement();
        }
        return instance;
    }//g

    public void droneLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private EnemyAi EnemyAiMovement;

    public EnemyAiMovement(){
        EnemyAiMovement = null;
    }

    public void setEnemyAiMovement(EnemyAi EnemyAiMovement){//setter
        this.EnemyAiMovement = EnemyAiMovement;
        EnemyAiMovement.AiMovement();
    }

    public EnemyAi getEnemyAiMovement(){//getter
        return EnemyAiMovement;
    }
}
