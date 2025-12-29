package io.github.maritims.advent_of_code.common.testing;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doReturn;

public abstract class DayTest<T extends PuzzleSolver<?, ?>> {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    protected void mockLoadInput(T sut, String input) {
        doReturn(Arrays.stream(input.split("\n")).collect(Collectors.toCollection(ArrayList::new))).when(sut).loadInput();
    }
}
