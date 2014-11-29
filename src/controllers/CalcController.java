package controllers;

import interfaces.IController;
import interfaces.IView;
import model.CalcModel;
import exceptions.InputException;



import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by VoldHouse on 11/22/2014.
 */
public class CalcController implements IController {
    // The Controller needs to interact with both the Model and View.
    private CalcModel mModel;

    // The list of views that listen for updates
    private List<IView> mViews;

    /**
     * Constructor
     */
    public CalcController() {
    }

    /**
     * This is my function to return random Number
     *It will return a random Number based on the formula i wrote
     * @param a First Number
     * @param b Second Number
     * @return
     */
    public static int getRandom(int a, int b) {
        Random x = new Random();
        int randomNumber = x.nextInt((b + 10) - a+2) + a+6;
        return randomNumber;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ACTION_GENERATE)) {
            // Reset the model to its default state
            if (mModel != null) {
                try {
                    //Set initial values
                    mModel.setValue(CalcModel.INITIAL_VALUE, CalcModel.INITIAL_VALUE1);

                    //Get random Integers for wind speed. Wind speed will be between those two Integers
                    int w = 0;
                    int q = +100;
                    int rand = this.getRandom(w, q);

                    //Get random integers for temperature. Temperature will be between those two Integers.

                    String string = "" + rand;
                    int w1 = -100;
                    int q1 = 100;
                    int rand1 = this.getRandom(w1, q1);

                    String string1 = "" + rand1;

                    //Set the values by this method
                    mModel.setValue(string, string1);
                } catch (InputException e) {
                    notifyViews(true, e.getMessage());
                }
            }
        }
    }

    /**
     * Adds a view reference in order to interact with it
     *
     * @param view The view from the controller will receive events and send messages
     */
    public void addView(IView view) {
        if (mViews == null) {
            mViews = new ArrayList<IView>();
        }

        mViews.add(view);
    }

    /**
     * Adds a reference to the model, so it can update it
     *
     * @param model The data model reference
     */
    public void addModel(CalcModel model) {
        mModel = model;
    }

    /**
     * Notifies the views when an message must be displayed
     *
     * @param isError {@code true} if the message is an error, {@code false} otherwise
     * @param message The string to be displayed
     */
    private void notifyViews(boolean isError, String message) {
        if (mViews != null && !mViews.isEmpty()) {
            for (IView view : mViews) {
                view.onMessage(isError, message);
            }
        }
    }
}
