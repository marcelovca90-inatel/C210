from .particle import Particle
from matplotlib import pyplot as plt

class PSO:

    def update_plot(self,bounds,x,y):
        plt.xlim(bounds[0])
        plt.ylim(bounds[1])
        plt.scatter(x,y)

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
                self.update_plot(bounds,swarm[j].position[0],swarm[j].position[1])
            plt.show()
            i += 1

        # print final results
        print(f'i = {i}\tgbest_pos = {gbest_pos}\tgbest_err = {gbest_err}')
