package com.gic;

import com.gic.console.MinesweeperInterface;
import com.gic.model.GameState;
import com.gic.model.Matrix;
import com.gic.service.MinesweeperService;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.Scanner;

import static org.mockito.Mockito.*;

class ApplicationTest {

    @Test
    void testMain_GameFlow_WithoutModifyingApplication() {

        Matrix matrix = mock(Matrix.class);

        try (
                MockedConstruction<Scanner> mockedScanner =
                        mockConstruction(Scanner.class,
                                (scannerMock, context) -> {

                                    when(scannerMock.nextInt())
                                            .thenReturn(3)
                                            .thenReturn(1)
                                            .thenReturn(0)
                                            .thenReturn(0);
                                });

                MockedConstruction<MinesweeperService> mockedService =
                        mockConstruction(MinesweeperService.class,
                                (serviceMock, context) -> {

                                    when(serviceMock.getMatrix())
                                            .thenReturn(matrix);

                                    when(serviceMock.getState())
                                            .thenReturn(GameState.IN_PROGRESS)
                                            .thenReturn(GameState.LOST);
                                });

                MockedConstruction<MinesweeperInterface> mockedConsole =
                        mockConstruction(MinesweeperInterface.class,
                                (consoleMock, context) -> {
                                })

        ) {

            Application.main(new String[]{});

            MinesweeperService service =
                    mockedService.constructed().get(0);

            MinesweeperInterface console =
                    mockedConsole.constructed().get(0);

            verify(service).start(3, 1);

            verify(service).reveal(0, 0);

            verify(console, atLeastOnce())
                    .printGameMatrix(matrix);

            verify(service, atLeast(2))
                    .getState();
        }
    }
}
