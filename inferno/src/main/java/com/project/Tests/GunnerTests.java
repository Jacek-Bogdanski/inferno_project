package com.project.Tests;

import com.project.Simulation.Gunner;
import com.project.Simulation.Position;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GunnerTests {

    @Test
    public void testCreate() {
        char team = 'C';
        Position position = new Position(8,6);

        Gunner gunner = new Gunner(team, position);

        Assert.assertNotNull(gunner);
        Assert.assertEquals(gunner.position,position);
        Assert.assertEquals(gunner.team,team);
    }
}
