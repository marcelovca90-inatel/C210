package br.inatel.c210.pso.entity;

import static br.inatel.c210.pso.algorithm.PSOUtils.NUM_DIMENSIONS;

import br.inatel.c210.pso.algorithm.RandomUtils;

public class Particle
{
    private String id;
    private Vector position;
    private Vector velocity;

    public Particle()
    {
        this.id = RandomUtils.randomId();
        this.position = new Vector(RandomUtils.randomDoubles(NUM_DIMENSIONS));
        this.velocity = new Vector(RandomUtils.randomDoubles(NUM_DIMENSIONS));
    }

    public Vector getPosition()
    {
        return this.position;
    }

    public double getPositionAt(int i)
    {
        return this.position.getValueAt(i);
    }

    public void setPosition(Vector position)
    {
        this.position = position;
    }

    public void setPositionAt(int i, double value)
    {
        this.position.setValueAt(i, value);
    }

    public Vector getVelocity()
    {
        return this.velocity;
    }

    public double getVelocityAt(int i)
    {
        return this.velocity.getValueAt(i);
    }

    public void setVelocity(Vector velocity)
    {
        this.velocity = velocity;
    }

    public void setVelocityAt(int i, double value)
    {
        this.velocity.setValueAt(i, value);
    }

    @Override
    public String toString()
    {
        return String.format("Pos=%s, Vel=%s", this.position, this.velocity);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Particle other = (Particle) obj;
        if (id == null)
        {
            if (other.id != null) return false;
        }
        else if (!id.equals(other.id)) return false;
        return true;
    }
}
