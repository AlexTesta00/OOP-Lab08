package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class UseController implements Controller {

    private final List<String> listPrinter;

    public UseController() {
        this.listPrinter = new ArrayList<>();
    }
    public void setNextString(final String text) {
        this.listPrinter.add(text);
    }

    public String getNext() {
        return null;
    }

    public List<String> getHistory() {
        return null;
    }

    public String printCurrentString() {
        return null;
    }

}
