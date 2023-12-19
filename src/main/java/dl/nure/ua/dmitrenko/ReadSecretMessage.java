package dl.nure.ua.dmitrenko;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class ReadSecretMessage {
    public String extractHiddenMessage(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            byte[] audioData = new byte[(int) audioInputStream.getFrameLength() * audioInputStream.getFormat().getFrameSize()];
            audioInputStream.read(audioData);

            StringBuilder extractedMessage = new StringBuilder();
            int messageByte = 0;

            for (int i = 0; i < audioData.length; i++) {
                messageByte = (messageByte << 1) | (audioData[i] & 0x01);

                if (i % audioInputStream.getFormat().getFrameSize() == audioInputStream.getFormat().getFrameSize() - 1) {
                    extractedMessage.append((char) messageByte);
                    messageByte = 0;
                }
            }

            return extractedMessage.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
