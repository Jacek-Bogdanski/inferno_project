package com.project.Tests;

import com.project.Simulation.Position;
import com.project.Simulation.Tank;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TankTests {

    @Test
    public void testCreate() {
        char team = 'A';
        Position position = new Position(2,2);

        Tank tank = new Tank(team,position);

        Assert.assertNotNull(tank);
        Assert.assertEquals(tank.position,position);
        Assert.assertEquals(tank.team,team);
    }
}
