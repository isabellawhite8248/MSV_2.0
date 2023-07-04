import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.HashMap;

public class Group {

    private ArrayList<Circle> circles;
    private ArrayList<Point2D> ranges;
    private int numGroups;
    private HashMap<Point2D, Circle> rangeToDots;
    private HashMap<Point2D, Color> rangeToColor;
    private double increment;
    private HashMap<Circle, RBGNumber> colorLookUp;

    public Group(ArrayList<Circle> dots, int groupNumber, HashMap<Circle, RBGNumber> dotCol){

        this.colorLookUp = dotCol;
        this.circles = dots;
        this.numGroups = groupNumber;
        this.rangeToDots = new HashMap<>();
        this.rangeToColor = new HashMap<>();
        this.ranges = new ArrayList<>();
        this.increment = (255/groupNumber);

        double last_el = 0, count = 1;

        for(int i = 0; i < numGroups; i++){
            double next_el = count*increment;
            Point2D newRange = new Point2D(last_el, next_el);
            ranges.add(newRange);
            System.out.println("\n NEW RANGE ADDED: " + newRange.toString() + "\n");
            count++;
            last_el = next_el;
        }

        for(Circle circ : this.circles){

            //TODO - redo
            RBGNumber n = this.colorLookUp.get(circ);
            double colVal = n.getCV();


            Point2D ran = getRange(colVal); //for loop in the helper method -- nested for loop may be a costly run time

        }

    }

    public Point2D getRange(double cv){

        for (Point2D dot : ranges){
            if(cv >= dot.getX() && cv <= dot.getY()){
                return dot;
            }
        }

        System.out.println("\n ERROR RANGE NOT FOUND \n");
        return null;
    }

}
