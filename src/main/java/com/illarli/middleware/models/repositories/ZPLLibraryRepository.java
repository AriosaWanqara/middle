package com.illarli.middleware.models.repositories;

public interface ZPLLibraryRepository {
    boolean print();

    void addText(String text);

    void addText(String text, int fontSize);

    void addText(int positionX, int positionY, String text, int fontSize);

    void addBarCode(String text);

    void addBarCode(int positionX, int positionY, String text, int barCodeHeight);

    void addBarCode(int positionX, int positionY, String text, int barCodeHeight, int barCodeWidth, int wideBarRatio);
}
