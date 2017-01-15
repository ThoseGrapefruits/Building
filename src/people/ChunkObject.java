package people;

import java.awt.*;
import java.util.ArrayList;

import base.BuildingObject;
import base.Movable;
import base.Visible;
import constants.Constants;
import main.Building;

public class ChunkObject extends BuildingObject implements Visible, Runnable, Movable {

    /**
     * List of <code>chunks</code> that make up the <code>ChunkObject</code>.
     */
    public ArrayList<Chunk> chunks = new ArrayList<>();

    public ChunkObject(Building building, double x, double y) {
        super(building, x, y, 1, 1);
    }

    @Override
    public int getWidth() {
        Chunk r = getRightmostChunk();
        Chunk l = getLeftmostChunk();
        int w = (int) ((r.getX() + r.getWidth()) - l.getX());
        width = w;
        return w;
    }

    @Override
    public int getHeight() {
        Chunk t = getTopmostChunk();
        int h = (int) ((t.getY() + t.getHeight()) - getBottommostChunk().getY());
        height = h;
        return h;
    }


    /**
     * Adds the given chunk to the ChunkObject
     */
    public void addChunk(Chunk c) {
        if (!chunks.contains(c)) {
            chunks.add(c);
        }
    }

    private Chunk getRightmostChunk() {
        Chunk rightmost = chunks.get(0);
        for (Chunk c : chunks) {
            if (c.getX() > rightmost.getX()) {
                rightmost = c;
            }
        }
        return rightmost;
    }

    private Chunk getLeftmostChunk() {
        Chunk leftmost = chunks.get(0);
        for (Chunk c : chunks) {
            if (c.getX() < leftmost.getX()) {
                leftmost = c;
            }
        }
        return leftmost;
    }

    private Chunk getTopmostChunk() {
        Chunk topmost = chunks.get(0);
        for (Chunk c : chunks) {
            if (c.getY() > topmost.getY()) {
                topmost = c;
            }
        }
        return topmost;
    }

    private Chunk getBottommostChunk() {
        Chunk bottommost = chunks.get(0);
        for (Chunk c : chunks) {
            if (c.getY() < bottommost.getY()) {
                bottommost = c;
            }
        }
        return bottommost;
    }

    @Override
    public void run() {
        for (Chunk c : chunks) {
            c.run();
        }

        try {
            Thread.sleep(Constants.TICK);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        for (Chunk c : chunks) {
            c.paint(g2d);
        }
    }

    @Override
    public String toString() {
        String output = super.toString() + "\n";
        for (Chunk c : chunks) {
            output.concat(c.toString());
        }
        return output;
    }

    @Override
    public boolean canMoveX(Building b) {
        return true;
    }

    @Override
    public boolean canMoveY(Building b) {
        return true;
    }

    @Override
    public void move(Building b) {
        for (Chunk c : chunks) {
            c.move(b);
        }
    }
}
