package com.project.Tests;

import com.project.Simulation.Drop;
import com.project.Simulation.Field;
import com.project.Simulation.MilitaryUnit;
import com.project.Simulation.SimulationMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.ArrayList;

public class SimulationMapTests {

    @Test
    public void testCreate() {
        JTextPane mapArea = new JTextPane();

        SimulationMap map = new SimulationMap(mapArea);

        Assert.assertNotNull(map);
    }
}
