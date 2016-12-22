package de.lv1871.dms.GameOfLife;

import static org.junit.Assert.assertFalse;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

import de.lv1871.dms.GameOfLife.domain.Field;

public class LambdaExpressionStep4 {

	// implementiert ein konditionales "toDeadField" das alle lebenden in tote
	// verwandelt

	@Test
	public void testMapToDeadFieldWhenAlive() {
		Field field = new Field(1, 1, true);

		// @formatter:off
		 Field result = Optional.of(field).map(toDeadField(assertTrue(Field::getAlive))).get();
		// @formatter:on

		 assertFalse(result.getAlive());

	}

	private Function<Field, Field> toDeadField(Predicate<Field> assertTrue) {
		return (field) ->  assertTrue.test(field) ? new Field(field.getX(),field.getY(), false) : field;
	}

	private static <F, T> Predicate<F> assertTrue(Function<F, Boolean> supplier) {
		return (value) -> supplier.apply(value);
	}

}
