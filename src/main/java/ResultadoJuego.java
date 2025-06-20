import java.util.Random;
public class ResultadoJuego {

        static private Random ran;
        public static char DrawResult(int whiteRating, int blackRating) {
            double pw = 1.0, pb = 0.0;
            if (ran == null) ran = new Random(47);

            double rm = (whiteRating + blackRating) / 2.0;
            double rd = (whiteRating - blackRating);
            double rmq = (rm > 1200.0) ? ((rm - 1200.0) / 1200.0) : 0.0;

            double wcl = 40.0;
            double wcv = 0.45 - 0.1 * rmq * rmq;
            double wll = -1492.0 + rm * 0.391;
            double wul = 1691.0 - rm * 0.428;
            double bcl = -80.0;
            double bcv = 0.46 - 0.13 * rmq * rmq;
            double bll = -1753 + rm * 0.416;
            double bul = 1428 - rm * 0.388;
            double wf1 = (rd - wll) / (wcl - wll);
            double wf2 = (rd - wul) / (wcl - wul);
            double bf1 = (rd - bll) / (bcl - bll);
            double bf2 = (rd - bul) / (bcl - bul);

            if (rd < wll) pw = 0.0;
            else if (wll <= rd && rd <= wcl) pw = wcv * wf1 * wf1;
            else if (wcl <= rd && rd <= wul) pw = 1.0 - (1.0 - wcv) * wf2 * wf2;
            else if (rd > wul) pw = 1.0;

            if (rd < bll) pb = 1.0;
            else if (bll <= rd && rd <= bcl) pb = 1.0 - (1.0 - bcv) * bf1 * bf1;
            else if (bll <= rd && rd <= bul) pb = bcv * bf2 * bf2;
            else if (rd > bul) pb = 0.0;

            double r = ran.nextDouble();

            if (r < pw) return 'w';
            if (r < pw + pb) return 'b';
            return 'b';
        }
    }


