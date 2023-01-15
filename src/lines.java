import java.awt.Graphics2D;

public class lines {
    int x, y, l;

    public lines(int x, int y, int l) {
        this.x = x;
        this.y = y;
        this.l = l;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public boolean collision(character c) {
        return ((((this.x + this.l) > c.x) && (this.x < (c.x + c.width)))
                && (this.y <= (c.y + c.height)));
        // && ((this.y <= c.y+c.height) && (c.y == this.y-200)));
    }

    public void paint(Graphics2D g) {
        g.drawLine(this.getX(), this.getY(), this.getX() + l, this.getY());
    }
}
