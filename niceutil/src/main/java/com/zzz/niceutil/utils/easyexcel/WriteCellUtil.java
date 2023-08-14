package com.zzz.niceutil.utils.easyexcel;

import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.ImageData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description WriteCellUtil
 * @Author 张卫刚
 * @Date Created on 2023/8/10
 */
public class WriteCellUtil{
    /**
     * 单元格内填充图片及文字
     * 将图片信息放入到WriteCellData中（单元格）
     * @param image 图片
     * @param content 添加的文字
//     * @param indent 首行缩进
     * @param top 图片上边距
     * @param bottom 图片下边距
     * @param left 图片左边距
     * @param right 图片右边距
     * @return
     */
    public static WriteCellData fillImage(BufferedImage image, String content, Integer top, Integer bottom, Integer left, Integer right ){
        WriteCellData<Void> writeCellData = new WriteCellData<>();
        //设置文本内容为String
        writeCellData.setType(CellDataTypeEnum.STRING);
        //填充文本内容
        writeCellData.setStringValue(content);
        //获取单元格样式类
        WriteCellStyle writeCellStyle = new WriteCellStyle();
        //设置当前单元格首航缩进距离  TODO short类型
//        writeCellStyle.setIndent(indent);
        //将样式放入到单元格内
        writeCellData.setWriteCellStyle(writeCellStyle);


        //获取图片集合，一个单元格可以放置多张图片，我这里只放了一张，放了多张需要自行调整样式
        List<ImageData> imageDataList = new ArrayList<>();
        //放置图片的类，只能放置byte数组格式
        ImageData imageData = new ImageData();
        try {
            //将数组放入
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            imageData.setImage(imageInByte);
            //设置图片类型为jpeg，其他类型可自行设置
            imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //设置图片相对于单元格的上下左右距离
        imageData.setTop(top);
        imageData.setRight(right);
        imageData.setBottom(bottom);
        imageData.setLeft(left);

        //相对于当前单元格往又移动一格，这里我全部设置为0了，作用就是占用当前单元格上下左右几个单元格，想了解自行测试
        imageData.setRelativeFirstRowIndex(0);
        imageData.setRelativeFirstColumnIndex(0);
        imageData.setRelativeLastRowIndex(0);
        imageData.setRelativeLastColumnIndex(0);

        //将图片信息放入集合
        imageDataList.add(imageData);

        //将图片集合放入单元格内
        writeCellData.setImageDataList(imageDataList);
        //返回当前单元格
        return writeCellData;
    }}
