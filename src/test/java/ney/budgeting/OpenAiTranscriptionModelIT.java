package ney.budgeting;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "GROQ_API_KEY", matches = ".+")
public class OpenAiTranscriptionModelIT {
    @Autowired
    OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    @ParameterizedTest
    @CsvSource(
            delimiter = '|',
            value = {
                    "Audio01.mp3 | 42,00",
                    "Audio02.mp3 | 150",
                    "Audio03.mp3 | 89,90",
                    "Audio04.mp3 | 230,00",
                    "Audio05.mp3 | 8,50",
                    "Audio06.mp3 | 70,00"
            }
    )
    void should_containExpectedAmount_when_audioFilesAreProcessed(
            String fileName,
            String expectedAmount
    ) {
        var recording = new ClassPathResource("audio/" + fileName);

        String transcription = openAiAudioTranscriptionModel.transcribe(recording);

        System.out.println(transcription);

        assertThat(transcription)
                .containsIgnoringCase(expectedAmount);
    }
}