/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.analyzer;

/**
 *
 * @author giuse_000
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageDescriptor {

    protected String path;
    protected long size;
    protected long mtime;
    protected byte[] md5Digest;
    protected int[] data;
    protected double rmse;

    public static ImageDescriptor readFromDisk(String path) {
        ImageDescriptor id = null;
        File f = new File(path);
        long size = f.length();
        long modifiedTime = f.lastModified();

        int[] data;

        try {
            FileInputStream fi = new FileInputStream(f);
            data = new int[(int) size];
			//int read = fi.read(data);
            //System.out.println("ImageDescriptor.main() read " + read + " bytes from file");
            id = new ImageDescriptor(path, size, modifiedTime);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
//

    public ImageDescriptor() {

    }

    /**
     * int[] data will be converted to argb byte[]
     *
     * @param path
     * @param size
     * @param mtime
     * @param data
     * @param md5
     */
    public ImageDescriptor(String path, long size, long mtime) {
        super();
        this.path = path;
        this.size = size;
        this.mtime = mtime;

//		byte[] rgba = convertToARGB(data);
//		this.data = rgba;
    }

//	public ImageDescriptor(String path, long size, long mtime, byte[]data, byte[] md5) {
//		super();
//		this.path = path;
//		this.size = size;
//		this.mtime = mtime;
//
//		this.data=data;
//		this.md5Digest= md5;
//	}
    protected byte[] convertToARGB(int[] data) {
        byte[] rgba = new byte[data.length * 4];
        //int j = 0;
        for (int i = 0; i < data.length - 4; i++) {
            if (i == 0) {
                System.out.println("ImageDescriptor.convertToARGB()");
                System.out.println("   " + ((data[i] >>> 16) & 0xFF) + " -> " + (byte) ((data[i] >>> 16) & 0xFF));
                System.out.println("   " + ((data[i] >>> 8) & 0xFF) + " -> " + (byte) ((data[i] >>> 8) & 0xFF));

                System.out.println("   " + ((data[i] >>> 0) & 0xFF) + " -> " + (byte) ((data[i] >>> 0) & 0xFF));

            }
            rgba[4 * i] = (byte) ((data[i] >>> 24) & 0xFF);
            rgba[4 * i + 1] = (byte) ((data[i] >>> 16) & 0xFF);
            rgba[4 * 1 + 2] = (byte) ((data[i] >>> 8) & 0xFF);
            rgba[4 * +3] = (byte) ((data[i] >>> 0) & 0xFF);
            //	j++;
        }
        return rgba;
    }

    public double getRmse() {
        return rmse;
    }

    public void setRmse(double rmse) {
        this.rmse = rmse;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }

    public void setMd5Digest(byte[] md5Digest) {
        this.md5Digest = md5Digest;
    }

    public void setData(int[] data) {
        this.data = data; //this.convertToARGB(data);
    }
//
//	public void setData(byte[] data) {
//		this.data = data;
//	}

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public long getMtime() {
        return mtime;
    }

    public int[] getData() {
        return data;
    }

    public byte[] getMD5() {
        return md5Digest;
    }

    @Override
    public String toString() {
        return "[path=" + path + "\n size=" + size + ",\n mtime=" + mtime + "]";
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\giuse_000\\Pictures\\164897_10201054548104840_376225746_n.jpg";
        //  System.out.println(ImageDescriptor.readFromDisk(path));
    }

}
