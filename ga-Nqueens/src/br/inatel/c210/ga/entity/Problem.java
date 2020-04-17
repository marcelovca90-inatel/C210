package br.inatel.c210.ga.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Problem
{
    public static final int N_QUEENS = 12;

    public static final int WORST_CASE = N_QUEENS * (N_QUEENS - 1) / 2;

    public static double f(Chromossome c)
    {
        Integer[] genes = c.getGenes();

        // search for pairs of attacking queens
        double attacks = 0.0;
        for (int q1 = 0; q1 < N_QUEENS; q1++)
        {
            double x1 = q1, y1 = genes[q1];
            for (int q2 = q1 + 1; q2 < N_QUEENS; q2++)
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
        return WORST_CASE - f(c);
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
