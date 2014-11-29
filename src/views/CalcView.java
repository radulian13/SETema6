package views;



import interfaces.IController;
import interfaces.IModelListener;
import interfaces.IView;
import model.CalcModel;
//import utils.CalculateAction;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Created by VoldHouse on 11/22/2014.
 */
public class CalcView extends JFrame implements IModelListener, IView {
    private static final long serialVersionUID = -5758555454500685115L;

    // View Components
    private JTextField mTemperatura = new JTextField(50);
    private JTextField mVitezaVantului = new JTextField(50);

    private JButton mRandomBtn = new JButton("Random Weather");
    private JButton mGenerateBtn = new JButton("Generate");


    private IController mCalcController;

    private CalcModel mModel;

    /**
     * Constructor
     */
    public CalcView() {
        // Initialize components
        mTemperatura.setEditable(false);
        mVitezaVantului.setEditable(false);

        mTemperatura.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e)
            {
                warn();
            }
            public void insertUpdate(DocumentEvent e)
            {
                warn();
            }
            public void warn()
            {;}


        });


        // Layout the components.
        //Box card1=Box.createHorizontalBox();
        JFrame f=new JFrame("My Random Forecast");
        JPanel content=new JPanel(new GridLayout(10,10));
       // content(SwingConstants.CENTER)
        content.setBackground(Color.orange);
        //mGenerateBtn.setPreferredSize(new Dimension(4,4));
        content.setLayout(new GridLayout(8, 6 , 7, 10));
        //content.add(new JLabel("Random Meteo"));
        content.setBorder(BorderFactory.createTitledBorder("Random Meteo"));
        //content.setHorizontal
        f.add(content);
        content.add(new JLabel("Grade Celsius"));
        content.add(mTemperatura);
        content.add(new JLabel("Viteza Vantului(km/h)"));
        content.add(mVitezaVantului);
        content.add(new JLabel("Press Button to Generate Random Values!"));

        content.getBaseline(40,40);
        content.add(mGenerateBtn);

        this.setContentPane(content);
        this.pack();

        this.setTitle("Meteo Forecast");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Sets the view's reference to the model - Only get operations allowed
     *
     * @param model The calc model
     */
    public void addModel(CalcModel model) {
        mModel = model;
        mVitezaVantului.setText(model.getValueViteza());
        mTemperatura.setText(model.getValueTemperatura());
    }

    /**
     * Sets the view's event listener - the controller - so that the changes made by the user in the view, can be reflected in the model
     *
     * @param controller The controller (event listener)
     */
    public void addController(IController controller) {
       mGenerateBtn.setActionCommand(IController.ACTION_GENERATE);
       mGenerateBtn.addActionListener(controller);
    }

    @Override
    public void onMessage(boolean isError, String message) {
        if (isError) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Situatie meteo MVC", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void onUpdate() {
        mVitezaVantului.setText(mModel.getValueViteza());
        mTemperatura.setText(mModel.getValueTemperatura());
    }
}
