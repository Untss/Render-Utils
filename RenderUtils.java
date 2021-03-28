package me.unts.learn.utils;


import org.lwjgl.opengl.GL11;

import java.awt.Color;

import static java.lang.Math.PI;
import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {

    /*
        Copyright 2021 Unts
     */

    public static void drawBorderedRect(float left, float top, float right, float bottom, Color innerColor, Color borderColor){
        RenderUtils.drawRect(left, top, right, bottom, innerColor);

        RenderUtils.renderHLine(left, right, top - 1, 1, borderColor);
        RenderUtils.renderHLine(left, right, bottom + 1, 1, borderColor);

        RenderUtils.renderVLine(top - 2, bottom + 2, right + 1, 1, borderColor);
        RenderUtils.renderVLine(top - 2, bottom + 2, left - 1, 1, borderColor);
    }

    public static void drawRect(float left, float top, float right, float bottom, Color c){
        glDisable(GL_TEXTURE_2D);
        glBegin(7);
        RenderUtils.glColor(c);
        GL11.glVertex2f((float)left, (float)top);
        GL11.glVertex2f((float)left, (float)bottom);
        GL11.glVertex2f((float)right, (float)bottom);
        GL11.glVertex2f((float)right, (float)top);
        glEnd();
        glEnable(GL_TEXTURE_2D);
    }

    public static void drawGradientHRect(float left, float top, float right, float bottom, Color topColor, Color bottomColor) {
        glDisable(GL_TEXTURE_2D);
        GL11.glShadeModel((int)7425);
        GL11.glBegin((int)7);
        RenderUtils.glColor(topColor);
        GL11.glVertex2f((float)left, (float)top);
        GL11.glVertex2f((float)left, (float)bottom);
        RenderUtils.glColor(bottomColor);
        GL11.glVertex2f((float)right, (float)bottom);
        GL11.glVertex2f((float)right, (float)top);
        GL11.glEnd();
        GL11.glShadeModel((int)7424);
        glEnable(GL_TEXTURE_2D);
    }

    public static void renderVLine(float startY, float endY, float x, float lineWidth, Color c){
        glLineWidth(lineWidth);
        glBegin(3);
        RenderUtils.glColor(c);
        glVertex2f(x, startY);
        glVertex2f(x, endY);
        glEnd();
    }

    public static void renderHLine(float startX, float endX, float y, float lineWidth, Color c){
        glLineWidth(lineWidth);
        glBegin(3);
        RenderUtils.glColor(c);
        glVertex2f(startX, y);
        glVertex2f(endX, y);
        glEnd();
    }

    public static void drawFullCircle(float x, float y, float radius, float triangleAmount, Color c){
        int i;

        float twicePi = 2.0f * (float) PI;

        glBegin(GL_TRIANGLE_FAN);
        glVertex2f(x, y);
        for(i = 0; i <= triangleAmount;i++) {
            glColor3f(c.getRed(), c.getBlue(), c.getGreen());
            glVertex2f(
                    x + (float) (radius * Math.cos(i *  twicePi / triangleAmount)),
                    y + (float) (radius * Math.sin(i * twicePi / triangleAmount))
            );
        }
        glEnd();
    }

    public static void drawOutlinedCircle(float cx, float cy, float r, int num_segments, java.awt.Color c){
        glBegin(GL_LINE_LOOP);
        for (int ii = 0; ii < num_segments; ii++)   {
            float theta = 2.0f * 3.1415926f * (ii) / (num_segments);
            float x = r * (float) Math.cos(theta);
            float y = r * (float) Math.sin(theta);
            glColor3f(c.getRed(), c.getGreen(), c.getBlue());
            glVertex2f(x + cx, y + cy);
        }
        glEnd();
    }

    public static void glColor(Color color) {
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

}
