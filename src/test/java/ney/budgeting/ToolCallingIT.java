package ney.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "GROQ_API_KEY", matches = ".+")
public class ToolCallingIT {
    @Autowired
    OpenAiChatModel chatModel;

    static class MathTools {
        @Tool(description = "Sum two integer numbers, a and b ")
        public int sum(int a, int b) {
            return a + b;
        }

        @Tool(description = "Retrieve two integer numbers, a and b")
        public int diff(int a, int b) {
            return a - b;
        }
    }

    @Test
    void should_executeSum_when_prompted() {
        var chatClient = ChatClient.builder(chatModel)
                .defaultSystem("You are a mathematical")
                .defaultTools(new MathTools())
                .build();

        var response = chatClient.prompt("Sum 10 + 20. Than retire 30 of the previous result. " +
                        "Show only the final result without explanation")
                .call()
                .content();

        assertThat(response).contains("0");
        IO.println("Response: " + response);
    }
}