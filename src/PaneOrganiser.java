import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PaneOrganiser {

    private BorderPane root;
    private ImageView frame;
    private Image pic;
    private ArrayList<Circle> circles;
    private HashMap<Circle, RBGNumber> dotToColor;

    //TODO: create a button that brings up the picture then takes it away
    //copy functionality where you can adjust the frame height and width but have a lock button that stops it "lock frame" (check)
    //TODO: create the pixelate button which takes what is one the screen and forms an array of dots adding them
    //based off of the color and have a specific size as well
    //load the images -- pic simple ones but include everything from the live lineup
    //should it have a draw feature to adjust the image?

    //TODO: make global variables for the image frame dimensions

    public PaneOrganiser() throws IOException {

        this.dotToColor = new HashMap<>();
        this.root = new BorderPane();
        this.frame = new ImageView();
        this.pic = new Image(Constants.IMAGE_PATH);
        this.frame.setImage(this.pic);
        this.circles = new ArrayList<>();

        String fp = Constants.ABS_IMAGE_PATH;
        File file = new File(fp);
        BufferedImage image = ImageIO.read(file);

        //makes a visual grid just so I can see where I am populating the dots, will delete later
        int width = image.getWidth();
        int height = image.getHeight();
        int RADIUS_DOUBLE = Constants.DOT_RADIUS*2;

        //used later for dot color groupings in the for loop
        int numGroups = 3;
        int mark1 = (255/numGroups);
        int mark2 = mark1*2;

        for(int i = 0; i < (width - RADIUS_DOUBLE); i = i + RADIUS_DOUBLE){
            for(int h = 0; h < (height - RADIUS_DOUBLE); h = h + RADIUS_DOUBLE){

                //loop through all the pixels in the box of size 2*radius
                float red = 0; //keeps track of the sum of reds, blues and greens to average together for later
                float blue = 0;
                float green = 0;

                int count = 0;

                for(int x = i; x < (i + RADIUS_DOUBLE); x++){
                    for(int y = h; y < (h + RADIUS_DOUBLE); y++){
                        count++;
                        int p = image.getRGB(x, y);
//                        int a = (p>>24)&0xff; -- FIGURE OUT WTF THIS VALUE IS BECAUSE IM NOT SURE
                        float r = (p>>16)&0xff;
                        float b = p&0xff;
                        float g = (p>>8)&0xff;
                        red = r + red;
                        blue = b + blue;
                        green = g + green;
                    }
                }

                double one = (int)red/100;
                double two = (int)blue/100;
                double three = (int)green/100;
                Color avg = createRBG(one, two, three); //helper method converts color to greyscale by averaging the 2 rbg vals
                Circle c = createCircle(i, h, Constants.CIRC_RAD, avg);
                dotToColor.put(c, new RBGNumber(avg.getBlue()));
            }
        }

        Group g = new Group(this.circles, Constants.NUM_GROUPS, dotToColor);

        this.setUpButtons();
    }

    public Circle createCircle(int i, int h, double circleRadius, Color col){
        Point2D center = new Point2D(i + Constants.DOT_RADIUS, h + Constants.DOT_RADIUS);
        Circle circ = new Circle(circleRadius);
        circ.setCenterX(center.getX());
        circ.setCenterY(center.getY());
        circ.setFill(col);
        this.circles.add(circ);
        this.root.getChildren().add(circ);
        return circ;
    }

    public Color createRBG(double red, double green, double blue){
        float r = (float)(red/Constants.MAX_RBG_VAL);
        float g = (float)(green/Constants.MAX_RBG_VAL);
        float b = (float)(blue/Constants.MAX_RBG_VAL);
        float av = ((r+g+b)/3);
        return new Color(av,av,av, 1.0);
    }

    public void setUpButtons(){

        HBox buttonBox = new HBox();

        Button quitButton = new Button("QUIT");
        quitButton.setOnAction((ActionEvent e) -> System.exit(0));
        quitButton.setFocusTraversable(false);
        quitButton.setLayoutX(Constants.APP_WIDTH);

        //where you can add more buttons to the button box

        buttonBox.getChildren().addAll(quitButton);
        this.root.setRight(buttonBox);
    }

    public Pane getRoot() {
        return this.root;
    }
}
