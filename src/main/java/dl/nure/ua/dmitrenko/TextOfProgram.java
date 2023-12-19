package dl.nure.ua.dmitrenko;

import java.util.Scanner;

public class TextOfProgram {
    public void main() {
        HidingInformationInMusicFiles hidingInformationInMusicFiles = new HidingInformationInMusicFiles();
        ReadSecretMessage readSecretMessage = new ReadSecretMessage();
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Ця програма буде правильно працювати якщо:\n").append("1)Спочатку введено файл в якому є який запис звуку;\n")
                        .append("2)В другій строчці треба вписати файл на який буде переданий файл та схований\n")
                                .append("3)Секретне повідомлення, яке програма дає змогу вбудовувати приховане повідомлення в інший тип даних, " +
                                        "використовує молодший біт кожного байта аудіоданих для вбудовування бітів повідомлення\n")
                                        .append("Чи вважаєте ви що ви все правильно заповнили? Так/Ні");
        System.out.println(stringBuilder3);
        String answer = scanner.next();
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();
        switch (answer.toLowerCase()) {
            case "так":
                hidingInformationInMusicFiles.hideInformationInMusicFiles("540HzMonitor.wav", "out.wav", "PASSWORD: 12345678");
                System.out.println("Чи хочете Ви щоб програма показала, що вона сховала?  +/-");
                String answer2 = scanner.next();
                switch (answer2) {
                    case "+", "так":
                        String extractedMessage = readSecretMessage.extractHiddenMessage("out.wav");
                        stringBuilder1.append("Extracted Message: ").append(extractedMessage);
                        break;
                    case "-", "ні":
                        stringBuilder1.append("Програма завершена!");
                        break;
                    default:
                        stringBuilder1.append("Ви повинні вести +(так) або -(ні)");
                        break;
                }
                break;
            case "ні":
                stringBuilder.append("Тоді заповнюйте правильно!");
                break;
            default:
                stringBuilder.append("Ви повинні вести так або ні");
                break;
        }
        System.out.println(stringBuilder1);
        System.out.println(stringBuilder);
    }
}
