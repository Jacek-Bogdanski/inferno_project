package com.project.Tests;

import com.project.Simulation.Drop;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropTests {

    @Test
    public void testCreate() {
        String type = "fuel";
        Integer value = 100;

        Drop drop = new Drop(type,value);

        Assert.assertNotNull(drop);
        Assert.assertEquals(drop.getType(),type);
        Assert.assertEquals(drop.getValue(),value);
    }
}
