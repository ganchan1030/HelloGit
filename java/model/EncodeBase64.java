package model;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

public class EncodeBase64 {
  public String loadBinaryImage(String filename) throws IOException {
    // ファイルインスタンスを取得し、ImageIOへ読み込ませる
    File f = new File(filename);
    BufferedImage image = ImageIO.read(f);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    BufferedOutputStream bos = new BufferedOutputStream(baos);
    image.flush();
    // 読み終わった画像をバイト出力へ
    ImageIO.write(image, "png", bos);
    bos.flush();
    bos.close();
    byte[] bImage = baos.toByteArray();
    // バイト配列→BASE64へ変換する
    Base64 base64 = new Base64();
    byte[] encoded = base64.encode(bImage);
    String base64Image = new String(encoded);
    return base64Image;
    }
}
