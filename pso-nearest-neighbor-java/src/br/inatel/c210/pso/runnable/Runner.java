package br.inatel.c210.pso.runnable;

import java.util.List;

import br.inatel.c210.pso.algorithm.PSOOperators;
import br.inatel.c210.pso.algorithm.PSOUtils;
import br.inatel.c210.pso.entity.Particle;
import br.inatel.c210.pso.view.PlotUtils;

public class Runner
{
    public static void main(String[] args) throws InterruptedException
    {
        List<Particle> swarm = PSOUtils.createInitialSwarm();

        PlotUtils.show(swarm);
        Thread.sleep(1000);

        while (true)
        {
            PSOOperators.adjustVelocity(swarm);
            PSOOperators.craziness(swarm);
            PSOOperators.updatePosition(swarm);

            PlotUtils.update(swarm);

            Thread.sleep(100);
        }
    }
}
