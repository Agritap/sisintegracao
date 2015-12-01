package com.agritap.sisintegracao.server;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

public class ServerUtilTest {

//	@Test
	public void test() {
		// 01/01/2015
		Calendar janeiro = Calendar.getInstance();
		janeiro.set(Calendar.MONTH, 0);
		janeiro.set(Calendar.YEAR, 2015);
		janeiro.set(Calendar.DAY_OF_MONTH, 1);

		// 01/06/2015
		Calendar junho = Calendar.getInstance();
		junho.set(Calendar.MONTH, 5);
		junho.set(Calendar.YEAR, 2015);
		junho.set(Calendar.DAY_OF_MONTH, 1);

		// 01/02/2015
		Calendar fevereiro = Calendar.getInstance();
		fevereiro.set(Calendar.MONTH, 1);
		fevereiro.set(Calendar.YEAR, 2015);
		fevereiro.set(Calendar.DAY_OF_MONTH, 1);

		// 01/02/2015
		Calendar agosto = Calendar.getInstance();
		agosto.set(Calendar.MONTH, 7);
		agosto.set(Calendar.YEAR, 2015);
		agosto.set(Calendar.DAY_OF_MONTH, 1);

		boolean deveriaSerFalso = ServerUtil.isPeriodosIntercecao(janeiro.getTime(), fevereiro.getTime(),
				junho.getTime(), agosto.getTime());
		assertFalse(deveriaSerFalso);

		deveriaSerFalso = ServerUtil.isPeriodosIntercecao(null, fevereiro.getTime(), junho.getTime(), agosto.getTime());
		assertFalse(deveriaSerFalso);

		deveriaSerFalso = ServerUtil.isPeriodosIntercecao(null, fevereiro.getTime(), junho.getTime(), null);
		assertFalse(deveriaSerFalso);

		deveriaSerFalso = ServerUtil.isPeriodosIntercecao(junho.getTime(), agosto.getTime(), janeiro.getTime(),
				fevereiro.getTime());
		assertFalse(deveriaSerFalso);

		deveriaSerFalso = ServerUtil.isPeriodosIntercecao(junho.getTime(), agosto.getTime(), null, fevereiro.getTime());
		assertFalse(deveriaSerFalso);

		deveriaSerFalso = ServerUtil.isPeriodosIntercecao(junho.getTime(), null, null, fevereiro.getTime());
		assertFalse(deveriaSerFalso);

		boolean deveriaSerTrue = ServerUtil.isPeriodosIntercecao(janeiro.getTime(), junho.getTime(),
				fevereiro.getTime(), agosto.getTime());
		assertTrue(deveriaSerTrue);

		deveriaSerTrue = ServerUtil.isPeriodosIntercecao(null, junho.getTime(), fevereiro.getTime(), agosto.getTime());
		assertTrue(deveriaSerTrue);

		deveriaSerTrue = ServerUtil.isPeriodosIntercecao(null, junho.getTime(), fevereiro.getTime(), null);
		assertTrue(deveriaSerTrue);

		deveriaSerTrue = ServerUtil.isPeriodosIntercecao(null, junho.getTime(), null, null);
		assertTrue(deveriaSerTrue);

		deveriaSerTrue = ServerUtil.isPeriodosIntercecao(null, null, null, null);
		assertTrue(deveriaSerTrue);

		deveriaSerTrue = ServerUtil.isPeriodosIntercecao(fevereiro.getTime(), agosto.getTime(), janeiro.getTime(),
				junho.getTime());
		assertTrue(deveriaSerTrue);

		deveriaSerTrue = ServerUtil.isPeriodosIntercecao(fevereiro.getTime(), agosto.getTime(), null, junho.getTime());
		assertTrue(deveriaSerTrue);

		deveriaSerTrue = ServerUtil.isPeriodosIntercecao(fevereiro.getTime(), null, null, junho.getTime());
		assertTrue(deveriaSerTrue);

		deveriaSerTrue = ServerUtil.isPeriodosIntercecao(null, null, null, junho.getTime());
		assertTrue(deveriaSerTrue);

	}

}
