package com.kit.util.file;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @Author: panda
 * @Date: 2020/8/27 0027 21:08
 */
@Slf4j
public class JpgUtil {
    
    private static byte end1=(byte) 0xFF;
    private static byte end2=(byte) 0xD9;
    
    final static String GPS_LATITUDE="GPS Latitude";
    final static String GPS_LONGITUDE="GPS Longitude";
    final static String GPS_ALTITUDE="GPS Altitude";
    
    @SneakyThrows
    public static void metadataExample(final File file) {
        Metadata metadata = JpegMetadataReader.readMetadata(file);
        
        for(Directory directory : metadata.getDirectories()){
            for(Tag tag : directory.getTags()){
                System.out.println(tag);
                System.out.print("name : " + tag.getTagName() + "  -->");
                System.out.println("desc : " + tag.getDescription());
            }
        }
        
    }   
    
    public BufferedImage drawRect(BufferedImage bufImage, int x, int y, int width, int height) {
    	Graphics2D g2d = bufImage.createGraphics();
    	g2d.setColor(Color.RED);
    	g2d.drawRect(x, y, width, height);
    	g2d.dispose();
    	return bufImage;
    }
    
    public BufferedImage addImage(BufferedImage bufImage, int x, int y, File img) throws IOException {
    	Graphics2D g2d = bufImage.createGraphics();
    	g2d.setColor(Color.RED);
    	g2d.drawImage(ImageIO.read(img), x, y, null);
    	g2d.dispose();
    	return bufImage;
    }
    
    @SneakyThrows
    public static JpgMetadata getJpgMetadata(final File file) {
        Metadata metadata = JpegMetadataReader.readMetadata(file);
        JpgMetadata jpgMetadata=new JpgMetadata();
        for(Directory directory : metadata.getDirectories()){
            for(Tag tag : directory.getTags()){
                String tagName = tag.getTagName();
                String description = tag.getDescription();
                System.out.println(tagName+":"+description);
                switch (tagName){
                    case GPS_LATITUDE:
                        jpgMetadata.setGpsLatitude(StringUtils.isEmpty(description)?"0":description);
                        log.info("图片："+file.getName()+",纬度为：'"+description+"'");
                        break;
                    case GPS_LONGITUDE:
                        jpgMetadata.setGpsLongitude(StringUtils.isEmpty(description)?"0":description);
                        log.info("图片："+file.getName()+",经度为：'"+description+"'");
                        break;
                    case GPS_ALTITUDE:
                        jpgMetadata.setGpsAltitude(StringUtils.isEmpty(description)?"0":description);
                        log.info("图片："+file.getName()+",高度为：'"+description+"'");
                        break;
                    default:;
                }
            }
        }
        return jpgMetadata;
    }
    
    @Data
    public static class JpgMetadata{

        String gpsLatitude;
        String gpsLongitude;
        String gpsAltitude;
    }


    @SneakyThrows
    public static boolean isIntact(File f)  {
       
        byte[] bytes = Files.readAllBytes(f.toPath());
        int len = bytes.length;
        
        return bytes[len-2]==end1&&bytes[len-1]==end2;
    }
    
    
}