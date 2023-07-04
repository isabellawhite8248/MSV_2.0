import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        //launches the program and creates a new instance of pane organizer
        PaneOrganiser organizer = null;
        try {
            organizer = new PaneOrganiser();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(organizer.getRoot(), Constants.APP_WIDTH, Constants.APP_HEIGHT);

        stage.setScene(scene);
        stage.setTitle("Music Video Sim 2.0");
        stage.show();
    }

    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}

//TODO: Finalized Live LineUp
//free falling
//dream on
//november rain
//snow
//wish you were here
//here I go again
//It's my life
//gives you hell
//complicated
//heaven
//the search is over
//faithfully
//alone
//these dreams
//It must have been love
//kiss me
//sweet child o mine
//hotel california
//redbone
//knocking on heaven's door
//beautiful
//sweet home alabama
//broken wings
//imagine
//human nature
//wheel in the sky
//give me a reason
//hear you me
//when I come around - green day