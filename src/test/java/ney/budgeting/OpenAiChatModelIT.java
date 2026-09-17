package ney.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENROUTER_API_KEY", matches = ".+")
public class OpenAiChatModelIT {

    @Autowired
    private OpenAiChatModel chatModel;

    @Test
    void should_receiveResponse_when_chatModelIsCalled() {
        var options = OpenAiChatOptions.builder()
                .temperature(0.8)
                .responseFormat(OpenAiChatModel.ResponseFormat.builder()
                        .type(OpenAiChatModel.ResponseFormat.Type.TEXT)
                        .build())
                .build();

        var response = chatModel.call("Generate a budget record—including description, expense category, " +
                "amount in Reais, and location—with six examples, presented in an organized format.");

        assertThat(response).isNotEmpty();

        IO.println("Response: " + response);
    }
}
