public class test2 {
    public static void main(String[] args) {
        javax.swing.JFrame frame = new javax.swing.JFrame("Space Shooter: Neon Galaxy ULTRA");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        javax.swing.JPanel panel = new javax.swing.JPanel() {
            int shipX = 470, shipY = 670, shipW = 60, shipH = 30;
            java.util.List<java.awt.Point> bullets = new java.util.ArrayList<>();
            java.util.List<java.awt.Point> enemies = new java.util.ArrayList<>();
            java.util.List<java.awt.Point> powerups = new java.util.ArrayList<>();
            java.util.List<java.awt.Point> explosions = new java.util.ArrayList<>();
            int score = 0;
            boolean left = false, right = false, space = false, up = false, down = false;
            boolean rapidFire = false;
            int rapidFireTicks = 0;
            javax.swing.Timer timer;
            int enemySpeed = 3;
            int bulletSpeed = 12;
            int spawnCounter = 0;
            int powerupCounter = 0;
            boolean gameOver = false;
            int bgOffset = 0;
            java.awt.Color[] neonColors = {
                java.awt.Color.CYAN, java.awt.Color.MAGENTA, java.awt.Color.YELLOW, java.awt.Color.GREEN, java.awt.Color.ORANGE
            };
            float shipAngle = 0;
            int hyperdrive = 0;
            boolean showReplay = false;
            java.awt.Rectangle replayBtn = new java.awt.Rectangle(400, 420, 200, 60);

            {
                setFocusable(true);
                addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent e) {
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) left = true;
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) right = true;
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) space = true;
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) up = true;
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) down = true;
                        if (gameOver && showReplay && e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                            replay();
                        }
                    }
                    public void keyReleased(java.awt.event.KeyEvent e) {
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) left = false;
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) right = false;
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) space = false;
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) up = false;
                        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) down = false;
                    }
                });
                addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if (gameOver && showReplay && replayBtn.contains(e.getPoint())) {
                            replay();
                        }
                    }
                });

                timer = new javax.swing.Timer(14, e -> {
                    if (gameOver) return;

                    // Neon animated starfield with parallax
                    bgOffset = (bgOffset + 3 + (hyperdrive > 0 ? 10 : 0)) % getHeight();

                    // Move ship
                    int moveSpeed = 10 + (hyperdrive > 0 ? 10 : 0);
                    if (left && shipX > 0) { shipX -= moveSpeed; shipAngle = Math.max(shipAngle - 0.12f, -0.5f);}
                    else if (right && shipX < getWidth() - shipW) { shipX += moveSpeed; shipAngle = Math.min(shipAngle + 0.12f, 0.5f);}
                    else shipAngle *= 0.8f;
                    if (up && shipY > 0) shipY -= moveSpeed;
                    if (down && shipY < getHeight() - shipH - 10) shipY += moveSpeed;

                    // Hyperdrive powerup effect
                    if (hyperdrive > 0) hyperdrive--;

                    // Fire bullets (spread if hyperdrive)
                    if (space && (bullets.isEmpty() || bullets.get(bullets.size() - 1).y < shipY - (rapidFire ? 8 : 32))) {
                        bullets.add(new java.awt.Point(shipX + shipW / 2 - 3, shipY));
                        if (rapidFire || hyperdrive > 0) {
                            bullets.add(new java.awt.Point(shipX + 10, shipY + 10));
                            bullets.add(new java.awt.Point(shipX + shipW - 16, shipY + 10));
                        }
                        if (hyperdrive > 0) {
                            bullets.add(new java.awt.Point(shipX + 5, shipY + 20));
                            bullets.add(new java.awt.Point(shipX + shipW - 11, shipY + 20));
                        }
                    }

                    // Move bullets
                    for (int i = 0; i < bullets.size(); i++) {
                        java.awt.Point b = bullets.get(i);
                        b.y -= bulletSpeed + (hyperdrive > 0 ? 8 : 0);
                        if (hyperdrive > 0) b.x += (i % 2 == 0 ? -2 : 2);
                    }
                    bullets.removeIf(b -> b.y < 0);

                    // Spawn enemies (waves, random UFOs, rare boss)
                    spawnCounter++;
                    if (spawnCounter % (hyperdrive > 0 ? 12 : 22) == 0) {
                        int ex = (int)(Math.random() * (getWidth() - 40));
                        enemies.add(new java.awt.Point(ex, 0));
                        // Sometimes spawn a "swarm"
                        if (Math.random() < 0.08) {
                            for (int k = -2; k <= 2; k++) {
                                enemies.add(new java.awt.Point(Math.max(0, Math.min(getWidth()-40, ex + k*50)), 0));
                            }
                        }
                    }
                    // Rare boss UFO
                    if (spawnCounter % 900 == 0) {
                        enemies.add(new java.awt.Point(-1000, -1000)); // special marker for boss
                    }

                    // Move enemies
                    for (int i = 0; i < enemies.size(); i++) {
                        java.awt.Point enemy = enemies.get(i);
                        if (enemy.x == -1000 && enemy.y == -1000) continue; // boss handled later
                        enemy.y += enemySpeed + (hyperdrive > 0 ? 6 : 0);
                        enemy.x += (int)(Math.sin(enemy.y / 28.0 + i) * 4);
                    }

                    // Move boss UFO
                    for (int i = 0; i < enemies.size(); i++) {
                        java.awt.Point en = enemies.get(i);
                        if (en.x == -1000 && en.y == -1000) {
                            // Boss appears at top, moves side to side
                            en.x = (int)(getWidth()/2 + Math.sin(spawnCounter/20.0)*350 - 60);
                            en.y = 60;
                        }
                    }

                    // Bullet-enemy collision
                    for (int i = 0; i < bullets.size(); i++) {
                        java.awt.Point b = bullets.get(i);
                        for (int j = 0; j < enemies.size(); j++) {
                            java.awt.Point en = enemies.get(j);
                            int ew = (en.x == -1000 ? 120 : 40), eh = (en.x == -1000 ? 60 : 40);
                            if (b.x > en.x && b.x < en.x + ew && b.y > en.y && b.y < en.y + eh) {
                                explosions.add(new java.awt.Point(en.x + ew/2, en.y + eh/2));
                                bullets.remove(i--);
                                // Boss takes 10 hits
                                if (en.x == -1000) {
                                    en.x = -1000 + (en.y == -1000 ? 0 : en.x); // keep marker
                                    en.y = -1000 + (en.y == -1000 ? 0 : en.y);
                                    if (!enemies.contains(new java.awt.Point(-999, -999))) {
                                        enemies.add(new java.awt.Point(-999, 10)); // boss hit counter
                                    } else {
                                        java.awt.Point bossHits = enemies.get(enemies.indexOf(new java.awt.Point(-999, -999)));
                                        bossHits.y++;
                                        if (bossHits.y >= 10) {
                                            enemies.remove(j--); // boss defeated
                                            enemies.remove(bossHits);
                                            score += 200;
                                            for (int p = 0; p < 3; p++)
                                                powerups.add(new java.awt.Point((int)(Math.random()*(getWidth()-40)), 100));
                                        }
                                    }
                                } else {
                                    enemies.remove(j--);
                                    score += 10;
                                    // Chance to spawn a powerup
                                    if (Math.random() < 0.13) {
                                        powerups.add(new java.awt.Point(en.x + 15, en.y + 15));
                                    }
                                }
                                break;
                            }
                        }
                    }

                    // Enemy-ship collision or enemy reaches bottom
                    for (java.awt.Point en : enemies) {
                        int ew = (en.x == -1000 ? 120 : 40), eh = (en.x == -1000 ? 60 : 40);
                        if ((en.x + ew > shipX && en.x < shipX + shipW && en.y + eh > shipY && en.y < shipY + shipH) ||
                            en.y > getHeight()) {
                            gameOver = true;
                            timer.stop();
                            showReplay = true;
                        }
                    }

                    // Remove off-screen enemies
                    enemies.removeIf(en -> en.y > getHeight());

                    // Powerup logic
                    powerupCounter++;
                    for (java.awt.Point p : powerups) {
                        p.y += 5;
                    }
                    // Ship collects powerup
                    for (int i = 0; i < powerups.size(); i++) {
                        java.awt.Point p = powerups.get(i);
                        if (p.x > shipX && p.x < shipX + shipW && p.y > shipY && p.y < shipY + shipH) {
                            double r = Math.random();
                            if (r < 0.5) {
                                rapidFire = true;
                                rapidFireTicks = 320;
                            } else {
                                hyperdrive = 180;
                            }
                            powerups.remove(i--);
                        }
                    }
                    // Remove off-screen powerups
                    powerups.removeIf(p -> p.y > getHeight());

                    // Rapid fire timer
                    if (rapidFire) {
                        rapidFireTicks--;
                        if (rapidFireTicks <= 0) rapidFire = false;
                    }

                    // Explosions animate
                    for (int i = 0; i < explosions.size(); i++) {
                        java.awt.Point ex = explosions.get(i);
                        ex.y += 1;
                        if (ex.y > 20) explosions.remove(i--);
                    }

                    // Increase difficulty
                    if (score > 0 && score % 120 == 0) {
                        enemySpeed = Math.min(11, enemySpeed + 1);
                    }

                    repaint();
                });
                timer.start();
            }

            void replay() {
                shipX = 470; shipY = 670;
                bullets.clear();
                enemies.clear();
                powerups.clear();
                explosions.clear();
                score = 0;
                left = right = space = up = down = false;
                rapidFire = false;
                rapidFireTicks = 0;
                enemySpeed = 3;
                bulletSpeed = 12;
                spawnCounter = 0;
                powerupCounter = 0;
                gameOver = false;
                bgOffset = 0;
                shipAngle = 0;
                hyperdrive = 0;
                showReplay = false;
                timer.start();
                requestFocusInWindow();
            }

            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);

                // Neon animated starfield (parallax)
                for (int layer = 0; layer < 3; layer++) {
                    int speed = (layer + 1) * (2 + layer * 2 + (hyperdrive > 0 ? 5 : 0));
                    for (int i = 0; i < 60 + layer * 30; i++) {
                        int y = (i * 60 + bgOffset * speed / 3) % getHeight();
                        int x = (int)(Math.sin(i * 0.5 + bgOffset * 0.02 + layer) * (400 - layer * 120) + getWidth() / 2);
                        g.setColor(neonColors[(i + layer) % neonColors.length]);
                        g.fillOval(x, y, 3 + layer * 2, 3 + layer * 2);
                    }
                }

                // Neon scanlines overlay
                for (int y = 0; y < getHeight(); y += 6) {
                    g.setColor(new java.awt.Color(0,255,255,10));
                    g.drawLine(0, y, getWidth(), y);
                }

                // Draw ship (rotated neon triangle with glow)
                java.awt.Graphics2D g2 = (java.awt.Graphics2D)g;
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                java.awt.geom.AffineTransform old = g2.getTransform();
                g2.translate(shipX + shipW/2, shipY + shipH/2);
                g2.rotate(shipAngle);
                java.awt.GradientPaint gp = new java.awt.GradientPaint(
                    -shipW/2, shipH/2, java.awt.Color.CYAN,
                    shipW/2, -shipH/2, java.awt.Color.MAGENTA
                );
                g2.setPaint(gp);
                int[] xPoints = {-shipW/2, 0, shipW/2};
                int[] yPoints = {shipH/2, -shipH/2, shipH/2};
                g2.fillPolygon(xPoints, yPoints, 3);
                // Ship glow
                g2.setColor(new java.awt.Color(0,255,255,90));
                g2.fillOval(-30, shipH/2-10, 60, 30);
                // Hyperdrive trail
                if (hyperdrive > 0) {
                    for (int t = 0; t < 8; t++) {
                        g2.setColor(new java.awt.Color(0,255,255,40-t*5));
                        g2.fillOval(-10, shipH/2+10+t*10, 20, 18);
                    }
                }
                g2.setTransform(old);

                // Draw bullets (neon, animated)
                for (java.awt.Point b : bullets) {
                    g2.setColor(java.awt.Color.YELLOW);
                    g2.fillRect(b.x, b.y, 6, 18);
                    g2.setColor(new java.awt.Color(255,255,0,80));
                    g2.fillRect(b.x-2, b.y-8, 10, 32);
                    g2.setColor(new java.awt.Color(255,255,255,40));
                    g2.drawRect(b.x-2, b.y-8, 10, 32);
                }

                // Draw enemies (animated neon UFOs, boss)
                for (int i = 0; i < enemies.size(); i++) {
                    java.awt.Point en = enemies.get(i);
                    if (en.x == -1000) {
                        // Boss UFO
                        int bx = en.x = (int)(getWidth()/2 + Math.sin(spawnCounter/20.0)*350 - 60);
                        int by = en.y = 60;
                        g2.setColor(java.awt.Color.MAGENTA);
                        g2.fillOval(bx, by, 120, 60);
                        g2.setColor(java.awt.Color.CYAN);
                        g2.drawOval(bx+10, by+15, 100, 30);
                        g2.setColor(new java.awt.Color(255,255,255,80));
                        g2.fillOval(bx+40, by+25, 40, 18);
                        // Boss lights
                        for (int l = 0; l < 10; l++) {
                            g2.setColor(neonColors[(i+l)%neonColors.length]);
                            g2.fillOval(bx + 10 + l*10, by + 50, 8, 8);
                        }
                        // Boss health bar
                        java.awt.Point bossHits = null;
                        for (java.awt.Point p : enemies) if (p.x == -999) bossHits = p;
                        int hits = bossHits == null ? 0 : bossHits.y;
                        g2.setColor(java.awt.Color.WHITE);
                        g2.drawRect(bx+10, by-18, 100, 10);
                        g2.setColor(java.awt.Color.GREEN);
                        g2.fillRect(bx+10, by-18, 100 - hits*10, 10);
                    } else if (en.x != -999) {
                        g2.setColor(neonColors[i % neonColors.length]);
                        g2.fillOval(en.x, en.y, 40, 24);
                        g2.setColor(java.awt.Color.WHITE);
                        g2.drawOval(en.x + 8, en.y + 6, 24, 12);
                        g2.setColor(new java.awt.Color(255,255,255,60));
                        g2.fillOval(en.x + 14, en.y + 10, 12, 6);
                        // UFO lights
                        for (int l = 0; l < 5; l++) {
                            g2.setColor(neonColors[(i+l)%neonColors.length]);
                            g2.fillOval(en.x + 6 + l*7, en.y + 20, 6, 6);
                        }
                    }
                }

                // Draw powerups (spinning neon orbs)
                for (int i = 0; i < powerups.size(); i++) {
                    java.awt.Point p = powerups.get(i);
                    int r = 18;
                    g2.setColor(java.awt.Color.GREEN);
                    g2.fillOval(p.x - r/2, p.y - r/2, r, r);
                    g2.setColor(java.awt.Color.WHITE);
                    g2.drawOval(p.x - r/2, p.y - r/2, r, r);
                    g2.setColor(new java.awt.Color(0,255,0,80));
                    g2.fillOval(p.x - r/4, p.y - r/4, r/2, r/2);
                    // Neon ring
                    g2.setColor(new java.awt.Color(0,255,128,120));
                    g2.drawOval(p.x - r, p.y - r, r*2, r*2);
                }

                // Draw explosions (neon expanding rings)
                for (java.awt.Point ex : explosions) {
                    int rad = ex.y * 2;
                    for (int c = 0; c < 3; c++) {
                        g2.setColor(new java.awt.Color(255,255,0, 120 - c*40));
                        g2.drawOval(ex.x - rad/2 - c*6, ex.y - rad/2 - c*6, rad + c*12, rad + c*12);
                    }
                }

                // Draw score
                g2.setColor(java.awt.Color.WHITE);
                g2.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 28));
                g2.drawString("Score: " + score, 30, 40);

                // Powerup indicator
                if (rapidFire) {
                    g2.setColor(java.awt.Color.GREEN);
                    g2.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 20));
                    g2.drawString("RAPID FIRE!", getWidth() - 180, 40);
                }
                if (hyperdrive > 0) {
                    g2.setColor(java.awt.Color.CYAN);
                    g2.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 20));
                    g2.drawString("HYPERDRIVE!", getWidth() - 340, 40);
                }

                // Game over message and replay button
                if (gameOver) {
                    g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 60));
                    g2.setColor(java.awt.Color.MAGENTA);
                    g2.drawString("GAME OVER", getWidth() / 2 - 220, getHeight() / 2 - 40);
                    g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 32));
                    g2.setColor(java.awt.Color.CYAN);
                    g2.drawString("Final Score: " + score, getWidth() / 2 - 110, getHeight() / 2 + 20);

                    // Replay button
                    if (showReplay) {
                        g2.setColor(new java.awt.Color(0,255,255,180));
                        g2.fillRoundRect(replayBtn.x, replayBtn.y, replayBtn.width, replayBtn.height, 30, 30);
                        g2.setColor(java.awt.Color.MAGENTA);
                        g2.setStroke(new java.awt.BasicStroke(4));
                        g2.drawRoundRect(replayBtn.x, replayBtn.y, replayBtn.width, replayBtn.height, 30, 30);
                        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 32));
                        g2.setColor(java.awt.Color.BLACK);
                        g2.drawString("REPLAY", replayBtn.x + 38, replayBtn.y + 42);
                        g2.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
                        g2.setColor(java.awt.Color.WHITE);
                        g2.drawString("Press ENTER or click", replayBtn.x + 22, replayBtn.y + 62);
                    }
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}
