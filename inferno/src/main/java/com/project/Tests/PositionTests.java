package com.project.Tests;

import com.project.Simulation.Position;
import com.project.Simulation.Tank;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositionTests {

    @Test
    public void testCreate() {
        Integer x = 2;
        Integer y = 4;

        Position position = new Position(x,y);

        Assert.assertNotNull(position);
        Assert.assertEquals(position.x,x);
        Assert.assertEquals(position.y,y);
    }

    @Test
    public void testToString() {
        Integer x = 5;
        Integer y = 3;

        Position position = new Position(x,y);

        Assert.assertEquals(position.toString(), "(5, 3)");
    }
}
