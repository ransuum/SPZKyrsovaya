package dl.nure.ua.dmitrenko;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;

public class HidingInformationInMusicFiles {
    public void hideInformationInMusicFiles(String inputPath, String outputPath, String message) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(inputPath));
            AudioFormat audioFormat = audioInputStream.getFormat();
            byte[] audioData = new byte[(int) audioInputStream.getFrameLength() * audioFormat.getFrameSize()];
            audioInputStream.read(audioData);

            byte[] messages = message.getBytes();
            int messIndex = 0;

            for (int i = 0; i < audioData.length; i++) {
                if (messIndex < messages.length * 8) {
                    audioData[i] = (byte) ((audioData[i] & 0xFE) | ((messages[messIndex >> 3] >> (7 - (messIndex & 0x07))) & 0x01));
                    messIndex++;
                }
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
            AudioInputStream modifiedAudioInputStream = new AudioInputStream(byteArrayInputStream, audioFormat,
                    audioData.length / audioFormat.getFrameSize());
            AudioSystem.write(modifiedAudioInputStream, AudioFileFormat.Type.WAVE, new File(outputPath));
            System.out.println("Інформація успішно прихована у файлі " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
