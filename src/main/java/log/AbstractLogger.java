package log;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractLogger implements Logger{
    private static int counter = 1;
    private SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss.SSS");

    public String log(String text) {
        Date date = new Date();
      //  String stringToLog = counter + ") " + sf.format(date) + " " + "[" + Thread.currentThread().getName() + "] " + text;
        String stringToLog = counter + ") " + sf.format(date) + " " + "[" + Thread.currentThread().getStackTrace()[3] + "] " + text;
        doLogging(stringToLog);
        toFile(stringToLog);
        counter++;
        return stringToLog;
    }

    protected void toFile(String stringToLog) {
    }

    protected abstract void doLogging(String stringToLog);
}
