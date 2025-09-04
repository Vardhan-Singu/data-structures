import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Particle System Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        ParticlePanel panel = new ParticlePanel();
        frame.add(panel);
        frame.setVisible(true);
        panel.start();
    }

    static class Particle {
        float x, y;
        float vx, vy;
        float life, maxLife;
        Color color;
        float alpha;

        public Particle(float x, float y, float vx, float vy, float maxLife, Color color) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.maxLife = maxLife;
            this.life = 0;
            this.color = color;
            this.alpha = 1.0f;
        }

        public void update(float gravity, float wind) {
            vx += wind;
            vy += gravity;
            x += vx;
            y += vy;
            life++;
            alpha = Math.max(0, 1.0f - (life / maxLife));
        }

        public boolean isAlive() {
            return life < maxLife && alpha > 0.01f;
        }
    }

    static class ParticlePanel extends JPanel implements ActionListener {
        ArrayList<Particle> particles = new ArrayList<>();
        Timer timer;
        Random rand = new Random();
        String mode = "fire"; // fire, smoke, snow, rain

        public ParticlePanel() {
            setBackground(Color.BLACK);
            timer = new Timer(16, this); // ~60 FPS

            // Switch modes on mouse click
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (mode.equals("fire")) mode = "smoke";
                    else if (mode.equals("smoke")) mode = "snow";
                    else if (mode.equals("snow")) mode = "rain";
                    else mode = "fire";
                }
            });
        }

        public void start() {
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            spawnParticles();
            updateParticles();
            repaint();
        }

        void spawnParticles() {
            int count = 0;
            if (mode.equals("fire")) count = 20;
            else if (mode.equals("smoke")) count = 10;
            else if (mode.equals("snow")) count = 5;
            else if (mode.equals("rain")) count = 30;

            for (int i = 0; i < count; i++) {
                if (mode.equals("fire")) {
                    float x = getWidth() / 2 + rand.nextFloat() * 40 - 20;
                    float y = getHeight() - 50;
                    float vx = rand.nextFloat() * 2 - 1;
                    float vy = -rand.nextFloat() * 4 - 2;
                    float life = 40 + rand.nextFloat() * 20;
                    Color color = new Color(1.0f, rand.nextFloat() * 0.5f, 0.0f);
                    particles.add(new Particle(x, y, vx, vy, life, color));
                } else if (mode.equals("smoke")) {
                    float x = getWidth() / 2 + rand.nextFloat() * 60 - 30;
                    float y = getHeight() - 60;
                    float vx = rand.nextFloat() * 1.5f - 0.75f;
                    float vy = -rand.nextFloat() * 2 - 1;
                    float life = 80 + rand.nextFloat() * 40;
                    Color color = new Color(0.5f + rand.nextFloat() * 0.5f, 0.5f + rand.nextFloat() * 0.5f, 0.5f + rand.nextFloat() * 0.5f);
                    particles.add(new Particle(x, y, vx, vy, life, color));
                } else if (mode.equals("snow")) {
                    float x = rand.nextFloat() * getWidth();
                    float y = -10;
                    float vx = rand.nextFloat() * 1.5f - 0.75f;
                    float vy = 1 + rand.nextFloat() * 1.5f;
                    float life = getHeight() / vy + 20;
                    Color color = Color.WHITE;
                    particles.add(new Particle(x, y, vx, vy, life, color));
                } else if (mode.equals("rain")) {
                    float x = rand.nextFloat() * getWidth();
                    float y = -10;
                    float vx = rand.nextFloat() * 0.5f - 0.25f;
                    float vy = 8 + rand.nextFloat() * 4;
                    float life = getHeight() / vy + 10;
                    Color color = new Color(0.3f, 0.3f, 1.0f);
                    particles.add(new Particle(x, y, vx, vy, life, color));
                }
            }
        }

        void updateParticles() {
            float gravity = 0, wind = 0;
            if (mode.equals("fire")) {
                gravity = -0.03f;
                wind = (rand.nextFloat() - 0.5f) * 0.02f;
            } else if (mode.equals("smoke")) {
                gravity = -0.01f;
                wind = (rand.nextFloat() - 0.5f) * 0.03f;
            } else if (mode.equals("snow")) {
                gravity = 0.02f;
                wind = (rand.nextFloat() - 0.5f) * 0.01f;
            } else if (mode.equals("rain")) {
                gravity = 0.1f;
                wind = 0.0f;
            }

            for (int i = particles.size() - 1; i >= 0; i--) {
                Particle p = particles.get(i);
                p.update(gravity, wind);
                if (!p.isAlive() || p.y > getHeight() + 20) {
                    particles.remove(i);
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            for (Particle p : particles) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, p.alpha));
                if (mode.equals("rain")) {
                    g2.setColor(p.color);
                    g2.drawLine((int) p.x, (int) p.y, (int) p.x, (int) (p.y + 10));
                } else if (mode.equals("snow")) {
                    g2.setColor(p.color);
                    g2.fillOval((int) p.x, (int) p.y, 4, 4);
                } else if (mode.equals("smoke")) {
                    g2.setColor(p.color);
                    g2.fillOval((int) p.x, (int) p.y, 16, 16);
                } else { // fire
                    g2.setColor(p.color);
                    g2.fillOval((int) p.x, (int) p.y, 8, 8);
                }
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.setColor(Color.WHITE);
            g2.drawString("Mode: " + mode + " (click to change)", 10, 20);
            g2.drawString("Particles: " + particles.size(), 10, 40);
        }
    }
}
