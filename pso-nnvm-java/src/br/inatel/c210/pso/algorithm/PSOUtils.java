package br.inatel.c210.pso.algorithm;

import java.util.ArrayList;
import java.util.List;

import br.inatel.c210.pso.entity.Particle;
import br.inatel.c210.pso.entity.Vector;

public class PSOUtils
{
    public static final int NUM_DIMENSIONS = 2;
    public static final int POPULATION_SIZE = 100;
    public static final double CRAZINESS_PROBABILITY = 0.10;

    public static List<Particle> createInitialPopulation()
    {
        List<Particle> population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++)
            population.add(new Particle());
        return population;
    }

    public static Double euclideanDistance(Particle p, Particle q)
    {
        Vector px = p.getPosition();
        Vector qx = q.getPosition();
        double ans = 0.0;
        for (int i = 0; i < NUM_DIMENSIONS; i++)
        {
            ans += Math.pow(qx.getValueAt(i) - px.getValueAt(i), 2.0);
        }
        return Math.sqrt(ans);
    }

    public static Particle findClosest(Particle p, List<Particle> population)
    {
        Double minDist = Double.MAX_VALUE;
        Particle closest = null;
        for (Particle q : population)
        {
            if (!p.equals(q))
            {
                Double dist = euclideanDistance(p, q);
                if (dist < minDist)
                {
                    minDist = dist;
                    closest = q;
                }
            }
        }
        return closest;
    }
}
