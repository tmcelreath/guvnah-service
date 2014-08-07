package com.schmapty.guvnah.test.model.check.impl;

import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.ReturnTrueCheck;

public class ReturnTrueCheckTest {

	@Test
	public void testPostitive() {
		ReturnTrueCheck check = new ReturnTrueCheck();
		assertTrue(check.execute(null, null));
	}
	@Test
	public void testNegative() {
		ReturnTrueCheck check = new ReturnTrueCheck();
		assertFalse(!check.execute(null, null));
	}
}
