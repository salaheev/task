package javatesttask.task.services;

public interface Calculable<E> {

    String calculate(String type, E from, E to);

    String calculateById(String type, Long from, Long to);

}
