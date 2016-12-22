package de.lv1871.dms.GameOfLife;

import static de.lv1871.dms.GameOfLife.domain.Gameboard.initRandomGame;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.lv1871.dms.GameOfLife.domain.Field;

public class App {
	public static void main(String[] args) throws InterruptedException {

		int xSize = 10;
		int ySize = 5;

		List<Field> gameboard = initRandomGame(xSize, ySize);

		while (true) {

			gameboard = iterateGameboard(gameboard);

			
//			gameboard.stream()
//			.forEach(System.out::println);
//			AtomicInteger ySizeBefore  = new AtomicInteger(0);
//			gameboard.stream()
//			.map(field -> field.getAlive() ? "X": "O")
//			.forEach( ySize != ySizeBefore.get() ? {System.out::println(); ySizeBefore.incrementAndGet();} : System.out::print);
			
			
			for(Field field : gameboard){
				if(field.getX() == 9) System.out.println(field.getAlive() ? " X ": " O ");
				else System.out.print(field.getAlive() ? " X ": " O ");
			}
			
			System.out.println("-----------------------------");
			Thread.sleep(1000);
		}
	}

	private static List<Field> iterateGameboard(List<Field> gameboard) {
//		return null;
		// @formatter:off
		return gameboard
				.stream()
	    		.map(toDeadField(which(isAlive(), and(),
		    			which(hasLessThanTwo(livingNeighboursIn(gameboard)), or(), hasMoreThanThree(livingNeighboursIn(gameboard))))))
	    		.map(toAliveField(which(isDead(), and(), hasExactThree(livingNeighboursIn(gameboard)))))
	    		.collect(Collectors.toList());
		// @formatter:on
	}


	private static Predicate<Field> hasExactThree(Function<Field, List<Field>> livingNeighboursIn) {
		return (value) -> livingNeighboursIn.apply(value).size() == 3;
	}

	private static Predicate<Field> isDead() {
		return (value) -> !value.getAlive();
	}
	
	private static Predicate<Field> isAlive() {
		return isDead().negate();
	}
	private static Predicate<Field> hasMoreThanThree(Function<Field, List<Field>> livingNeighboursIn) {
		return (value) -> livingNeighboursIn.apply(value).size() > 3;
	}

	private static Predicate<Field> hasLessThanTwo(Function<Field, List<Field>> livingNeighboursIn) {
		return (value) -> livingNeighboursIn.apply(value).size() < 2;
	}

	private static Function<Field, Field> toAliveField(Predicate<Field> assertTrue) {
		return (field) ->  assertTrue.test(field) ? new Field(field.getX(),field.getY(), true) : field;
	}
	private static Function<Field, Field> toDeadField(Predicate<Field> assertTrue) {
		return (field) ->  assertTrue.test(field) ? new Field(field.getX(),field.getY(), false) : field;
	}
	
	 public static Function<Field, List<Field>> livingNeighboursIn(List<Field>
	 game) {
		// @formatter:off
		return (field) -> game
				.stream()
				.filter(isLivingNeighbour(field))
				.collect(Collectors.toList());
		// @formatter:on
	 }

	private static Predicate<? super Field> isLivingNeighbour(Field field) {
		return (value) -> !value.equals(field) && value.getAlive()&& Math.abs(field.getX()-value.getX())<2 && Math.abs(field.getY() - value.getY())<2;
	}
	
	private static BiFunction<Predicate<Field>, Predicate<Field>, Predicate<Field>> and() {
		return (value1, value2) -> value1.and(value2);
	}
	
	private static BiFunction<Predicate<Field>, Predicate<Field>, Predicate<Field>> or() {
		return (value1, value2) -> value1.or(value2);
	}

	private static Predicate<Field> which(Predicate<Field> p1, BiFunction<Predicate<Field>, Predicate<Field>, Predicate<Field>> biFunc, Predicate<Field> p3) {
		return biFunc.apply(p1,  p3);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
