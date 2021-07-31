package aspectTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.forge.revature.aspects.LoggingAspect;

class TestLoggingAspect {
	LoggingAspect LA;
	JoinPoint JP;
	Exception e;
	ProceedingJoinPoint PJP;
	
	@BeforeEach
	void init() {
		LA = mock(LoggingAspect.class);
		JP = mock(JoinPoint.class);
		e = mock(Exception.class);
		PJP = mock(ProceedingJoinPoint.class);
	}
	
	
	@Test
	void beanPointCutDoesNotModifyAspect() {
		LoggingAspect LA2 = LA;
		LA.beanPointcut();
		assertEquals(LA,LA2);
	}
	
	@Test
	void packagePointCutDoesNotModifyAspect() {
		LoggingAspect LA2 = LA;
		LA.packagePointcut();
		assertEquals(LA,LA2);
	}
	
	@Test
	void beanPointCutReturnsNothing() {
		LA.beanPointcut();
		verify(LA, times(1)).beanPointcut();
	}

	@Test
	void packagePointCutReturnsNothing() {
		LA.packagePointcut();
		verify(LA, times(1)).packagePointcut();
	}

	@Test
	void logAfterThrowing() {
		LA.logAfterThrowing(JP, e);
		verify(LA, times(1)).logAfterThrowing(JP, e);
	}
	
	@Test
	void logAroundTest() throws Throwable {
		LA.logAround(PJP);
		verify(PJP, never()).proceed();
		verify(LA, times(1)).logAround(PJP);
	}
	
	
}
