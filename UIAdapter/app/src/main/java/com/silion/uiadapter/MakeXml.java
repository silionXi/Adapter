package com.silion.uiadapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2015/9/8.
 */
public class MakeXml {
    private final static float widthBase = 320f;
    private final static float heighBase = 480f;
    private final static String widthTemplate = "<dimen name=\"x{1}\">{2}px</dimen>\n";
    private final static String heightTemplate = "<dimen name=\"y{1}\">{2}px</dimen>\n";
    private final static String savePath = "F:/02_Work/03_SourceCode/UIAdapter/app/src/main/res/values-{1}x{2}/";

    public MakeXml() {
    }

    public void makeString(List<Resolution> resolutionList) {
        for(Resolution resolution : resolutionList) {
            makeString(resolution);
        }
    }

    public void makeString(Resolution resolution) {
        StringBuffer sbWidth = new StringBuffer();
        sbWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbWidth.append("<resources>\n");
        float cellWidth = resolution.width / widthBase;
        for(int i = 1; i < 320; i++) {
            sbWidth.append(widthTemplate.replace("{1}", i + "").replace("{2}",
                    (cellWidth * i) + "") + "\n");
        }
        sbWidth.append(widthTemplate.replace("{1}", "320").replace("{2}",
                resolution.width + ""));
        sbWidth.append("</resources>\n");

        StringBuffer sbHeight = new StringBuffer();
        sbHeight.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbHeight.append("<resources>\n");
        float cellHeight = resolution.height / heighBase;
        for(int i = 1; i < 480; i++) {
            sbHeight.append(heightTemplate.replace("{1}", i + "").replace("{2}",
                    (cellHeight * i) + "") + "\n");
        }
        sbHeight.append(heightTemplate.replace("{1}", 480 + "").replace("{2}",
                (cellHeight * 480) + "") + "\n");
        sbHeight.append("</resources>\n");

        String path = savePath.replace("{1}", resolution.width + "").replace("{2}", resolution.height + "");
        saveString(path, sbWidth, sbHeight);
    }

    public void saveString(String path, StringBuffer width, StringBuffer height) {
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdir();
        }

        FileOutputStream fos = null;

        File layoutXFile = new File(path + "layoutX");
        try {
            fos = new FileOutputStream(layoutXFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File layoutYFile = new File(path + "layoutY");
        try {
            fos = new FileOutputStream(layoutYFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Resolution {
        int width;
        int height;
    }
}
