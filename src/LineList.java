import java.awt.Graphics2D;

public class LineList {
    private Node front, rear;

    class Node {
        lines line;
        Node link;

        Node(lines l, Node n) {
            line = l;
            link = n;
        }
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public void addLine(lines l) {
        Node temp = new Node(l, null);
        if (rear == null)
            front = rear = temp;
        else {
            rear = rear.link = temp;
        }
    }

    public void moveRight(int speed) {
        if (!isEmpty()) {
            for (Node temp = front; temp != null; temp = temp.link) {
                temp.line.x = temp.line.x - speed;
            }
        }
    }

    public void moveLeft(int speed) {
        if (!isEmpty()) {
            for (Node temp = front; temp != null; temp = temp.link) {
                temp.line.x = temp.line.x + speed;
            }
        }
    }

    public boolean collision(character c) {
        if (!isEmpty()) {
            for (Node temp = front; temp != null; temp = temp.link) {
                if (temp.line.collision(c))
                    return true;
            }
            return false;
        }
        return false;
    }

    public boolean frontCollision(character c) {
        if (!isEmpty()) {
            return (front.line.collision(c));
        }
        return false;
    }

    public void paint(Graphics2D g) {
        if (!isEmpty()) {
            for (Node temp = front; temp != null; temp = temp.link) {
                temp.line.paint(g);
            }
        }
    }
}