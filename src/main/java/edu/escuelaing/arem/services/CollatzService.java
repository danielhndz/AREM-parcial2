package edu.escuelaing.arem.services;

public class CollatzService {

    private static CollatzService instance;

    private CollatzService() {
    }

    public static CollatzService getInstance() {
        if (instance == null) {
            instance = new CollatzService();
        }
        return instance;
    }

    public String getResponse(int k) {
        StringBuilder res = new StringBuilder();
        res.append("{");
        res.append("\"operation\": \"collatzsequence\",");
        res.append("\"input\": " + k + ",");
        res.append("\"output\": " + "\"" + buildSequence(k) + "\"");
        res.append("}");
        return res.toString();
    }

    private String buildSequence(int k) {
        StringBuilder output = new StringBuilder();
        while (k != 1) {
            if (output.length() == 0) {
                output.append(k + "->");
                k = collatzRules(k);
                output.append(k);
            } else {
                output.append("->");
                k = collatzRules(k);
                output.append(k);
            }
        }
        return output.toString().replace("->", " -> ");
    }

    private int collatzRules(int k) {
        if (k % 2 == 0) {
            return k / 2;
        } else {
            return (3 * k) + 1;
        }
    }
}
