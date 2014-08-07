package com.schmapty.guvnah.test.model.check.impl;

import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.ReturnFalseCheck;

public class ReturnFalseCheckTest {

	@Test
	public void testPostitive() {
		ReturnFalseCheck check = new ReturnFalseCheck();
		assertTrue(!check.execute(null,null));
	}
	@Test
	public void testNegative() {
		ReturnFalseCheck check = new ReturnFalseCheck();
		assertFalse(check.execute(null,null));
	}
}
