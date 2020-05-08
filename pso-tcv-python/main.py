from modules.functions import Functions
from modules.particle import Particle
from modules.pso import PSO

if __name__ == "__main__":

    num_dimensions = 2                  # number of dimensions in the search space
    cost_function = Functions.sphere    # cost function to be evaluated (sphere, rastrigin, etc.)
    initial_location = [5,5]            # initial starting location [x1,x2...]
    input_bounds = [(-10,10),(-10,10)]  # input bounds [(x1_min,x1_max),(x2_min,x2_max)...]
    num_particles = 15                  # number of particles in the swarm
    max_iterations = 30                 # maximum number of iterations to perform

    PSO(num_dimensions,cost_function,initial_location,input_bounds,num_particles,max_iterations)
