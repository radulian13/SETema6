package model;

import exceptions.InputException;
import interfaces.IModelListener;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by VoldHouse on 11/22/2014.
 */
public class CalcModel {
    // Constants
    public static final String INITIAL_VALUE = "0";
    public static final String INITIAL_VALUE1= "0";

    // Member variable defining state of calculator, the total current value
    private BigInteger VitezaVantului;
    private BigInteger Temperatura;


    private List<IModelListener> mListeners;

    /**
     * Constructor
     */
    public CalcModel() {

        VitezaVantului = new BigInteger(INITIAL_VALUE);
        Temperatura =new BigInteger(INITIAL_VALUE);

    }

    /**
     * Set the total value.
     *
     * @param value New value that should be used for the calculator total.
     */
    public void setValue(String value, String value1) throws InputException {
        try {
            VitezaVantului=new BigInteger(value);
            Temperatura=new BigInteger(value1);
            notifyListeners();
        } catch (NumberFormatException e) {
            throw new InputException(value, e.getMessage());
        }
    }

    /**
     * Return current wind speed and degree.
     */
    public String getValueViteza() {
        return VitezaVantului.toString();
    }
    public String getValueTemperatura(){return Temperatura.toString();}

    /**
     * Adds the view listener to the list
     *
     * @param listener The model event listener
     */
    public void addModelListener(IModelListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListener>();
        }

        mListeners.add(listener);
    }

    /**
     * Notifies the views listeners of the changed state (value)
     */
    private void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (IModelListener listener : mListeners)
                listener.onUpdate();
        }
    }

}
