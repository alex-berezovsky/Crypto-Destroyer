package com.teamthirty.buyhighselllow;

import com.teamthirty.buyhighselllow.Utilities.Util;
import org.junit.Assert;
import org.junit.Test;

public class UtilTest {

    @Test
    public void nullNameInput() {

        String name = null;
        Assert.assertFalse(Util.sanitizeNameInput(null, name));

    }

    @Test
    public void emptyNameInput() {

        String name = "";
        Assert.assertFalse(Util.sanitizeNameInput(null, name));

        name = "     ";
        Assert.assertFalse(Util.sanitizeNameInput(null, name));

    }
}