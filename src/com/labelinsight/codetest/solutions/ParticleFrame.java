package com.labelinsight.codetest.solutions;

import java.util.ArrayList;
import java.util.List;

public class ParticleFrame {
    private List<Particle> particles = new ArrayList<>();

    public  ParticleFrame() {}

    public ParticleFrame(List<Particle> particles) {
        this.particles = particles;
    }

    public void addParticle(Particle particle) {
        particles.add(particle);
    }

    public void addParticle(int index, Particle particle) {
        particles.add(index, particle);
    }

    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        for (Particle p : particles) {
            Direction dir = p.getDir();
            if (dir.equals(Direction.O)) {
                out.append(".");
            } else {
                out.append("X");
            }
        }
        return out.toString();
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }
}
