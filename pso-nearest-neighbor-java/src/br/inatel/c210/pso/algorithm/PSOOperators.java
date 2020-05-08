package br.inatel.c210.pso.algorithm;

import static br.inatel.c210.pso.algorithm.PSOUtils.CRAZINESS_PROBABILITY;
import static br.inatel.c210.pso.algorithm.PSOUtils.NUM_DIMENSIONS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.inatel.c210.pso.entity.Particle;
import br.inatel.c210.pso.entity.Vector;

public abstract class PSOOperators
{
    public static void adjustVelocity(List<Particle> population)
    {
        Map<Particle, Vector> velocityCache = new HashMap<>();

        for (Particle particle : population)
        {
            Particle closest = PSOUtils.findClosest(particle, population);
            velocityCache.put(particle, closest.getVelocity().clone());
        }

        for (Particle particle : population)
        {
            Vector cachedVelocity = velocityCache.get(particle);
            particle.setVelocity(cachedVelocity);
        }
    }

    public static void craziness(List<Particle> population)
    {
        for (Particle particle : population)
        {
            if (RandomUtils.randomDouble() < CRAZINESS_PROBABILITY)
            {
                Vector newVelocity = new Vector(RandomUtils.randomSignedDoubles(NUM_DIMENSIONS));
                particle.setVelocity(newVelocity);
            }
        }
    }

    public static void updatePosition(List<Particle> population)
    {
        for (Particle particle : population)
        {
            for (int i = 0; i < NUM_DIMENSIONS; i++)
            {
                double newPosition = particle.getPositionAt(i) + particle.getVelocityAt(i);
                particle.setPositionAt(i, newPosition);
            }
        }
    }
}
