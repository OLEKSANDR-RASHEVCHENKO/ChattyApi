package Integration.tests.sendFeedback;

import Integration.authApi.AuthApi;
import Integration.feedback.Feedback;
import Integration.schemas.FeedbackReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.testng.annotations.Test;

public class AdminCanSendFeedback {
    AuthApi authApi;
    Feedback feedback;
    @Epic(value = "Send Feedback")
    @Feature(value= "Sending Feedback")
    @Story(value = "User can send Feedback with role admin")
    @Description(value = "User can send Feedback")
    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "User can send Feedback")
    public void userCanUpdatePost() throws JsonProcessingException {
        String email = "alewwx@gmail.com";
        String password = "Gazmanov1234";
        String name = "Alex";
        String content = "hallo";
        authApi = new AuthApi();
        String token = authApi.login(email, password, 200);

        FeedbackReq feedbackReq = new FeedbackReq();
        feedbackReq.setName(name);
        feedbackReq.setEmail(email);
        feedbackReq.setContent(content);

        feedback = new Feedback(token);
        feedback.sendFeedback(feedbackReq,201);
    }
}
