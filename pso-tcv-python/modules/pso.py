from .particle import Particle
from matplotlib import pyplot as plt

class PSO:

    def __init__(self,num_dimensions,cost_function,x0,bounds,num_particles,max_iterations):

        gbest_err = -1    # best error for group
        gbest_pos = []    # best position for group
        plot_x = []       # placeholder for plot data (x)
        plot_y = []       # placeholder for plot data (x)

        # establish the swarm
        swarm = []
        for i in range(0,num_particles):
            swarm.append(Particle(x0,num_dimensions))

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

            plot_x.append(i)
            plot_y.append(gbest_err)
            
            i += 1

        # print final results
        print(f'i = {i}\tgbest_pos = {gbest_pos}\tgbest_err = {gbest_err}')

        # plot results across iterations
        plt.gca().set_xlabel('iteration')
        plt.gca().set_ylabel('f(x1,x2,...)')
        plt.gca().set_title('f(x1,x2,...) per iteration')
        plt.plot(plot_x, plot_y)
        plt.show()        
