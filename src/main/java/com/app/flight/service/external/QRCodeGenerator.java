package com.app.flight.service.external;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;

public class QRCodeGenerator {
    public static final String QR_CODE_PATH = "data/image/QR_Code/";
    private static final String TAG_IMAGE_PATH = "data/image/QR_Code/TagLogo.png";
    private static final String TAG_TXT = "data/printer/Tag.txt";
    private static final String BOARDING_IMAGE_PATH = "data/image/QR_Code/BoardingPassLogo.png";
    private static final String BOARDING_TXT = "data/printer/BoardingPass.txt";
    private static final String PAYPAL_IMAGE_PATH = "data/image/QR_Code/PaypalLogo.png";
    private static final String PAYPAL_SITE = "https://www.paypal.com/ph/signin";
    private static final String ALIPAY_IMAGE_PATH = "data/image/QR_Code/AlipayLogo.png";
    private static final String ALIPAY_SITE = "https://auth.alipay.com/login/index.htm";
    private static String url = "";
    private static String path = "";

    /**
     * Generate QR code for pay
     *
     * @param type 1 for PayPal, 2 for alipay
     */
    public static void generatePayCode(String type) {
        if (type.equals("paypal")) {
            url = PAYPAL_SITE;
            path = PAYPAL_IMAGE_PATH;
        } else if (type.equals("alipay")) {
            url = ALIPAY_SITE;
            path = ALIPAY_IMAGE_PATH;
        }
        QrConfig config = new QrConfig(600, 600);
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        config.setMargin(1);
        config.setImg(new File(path));
        config.setRatio(8);
        QrCodeUtil.generate(
                url,
                config,
                FileUtil.newFile(QR_CODE_PATH + "QR.jpg")
        );
    }

    /**
     * Generate QR code for boarding pass
     */
    public static void generateBoardingPassCode() {
        url = FileUtil.readUtf8String(FileUtil.newFile(BOARDING_TXT));
        path = BOARDING_IMAGE_PATH;
        QrConfig config = new QrConfig(600, 600);
        config.setImg(new File(path));
        config.setErrorCorrection(ErrorCorrectionLevel.L);
        config.setMargin(0);
        config.setRatio(6);
        QrCodeUtil.generate(
                String.valueOf(Printer.boardingPassData),
                config,
                FileUtil.newFile(QR_CODE_PATH + "BoardingPassQR.jpg")
        );
    }

    /**
     * Generate QR code for tag
     */
    public static void generateTagCode() {
        url = FileUtil.readUtf8String(FileUtil.newFile(TAG_TXT));
        path = TAG_IMAGE_PATH;
        QrConfig config = new QrConfig(600, 600);
        config.setImg(new File(path));
        config.setErrorCorrection(ErrorCorrectionLevel.L);
        config.setMargin(0);
        config.setRatio(5);
        QrCodeUtil.generate(
                String.valueOf(Printer.tagData),
                config,
                FileUtil.newFile(QR_CODE_PATH + "TagQR.jpg")
        );
    }
}
