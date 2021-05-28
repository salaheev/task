package javatesttask.task.service;

import javatesttask.task.dto.Transferable;

public interface Calculable<E, D extends Transferable> {

    D calculate(String type, E from, E to);

    D calculateById(String type, Long from, Long to);
}
