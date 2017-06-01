package edu.tep.practicando;

import edu.tep.practicando.servicios.ConexionService;
import static spark.Spark.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    //Teniendo la referencia de la ConexionService
    private static ConexionService conexionService = ConexionService.getInstancia();
        public static void main(String[] args) {
        get("/", (request, response) -> {
            return "Practicando ya";
        });
    }

}

