package com.illarli.middleware.infrastructure.print;

import com.illarli.middleware.models.TagPrinter;
import com.illarli.middleware.models.repositories.ZPLLibraryRepository;
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.element.ZebraBarCode128;
import fr.w3blog.zpl.model.element.ZebraBarCode39;
import fr.w3blog.zpl.model.element.ZebraText;
import fr.w3blog.zpl.utils.ZebraUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZebraZPL implements ZPLLibraryRepository {
    private ZebraLabel zebraLabel;
    private TagPrinter printer;
    private final Logger logger = LoggerFactory.getLogger(ZebraZPL.class);

    public ZebraZPL(int width, int height, TagPrinter printer) {
        this.zebraLabel = new ZebraLabel(width, height);
        this.printer = printer;
        this.zebraLabel.setDefaultZebraFont(ZebraFont.ZEBRA_ZERO);
    }

    public ZebraZPL(TagPrinter printer) {
        this.zebraLabel = new ZebraLabel();
        this.printer = printer;
    }

    @Override
    public boolean print() {
        try {
            ZebraUtils.printZpl(this.zebraLabel, this.printer.getName());
            return true;
        } catch (Exception e) {
            logger.warn("Error print zpl");
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean print(String zpl) {
        try {
            ZebraUtils.printZpl(zpl, this.printer.getName());
            return true;
        } catch (Exception e) {
            logger.warn("Error print zpl");
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean print(String[] zplList) {
        try {
            for (String zpl : zplList) {
                ZebraUtils.printZpl(zpl, this.printer.getName());
            }
            return true;
        } catch (Exception e) {
            logger.warn("Error print zpl");
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void addText(String text) {
        this.zebraLabel.addElement(new ZebraText(text));
    }

    @Override
    public void addText(String text, int fontSize) {
        this.zebraLabel.addElement(new ZebraText(text, fontSize));
    }

    @Override
    public void addText(int positionX, int positionY, String text, int fontSize) {
        this.zebraLabel.addElement(new ZebraText(positionX, positionY, text, fontSize));
    }

    @Override
    public void addBarCode(String text) {
//        this.zebraLabel.addElement(new ZebraBarCode39(text));
    }

    @Override
    public void addBarCode(int positionX, int positionY, String text, int barCodeHeight) {
        this.zebraLabel.addElement(new ZebraBarCode39(positionX, positionY, text, barCodeHeight));
    }

    @Override
    public void addBarCode(int positionX, int positionY, String text, int barCodeHeight, int barCodeWidth, int wideBarRatio) {
        this.zebraLabel.addElement(new ZebraBarCode128(positionX, positionY, text, barCodeHeight, barCodeWidth, wideBarRatio));
    }

    public ZebraLabel getZebraLabel() {
        return zebraLabel;
    }
}
