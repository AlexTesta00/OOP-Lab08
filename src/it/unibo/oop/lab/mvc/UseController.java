package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public final class UseController implements Controller {

    private final List<String> listPrinter;

    public UseController() {
        this.listPrinter = new ArrayList<>();
    }

    public void setNextString(final String text) {
       try {
        this.listPrinter.add(text);
       } catch (Exception e) {
        System.out.println(e);
       }
    }

    public String getNext() {
        return this.listPrinter.iterator().next();
    }

    public List<String> getHistory() {
        return this.listPrinter;
    }

    public String printCurrentString(final String text) {
        try {
            listPrinter.add(text);
            return text;
        } catch (Exception e) {
            return e.toString();
        }
    }

}
