package com.wbu.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;

import cn.hutool.log.Log;

public class FileProcessUtil {

    public static String basePath="upload/";
    
    public static String[] fileTransfer2Array(File file){
        BufferedReader reader=null;
        String sqlValueArray[]=null;

        int totalFileRows=FileProcessUtil.countFileRows(file);
        sqlValueArray=new String[totalFileRows];

        try {
            reader=new BufferedReader(new FileReader(file));

            String str=null;
            int loopTimes=-1;
            while ((str=reader.readLine())!=null) {
              if(!"".equals(str.trim())){
                sqlValueArray[++loopTimes]= str;
              }else{
                continue;
              }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return sqlValueArray;
    }

    public static int countFileRows(File file){
        int rowCount=-1;
        BufferedReader bfreader=null;
        try {
            bfreader=new BufferedReader(new FileReader(file));

            String str=null;
            while ((str=bfreader.readLine())!=null) {
              rowCount++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            try {
                bfreader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return rowCount;
    }
    
    //#region copyFile
    public static void doBufferReadFile(File file,String bashFolderPath){
        BufferedReader bfreader=null;
        BufferedWriter bfWriter=null;

        try {
            bfreader=new BufferedReader(new FileReader(file));
            bfWriter=new BufferedWriter(new FileWriter(new File(bashFolderPath+file.getName())));

            String str=null;
            while ((str=bfreader.readLine())!=null) {
                bfWriter.flush();
                bfWriter.write(str);
                bfWriter.newLine();
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
            e.printStackTrace();
        }finally{
            try {
                bfWriter.close();
                bfreader.close();
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }
    }
    //#endregion
    //#region channelCopy
    public static void fileChannelCopy(File targetFile){
        RandomAccessFile outRandomAccessFile=null;
        RandomAccessFile inRandomAccessFile=null;
        try {
            inRandomAccessFile=new RandomAccessFile(targetFile, "r");
            outRandomAccessFile=new RandomAccessFile(basePath+targetFile.getName(), "rw");

            FileChannel inChannel =inRandomAccessFile.getChannel();
            FileChannel outChannel =outRandomAccessFile.getChannel();

            long size=inChannel.size();
            System.out.println("file size"+size);

            long startIndex=0;
            long everySize=512 * 1024 *1024;
            long copySize=size;
            long copyTimes=1;
            if(size>everySize){
                copyTimes=size%everySize==0?size/everySize:size/everySize+1;
                copySize=everySize;
            }

            for(int i=0;i<copyTimes;i++){
                MappedByteBuffer inMap=inChannel.map(FileChannel.MapMode.READ_ONLY, startIndex, copySize);
                MappedByteBuffer outMap=outChannel.map(FileChannel.MapMode.READ_WRITE, startIndex, copySize);

                System.out.println("copy file size"+copySize);
                System.out.println("file start position"+startIndex);
                
                for(int j=0;j<copySize;j++){
                    byte b=inMap.get(j);
                    outMap.put(j,b);
                }
                startIndex +=copySize;
                copySize=size-startIndex > everySize?everySize:size;

                inChannel.close();
                outChannel.close();
                
                outRandomAccessFile.close();
                inRandomAccessFile.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    //#endregion
    public static void main(String[] args) {
//        long startTime=System.currentTimeMillis();
//        doBufferReadFile(new File("D:\\ChromeDownloadTempFiles\\1060210968\\FileRecv\\log4property.txt"), basePath);
//        long endTime=System.currentTimeMillis();
//        System.out.println("use time:"+(endTime-startTime));

         long startTime=System.currentTimeMillis();
         fileChannelCopy(new File("D:\\ChromeDownloadTempFiles\\1060210968\\FileRecv\\log4property.txt"));
         long endTime=System.currentTimeMillis();
         System.out.println("channelCopy use time:"+(endTime-startTime));
    }
}
