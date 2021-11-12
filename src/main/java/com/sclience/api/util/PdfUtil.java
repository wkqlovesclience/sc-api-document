package com.sclience.api.util;

import com.sclience.sc.boot.core.exception.BusinessException;
import com.sclience.sc.boot.core.support.LogSupport;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * <h1>[]-[]</h1>
 * <p>
 * pdf 转化工具类
 * </p>
 *
 * @author wkqsclience118706@163.com
 * @date 2021/11/12 2:31 下午
 * @since 1.0
 */
public class PdfUtil {

    /**
     * <p>
     * pdf文档转word
     * </p>
     *
     * @param path pdf文件路径，eg: /Users/sclience/Documents/word-transfer/贺小思/贺小思-检测报告.pdf
     * @return 转换后的word文件路径，eg: /Users/sclience/Documents/word-transfer/贺小思/贺小思-检测报告.doc
     * @author wkqsclience118706@163.com
     * @date 2021/11/12 2:32 下午
     * @since 1.0
     */
    public static String pdfTransferToWord(String path) {
        try {
            PDDocument doc = PDDocument.load(new File(path));
            int pageNumber = doc.getNumberOfPages();
            path = path.substring(0, path.lastIndexOf("."));
            String fileName = path + ".doc";
            File file = new File(fileName);
            if (!file.exists()) {
                boolean result = file.createNewFile();
                LogSupport.logDebug("创建空文件结果：{}", result);
            }
            FileOutputStream fos = new FileOutputStream(fileName);
            Writer writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            PDFTextStripper stripper = new PDFTextStripper();
            // 排序
            stripper.setSortByPosition(true);
            // 设置转换的开始页
            stripper.setStartPage(1);
            // 设置转换的结束页
            stripper.setEndPage(pageNumber);
            stripper.writeText(doc, writer);
            writer.close();
            doc.close();
            LogSupport.logDebug("pdf转换word成功！");
            return fileName;
        } catch (IOException e) {
            LogSupport.logError("pdf转word失败：{}", e, path);
            throw new BusinessException("pdf转word失败", e);
        }
    }
}
