package com.project.Tests;

import com.project.Simulation.Position;
import com.project.Simulation.Soldier;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SoldierTests {

    @Test
    public void testCreate() {
        char team = 'B';
        Position position = new Position(9,1);

        Soldier soldier = new Soldier(team,position);

        Assert.assertNotNull(soldier);
        Assert.assertEquals(soldier.position,position);
        Assert.assertEquals(soldier.team,team);
    }
}
