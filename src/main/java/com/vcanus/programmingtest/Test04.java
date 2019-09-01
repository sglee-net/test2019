package com.vcanus.programmingtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;

public class Test04 {
    private static final Logger logger =
            LoggerFactory.getLogger(Application.class);

    private int [][]cells;

    public Test04() {
    }

    public void run() {
        updateCells();
        int sum = sum();
        System.out.printf("Sum of water in pond is %d \r\n", sum);
    }

    public void print() {
        assert(cells != null);
        int rowSize = cells.length;
        int colSize = cells[0].length;
        assert(rowSize > 0);
        assert(colSize > 0);

        for(int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                System.out.print(this.cells[i][j]);
                System.out.print(" ");
            }
            System.out.print("\r\n");
        }
    }

    public int sum() {
        assert(cells != null);
        int rowSize = cells.length;
        int colSize = cells[0].length;
        assert(rowSize > 0);
        assert(colSize > 0);

        int sum = 0;
        for(int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                sum += this.cells[i][j];
            }
        }
        return sum;
    }

    public void updateCells() {
        assert(cells != null);
        int rowSize = cells.length;
        int colSize = cells[0].length;
        assert(rowSize > 0);
        assert(colSize > 0);

        boolean isUpdated = false;
        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < colSize; j++) {
                logger.info("i:{}, j:{}", i, j);
                if(i-1 < 0) {
                    continue;
                }
                if(j-1 < 0) {
                    continue;
                }
                if(i+1 >= rowSize) {
                    continue;
                }
                if(j+1 >= colSize) {
                    continue;
                }
                // -------> i
                // |
                // |
                // j
                //
                // Cell
                //     N
                // W  cell  E
                //     S
                int cellN = this.cells[i-1][j];
                int cellS = this.cells[i+1][j];
                int cellE = this.cells[i][j+1];
                int cellW = this.cells[i][j-1];
                if(cellN == 0 ||
                cellS == 0 ||
                cellE == 0 ||
                cellW == 0) {
                    continue;
                }
                if(this.cells[i][j] <= cellN &&
                this.cells[i][j] <= cellS &&
                this.cells[i][j] <= cellE &&
                this.cells[i][j] <= cellW) {
                    this.cells[i][j]++;
                    isUpdated = true;
                }
            }
        }

        if(isUpdated) {
            updateCells();
        } else {
            return;
        }
    }

    public boolean readFile(
            String filePath,
            int rowSize,
            int colSize,
            String delimiter)
            throws IOException {
        File file = null;
        try{
            file = new File(filePath);
        } catch (Exception e) {
            logger.error(e.toString());
            return false;
        }

        if(!file.exists()) {
            logger.error("no such file exists");
            return false;
        }

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader =
                new BufferedReader(fileReader);

        //assert(rowSize > 0 && colSize > 0);
        if(rowSize <= 0 || colSize <= 0) {
            logger.error("row or column size should be bigger than 0");
            return false;
        }
        cells = new int[rowSize][colSize];

        int rowIndex = 0;
        String line = "";
        while((line = bufferedReader.readLine()) != null) {
            String[] row = line.split(delimiter);
            //assert(row.length == colSize);
            if(row.length != colSize) {
                logger.error("wrong column size");
                return false;
            }
            for(int j = 0; j < row.length; j++) {
                this.cells[rowIndex][j] =
                        Integer.parseInt(row[j]);
            }
            rowIndex++;
        }
        if(rowSize != rowIndex) {
            return false;
        }
        return true;
    }

    public int at(int row, int col) {
        return this.cells[row][col];
    }
}
