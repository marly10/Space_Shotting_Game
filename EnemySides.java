import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.application.Application;
import java.io.*;
import java.util.*;
import java.text.*;

public class EnemySides extends Enemy
{
    public EnemySides(Drone dDrone)
    {
        this.dDrone = dDrone;
    }
    @Override
    public void shootTarget()
    {
        this.dDrone.setWeapon(new WeaponSides(0, 0, dDrone, false));
        this.dDrone.setWeapon(new WeaponSides(5,0,dDrone,false));
    }

}
