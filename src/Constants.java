import javafx.scene.paint.Color;

/**
 * The constants class contains all the constants necessary for the program to run
 */

public class Constants {
    //dimensions of the app
    public static int APP_HEIGHT = 800;
    public static int APP_WIDTH = 1500;
    public static int MAX_RBG_VAL = 255;
    public static int NUM_GROUPS = 3; //number of dot groups the image gets split into

    public static int FRAME_WIDTH = 600;
    public static int FRAME_HEIGHT = 600;
    public static Color INVISIBLE = Color.color(0,0,0,0);
    public static double CIRC_RAD = 5; //radius of the individual dots
    public static int DOT_RADIUS = 5; //radius of a circle if it were to take up the full square - half a square length

    public static String IMAGE_PATH = "images/give_me_one_reason.jpg";
    public static String ABS_IMAGE_PATH = "/Users/isabellawhite/MSV_2.0/src/images/give_me_one_reason.jpg";
}
