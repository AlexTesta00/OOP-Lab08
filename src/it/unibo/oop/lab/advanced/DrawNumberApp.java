package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final String PATH_RESOURCE = "config.yml";
    private final int min = Integer.valueOf(this.getSettings().get(1));
    private final int max = Integer.valueOf(this.getSettings().get(3));
    private final int attemps = Integer.valueOf(this.getSettings().get(5));
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * 
     */
    public DrawNumberApp() {
        this.model = new DrawNumberImpl(min, max, attemps);
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    public List<String> getSettings() {
      final List<String> data = new ArrayList<>();
      StringTokenizer tokenizer;
      try {
          final InputStream in = ClassLoader.getSystemResourceAsStream(PATH_RESOURCE);
          final BufferedReader br = new BufferedReader(new InputStreamReader(in));
          String appoggio;
          //Clean the string
          while ((appoggio = br.readLine()) != null) {
              tokenizer = new StringTokenizer(appoggio, " :");
              while (tokenizer.hasMoreElements()) {
                  data.add(tokenizer.nextToken());
              }
          }
          in.close();
          System.out.println(data.get(3));
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

      return data;
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp().getSettings();
    }

}
