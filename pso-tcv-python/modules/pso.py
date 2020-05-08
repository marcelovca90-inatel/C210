from .particle import Particle
from .plot_utils import PlotUtils

class PSO:

    def __init__(self,num_dimensions,cost_function,bounds,num_particles,max_iterations):

        gbest_err = -1    # best error for group
        gbest_pos = []    # best position for group

        # establish the swarm
        swarm = []
        for i in range(0,num_particles):
            swarm.append(Particle(num_dimensions,bounds))

        # begin optimization loop
        i = 0
        while i < max_iterations:
            print(f'i = {i}\tgbest_pos = {gbest_pos}\tgbest_err = {gbest_err}')
            # cycle through particles in swarm and evaluate fitness
            for j in range(0,num_particles):
                swarm[j].evaluate(cost_function)

                # determine if current particle is the best (globally)
                if swarm[j].err < gbest_err or gbest_err == -1:
                    gbest_pos = list(swarm[j].position)
                    gbest_err = float(swarm[j].err)

            # cycle through swarm and update velocities and position
            for j in range(0,num_particles):
                swarm[j].update_velocity(gbest_pos)
                swarm[j].update_position(bounds)
                PlotUtils.plot_particle(swarm[j])    # plot the current particle
            PlotUtils.plot_iteration(bounds,i)       # plot the current iteration
            i += 1

        PlotUtils.save()    # persist the animated plot to the filesystem
        PlotUtils.play()    # call a process to play the persisted animation

        # print final results
        print(f'i = {i}\tgbest_pos = {gbest_pos}\tgbest_err = {gbest_err}')
