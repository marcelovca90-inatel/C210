package br.inatel.c210.ga.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Problem
{
    public static double f(Chromossome c)
    {
        Integer[] genes = c.getGenes();

        // search for pairs of attacking queens
        double attacks = 0.0;
        for (int q1 = 0; q1 < 8; q1++)
        {
            double x1 = q1, y1 = genes[q1];
            for (int q2 = q1 + 1; q2 < 8; q2++)
            {
                double x2 = q2, y2 = genes[q2];
                double m = (y2 - y1) / (x2 - x1);
                double deg = Math.toDegrees(Math.atan(m));
                if ((Double.compare(deg, 0.0) == 0) || // horizontal
                        (Double.compare(Math.abs(deg), 45.0) == 0)) // diagonal
                    attacks++;
            }
        }

        return attacks;
    }

    public static double g(Chromossome c)
    {
        return 28.0 - f(c);
    }

    public static double f_average(List<Chromossome> pop)
    {
        return pop.stream().collect(Collectors.averagingDouble(c -> f(c)));
    }

    public static double g_average(List<Chromossome> pop)
    {
        return pop.stream().collect(Collectors.averagingDouble(c -> g(c)));
    }
}
