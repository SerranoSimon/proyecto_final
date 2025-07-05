package Logica;

import java.util.Random;
/*
Codigo de:
Probability for the outcome of a chess game based on rating
by
Otto Milvang
Para obtener el resultado del juego de forma realista, a partir de los ELOS.
 */
public class ResultadoJuego {

    private static Random ran = new Random(); // para darle una aleatoriedad

    public static char drawResult(int whiteRating, int blackRating) {
        // Calcular la diferencia de ELO
        double rd = whiteRating - blackRating;

        // Calcular el valor medio de ELO
        double rm = (whiteRating + blackRating) / 2.0;
        double rmq = (rm > 1200.0) ? ((rm - 1200.0) / 1200.0) : 0.0; // Ajuste por rating medio

        // Parámetros específicos del estudio para el blanco
        double WLL = -655.0;
        double WCL = 40.0;
        double WUL = 876.0;
        double WCV = 0.45 - 0.1 * rmq * rmq;

        // Constantes adicionales
        double c1 = 84.869 * Math.pow(10, -8);
        double c2 = 84.427 * Math.pow(10, -8);

        // Probabilidad de que el blanco gane (Pw)
        double pw = 0.0;
        if (rd < WLL) {
            pw = 0.0;
        } else if (rd >= WLL && rd <= WCL) {
            pw = WCV * Math.pow((rd - WLL) / (WCL - WLL), 2);
        } else if (rd > WCL && rd <= WUL) {
            pw = 1.0 - (1.0 - WCV) * Math.pow((rd - WUL) / (WCL - WUL), 2);
        } else if (rd > WUL) {
            pw = 1.0;
        }

        // Parámetros específicos del estudio para el negro
        double BLL = -1753.0;
        double BCL = -80.0;
        double BUL = 1428.0;
        double BCV = 0.46 - 0.13 * rmq * rmq;

        // Probabilidad de que el negro gane (Pb)
        double pb = 0.0;
        if (rd < BLL) {
            pb = 1.0;
        } else if (rd >= BLL && rd <= BCL) {
            pb = 1.0 - (1.0 - BCV) * Math.pow((rd - BLL) / (BCL - BLL), 2);
        } else if (rd > BCL && rd <= BUL) {
            pb = BCV * Math.pow((rd - BUL) / (BCL - BUL), 2);
        } else if (rd > BUL) {
            pb = 0.0;
        }

        // Probabilidad de empate (Pd)
        double pd = 1.0 - pw - pb;

        // Generar un número aleatorio para determinar el resultado
        double r = ran.nextDouble();

        // Determinar el resultado según las probabilidades
        if (r < pw) {
            return 'w';  // Blanco gana
        } else if (r < pw + pb) {
            return 'b';  // Negro gana
        } else {
            return '=';  // Empate
        }
    }


}


