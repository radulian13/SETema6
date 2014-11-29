import controllers.CalcController;
import model.CalcModel;
import views.CalcView;

/**
 * Created by VoldHouse on 11/22/2014.
 */
public class CalcMain {
    public static void main(String[] args) {
        // Instantiate the MVC elements
        CalcModel model = new CalcModel();
        CalcView view = new CalcView();
        CalcController controller = new CalcController();

        // Attach the view to the model
        model.addModelListener(view);

        // Tell the view about the model and the controller
        view.addModel(model);
        view.addController(controller);

        // Tell the controller about the model and the view
        controller.addModel(model);
        controller.addView(view);

        // Display the view
        view.setVisible(true);
    }
}
