package br.inatel.c210.pso.runnable;

import java.util.List;

import br.inatel.c210.pso.algorithm.PSO;
import br.inatel.c210.pso.algorithm.PSOUtils;
import br.inatel.c210.pso.entity.Particle;
import br.inatel.c210.pso.view.PlotUtils;

public class Runner
{
    public static void main(String[] args) throws InterruptedException
    {
        List<Particle> population = PSOUtils.createInitialPopulation();

        PlotUtils.show(population);
        Thread.sleep(1000);

        while (true)
        {
            PSO.adjustVelocity(population);
            PSO.craziness(population);
            PSO.updatePosition(population);

            PlotUtils.update(population);

            Thread.sleep(10);
        }
    }
}
