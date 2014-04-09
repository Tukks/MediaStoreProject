/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class hash {
    private static int WIDTH = 10;
    private static int HEIGHT = 10;

    public static BufferedImage downScaleImageToGray(BufferedImage bi, int nw, int nh) throws IOException {
        BufferedImage scaledBI = null;
        scaledBI = new BufferedImage(nw, nh, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(bi, 0, 0, nw, nh, null);
        g.dispose();
        
        // }
        return scaledBI;
    }

    private static int meanValue(BufferedImage bf) {
        int[] data1 = new int[bf.getWidth() * bf.getHeight()];
        bf.getRGB(0, 0, bf.getWidth(), bf.getHeight(), data1, 0, bf.getWidth());
        //now average values
        long total = 0;
        for (int i = 0; i < data1.length; i++) {
            total += data1[i];
        }
        return (int) (total / data1.length);
    }

    public static String generateSignature(BufferedImage source) throws IOException {
        BufferedImage bf = downScaleImageToGray(source, WIDTH, HEIGHT);
        String signature = "";
        int[] data1 = new int[bf.getWidth() * bf.getHeight()];
        bf.getRGB(0, 0, bf.getWidth(), bf.getHeight(), data1, 0, bf.getWidth());
        int mean = meanValue(bf);
        for (int i = 0; i < data1.length; i++) {
            if (data1[i] > mean) {
                signature += "1";
            } else {
                signature += "0";
            }
        }
        return signature;
    }

    public static BufferedImage signatureToImage(String signature) {
        final BufferedImage bf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        int[] data = new int[WIDTH * HEIGHT];
        for (int i = 0; i < data.length; i++) {
            if (signature.charAt(i) == '0') {
                data[i] = Color.white.getRGB();
            } else {
                data[i] = Color.black.getRGB();
            }
        }
        bf.setRGB(0, 0, WIDTH, HEIGHT, data, 0, WIDTH);
        return bf;
    }

    public static String generateSignature(String path) throws IOException {
        BufferedImage bf = ImageIO.read(new File(path));
        return generateSignature(bf);
    }

    public static String generateSignature(InputStream in) throws IOException {
        BufferedImage bf = ImageIO.read(in);
        return generateSignature(bf);
    }

    public static int hammingDistance(String sg1, String sg2) {

        if (sg1.length() != sg2.length()) {
            return -1;
        }

        int distance = 0;
        for (int i = 0; i < sg1.length(); i++) {
            if (sg1.charAt(i) != sg2.charAt(i)) {
                distance++;
            }
        }
        return distance;

    }
 }
