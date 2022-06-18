import javafx.scene.Group;

import java.awt.*;
import java.util.ArrayList;

public class Walls extends Group {
    Hexagon[] wall;
    double side = 45 / Math.sqrt(3);


    public Walls() {
        wall = new Hexagon[52];
        int i = 0;
        for (i = 0; i < 19; i++) {
            wall[i] = new Hexagon(0, i, side);
//            busy.add(new Point((int) (wall[i].getX()), (int) (wall[i].getY())));
        }
        for (int k = 0; i < 38; i++, k++) {
            wall[i] = new Hexagon(14, k, side);
//            busy.add(new Point((int) (wall[i].getX()), (int) (wall[i].getY())));
        }
        for (int k = 0; k < 14; i++, k++) {
            wall[i] = new Hexagon(k, 18, side);
//            busy.add(new Point((int) (wall[i].getX()), (int) (wall[i].getY())));
        }

        getChildren().addAll(wall);
    }
}
