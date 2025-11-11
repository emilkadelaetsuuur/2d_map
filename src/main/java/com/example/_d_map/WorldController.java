package com.example._d_map;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.Random;

    public class WorldController {
        private final Random random = new Random();
        private Color bacgroundcolor;

    @FXML
    private Canvas canvasWorld;

    private final int cellSize = 20;
    private final int width = 90;
    private final int height = 55;
    private final int[][] world = new int[width][height];

    @FXML
    public void initialize() {
        assert canvasWorld != null : "fx:id=\"canvasWorld\" was not injected: check your FXML file.";

        // Добавляем обработчик кликов мыши на canvas
        canvasWorld.addEventHandler(MouseEvent.MOUSE_PRESSED, this::handleMouse);

        // Перетаскивание мыши
        canvasWorld.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouse);

    bacgroundcolor = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
        // Начальная отрисовка пустого мира
        drawWorld();
    }

    private void handleMouse(MouseEvent e) {
        int cellX = (int) (e.getX() / cellSize);
        int cellY = (int) (e.getY() / cellSize);

        // Проверяем, чтобы координаты не выходили за пределы массива
        if (cellX >= 0 && cellX < width && cellY >= 0 && cellY < height) {
            world[cellX][cellY] = 1;
            drawWorld();
        }
    }

    private void drawWorld() {
        GraphicsContext gc = canvasWorld.getGraphicsContext2D();

        // заливаем фон рандомным цветом (изначально был черный)
        gc.setFill(bacgroundcolor);
        gc.fillRect(0, 0, width * cellSize, height * cellSize);

        // рисуем клетки
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (world[x][y] == 1) {
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
                // рисуем сетку
                gc.setStroke(Color.GRAY);
                gc.strokeRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }
}

