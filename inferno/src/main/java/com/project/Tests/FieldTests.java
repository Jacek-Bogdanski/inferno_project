package com.project.Tests;

import com.project.Simulation.Drop;
import com.project.Simulation.Field;
import com.project.Simulation.MilitaryUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class FieldTests {

    @Test
    public void testCreate() {
        int type = 1;
        Field field = new Field(type);
        ArrayList<MilitaryUnit> expectedArrayList = new ArrayList<>();
        ArrayList<Drop> expectedArrayList2 = new ArrayList<>();

        Assert.assertNotNull(field);
        Assert.assertEquals(field.getType(),type);
        Assert.assertEquals(field.getUnits(),expectedArrayList);
        Assert.assertEquals(field.getDrops(),expectedArrayList2);
    }
}
