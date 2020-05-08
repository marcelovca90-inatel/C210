import random

class Particle:

    def __init__(self,num_dimensions,bounds):
        
        self.num_dimensions = num_dimensions
        self.position = []    # particle position
        self.velocity = []    # particle velocity
        self.pbest_pos = []   # best individual position
        self.pbest_err = -1   # best individual error
        self.err = -1         # current individual error

        # randomly initialize the positions and velocities
        for i in range(0,self.num_dimensions):
            self.position.append(random.uniform(bounds[i][0],bounds[i][1]))
            self.velocity.append(random.uniform(bounds[i][0],bounds[i][1]))

    # evaluate current fitness
    def evaluate(self,cost_function):
        self.err = cost_function(self.position)

        # check to see if the current position is an individual best
        if self.err < self.pbest_err or self.pbest_err==-1:
            self.pbest_pos = self.position
            self.pbest_err = self.err

    # update new particle velocity
    def update_velocity(self,gbest_pos):
        w = 0.5    # constant inertia weight (how much to weigh the previous velocity)
        c1 = 1     # individual/cognitive constant
        c2 = 1     # global/social constant

        for i in range(0,self.num_dimensions):
            r1 = random.random()
            r2 = random.random()

            vel_cognitive = c1 * r1 * (self.pbest_pos[i] - self.position[i])
            vel_social = c2 * r2 * (gbest_pos[i] - self.position[i])

            self.velocity[i] = w * self.velocity[i] + vel_cognitive + vel_social

    # update the particle position based off new velocity updates
    def update_position(self,bounds):
        for i in range(0,self.num_dimensions):
            self.position[i] = self.position[i] + self.velocity[i]

            # adjust minimum position if necessary
            if self.position[i] < bounds[i][0]:
                self.position[i] = bounds[i][0]

            # adjust maximum position if necessary
            if self.position[i] > bounds[i][1]:
                self.position[i] = bounds[i][1]
