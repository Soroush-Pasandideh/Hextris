import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class Shapes extends Group {

    int x, y, type;
    Hexagon[] shape;
    final double side = 45 / Math.sqrt(3);
    public static int XMax = 14;
    public static int YMax = 18;
    public static boolean canMove = true;
    public static boolean evenColumn = true;
    public static boolean[][] gamePlan = new boolean[19][15];//defalt==false==khaali

    public Shapes(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        shape = new Hexagon[4];
        if (type == 1) {// bar asase vasati- saat gard az rast
            shape[0] = new Hexagon(x, (x % 2 == 1 ? y + 1 : y), side);
            shape[1] = new Hexagon(x + 1, y + 1, side);
            shape[2] = new Hexagon(x - 1, y + 1, side);
            shape[3] = new Hexagon(x - 1, y, side);
        }
        if (type == 2) {// bar asase vasati- saat gard az bala
            shape[0] = new Hexagon(x, (x % 2 == 1 ? y + 1 : y), side);
            shape[1] = new Hexagon(x, (x % 2 == 1 ? y : y - 1), side);
            shape[2] = new Hexagon(x + 1, y + 1, side);
            shape[3] = new Hexagon(x - 1, y + 1, side);
        }
        if (type == 3) {// bar asase vasati- saat gard az rast
            shape[0] = new Hexagon(x, (x % 2 == 1 ? y + 1 : y), side);
            shape[1] = new Hexagon(x + 1, y + 1, side);
            shape[2] = new Hexagon(x - 1, y, side);
            shape[3] = new Hexagon(x, (x % 2 == 1 ? y : y - 1), side);
        }
        if (type == 4) {// bar asase payin vasat - saat gard az chap
            shape[0] = new Hexagon(x, (x % 2 == 1 ? y + 1 : y), side);
            shape[1] = new Hexagon(x - 1, y, side);
            shape[2] = new Hexagon(x, (x % 2 == 1 ? y : y - 1), side);
            shape[3] = new Hexagon(x + 1, y, side);
        }
        if (type == 5) {// bar asase dovomi chap - aval chap- dovom rats  - akhar rast
            shape[0] = new Hexagon(x, (x % 2 == 1 ? y + 1 : y), side);
            shape[1] = new Hexagon(x - 1, y + 1, side);
            shape[2] = new Hexagon(x + 1, y + 1, side);
            shape[3] = new Hexagon(x + 2, (x % 2 == 1 ? y + 1 : y), side);
        }
        if (type == 6) {// bar asase dovomi rast - rast - dovom chap - chap
            shape[0] = new Hexagon(x, (x % 2 == 1 ? y + 1 : y), side);
            shape[1] = new Hexagon(x + 1, y, side);
            shape[2] = new Hexagon(x - 1, y + 1, side);
            shape[3] = new Hexagon(x - 1, y + 2, side);
        }
        if (type == 7) {// bar asase dovomi bala - bala - dovom payin - payin
            shape[0] = new Hexagon(x, (x % 2 == 1 ? y : y - 1), side);
            shape[1] = new Hexagon(x, (x % 2 == 1 ? y + 1 : y), side);
            shape[2] = new Hexagon(x, (x % 2 == 1 ? y + 2 : y + 1), side);
            shape[3] = new Hexagon(x, (x % 2 == 1 ? y + 3 : y + 2), side);
        }
        for (int i = 0; i < 4; i++) {
            switch (type) {
                case 1:
                    shape[i].setFill(Color.BLUE);
                    break;
                case 2:
                    shape[i].setFill(Color.BLUEVIOLET);
                    break;
                case 3:
                    shape[i].setFill(Color.OLIVEDRAB);
                    break;
                case 4:
                    shape[i].setFill(Color.DEEPPINK);
                    break;
                case 5:
                    shape[i].setFill(Color.GOLDENROD);
                    break;
                case 6:
                    shape[i].setFill(Color.rgb(208, 255, 0));
                    break;
                case 7:
                    shape[i].setFill(Color.rgb(0, 255, 10));
                    break;
            }
        }
        showShape();
    }

//    public void makeFloorTrue(Hexagon[] shape) {
//        for (int i = 0; i < 4; i++) {
//            gamePlan[shape[i].getY()][shape[i].getX()] = true;
//        }
//    }

    public static boolean isLineFull(int line) {
        boolean isLineFull = true;
        for (int i = 1; i < 14; i++)
            if (gamePlan[line][i])
                isLineFull = false;
        return isLineFull;
    }

//    public void move2() {
//        for (int i = 0; i < 4; i++)
//            gamePlan[shape[i].getY()][shape[i].getX()] = true;
//        Shapes shapes = new Shapes(x, y + 1, type);
//        getChildren().clear();
//        getChildren().add(shapes);
//        y++;
//        for (int i = 0; i < 4; i++) {
//            shape[i].setY(shape[i].getY() + 1);
//        }
//        for (int i = 0; i < 4; i++) {
//            gamePlan[shape[i].getY()][shape[i].getX()] = false;
//        }
//    }

    public static void fillPlan() {
        for (int i = 0; i <= 18; i++)
            for (int j = 0; j < 15; j++)
                if (i == 18)
                    gamePlan[i][j] = false;
                else
                    gamePlan[i][j] = true;
    }

    public void showShape() {
        getChildren().clear();
        getChildren().addAll(shape);
    }


    public boolean isGameOver(Hexagon[] shape) {
        boolean isGameOver = false;
        for (int i = 0; i < 4; i++)
            if (shape[i].getY() == 0 && !canMoveDown(shape))
                isGameOver = true;
        return isGameOver;
    }

    public boolean canMoveRight(Hexagon[] shape) {
        if (shape[0].getY() < 1)
            return true;
        else {
            if ((gamePlan[shape[0].getY()][shape[0].getX() + 1] && gamePlan[shape[1].getY()][shape[1].getX() + 1] &&
                    gamePlan[shape[2].getY()][shape[2].getX() + 1] && gamePlan[shape[3].getY()][shape[3].getX() + 1]) /*&&
                    (gamePlan[shape[0].getY() + 1][shape[0].getX() + 1] && gamePlan[shape[1].getY() + 1][shape[1].getX() + 1] &&
                            gamePlan[shape[2].getY() + 1][shape[2].getX() + 1] && gamePlan[shape[3].getY() + 1][shape[3].getX() + 1])*/)
                return canMove = true;
            else
                return canMove = false;
        }
    }

    public boolean canMoveLeft(Hexagon[] shape) {
        if (shape[0].getY() < 1)
            return true;
        else {
            if ((gamePlan[shape[0].getY()][shape[0].getX() - 1] && gamePlan[shape[1].getY()][shape[1].getX() - 1] &&
                    gamePlan[shape[2].getY()][shape[2].getX() - 1] && gamePlan[shape[3].getY()][shape[3].getX() - 1]) /*&&
                    (gamePlan[shape[0].getY() + 1][shape[0].getX() - 1] && gamePlan[shape[1].getY() + 1][shape[1].getX() - 1] &&
                            gamePlan[shape[2].getY() + 1][shape[2].getX() - 1] && gamePlan[shape[3].getY() + 1][shape[3].getX() - 1])*/)
                return canMove = true;
            else
                return canMove = false;
        }
    }

    public boolean canMoveDown(Hexagon[] shape) {
        if (shape[0].getY() < 0)
            return true;
        else {
            if (gamePlan[shape[0].getY() + 1][shape[0].getX()] && gamePlan[shape[1].getY() + 1][shape[1].getX()] &&
                    gamePlan[shape[2].getY() + 1][shape[2].getX()] && gamePlan[shape[3].getY() + 1][shape[3].getX()])
                return canMove = true;
            else {
                for (int i = 0; i < 4; i++) {
                    gamePlan[shape[i].getY()][shape[i].getX()] = false;
                }
                return canMove = false;
            }
        }
    }

    public void moveDown() {
        if (canMoveDown(shape)) {
            Shapes shapes = new Shapes(x, y + 1, type);
            getChildren().clear();
            getChildren().add(shapes);
            y++;
            for (int i = 0; i < 4; i++) {
                shape[i].setY(shape[i].getY() + 1);
            }
        }
    }

    public void moveRight() {
        if (canMoveRight(shape)) {
            if (canMoveDown(shape)) {
                if (shape[0].getX() + 1 < XMax && shape[1].getX() + 1 < XMax &&
                        shape[2].getX() + 1 < XMax && shape[3].getX() + 1 < XMax) {
                    Shapes shapes = new Shapes(x + 1, y, type);
                    x++;
                    getChildren().clear();
                    getChildren().addAll(shapes);
                    for (int i = 0; i < 4; i++)
                        shape[i].setX(shape[i].getX() + 1);
                    for (int i = 0; i < 4; i++)
                        if (evenColumn)
                            shape[i].setY(shape[i].getX() % 2 == shape[0].getX() % 2 ? shape[i].getY() + 1 : shape[i].getY());
                        else
                            shape[i].setY(shape[i].getX() % 2 == shape[0].getX() % 2 ? shape[i].getY() - 1 : shape[i].getY());
                    evenColumn = !evenColumn;
                }
            }
        }
    }

    public void moveLeft() {
        if (canMoveLeft(shape)) {
            if (canMoveDown(shape)) {
                if (shape[0].getX() - 1 > 0 && shape[1].getX() - 1 > 0 &&
                        shape[2].getX() - 1 > 0 && shape[3].getX() - 1 > 0) {
                    Shapes shapes = new Shapes(x - 1, y, type);
                    x--;
                    getChildren().clear();
                    getChildren().add(shapes);
                    for (int i = 0; i < 4; i++)
                        shape[i].setX(shape[i].getX() - 1);
                    for (int i = 0; i < 4; i++)
                        if (evenColumn)
                            shape[i].setY(shape[i].getX() % 2 == shape[0].getX() % 2 ? shape[i].getY() + 1 : shape[i].getY());
                        else
                            shape[i].setY(shape[i].getX() % 2 == shape[0].getX() % 2 ? shape[i].getY() - 1 : shape[i].getY());
                    evenColumn = !evenColumn;
                }
            }
        }
    }

    public void returnClockWise() {
       /* for (int i = 0; i < 4; i++) {
        Rotate rotate=new Rotate(60, (shape[i].getBoundsInParent().getMaxX() + shape[i].getBoundsInParent().getMinX()) / 2,
                (shape[i].getBoundsInParent().getMaxY() + shape[i].getBoundsInParent().getMinY()) / 2);
            shape[i].getTransforms().add(rotate);
        }
        getChildren().clear();
        getChildren().addAll(shape);*/
        /*if (type == 1) {// bar asase vasati- saat gard az rast
            shape[0] = new Hexagon(x, (x % 2 == 1 ? y : y - 1), side);
            shape[1] = new Hexagon(x, (x % 2 == 1 ? y + 1 : y), side);
            shape[2] = new Hexagon(x - 1, y - 1, side);
            shape[3] = new Hexagon(x, (x % 2 == 1 ? y - 1 : y - 2), side);
        }
            shape[1].setX(shape[1].getX() - 1);
            shape[3].setX(shape[3].getX() - 1);

        for (int i = 0; i < 4; i++)
            if (evenColumn)
                shape[i].setY(shape[i].getX() % 2 == shape[0].getX() % 2 ? shape[i].getY() + 1 : shape[i].getY());
            else
                shape[i].setY(shape[i].getX() % 2 == shape[0].getX() % 2 ? shape[i].getY() - 1 : shape[i].getY());
        evenColumn = !evenColumn;
        getChildren().clear();
        getChildren().addAll(shape);
*/
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }
}