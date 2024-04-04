package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;
import java.util.ArrayList;

public class MathServices {
    public static void main (String[] args){

        port(getPort());
        get("/factors", (req, res) -> {
            res.type("application/json");
            int n = Integer.parseInt(req.queryParams("value"));
            String result = "{\"operation\":\"factors\",\"input\":\"" + n + "\", \"output\":\"";
            ArrayList<Integer> factorsToAdd = factors(n);
            for (int i = 0; i < factorsToAdd.size(); i++) {
                if (i != factorsToAdd.size()-1) {
                    result += factorsToAdd.get(i) + ", ";
                } else {
                    result += factorsToAdd.get(i);
                }
            }
            result += "\"}";
            return result;
        });

        get("/resta", (req, res) -> {
            res.type("application/json");
            int n = Integer.parseInt(req.queryParams("value"));
            String result = "{\"operation\":\"factors\",\"input\":\"" + n + "\", \"output\":\"";
            result += "\"}";
            return result;
        });

        get("/fibonacci", (req, res) -> {
            res.type("application/json");
            int n = Integer.parseInt(req.queryParams("value"));
            String result = "{\"operation\":\"fibonacci\",\"input\":\"" + n + "\", \"output\":\"";
            ArrayList<Integer> fibonacciSequence = new ArrayList<>();
            int prev = 0;
            int curr = 1;
            fibonacciSequence.add(prev);
            for (int i = 1; i < n; i++) {
                fibonacciSequence.add(curr);
                int next = prev + curr;
                prev = curr;
                curr = next;
            }
            result += fibonacciSequence.toString() + "\"}";
            return result;

        });

        get("/sumaSerie", (req, res) -> {
            String type = req.queryParams("type");
            int n = Integer.parseInt(req.queryParams("n"));
            double result = 0;
            if ("naturales".equals(type)) {
                result = (n * (n + 1)) / 2;
            } else if ("geometrica".equals(type)) {
                // Implementa la lógica para la suma de una serie geométrica
            }
            return "{\"operation\":\"sumaSerie\", \"type\":\"" + type + "\", \"n\":\"" + n + "\", \"output\":\"" + result + "\"}";
        });

        get("/raizCuadrada", (req, res) -> {
            double number = Double.parseDouble(req.queryParams("number"));
            double result = Math.sqrt(number);
            return "{\"operation\":\"raizCuadrada\", \"number\":\"" + number + "\", \"output\":\"" + result + "\"}";
        });

        get("/collatz", (req, res) -> {
            int start = Integer.parseInt(req.queryParams("start"));
            StringBuilder result = new StringBuilder("{\"operation\":\"collatz\", \"start\":\"" + start + "\", \"sequence\":[");
            while (start != 1) {
                result.append(start).append(", ");
                if (start % 2 == 0) {
                    start /= 2;
                } else {
                    start = 3 * start + 1;
                }
            }
            result.append(1).append("]}");
            return result.toString();
        });

        get("/primes" , (req,res) -> {
            res.type("application/json");
            int n = Integer.parseInt(req.queryParams("value"));
            String result = "{\"operation\":\"primes\", \"input\":\"" + n + "\", \"output\":\"";
            for (int i = 1; i <= n; i++) {
                if (factors(i).size() == 2) {
                    if (i != 2) {
                        result += ", " + i;
                    } else {
                        result += i + "";
                    }
                }
            }
            result += "\"}";
            return result;
        });
    }

    private static ArrayList<Integer> factors (int n) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n/2; i++) {
            if (n % i == 0) {
                result.add(i);
            }
        }
        result.add(n);
        return result;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
