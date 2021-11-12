package day04.ex00.ImagesToChar.src.edu.school21.printer.app;

public class Settings {
    private char w;
    private char b;
    private String path;
    private boolean valid;

    public Settings(String[] args) {
        String[] params = getParams(args);
        if (params == null) {
            valid = false;
            return;
        } else {
            w = params[0].toCharArray()[0];
            b = params[1].toCharArray()[0];
        }
        path = params[2];
        valid = path != null;
    }

    public char getW() {
        return w;
    }

    public char getB() {
        return b;
    }

    public String getPath() {
        return path;
    }

    public boolean isValid() {
        return valid;
    }

    private String getString(String str) {
        String[] arr = str.split("=");
        String result = null;
        if (arr.length == 2) {
            try {
                result = arr[1];
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return result;
    }

    private String[] getParams(String[] args) {
        String[] params = new String[3];
        for (String arg : args) {
            if (arg.startsWith("--w=")) {
                params[0] = getString(arg);
            } else if (arg.startsWith("--b=")) {
                params[1] = getString(arg);
            } else if (arg.startsWith("--path=")) {
                params[2] = getString(arg);
            } else {
                return null;
            }
        }
        return params;
    }
}
